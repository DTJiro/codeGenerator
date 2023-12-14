package ${package.Other}.${mapstructPackageName};

import ${package.Other}.${dtoPackageName}.${dtoSaveName};
import ${package.Entity}.${entity};
import ${package.Other}.${voPackageName}.${voName};
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper
public interface ${mapstructName} {

    ${mapstructName} mapper = Mappers.getMapper(${mapstructName}.class);

<#if !isOnlyQuery>
    @Mappings({
    <#list table.fields as field>
        <#if field.logicDeleteField>
        @Mapping( target = "${field.propertyName}", constant = "0")
        </#if>
    </#list>
    })
    ${entity} saveDTO2Po(${dtoSaveName} dto);

</#if>
    ${voName} po2Vo(${entity} po);

    List<${voName}> poList2VoList(List<${entity}> list);

    Page<${voName}> poPage2VoPage(Page<${entity}> page);

}
