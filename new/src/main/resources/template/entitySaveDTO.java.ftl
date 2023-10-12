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
    <#if chainModel>
@Accessors(chain = true)
    </#if>
</#if>
<#if swagger>
@ApiModel(value = "${variableNameUpper}保存对象", description = "")
</#if>
<#if entitySerialVersionUID>
public class ${dtoSaveName} implements Serializable {
<#else>
public class ${dtoSaveName} {
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
    public ${dtoSaveName} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
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

}
