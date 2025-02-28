package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if mapperAnnotation>
import org.apache.ibatis.annotations.Mapper;
</#if>
<#if dynamicDatasource??>
import com.baomidou.dynamic.datasource.annotation.DS;
</#if>

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if mapperAnnotation>
@Mapper
</#if>
<#if dynamicDatasource??>
@DS("${dynamicDatasource}")
</#if>
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    <#if isUseMyBatis>
    int deleteByPrimaryKey(Long id);

    int insert(${tableClass.shortClassName} record);

    int insertSelective(${tableClass.shortClassName} record);

    ${tableClass.shortClassName} selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(${tableClass.shortClassName} record);

    int updateByPrimaryKey(${tableClass.shortClassName} record);
    </#if>
}
</#if>
