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

}
