package com.example;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
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
    @Value("${app.update-time-field-name}")
    private String updateTimeFieldName;
    @Value("${app.version-field-name}")
    private String versionFieldName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
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

    // 项目目录
    private String projectPath = System.getProperty("user.dir");

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        String[] split = packageName.split(StringPool.BACK_SLASH + StringPool.DOT);
        String pathname = projectPath + servicePath + File.separator + String.join(File.separator, split);
        deleteFile(new File(pathname));
        deleteFile(new File(projectPath + mapperPath));

        // codeGeneratorOld();
        codeGeneratorNew();
    }

    // 3.5.2 版本的代码生成
    private void codeGeneratorNew() {
        // FastAutoGenerator.create(url, username, password)
        FastAutoGenerator.create(new DataSourceConfig.Builder(url, username, password)
                        .dbQuery(new MySqlQuery()) // 数据库查询
                        // .schema("mybatis-plus") // 数据库 schema(部分数据库适用)
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
                    builder.author(author) // 设置作者
                            // .enableKotlin() // 开启 kotlin 模式
                            .dateType(DateType.ONLY_DATE) // 时间策略，Date
                            // .dateType(DateType.TIME_PACK) // 时间策略，LocalDateTime 和 LocalDate
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
                            .mapper(mapperPackageName) // Mapper 包名
                            // .xml("mapper.xml") // Mapper XML 包名
                            .controller(controllerPackageName) // Controller 包名
                            .other(otherPackageName) // 自定义文件包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + mapperPath + (StringUtils.isNotBlank(mapperXmlPackage) ? (mapperXmlPackage + File.separator) : ""))); // 设置mapperXml生成路径
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
                            .addTableFills(new Column(createTimeFieldName, FieldFill.INSERT),
                                    new Column(updateTimeFieldName, FieldFill.INSERT_UPDATE)) // 添加表字段填充
                            // .idType(IdType.AUTO) // 	全局主键类型
                            // .convertFileName() // 转换文件名称
                            .formatFileName(entityName); // 格式化文件名称
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
                    });
                    if (!isRelation) {
                        // 不为关系表生成 自定义类
                        Map<String, String> customFile = new HashMap<>();
                        // DTO
                        customFile.put(dtoPackageName + File.separator + dtoQueryName + ".java", "/template/new/entityQueryDTO.java.ftl");
                        customFile.put(dtoPackageName + File.separator + dtoSaveName + ".java", "/template/new/entitySaveDTO.java.ftl");
                        // Vo
                        customFile.put(voPackageName + File.separator + voName + ".java", "/template/new/entityVo.java.ftl");
                        // mapstruct
                        customFile.put(mapstructPackageName + File.separator + mapstructName + ".java", "/template/new/Transform.java.ftl");
                        consumer.customFile(customFile);
                    }
                })
                .templateConfig(builder -> {
                    if (isRelation) {
                        // 为关系表则不生成 controller和自定义代码
                        builder
                                .entity("/template/new/entity.java")
                                // .service("/template/new/service.java")
                                // .serviceImpl("/template/new/serviceImpl.java")
                                .mapper("/template/new/mapper.java")
                                .xml("/template/new/mapper.xml")
                                // .controller("/template/new/controller.java")
                                .disable(TemplateType.CONTROLLER) // 禁用模板
                                // .disable() // 禁用所有模板
                                .build();
                    } else {
                        builder
                                .entity("/template/new/entity.java")
                                .service("/template/new/service.java")
                                .serviceImpl("/template/new/serviceImpl.java")
                                .mapper("/template/new/mapper.java")
                                .xml("/template/new/mapper.xml")
                                .controller("/template/new/controller.java")
                                // .disable(TemplateType.ENTITY) // 禁用模板
                                // .disable() // 禁用所有模板
                                .build();
                    }
                })
                .templateEngine(new FreemarkerTemplateEngine(){
                    @Override
                    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {

                        String otherPath = this.getPathInfo(OutputFile.other);
                        customFile.forEach((key, value) -> {
                            String fileName = String.format(otherPath + File.separator + key, objectMap.get("variableNameUpper"));
                            this.outputFile(new File(fileName), objectMap, value, fileOverride);
                        });
                    }
                }) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    // // 3.4.0 版本的代码生成
    // private void codeGeneratorOld() {
    //
    //     // 代码生成器
    //     AutoGenerator mpg = new AutoGenerator();
    //
    //     // 全局配置
    //     GlobalConfig gc = new GlobalConfig();
    //     gc.setOutputDir(projectPath + servicePath);
    //     gc.setAuthor(author);
    //     // 设置完之后是否打开资源管理器
    //     gc.setOpen(open);
    //     // 设置是否覆盖原始生成的文件
    //     gc.setFileOverride(fileOverride);
    //     // 设置id生成策略
    //     gc.setIdType(IdType.ASSIGN_ID);
    //     // 生成基本ResultMap
    //     gc.setBaseResultMap(baseResultMap);
    //     // 生成基本ColumnList
    //     gc.setBaseColumnList(baseColumnList);
    //     // 设置时间类型，不设置时默认为 LocalDateTime 和 LocalDate
    //     gc.setDateType(DateType.ONLY_DATE);
    //     // 设置数据层接口名，%s为占位符  代表数据库中的表名或模块名 "%sDao"
    //     if(StringUtils.isNotBlank(mapperName)) {
    //         gc.setMapperName(mapperName);
    //     }
    //     if(StringUtils.isNotBlank(entityName)) {
    //         gc.setEntityName(entityName);
    //     }
    //     if(StringUtils.isNotBlank(serviceName)) {
    //         gc.setServiceName(serviceName);
    //     }
    //     // 实体属性 Swagger2 注解
    //     gc.setSwagger2(Swagger2);
    //     mpg.setGlobalConfig(gc);
    //
    //     // 数据源配置
    //     DataSourceConfig dsc = new DataSourceConfig();
    //     dsc.setUrl(url);
    //     // dsc.setSchemaName("public");
    //     dsc.setDriverName(driverClassName);
    //     dsc.setUsername(username);
    //     dsc.setPassword(password);
    //     dsc.setTypeConvert(new MySqlTypeConvert() {
    //         @Override
    //         public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
    //             if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
    //                 return DbColumnType.INTEGER;
    //             }
    //             return super.processTypeConvert(config, fieldType);
    //         }
    //     });
    //     mpg.setDataSource(dsc);
    //
    //     // 包配置
    //     PackageConfig pc = new PackageConfig();
    //     pc.setModuleName(moduleName);
    //     pc.setParent(packageName);
    //     //设置实体类包名
    //     if(StringUtils.isNotBlank(entityPackageName)) {
    //         pc.setEntity(entityPackageName);
    //     }
    //     //设置数据层包名
    //     if(StringUtils.isNotBlank(mapperPackageName)) {
    //         pc.setMapper(mapperPackageName);
    //     }
    //     // Controller 包名
    //     if(StringUtils.isNotBlank(controllerPackageName)) {
    //         pc.setController(controllerPackageName);
    //     }
    //
    //     mpg.setPackageInfo(pc);
    //
    //     // 自定义配置
    //     InjectionConfig cfg = new InjectionConfig() {
    //         @Override
    //         public void initMap() {
    //             // to do nothing
    //         }
    //     };
    //
    //     // 自定义输出配置
    //     List<FileOutConfig> focList = new ArrayList<>();
    //     // 自定义配置会被优先输出
    //     focList.add(new FileOutConfig(templatePath) {
    //         @Override
    //         public String outputFile(TableInfo tableInfo) {
    //             // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
    //             return projectPath + mapperPath + (StringUtils.isNotBlank(mapperXmlPackage) ? (mapperXmlPackage + File.separator) : "")
    //                     // + tableInfo.getEntityName() + mapperSuffix
    //                     + tableInfo.getXmlName()
    //                     + StringPool.DOT_XML;
    //         }
    //     });
    //     /*
    //     cfg.setFileCreate(new IFileCreate() {
    //         @Override
    //         public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
    //             // 判断自定义文件夹是否需要创建
    //             checkDir("调用默认方法创建的目录，自定义目录用");
    //             if (fileType == FileType.MAPPER) {
    //                 // 已经生成 mapper 文件判断存在，不想重新生成返回 false
    //                 return !new File(filePath).exists();
    //             }
    //             // 允许生成模板文件
    //             return true;
    //         }
    //     });
    //     */
    //     cfg.setFileOutConfigList(focList);
    //     mpg.setCfg(cfg);
    //
    //     // 配置模板
    //     TemplateConfig templateConfig = new TemplateConfig();
    //     // templateConfig.disable(TemplateType.CONTROLLER, TemplateType.ENTITY, TemplateType.SERVICE, TemplateType.MAPPER, TemplateType.XML);
    //
    //     // 配置自定义输出模板
    //     // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
    //     templateConfig.setEntity("/template/old/entity.java");
    //     templateConfig.setService("/template/old/service.java");
    //     templateConfig.setController("/template/old/controller.java");
    //     templateConfig.setMapper("/template/old/mapper.java");
    //     templateConfig.setServiceImpl("/template/old/serviceImpl.java");
    //     templateConfig.setXml("/template/old/mapper.xml");
    //     mpg.setTemplate(templateConfig);
    //
    //     // 策略配置
    //     StrategyConfig strategy = new StrategyConfig();
    //     strategy.setNaming(NamingStrategy.underline_to_camel);
    //     strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    //     // 你自己的父类实体,没有就不用设置!
    //     strategy.setSuperEntityClass(superEntityClass);
    //     //设置是否启用Lombok
    //     strategy.setEntityLombokModel(entityLombokModel);
    //     // 是否启用Rest风格
    //     strategy.setRestControllerStyle(true);
    //     strategy.setEntitySerialVersionUID(true);
    //     // 公共父类
    //     // 你自己的父类控制器,没有就不用设置!
    //     strategy.setSuperControllerClass(superControllerClass);
    //     // 写于父类中的公共字段
    //     strategy.setSuperEntityColumns(superEntityColumns);
    //     strategy.setInclude(tableNames.split(StringPool.COMMA));
    //     strategy.setControllerMappingHyphenStyle(false);
    //     // 设置数据库表的前缀名称
    //     // strategy.setTablePrefix(pc.getModuleName() + StringPool.UNDERSCORE);
    //     strategy.setTablePrefix(tablePrefix.split(StringPool.COMMA));
    //     // 设置逻辑删除字段名
    //     strategy.setLogicDeleteFieldName(logicDeleteFieldName);
    //     // 设置乐观锁字段名
    //     strategy.setVersionFieldName(versionFieldName);
    //     // 是否生成实体时，生成字段注解
    //     strategy.setEntityTableFieldAnnotationEnable(entityTableFieldAnnotationEnable);
    //
    //     //设置自动填充配置
    //     TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
    //     TableFill gmtModified = new TableFill("update_time", FieldFill.INSERT_UPDATE);
    //     ArrayList<TableFill> tableFills=new ArrayList<>();
    //     tableFills.add(gmtCreate);
    //     tableFills.add(gmtModified);
    //     strategy.setTableFillList(tableFills);
    //
    //     mpg.setStrategy(strategy);
    //     mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    //     mpg.execute();
    // }

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
