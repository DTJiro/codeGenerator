package ${package.Other}.${voPackageName};

<#list table.importPackages as pkg>
    <#if pkg?contains("com.baomidou.mybatisplus")>
    <#else>
import ${pkg};
        <#if pkg?contains("java.util.Date")>
import com.fasterxml.jackson.annotation.JsonFormat;
        </#if>
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
    <#if chainModel>
@Accessors(chain = true)
    </#if>
</#if>
<#if swagger>
@ApiModel(value = "${variableNameUpper}查询结果对象", description = "")
</#if>
<#if entitySerialVersionUID>
public class ${voName} implements Serializable {
<#else>
public class ${voName} {
</#if>
<#if entitySerialVersionUID>

    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>
    <#if !field.logicDeleteField && "${createTimeFieldName}"!="${field.annotationColumnName}"
    && "${createUserFieldName}"!="${field.annotationColumnName}"
    && "${updateTimeFieldName}"!="${field.annotationColumnName}"
    && "${updateUserFieldName}"!="${field.annotationColumnName}">

        <#if field.comment!?length gt 0>
            <#if swagger>
    @ApiModelProperty("${field.comment}")
            <#else>
    /**
     * ${field.comment}
     */
            </#if>
        </#if>
        <#-- 添加时间转换注解 -->
        <#if field.propertyType == "Date">
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        </#if>
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->

<#if !entityLombokModel>
    <#list table.fields as field>
        <#if !field.logicDeleteField && "${createTimeFieldName}"!="${field.annotationColumnName}"
        && "${createUserFieldName}"!="${field.annotationColumnName}"
        && "${updateTimeFieldName}"!="${field.annotationColumnName}"
        && "${updateUserFieldName}"!="${field.annotationColumnName}">
            <#if field.propertyType == "boolean">
                <#assign getprefix="is"/>
            <#else>
                <#assign getprefix="get"/>
            </#if>
    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }

        <#if chainModel>
    public ${voName} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        <#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
            <#if chainModel>
        return this;
            </#if>
    }
        </#if>
    </#list>
</#if>

<#if entityColumnConstant>
    <#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";

    </#list>
</#if>
<#if activeRecord>
    @Override
    public Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }

</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
        return "${voName}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName}=" + ${field.propertyName} +
        <#else>
            ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
        "}";
    }
</#if>
}
