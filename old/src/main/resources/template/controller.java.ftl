package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
<#if swagger2>
 import io.swagger.annotations.Api;
</#if>

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
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if swagger2>
@Api(tags = "${table.comment!}模块")
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

<#--    @Autowired-->
<#--    private IRoleService roleService;-->
<#--    -->
<#--    @PostMapping(value = "/save")-->
<#--    @PassToken-->
<#--    @ApiOperation("保存")-->
<#--    public Result save(@RequestBody RoleSaveDTO param){-->
<#--        roleService.saveData(param);-->
<#--        return Result.success();-->
<#--    }-->
<#--    -->
<#--    @GetMapping(value = "/deleteById")-->
<#--    @PassToken-->
<#--    @ApiOperation("根据id删除")-->
<#--    @ApiImplicitParams({-->
<#--        @ApiImplicitParam(name = "id", value = "角色Id", required = true, dataType = "long"),-->
<#--    })-->
<#--    @ApiResponses({-->
<#--        @ApiResponse(code = 0, message = "OK")-->
<#--    })-->
<#--    public Result deleteById(Long id) {-->
<#--        roleService.deleteById(id);-->
<#--        return Result.success();-->
<#--    }-->
<#--    -->
<#--    @GetMapping(value = "/queryById")-->
<#--    @PassToken-->
<#--    @ApiOperation("根据id查询")-->
<#--    @ApiImplicitParams({-->
<#--        @ApiImplicitParam(name = "id", value = "角色Id", required = true, dataType = "long"),-->
<#--    })-->
<#--    @ApiResponses({-->
<#--        @ApiResponse(code = 0, message = "OK", response = RoleVo.class)-->
<#--    })-->
<#--    public Result<RoleVo> queryById(Long id) {-->
<#--        return Result.success(roleService.queryById(id));-->
<#--    }-->
<#--    -->
<#--    @PostMapping(value = "/queryByPage")-->
<#--    @PassToken-->
<#--    @ApiOperation("分页查询")-->
<#--    public Result<IPage<RoleVo>> queryByPage(@RequestBody PageParam<CommonQueryDTO> pageParam) {-->
<#--        return Result.success(roleService.queryByPage(pageParam.getPage(),pageParam.getLimit(),pageParam.getParam()));-->
<#--    }-->
<#--    -->
<#--    @PostMapping(value = "/queryList")-->
<#--    @PassToken-->
<#--    @ApiOperation("列表查询")-->
<#--    public Result<List<RoleVo>> queryList(@RequestBody CommonQueryDTO param) {-->
<#--        return Result.success(roleService.queryList(param));-->
<#--    }-->

}
</#if>
