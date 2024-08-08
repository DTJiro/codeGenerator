package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${package.Parent}.annotations.PassToken;
import ${package.Other}.${dtoPackageName}.${dtoSaveName};
import ${package.Other}.${voPackageName}.Result;
<#if isUseCommonQueryDto>
import ${package.Other}.${dtoPackageName}.CommonQueryDTO;
</#if>
<#if !isUseCommonQueryDto>
import ${package.Other}.${dtoPackageName}.${dtoQueryName};
</#if>
import ${package.Other}.${voPackageName}.${voName};
import ${package.Other}.${voPackageName}.PageParam;
import ${package.Service}.${table.serviceName};
import com.baomidou.mybatisplus.core.metadata.IPage;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
<#if swagger>
import io.swagger.annotations.*;
</#if>
import java.util.List;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#--@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath!?replace('Po', '')}</#if>")-->
@RequestMapping("/api/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath!?replace('Po', '')}</#if>")
<#if swagger>
<#--@Api(tags = "${table.comment!}模块")-->
@Api(tags = "${(table.comment!?replace('表', ''))!}模块")
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${serviceVariable};
    <#if !isOnlyQuery>

    @PostMapping(value = "/save")
    @PassToken
        <#if swagger>
    @ApiOperation("保存")
        </#if>
    public Result<Void> save(@RequestBody ${dtoSaveName} param){
        ${serviceVariable}.saveData(param);
        return Result.success();
    }

    @GetMapping(value = "/deleteById")
    @PassToken
        <#if swagger>
    @ApiOperation("根据id删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long"),
    })
    @ApiResponses({
        @ApiResponse(code = 0, message = "OK")
    })
        </#if>
    public Result<Void> deleteById(@RequestParam("id") Long id) {
        ${serviceVariable}.deleteById(id);
        return Result.success();
    }
    </#if>

    @GetMapping(value = "/queryById")
    @PassToken
    <#if swagger>
    @ApiOperation("根据id查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "long"),
    })
    @ApiResponses({
        @ApiResponse(code = 0, message = "OK", response = ${voName}.class)
    })
    </#if>
    public Result<${voName}> queryById(@RequestParam("id") Long id) {
        return Result.success(${serviceVariable}.queryById(id));
    }

    @PostMapping(value = "/queryByPage")
    @PassToken
    <#if swagger>
    @ApiOperation("分页查询")
    </#if>
    <#if isUseCommonQueryDto>
    public Result<IPage<${voName}>> queryByPage(@RequestBody PageParam<CommonQueryDTO> pageParam) {
    </#if>
    <#if !isUseCommonQueryDto>
    public Result<IPage<${voName}>> queryByPage(@RequestBody PageParam<${dtoQueryName}> pageParam) {
    </#if>
        return Result.success(${serviceVariable}.queryByPage(pageParam.getPage(),pageParam.getLimit(),pageParam.getParam()));
    }

    @PostMapping(value = "/queryList")
    @PassToken
    <#if swagger>
    @ApiOperation("列表查询")
    </#if>
    <#if isUseCommonQueryDto>
    public Result<List<${voName}>> queryList(@RequestBody CommonQueryDTO param) {
    </#if>
    <#if !isUseCommonQueryDto>
    public Result<List<${voName}>> queryList(@RequestBody ${dtoQueryName} param) {
    </#if>
        return Result.success(${serviceVariable}.queryList(param));
    }
}
</#if>
