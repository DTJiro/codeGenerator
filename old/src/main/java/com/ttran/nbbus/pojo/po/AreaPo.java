package com.ttran.nbbus.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 行政区划
 * </p>
 *
 * @author zjm
 * @since 2023-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("AREA")
@ApiModel(value="AreaPo对象", description="行政区划")
public class AreaPo extends Model {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "行政区划代码")
    @TableId(value = "area_code", type = IdType.ASSIGN_ID)
    private String areaCode;

    @ApiModelProperty(value = "行政区划")
    @TableField("area_name")
    private String areaName;


}
