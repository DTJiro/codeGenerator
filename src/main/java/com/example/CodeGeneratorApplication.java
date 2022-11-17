package com.example;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@ComponentScan(excludeFilters =
{
    @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.*")
})
public class CodeGeneratorApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorApplication.class, args);
    }

    @Value("${app.package-name}")
    private String packageName;
    @Value("${app.entity-package-name}")
    private String entityPackageName;
    @Value("${app.service-package-name}")
    private String servicePackageName;
    @Value("${app.service-impl-package-name}")
    private String serviceImplPackageName;
    @Value("${app.mapper-name}")
    private String mapperName;
    @Value("${app.mapper-package-name}")
    private String mapperPackageName;
    @Value("${app.mapper-xml-package}")
    private String mapperXmlPackage;
    @Value("${app.controller-package-name}")
    private String controllerPackageName;
    @Value("${app.module-name}")
    private String moduleName;
    @Value("${app.table-name}")
    private String tableNames;
    @Value("${app.table-prefix}")
    private String tablePrefix;
    @Value("${app.logic-delete-field-name}")
    private String logicDeleteFieldName;
    @Value("${app.version-field-name}")
    private String versionFieldName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${app.author}")
    private String author;
    @Value("${app.open}")
    private boolean open;
    @Value("${app.file-override}")
    private boolean fileOverride;
    // 服务代码路径
    @Value("${app.service-path}")
    private String servicePath;
    // mapper文件路径
    @Value("${app.mapper-path}")
    private String mapperPath;
    // mapper文件后缀
    @Value("${app.mapper-suffix}")
    private String mapperSuffix;
    // 如果模板引擎是 freemarker
    @Value("${app.template-path}")
    private String templatePath;
    // 如果模板引擎是 velocity
    // private String templatePath;
    // 公共字段
    @Value("${app.super-entity-columns}")
    private String superEntityColumns;
    @Value("${app.entity-lombok-model}")
    private boolean entityLombokModel;
    // 你自己的父类实体,没有就不用设置!
    @Value("${app.super-entity-class}")
    private String superEntityClass;
    // 你自己的父类控制器,没有就不用设置!
    @Value("${app.super-controller-class}")
    private String superControllerClass;
    @Value("${app.base-result-map}")
    private boolean baseResultMap;
    @Value("${app.base-column-list}")
    private boolean baseColumnList;
    @Value("${app.swagger2}")
    private boolean swagger2;
    @Value("${app.entity-table-field-annotation-enable}")
    private boolean entityTableFieldAnnotationEnable;
    @Value("${app.use-date}")
    private boolean useDate;
    @Value("${app.comment-date}")
    private String commentDate;

    // 项目目录
    private String projectPath = System.getProperty("user.dir");

    @Override
    public void run(String... args) throws Exception {

        String[] split = packageName.split(StringPool.BACK_SLASH + StringPool.DOT);
        String pathname = projectPath + servicePath + File.separator + String.join(File.separator, split);
        deleteFile(new File(pathname));
        deleteFile(new File(projectPath + mapperPath));

        FastAutoGenerator.create(url,
                        username,
                        password)

                // 全局配置
                .globalConfig(builder -> {
                    builder
                            .author(author) // 作者名称
                            .commentDate(commentDate) // 注释日期
                            .outputDir(projectPath + servicePath); // 输出目录
                    if(swagger2){
                        builder.enableSwagger(); // 是否启用swagger注解
                    }
                    if(useDate){
                        builder.dateType(DateType.ONLY_DATE); // 时间策略
                    }
                    if(fileOverride){
                        builder.fileOverride(); // 覆盖已生成文件
                    }
                    if(open){
                        builder.disableOpenDir(); // 生成后禁止打开所生成的系统目录
                    }
                })

                // 包配置
                .packageConfig(builder -> {
                    builder
                            .parent(packageName) // 父包名
                            .moduleName(moduleName) // 模块包名
                            // .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Dpan\\workspace\\study\\Ibatis_generate\\src\\main\\resources\\mappers")) // xml位置（还可自定义配置entity，service等位置）
                            .other("other"); // 自定义包名
                    if(StringUtils.isNotBlank(entityPackageName)) {
                        builder.entity(entityPackageName); // 实体类包名
                    }
                    if(StringUtils.isNotBlank(mapperPackageName)) {
                        builder.mapper(mapperPackageName); // mapper包名
                    }
                    if(StringUtils.isNotBlank(controllerPackageName)) {
                        builder.controller(controllerPackageName); // controller包名
                    }
                    if(StringUtils.isNotBlank(servicePackageName)) {
                        builder.service(servicePackageName); // service包名
                    }
                    if(StringUtils.isNotBlank(serviceImplPackageName)) {
                        builder.serviceImpl(serviceImplPackageName); // serviceImpl包名
                    }
                })

                // 策略配置
                .strategyConfig(builder -> {
                    builder
                            .addTablePrefix(tablePrefix.split(StringPool.COMMA)) // 增加过滤表前缀
                            // .addTableSuffix("_db") // 增加过滤表后缀
                            // .addFieldPrefix("t_") // 增加过滤字段前缀
                            // .addFieldSuffix("_field") // 增加过滤字段后缀
                            .addInclude(tableNames) // 表匹配

                            // Entity 策略配置
                            .entityBuilder()
                            .enableLombok() // 开启lombok
                            .enableChainModel() // 链式
                            .enableRemoveIsPrefix() // 开启boolean类型字段移除is前缀
                            .enableTableFieldAnnotation() //开启生成实体时生成的字段注解
                            .versionColumnName("version") // 乐观锁数据库字段
                            .versionPropertyName("version") // 乐观锁实体类名称
                            .logicDeleteColumnName("is_deleted") // 逻辑删除数据库中字段名
                            .logicDeletePropertyName("deleted") // 逻辑删除实体类中的字段名
                            .naming(NamingStrategy.underline_to_camel) // 表名 下划线 -》 驼峰命名
                            .columnNaming(NamingStrategy.underline_to_camel) // 字段名 下划线 -》 驼峰命名
                            .idType(IdType.ASSIGN_ID) // 主键生成策略 雪花算法生成id
                            .formatFileName("%s") // Entity 文件名称
                            .addTableFills(new Column("create_time", FieldFill.INSERT)) // 表字段填充
                            .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE)) // 表字段填充
                            .enableColumnConstant()
                            .enableActiveRecord()

                            // Controller 策略配置
                            .controllerBuilder()
                            .enableRestStyle() // 开启@RestController
                            .formatFileName("%sController") // Controller 文件名称

                            // Service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService") // Service 文件名称
                            .formatServiceImplFileName("%sServiceImpl") // ServiceImpl 文件名称

                            // Mapper 策略配置
                            .mapperBuilder()
                            .enableMapperAnnotation() // 开启@Mapper
                            .enableBaseColumnList() // 启用 columnList (通用查询结果列)
                            .enableBaseResultMap() // 启动resultMap
                            .formatMapperFileName("%sMapper") // Mapper 文件名称
                            .formatXmlFileName("%sMapper"); // Xml 文件名称
                })
                .execute(); // 执行
    }

    public void deleteFile(File file){
        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            // System.out.println("文件删除失败,请检查文件是否存在以及文件路径是否正确");
            return;
        }
        //获取目录下子文件
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f : files) {
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                //递归删除目录下的文件
                deleteFile(f);
            } else {
                String currentClassName = this.getClass().getSimpleName().substring(0,this.getClass().getSimpleName().indexOf(StringPool.DOLLAR));
                String substring = f.getName().substring(0, f.getName().indexOf(StringPool.DOT));
                if(!currentClassName.equals(substring)) {
                    //文件删除
                    f.delete();
                    //打印文件名
                    System.out.println("文件名：" + f.getName());
                }
            }
        }
        //文件夹删除
        file.delete();
        System.out.println("目录名：" + file.getName());
    }
}
