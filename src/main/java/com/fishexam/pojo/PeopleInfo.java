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
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PeopleInfo extends Model<PeopleInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 人员id
     */
      @TableId(value = "people_id", type = IdType.AUTO)
    private Integer peopleId;

    /**
     * 人员编号
     */
    private String peopleNumber;

    /**
     * 人员昵称
     */
    private String peopleName;

    /**
     * 人员电话号码
     */
    private String peoplePhone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 人员年龄
     */
    private Integer peopleAge;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 0未删除 1删除
     */
    private Integer delFlag;
}
