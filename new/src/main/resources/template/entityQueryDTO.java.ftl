package ${package.Other}.${dtoPackageName};

<#list table.importPackages as pkg>
    <#if pkg?contains("com.baomidou.mybatisplus")>
    <#else>
import ${pkg};
    </#if>
</#list>
<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
    <#if chainModel>
import lombok.experimental.Accessors;
    </#if>
</#if>

/**
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
</#if>
<#if swagger>
@ApiModel(value = "${variableNameUpper}查询入参对象", description = "")
</#if>
<#if entitySerialVersionUID>
public class ${dtoQueryName} implements Serializable {
<#else>
public class ${dtoQueryName} {
</#if>
<#if entitySerialVersionUID>

    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>
    <#if !field.logicDeleteField && !field.keyFlag
    && "${createTimeFieldName}"!="${field.annotationColumnName}"
    && "${createUserFieldName}"!="${field.annotationColumnName}"
    && "${updateTimeFieldName}"!="${field.annotationColumnName}"
    && "${updateUserFieldName}"!="${field.annotationColumnName}">

        <#if field.comment!?length gt 0>
            <#if swagger>
    @ApiModelProperty("${field.comment?trim}")
            <#else>
    /**
    * ${field.comment?trim}
    */
            </#if>
        </#if>
    <#-- 添加时间转换注解 -->
        <#if field.propertyType == "Date"||field.propertyType == "LocalDateTime">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        </#if>
        <#if field.propertyType == "LocalDate">
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
        </#if>
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->
}
