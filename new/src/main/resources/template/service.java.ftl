package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import ${package.Other}.${dtoPackageName}.${dtoSaveName};
import ${package.Other}.${voPackageName}.${voName};
import com.baomidou.mybatisplus.core.metadata.IPage;
<#if isUseCommonQueryDto>
import ${package.Other}.${dtoPackageName}.CommonQueryDTO;
</#if>
<#if !isUseCommonQueryDto>
import ${package.Other}.${dtoPackageName}.${dtoQueryName};
</#if>

import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    <#if !isOnlyQuery>
    void saveData(${dtoSaveName} param);

    void deleteById(Long id);

    </#if>
    ${voName} queryById(Long id);

    <#if isUseCommonQueryDto>
    IPage<${voName}> queryByPage(Integer page, Integer limit, CommonQueryDTO param);

    List<${voName}> queryList(CommonQueryDTO param);
    </#if>
    <#if !isUseCommonQueryDto>
    IPage<${voName}> queryByPage(Integer page, Integer limit, ${dtoQueryName} param);

    List<${voName}> queryList(${dtoQueryName} param);
    </#if>

}
</#if>
