package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import ${package.Other}.${dtoPackageName}.${dtoSaveName};
import ${package.Other}.${mapstructPackageName}.${mapstructName};
import ${package.Other}.${voPackageName}.${voName};
import ${package.Parent}.utils.BizExceptionUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${package.Other}.${dtoPackageName}.CommonQueryDTO;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    <#if !isOnlyQuery>
    @Override
    public void saveData(${dtoSaveName} param) {
        if (param == null) {
            BizExceptionUtils.createBizException("参数异常");
        }
        saveOrUpdate(${mapstructName}.mapper.saveDTO2Po(param));
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            BizExceptionUtils.createBizException("参数异常");
        }
        removeById(id);
    }

    </#if>
    @Override
    public ${voName} queryById(Long id) {
        if (id == null) {
            BizExceptionUtils.createBizException("参数异常");
        }
        return ${mapstructName}.mapper.po2Vo(getById(id));
    }

    @Override
    public IPage<${voName}> queryByPage(Integer page, Integer limit, CommonQueryDTO param) {
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }
        LambdaQueryChainWrapper<${entity}> wrapper = lambdaQuery();
        // if (param != null) {
        //     wrapper.like(CharSequenceUtil.isNotBlank(param.getName()), ${entity}::getName, param.getName());
        // }
        wrapper.orderByAsc(${entity}::getId);
        return ${mapstructName}.mapper.poPage2VoPage(wrapper.page(new Page<>(page, limit)));
    }

    @Override
    public List<${voName}> queryList(CommonQueryDTO param) {
        LambdaQueryChainWrapper<${entity}> wrapper = lambdaQuery();
        // if (param != null) {
        //     wrapper.like(CharSequenceUtil.isNotBlank(param.getName()), ${entity}::getName, param.getName());
        // }
        return ${mapstructName}.mapper.poList2VoList(wrapper.list());
    }
}
</#if>
