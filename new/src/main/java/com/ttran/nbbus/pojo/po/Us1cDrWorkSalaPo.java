package com.ttran.nbbus.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 基础薪资数据表
 * </p>
 *
 * @author zjm
 * @since 2023-10-12
 */
@Data
@TableName("us1c_dr_work_sala")
@ApiModel(value = "Us1cDrWorkSalaPo对象", description = "基础薪资数据表")
public class Us1cDrWorkSalaPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("work_id")
    private String workId;

    @TableField("`name`")
    private String name;

    @TableField("work_hour")
    private BigDecimal workHour;

    @TableField("`date`")
    private Date date;

    @TableField("base_hour")
    private BigDecimal baseHour;

    @TableField("base_sala")
    private BigDecimal baseSala;

    @TableField("over_hour")
    private BigDecimal overHour;

    @TableField("over_sala")
    private BigDecimal overSala;

    @TableField("work_sala")
    private BigDecimal workSala;

    @TableField("work_day")
    private Integer workDay;


}
