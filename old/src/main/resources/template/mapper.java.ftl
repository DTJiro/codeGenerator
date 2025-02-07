package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if cfg.isUseMapper>
import org.apache.ibatis.annotations.Mapper;
</#if>

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if cfg.isUseMapper>
@Mapper
</#if>
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName}<#if !cfg.isUseMyBatis> extends ${superMapperClass}<${entity}></#if> {

    <#if cfg.isUseMyBatis>
    int deleteByPrimaryKey(Long id);

    int insert(${entity} record);

    int insertSelective(${entity} record);

    ${entity} selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(${entity} record);

    int updateByPrimaryKey(${entity} record);
    </#if>
}
</#if>
