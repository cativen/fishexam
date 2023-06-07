package com.fishexam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fishexam.controller.PetsController;
import com.fishexam.dto.EmailDTO;
import com.fishexam.enums.PetServiceTypeEnum;
import com.fishexam.pojo.PeopleInfo;
import com.fishexam.mapper.PeopleInfoMapper;
import com.fishexam.pojo.WashRegister;
import com.fishexam.service.PeopleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fishexam.service.WashRegisterService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cativen
 * @since 2023-03-01
 */
@Service
public class PeopleInfoServiceImpl extends ServiceImpl<PeopleInfoMapper, PeopleInfo> implements PeopleInfoService {

    @Autowired
    private PeopleInfoMapper peopleInfoMapper;

    @Autowired
    private WashRegisterService washRegisterService;

    private Logger logger= LoggerFactory.getLogger(PetsController.class);

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    public static String myEmailAccount = "@163.com";
    public static String myEmailPassword = "";
    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.163.com";

    @Override
    public int sendEmail(List<EmailDTO> emailAndContent) {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // 开启 SSL 连接, 以及更详细的发送步骤请看上一篇: 基于 JavaMail 的 Java 邮件发送：简单邮件发送

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        // 3. 创建一封邮件
        MimeMessage message = null;
        try {
            long startTime=System.currentTimeMillis();

            for (EmailDTO emailDTO : emailAndContent) {
                String userEmail = emailDTO.getUserEmail();
                List<WashRegister> washRegisterList = emailDTO.getWashRegisterList();
                for (WashRegister washRegister : washRegisterList) {
                    message = createMimeMessage(session, myEmailAccount, userEmail,washRegister);
                    // 4. 根据 Session 获取邮件传输对象
                    Transport transport = session.getTransport();

                    // 5. 使用 邮箱账号 和 密码 连接邮件服务器
                    //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
                    transport.connect(myEmailAccount, myEmailPassword);

                    // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
                    transport.sendMessage(message, message.getAllRecipients());
                    long endTime=System.currentTimeMillis();
                    logger.info("发送邮件耗时为："+(endTime-startTime));
                    logger.info(washRegister.getName()+"邮件通知到位了");
                    // 7. 关闭连接
                    transport.close();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }


    @Override
    public void sendMessage(List<EmailDTO> emailAndContent) {
        for (EmailDTO emailDTO : emailAndContent) {
            //sendMessage(emailDTO);
        }

        if (!CollectionUtils.isEmpty(emailAndContent)){
            EmailDTO emailDTO = emailAndContent.get(0);
            List<WashRegister> washRegisterList = emailDTO.getWashRegisterList();
            for (WashRegister washRegister : washRegisterList) {
                    int type = washRegister.getType();
                    if (type==1||type==2){
                        tencentMessage(washRegister);
                    }
                    washRegister.setMsgNotify(1);
                    washRegisterService.updateById(washRegister);
            }
        }
    }

    private void sendMessage(EmailDTO emailDTO) {
      /*  String phone = emailDTO.getPhone();
        List<WashRegister> washRegisterList = emailDTO.getWashRegisterList();
        for (WashRegister washRegister : washRegisterList) {
            int type = washRegister.getType();

        }*/
    }

    public void tencentMessage(WashRegister washRegister){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            /* 必要步骤：
             * 实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
             * 这里采用的是从环境变量读取的方式，需要在环境变量中先设置这两个值。
             * 你也可以直接在代码中写死密钥对，但是小心不要将代码复制、上传或者分享给他人，
             * 以免泄露密钥对危及你的财产安全。
             * SecretId、SecretKey 查询: https://console.cloud.tencent.com/cam/capi */
            Credential cred = new Credential("AKIDPbXDehbJlwqUvZ4LNBF1WkkT6YjeKlvM", "GFxOXP3b0EDvypRnumQpy3xay61hJuRn");

            // 实例化一个http选项，可选，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            // 设置代理（无需要直接忽略）
            // httpProfile.setProxyHost("真实代理ip");
            // httpProfile.setProxyPort(真实代理端口);
            /* SDK默认使用POST方法。
             * 如果你一定要使用GET方法，可以在这里设置。GET方法无法处理一些较大的请求 */
            httpProfile.setReqMethod("POST");
            /* SDK有默认的超时时间，非必要请不要进行调整
             * 如有需要请在代码中查阅以获取最新的默认值 */
            httpProfile.setConnTimeout(60);
            /* 指定接入地域域名，默认就近地域接入域名为 sms.tencentcloudapi.com ，也支持指定地域域名访问，例如广州地域的域名为 sms.ap-guangzhou.tencentcloudapi.com */
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            /* 非必要步骤:
             * 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();
            /* SDK默认用TC3-HMAC-SHA256进行签名
             * 非必要请不要修改这个字段 */
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            /* 实例化要请求产品(以sms为例)的client对象
             * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，支持的地域列表参考 https://cloud.tencent.com/document/api/382/52071#.E5.9C.B0.E5.9F.9F.E5.88.97.E8.A1.A8 */
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);
            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 你可以直接查询SDK源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用IDE进行开发，可以方便的跳转查阅各个接口和数据结构的文档说明 */
            SendSmsRequest req = new SendSmsRequest();

            /* 填充请求参数,这里request对象的成员变量即对应接口的入参
             * 你可以通过官网接口文档或跳转到request对象的定义处查看请求参数的定义
             * 基本类型的设置:
             * 帮助链接：
             * 短信控制台: https://console.cloud.tencent.com/smsv2
             * 腾讯云短信小助手: https://cloud.tencent.com/document/product/382/3773#.E6.8A.80.E6.9C.AF.E4.BA.A4.E6.B5.81 */

            /* 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId，示例如1400006666 */
            // 应用 ID 可前往 [短信控制台](https://console.cloud.tencent.com/smsv2/app-manage) 查看
            int type = washRegister.getType();
            String sdkAppId ="1400810373";
            req.setSmsSdkAppId(sdkAppId);

            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名 */
            // 签名信息可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-sign) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-sign) 的签名管理查看
            String signName = "长沙市天心区猫薄荷生活馆";
            req.setSignName(signName);

            /* 模板 ID: 必须填写已审核通过的模板 ID */
            // 模板 ID 可前往 [国内短信](https://console.cloud.tencent.com/smsv2/csms-template) 或 [国际/港澳台短信](https://console.cloud.tencent.com/smsv2/isms-template) 的正文模板管理查看
            String templateId ="";
            if (type==2){
                templateId = "1758259";
           }else if(type==1){
                templateId = "1758255";
            }
            req.setTemplateId(templateId);

            /* 模板参数: 模板参数的个数需要与 TemplateId 对应模板的变量个数保持一致，若无模板参数，则设置为空 */
            String[] templateParamSet = {washRegister.getPetName(),washRegister.getPetName(),simpleDateFormat.format(washRegister.getServiceDate()),simpleDateFormat.format(washRegister.getWashDate())};
            req.setTemplateParamSet(templateParamSet);

            /* 下发手机号码，采用 E.164 标准，+[国家或地区码][手机号]
             * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号 */
            String[] phoneNumberSet = {"+86"+washRegister.getPhone()};
            req.setPhoneNumberSet(phoneNumberSet);

            /* 用户的 session 内容（无需要可忽略）: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
            String sessionContext = "";
            req.setSessionContext(sessionContext);

            /* 短信码号扩展号（无需要可忽略）: 默认未开通，如需开通请联系 [腾讯云短信小助手] */
            String extendCode = "";
            req.setExtendCode(extendCode);

            /* 国际/港澳台短信 SenderId（无需要可忽略）: 国内短信填空，默认未开通，如需开通请联系 [腾讯云短信小助手] */
            String senderid = "";
            req.setSenderId(senderid);

            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);

            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));
            logger.info(washRegister.getName()+"电话号码：{}通知到位了",washRegister.getPhone());

            // 也可以取出单个值，你可以通过官网接口文档或跳转到response对象的定义处查看返回字段的定义
            // System.out.println(res.getRequestId());

            /* 当出现以下错误码时，快速解决方案参考
             * [FailedOperation.SignatureIncorrectOrUnapproved](https://cloud.tencent.com/document/product/382/9558#.E7.9F.AD.E4.BF.A1.E5.8F.91.E9.80.81.E6.8F.90.E7.A4.BA.EF.BC.9Afailedoperation.signatureincorrectorunapproved-.E5.A6.82.E4.BD.95.E5.A4.84.E7.90.86.EF.BC.9F)
             * [FailedOperation.TemplateIncorrectOrUnapproved](https://cloud.tencent.com/document/product/382/9558#.E7.9F.AD.E4.BF.A1.E5.8F.91.E9.80.81.E6.8F.90.E7.A4.BA.EF.BC.9Afailedoperation.templateincorrectorunapproved-.E5.A6.82.E4.BD.95.E5.A4.84.E7.90.86.EF.BC.9F)
             * [UnauthorizedOperation.SmsSdkAppIdVerifyFail](https://cloud.tencent.com/document/product/382/9558#.E7.9F.AD.E4.BF.A1.E5.8F.91.E9.80.81.E6.8F.90.E7.A4.BA.EF.BC.9Aunauthorizedoperation.smssdkappidverifyfail-.E5.A6.82.E4.BD.95.E5.A4.84.E7.90.86.EF.BC.9F)
             * [UnsupportedOperation.ContainDomesticAndInternationalPhoneNumber](https://cloud.tencent.com/document/product/382/9558#.E7.9F.AD.E4.BF.A1.E5.8F.91.E9.80.81.E6.8F.90.E7.A4.BA.EF.BC.9Aunsupportedoperation.containdomesticandinternationalphonenumber-.E5.A6.82.E4.BD.95.E5.A4.84.E7.90.86.EF.BC.9F)
             * 更多错误，可咨询[腾讯云助手](https://tccc.qcloud.com/web/im/index.html#/chat?webAppId=8fa15978f85cb41f7e2ea36920cb3ae1&title=Sms)
             */

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EmailDTO> findEmailAndContent() {
        List<WashRegister> washRegisters = washRegisterService.querySendMail();
        List<EmailDTO> list=new ArrayList<>();
        List<PeopleInfo> peopleInfos = peopleInfoMapper.selectList(new LambdaQueryWrapper<PeopleInfo>().eq(PeopleInfo::getDelFlag, 0));
        for (PeopleInfo peopleInfo : peopleInfos) {
            if (peopleInfo.getEmail()!=null){
                EmailDTO dto=new EmailDTO();
                dto.setPhone(peopleInfo.getPeoplePhone());
                dto.setUserEmail(peopleInfo.getEmail());
                dto.setWashRegisterList(washRegisters);
                list.add(dto);
            }
        }
        return list;
    }


    public MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,WashRegister washRegister) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 1. 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "catnip猫文化生活馆", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail, "", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(washRegister.getName()+"的"+washRegister.getPetName()+PetServiceTypeEnum.getText(washRegister.getType())+"提醒", "UTF-8");

        // 5. 创建文本“节点”
        MimeBodyPart text = new MimeBodyPart();
        //    这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        text.setContent("             提醒"+washRegister.getPetName()+"主人"+washRegister.getName()+"，"+simpleDateFormat.format(washRegister.getWashDate())+"带猫咪来"+PetServiceTypeEnum.getText(washRegister.getType()), "text/html;charset=UTF-8");

        // 6. （文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.setSubType("related");	// 关联关系

        // 7. 将 文本+图片 的混合“节点”封装成一个普通“节点”
        //    最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        //    上面的 mm_text_image 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);

        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);

        // 8. 创建附件“节点”
        mm.setSubType("mixed");			// 混合关系


        // 9. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
        message.setContent(mm);

        // 10. 设置发件时间
        message.setSentDate(new Date());

        // 11. 保存上面的所有设置
        message.saveChanges();

        return message;

    }
}
