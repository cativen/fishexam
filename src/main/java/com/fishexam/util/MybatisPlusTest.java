package com.fishexam.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import jdk.nashorn.internal.runtime.logging.Logger;

@Logger
public class MybatisPlusTest {
    public static void main(String[] args) {
        // 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true)        //是否i支持ar模式 ActiveRecord(活动记录)，是一种领域模型模式，特点是一个模型类对应关系型数据库中的一个表，而模型类的一个实例对应表中的一行记录。
                .setAuthor("cativen")       //作者
                .setOutputDir("D:\\zhoutao\\chongwuchongwu\\fish_exam_system\\src\\main\\java")     //输出到的目录下
                .setFileOverride(true)          //是否开启文件覆盖
                .setIdType(IdType.AUTO)         //主键策略
                .setServiceName("%sService")     //设置生成的service接口的收字母是否为I       //IEmployService
                .setBaseResultMap(true)
                .setOpen(false)//生成后打开文件夹
                .setBaseColumnList(true);
        //数据源配置
        DataSourceConfig datas=new DataSourceConfig();
        datas.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")         //数据库驱动连接
                .setUrl("jdbc:mysql://localhost:3306/zhuyu_pets?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2b8&allowMultiQueries=true")        //数据库位置
                .setUsername("root")                //用户名
                .setPassword("111111");      //密码


        //策略配置
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setCapitalMode(true)         //是否全局大写命名
                .setNaming(NamingStrategy.underline_to_camel)          //数据库映射到实体类的策略
                //.setTablePrefix("t_")           //表前缀   （下划线前）
                .setRestControllerStyle(true)
                //.setDbColumnUnderline(true)         //指定表名 字段名是否使用下划线
                .setEntityLombokModel(true)
                //.setSuperEntityClass("com.ucloud.db.entity.BaseEntityMB")
                .setInclude("wash_register");           //指定表



        //包名策略
        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setParent("com.fishexam")           //指定父类包
                .setMapper("mapper")                //mapper所存在的地方
                .setService("service")              //service的目录
                .setController("controller")        //controller目录
                .setEntity("pojo")                //实体类目录
                .setXml("mapper");                  //xml文件目录


        //整合配置
        AutoGenerator autoGenerator=new AutoGenerator();
        autoGenerator.setGlobalConfig(config)
                .setDataSource(datas)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);


        //执行
        autoGenerator.execute();
    }
}
