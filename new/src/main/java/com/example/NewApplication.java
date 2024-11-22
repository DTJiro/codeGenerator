package com.example;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.internal.NotNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.io.File;
import java.util.*;

@SpringBootApplication
@ComponentScan(excludeFilters =
        {
                @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.*")
        })
public class NewApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NewApplication.class, args);
    }

    @Value("${app.package-name}")
    private String packageName;
    @Value("${app.entity-package-name}")
    private String entityPackageName;
    @Value("${app.service-package-name}")
    private String servicePackageName;
    @Value("${app.service-impl-package-name}")
    private String serviceImplPackageName;
    @Value("${app.controller-package-name}")
    private String controllerPackageName;
    @Value("${app.other-package-name}")
    private String otherPackageName;
    @Value("${app.dto-package-name}")
    private String dtoPackageName;
    @Value("${app.vo-package-name}")
    private String voPackageName;
    @Value("${app.mapstruct-package-name}")
    private String mapstructPackageName;
    @Value("${app.mapper-name}")
    private String mapperName;
    @Value("${app.entity-name}")
    private String entityName;
    @Value("${app.dto-save-name}")
    private String dtoSaveName;
    @Value("${app.dto-query-name}")
    private String dtoQueryName;
    @Value("${app.vo-name}")
    private String voName;
    @Value("${app.mapstruct-name}")
    private String mapstructName;
    @Value("${app.controller-name}")
    private String controllerName;
    @Value("${app.service-name}")
    private String serviceName;
    @Value("${app.service-impl-name}")
    private String serviceImplName;
    @Value("${app.mapper-package-name}")
    private String mapperPackageName;
    @Value("${app.mapper-xml-package}")
    private String mapperXmlPackage;
    @Value("${app.module-name}")
    private String moduleName;
    @Value("${app.table-name}")
    private String tableNames;
    @Value("${app.table-prefix}")
    private String tablePrefix;
    @Value("${app.logic-delete-field-name}")
    private String logicDeleteFieldName;
    @Value("${app.create-time-field-name}")
    private String createTimeFieldName;
    @Value("${app.create-user-field-name}")
    private String createUserFieldName;
    @Value("${app.update-time-field-name}")
    private String updateTimeFieldName;
    @Value("${app.update-user-field-name}")
    private String updateUserFieldName;
    @Value("${app.version-field-name}")
    private String versionFieldName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${app.schema-name}")
    private String schemaName;
    // @Value("${spring.datasource.driver-class-name}")
    // private String driverClassName;
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
    @Value("${app.Swagger2}")
    private boolean Swagger2;
    @Value("${app.entity-table-field-annotation-enable}")
    private boolean entityTableFieldAnnotationEnable;
    @Value("${app.mapper-annotation-enable}")
    private boolean mapperAnnotationEnable;
    @Value("${app.is-relation}")
    private boolean isRelation;
    @Value("${app.dynamic-datasource}")
    private String dynamicDatasource;
    @Value("${app.is-global-config-logic-delete}")
    private boolean isGlobalConfigLogicDelete;
    @Value("${app.is-only-query}")
    private boolean isOnlyQuery;
    @Value("${app.is-use-LocalDate}")
    private boolean isUseLocalDate;
    @Value("${app.is-use-common-query-dto}")
    private boolean isUseCommonQueryDto;
    @Value("${app.is-logic-delete-field-default}")
    private boolean isLogicDeleteFieldDefault;
    @Value("${app.is-use-api}")
    private boolean isUseApi;

    // 项目目录
    private String projectPath = System.getProperty("user.dir");

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        String[] split = packageName.split(StringPool.BACK_SLASH + StringPool.DOT);
        String pathname = projectPath + servicePath + File.separator + String.join(File.separator, split);
        System.out.println(pathname);
        System.out.println(projectPath + mapperPath);
        deleteFile(new File(pathname));
        deleteFile(new File(projectPath + mapperPath));
        try {
            codeGeneratorNew();
        } catch (Exception e) {
            e.printStackTrace();
            codeGeneratorNew();
        }
    }

    // 3.5.2 版本的代码生成
    private void codeGeneratorNew() throws Exception {
        // FastAutoGenerator.create(url, username, password)
        FastAutoGenerator.create(new DataSourceConfig.Builder(url, username, password)
                                .dbQuery(new MySqlQuery()) // 数据库查询
                                .schema(schemaName) // 数据库 schema(部分数据库适用)
                                .typeConvert(new MySqlTypeConvert(){
                                    @Override
                                    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                                        IColumnType iColumnType = super.processTypeConvert(config, fieldType);
                                        if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                                            iColumnType = DbColumnType.INTEGER;
                                        }
                                        // if (fieldType.equals("bit(1)")) {
                                        //     iColumnType = DbColumnType.BYTE;
                                        // }
                                        return iColumnType;
                                    }
                                }) // 数据库类型转换器
                                .keyWordsHandler(new MySqlKeyWordsHandler()) // 数据库关键字处理器
                        // .databaseQueryClass(SQLQuery.class)
                )
                .globalConfig(builder -> {
                    if (Swagger2) {
                        // 开启 swagger 模式
                        builder.enableSwagger();
                    }
                    if (isUseLocalDate) {
                        // 时间策略，LocalDateTime 和 LocalDate
                        builder.dateType(DateType.TIME_PACK);
                    } else {
                        // 时间策略，Date
                        builder.dateType(DateType.ONLY_DATE);
                    }
                    builder.author(author) // 设置作者
                            // .enableKotlin() // 开启 kotlin 模式
                            .commentDate("yyyy-MM-dd") // 文档注释日期
                            .outputDir(projectPath + servicePath); // 指定输出目录
                    // 禁止打开输出目录
                    if (!open) {
                        builder.disableOpenDir();
                    }
                })
                .packageConfig(builder -> {
                    builder.parent(packageName) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .entity(entityPackageName) // Entity 包名
                            .service(servicePackageName) // Service 包名
                            .serviceImpl(serviceImplPackageName) // Service Impl 包名
                            .mapper(mapperPackageName + (CharSequenceUtil.isNotBlank(dynamicDatasource) ?
                                    StringPool.DOT + dynamicDatasource : "")) // Mapper 包名
                            // .xml("mapper.xml") // Mapper XML 包名
                            .controller(controllerPackageName) // Controller 包名
                            .other(otherPackageName) // 自定义文件包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + mapperPath
                                    + (StringUtils.isNotBlank(mapperXmlPackage) ? (mapperXmlPackage + File.separator) : "")
                                    + (StringUtils.isNotBlank(dynamicDatasource) ? (dynamicDatasource + File.separator) : "")
                            )); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames.split(StringPool.COMMA)) // 设置需要生成的表名
                            // .addExclude() // 增加表排除匹配
                            // .enableCapitalMode() // 开启大写命名
                            // .enableSkipView() // 开启跳过视图
                            // .disableSqlFilter() // 禁用 sql 过滤
                            // .enableSchema() // 启用 schema

                            // // likeTable 与 notLikeTable 只能配置一项
                            // .likeTable(new LikeTable("USER")) // 模糊表匹配(sql 过滤)
                            // .notLikeTable() // 模糊表排除(sql 过滤)

                            // .addFieldSuffix("") // 增加过滤表后缀
                            // .addFieldPrefix() // 增加过滤字段前缀
                            // .addFieldSuffix() // 增加过滤字段后缀
                            .addTablePrefix(tablePrefix.split(StringPool.COMMA)); // 设置过滤表前缀
                    // 实体策略配置
                    Entity.Builder entityBuilder = builder.entityBuilder();
                    if (fileOverride) {
                        entityBuilder.fileOverride();
                    }
                    if (entityTableFieldAnnotationEnable) {
                        // 开启生成实体时生成字段注解
                        entityBuilder.enableTableFieldAnnotation();
                    }
                    if (StringUtils.isNotBlank(superEntityClass)) {
                        // 设置父类
                        entityBuilder.superClass(superEntityClass);
                    }
                    if (StringUtils.isNotBlank(superEntityColumns)) {
                        // 添加父类公共字段
                        entityBuilder.addSuperEntityColumns(superEntityColumns.split(StringPool.COMMA));
                    }
                    if (entityLombokModel) {
                        // 开启 lombok 模型
                        entityBuilder.enableLombok();
                    }
                    entityBuilder
                            // .nameConvert() // 名称转换实现
                            // .disableSerialVersionUID() // 禁用生成 serialVersionUID
                            // .enableColumnConstant() // 开启生成字段常量
                            // .enableChainModel() // 开启链式模型
                            // .enableRemoveIsPrefix() // 开启 Boolean 类型字段移除 is 前缀
                            // .enableActiveRecord() // 开启 ActiveRecord 模型
                            .versionColumnName(versionFieldName) // 乐观锁字段名(数据库字段)
                            //.versionPropertyName("version") // 乐观锁属性名(实体)
                            .logicDeleteColumnName(logicDeleteFieldName) // 逻辑删除字段名(数据库字段)
                            //.logicDeletePropertyName("deleteFlag") // 逻辑删除属性名(实体)
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
                            // .addIgnoreColumns("age") // 添加忽略字段
                            // .idType(IdType.AUTO) // 	全局主键类型
                            // .convertFileName() // 转换文件名称
                            .formatFileName(entityName); // 格式化文件名称

                    List<IFill> list = new ArrayList<>();
                    for (String s : createTimeFieldName.split(",")) {
                        list.add(new Column(s, FieldFill.INSERT));
                    }
                    for (String s : createUserFieldName.split(",")) {
                        list.add(new Column(s, FieldFill.INSERT));
                    }
                    for (String s : updateUserFieldName.split(",")) {
                        list.add(new Column(s, FieldFill.INSERT_UPDATE));
                    }
                    for (String s : updateTimeFieldName.split(",")) {
                        list.add(new Column(s, FieldFill.INSERT_UPDATE));
                    }
                    entityBuilder.addTableFills(list); // 添加表字段填充

                    // controller 策略配置
                    Controller.Builder controllerBuilder = builder.controllerBuilder();
                    if (fileOverride) {
                        controllerBuilder.fileOverride();
                    }
                    if (StringUtils.isNotBlank(superControllerClass)) {
                        // 设置父类
                        controllerBuilder.superClass(superControllerClass);
                    }
                    controllerBuilder.enableRestStyle() // 开启生成@RestController 控制器
                            // .enableHyphenStyle() // 开启驼峰转连字符
                            // .convertFileName() // 转换文件名称
                            .formatFileName(controllerName); // 格式化文件名称
                    // mapper 策略配置
                    Mapper.Builder mapperBuilder = builder.mapperBuilder();
                    if (fileOverride) {
                        mapperBuilder.fileOverride();
                    }
                    if (baseResultMap) {
                        // 启用 BaseResultMap 生成
                        mapperBuilder.enableBaseResultMap();
                    }
                    if (baseColumnList) {
                        // 启用 BaseColumnList
                        mapperBuilder.enableBaseColumnList();
                    }
                    if (mapperAnnotationEnable) {
                        // 开启 @Mapper 注解
                        mapperBuilder.enableMapperAnnotation();
                    }
                    mapperBuilder
                            // .superClass(BaseMapper.class) // 设置父类
                            // .cache(MyMapperCache.class) // 设置缓存实现类
                            // .convertMapperFileName() // 转换 mapper 类文件名称
                            // .convertXmlFileName() // 转换 xml 文件名称
                            // .formatXmlFileName("%sXml") // 格式化 xml 实现类文件名称
                            .formatMapperFileName(mapperName); // 格式化 mapper 文件名称
                    // service 策略配置
                    Service.Builder serviceBuilder = builder.serviceBuilder();
                    if (fileOverride) {
                        serviceBuilder.fileOverride();
                    }
                    serviceBuilder.formatServiceFileName(serviceName) // 格式化 service 接口文件名称
                            // .superServiceClass(BaseService.class) // 设置 service 接口父类
                            // .superServiceImplClass(BaseServiceImpl.class) // 设置 service 实现类父类
                            // .convertServiceFileName() // 转换 service 接口文件名称
                            // .convertServiceImplFileName() // 转换 service 实现类文件名称
                            .formatServiceImplFileName(serviceImplName); // 格式化 service 实现类文件名称
                })
                .injectionConfig(consumer -> {
                    consumer.beforeOutputFile((tableInfo, objectMap) -> {
                        // 设置服务类小驼峰变量名称
                        String serviceName = ((TableInfo) objectMap.get("table")).getServiceName().substring(1);
                        objectMap.put("serviceVariable", CharSequenceUtil.lowerFirst(serviceName));
                        // 获取实体类命名后缀
                        String substring = entityName.substring(2);

                        // 根据命名后缀截取前段
                        String name = CharSequenceUtil.subBefore(tableInfo.getEntityName(), substring, true);
                        objectMap.put("variableNameUpper", name);
                        objectMap.put("variableName", CharSequenceUtil.lowerFirst(name));
                        objectMap.put("voName", String.format(voName, name));
                        objectMap.put("dtoSaveName", String.format(dtoSaveName, name));
                        objectMap.put("dtoQueryName", String.format(dtoQueryName, name));
                        objectMap.put("mapstructName", String.format(mapstructName, name));
                        objectMap.put("dtoPackageName", dtoPackageName);
                        objectMap.put("voPackageName", voPackageName);
                        objectMap.put("mapstructPackageName", mapstructPackageName);
                        objectMap.put("createTimeFieldName", createTimeFieldName);
                        objectMap.put("createUserFieldName", createUserFieldName);
                        objectMap.put("updateUserFieldName", updateUserFieldName);
                        objectMap.put("updateTimeFieldName", updateTimeFieldName);
                        objectMap.put("dynamicDatasource", "master".equals(dynamicDatasource) ? null : dynamicDatasource);
                        objectMap.put("isOnlyQuery", isOnlyQuery);
                        objectMap.put("isUseCommonQueryDto", isUseCommonQueryDto);
                        objectMap.put("isLogicDeleteFieldDefault", isLogicDeleteFieldDefault);
                        objectMap.put("isGlobalConfigLogicDelete", isGlobalConfigLogicDelete);
                        objectMap.put("isRelation", isRelation);
                        objectMap.put("isUseApi", isUseApi);

                        try {
                            System.out.println("tableInfo: " + objectMapper.writeValueAsString(tableInfo));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        System.out.println("objectMap: " + objectMap);
                    });
                    if (!isRelation) {
                        // 不为关系表生成 自定义类
                        Map<String, String> customFile = new HashMap<>();
                        // DTO
                        if (!isOnlyQuery) {
                            customFile.put(dtoPackageName + File.separator + dtoSaveName + ".java", "/template/entitySaveDTO.java.ftl");
                        }
                        customFile.put(dtoPackageName + File.separator + dtoQueryName + ".java", "/template/entityQueryDTO.java.ftl");
                        // Vo
                        customFile.put(voPackageName + File.separator + voName + ".java", "/template/entityVo.java.ftl");
                        // mapstruct
                        customFile.put(mapstructPackageName + File.separator + mapstructName + ".java", "/template/Transform.java.ftl");
                        consumer.customFile(customFile);
                    }
                })
                .templateConfig(builder -> {
                    if (isRelation) {
                        // 为关系表则不生成 controller和自定义代码
                        builder
                                .entity("/template/entity.java")
                                // .service("/template/service.java")
                                // .serviceImpl("/template/serviceImpl.java")
                                .mapper("/template/mapper.java")
                                .xml("/template/mapper.xml")
                                // .controller("/template/controller.java")
                                .disable(TemplateType.CONTROLLER) // 禁用模板
                                // .disable() // 禁用所有模板
                                .build();
                    } else {
                        builder
                                .entity("/template/entity.java")
                                .service("/template/service.java")
                                .serviceImpl("/template/serviceImpl.java")
                                .mapper("/template/mapper.java")
                                .xml("/template/mapper.xml")
                                .controller("/template/controller.java")
                                // .disable(TemplateType.ENTITY) // 禁用模板
                                // .disable() // 禁用所有模板
                                .build();
                    }
                })
                .templateEngine(new FreemarkerTemplateEngine(){
                    @SneakyThrows
                    @Override
                    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
                        // System.out.println(objectMapper.writeValueAsString(tableInfo));
                        System.out.println(objectMap);
                        String otherPath = this.getPathInfo(OutputFile.other);
                        customFile.forEach((key, value) -> {
                            String fileName = String.format(otherPath + File.separator + key, objectMap.get("variableNameUpper"));
                            this.outputFile(new File(fileName), objectMap, value, fileOverride);
                        });
                    }
                }) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    private void deleteFile(File file){
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
