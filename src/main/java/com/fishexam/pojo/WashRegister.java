package com.fishexam.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author cativen
 * @since 2023-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WashRegister extends Model<WashRegister> {

    private static final long serialVersionUID = 1L;

    /**
     * 人员id
     */
      @TableId(value = "register_id", type = IdType.AUTO)
    private Integer registerId;

    /**
     * 登记编号
     */
    private String washRegisterNumber;

    /**
     * 登记人名
     */
    private String name;

    /**
     * 宠物昵称
     */
    private String petName;

    /**
     * 登记电话号码
     */
    private String phone;

    /**
     * 提前多少天提醒
     */
    private int advanceDay;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 0未删除 1删除
     */
    private Integer delFlag;

    /**
     * 下次预约时间
     * @return
     */
    private Date washDate;

    /**
     *本地服务时间
     */
    private Date serviceDate;

    /**
     * 1、洗护 2、驱虫 3、购粮 4、寄养
     */
    private int type;

    /**
     * 本地服务时间和下次洗护时间的跨度
     */
    private int timeSpan;

    /**
     * 0代表未通知，1代表已通知
     */
    private int msgNotify;
}
