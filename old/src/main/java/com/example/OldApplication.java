package com.example;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@ComponentScan(excludeFilters =
{
        @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.*")
})
public class OldApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OldApplication.class, args);
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
    // @Value("${app.other-package-name}")
    // private String otherPackageName;
    // @Value("${app.dto-package-name}")
    // private String dtoPackageName;
    // @Value("${app.vo-package-name}")
    // private String voPackageName;
    // @Value("${app.mapstruct-package-name}")
    // private String mapstructPackageName;
    @Value("${app.mapper-name}")
    private String mapperName;
    @Value("${app.entity-name}")
    private String entityName;
    // @Value("${app.dto-save-name}")
    // private String dtoSaveName;
    // @Value("${app.dto-query-name}")
    // private String dtoQueryName;
    // @Value("${app.vo-name}")
    // private String voName;
    // @Value("${app.mapstruct-name}")
    // private String mapstructName;
    // @Value("${app.controller-name}")
    // private String controllerName;
    @Value("${app.service-name}")
    private String serviceName;
    // @Value("${app.service-impl-name}")
    // private String serviceImplName;
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
    // // mapper文件后缀
    // @Value("${app.mapper-suffix}")
    // private String mapperSuffix;
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
    // @Value("${app.mapper-annotation-enable}")
    // private boolean mapperAnnotationEnable;
    @Value("${app.is-relation}")
    private boolean isRelation;
    @Value("${app.is-use-LocalDate}")
    private boolean isUseLocalDate;
    @Value("${app.is-show-mapper-module}")
    private boolean isShowMapperModule;
    @Value("${app.is-use-api}")
    private boolean isUseApi;
    @Value("${app.is-use-MyBatis}")
    private boolean isUseMyBatis;
    @Value("${app.is-use-Mapper}")
    private boolean isUseMapper;

    // 项目目录
    private String projectPath = System.getProperty("user.dir");

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        String[] split = packageName.split(StringPool.BACK_SLASH + StringPool.DOT);
        String pathname = projectPath + servicePath + File.separator + String.join(File.separator, split);
        deleteFile(new File(pathname));
        deleteFile(new File(projectPath + mapperPath));

        codeGeneratorOld();
    }

    // 3.4.0 版本的代码生成
    private void codeGeneratorOld() {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + servicePath);
        gc.setAuthor(author);
        // 设置完之后是否打开资源管理器
        gc.setOpen(open);
        // 设置是否覆盖原始生成的文件
        gc.setFileOverride(fileOverride);
        // 设置id生成策略
        gc.setIdType(IdType.ASSIGN_ID);
        // 生成基本ResultMap
        gc.setBaseResultMap(baseResultMap);
        // 生成基本ColumnList
        gc.setBaseColumnList(baseColumnList);
        // 设置时间类型，不设置时默认为 LocalDateTime 和 LocalDate
        if (!isUseLocalDate) {
            gc.setDateType(DateType.ONLY_DATE);
        }
        // 设置数据层接口名，%s为占位符  代表数据库中的表名或模块名 "%sDao"
        if(StringUtils.isNotBlank(mapperName)) {
            gc.setMapperName(mapperName);
        }
        if(StringUtils.isNotBlank(entityName)) {
            gc.setEntityName(entityName);
        }
        if(StringUtils.isNotBlank(serviceName)) {
            gc.setServiceName(serviceName);
        }
        // 实体属性 Swagger2 注解
        gc.setSwagger2(Swagger2);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverClassName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                    return DbColumnType.INTEGER;
                }
                return super.processTypeConvert(config, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(packageName);
        //设置实体类包名
        if(StringUtils.isNotBlank(entityPackageName)) {
            pc.setEntity(entityPackageName);
        }
        //设置数据层包名
        if(StringUtils.isNotBlank(mapperPackageName)) {

            if (isShowMapperModule) {
                pc.setMapper(mapperPackageName + (StringUtils.isNotBlank(mapperXmlPackage) ? StringPool.DOT + mapperXmlPackage : ""));
            }
        }
        // Controller 包名
        if(StringUtils.isNotBlank(controllerPackageName)) {
            pc.setController(controllerPackageName);
        }

        // Service 包名
        if(StringUtils.isNotBlank(servicePackageName)) {
            pc.setService(servicePackageName);
        }

        // ServiceImpl 包名
        if(StringUtils.isNotBlank(serviceImplPackageName)) {
            pc.setServiceImpl(serviceImplPackageName);
        }

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //自定义属性注入:abc
                //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
                Map<String, Object> map = new HashMap<>();
                map.put("isUseMyBatis", isUseMyBatis);
                map.put("isUseApi", isUseApi);
                map.put("isUseMapper", isUseMapper);
                this.setMap(map);
            }

            @Override
            public List<FileOutConfig> getFileOutConfigList() {
                // 自定义输出配置
                List<FileOutConfig> focList = new ArrayList<>();
                // 自定义配置会被优先输出
                focList.add(new FileOutConfig(templatePath) {
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                        return projectPath + mapperPath + (StringUtils.isNotBlank(mapperXmlPackage) ? (mapperXmlPackage + File.separator) : "")
                                // + tableInfo.getEntityName() + mapperSuffix
                                + tableInfo.getXmlName()
                                + StringPool.DOT_XML;
                    }

                    @Override
                    public String getTemplatePath() {
                        return "/template/mapper.xml.ftl";
                    }
                });
                return focList;
            }
        };

        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // templateConfig.disable(TemplateType.CONTROLLER, TemplateType.ENTITY, TemplateType.SERVICE, TemplateType.MAPPER, TemplateType.XML);

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("/template/entity.java");
        templateConfig.setService("/template/service.java");
        templateConfig.setMapper("/template/mapper.java");
        templateConfig.setServiceImpl("/template/serviceImpl.java");
        if (!isRelation) {
            // 不为关系表生成 生成controller
            templateConfig.setController("/template/controller.java");
        } else {
            templateConfig.setController(null);
        }

        // 不生成默认路径的xml
        templateConfig.setXml(null);
        // templateConfig.setXml("/template/mapper.xml");
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 你自己的父类实体,没有就不用设置!
        strategy.setSuperEntityClass(superEntityClass);
        //设置是否启用Lombok
        strategy.setEntityLombokModel(entityLombokModel);
        // 是否启用Rest风格
        strategy.setRestControllerStyle(true);
        strategy.setEntitySerialVersionUID(true);
        // 公共父类
        // 你自己的父类控制器,没有就不用设置!
        strategy.setSuperControllerClass(superControllerClass);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns(superEntityColumns);
        strategy.setInclude(tableNames.split(StringPool.COMMA));
        strategy.setControllerMappingHyphenStyle(false);
        // 设置数据库表的前缀名称
        // strategy.setTablePrefix(pc.getModuleName() + StringPool.UNDERSCORE);
        strategy.setTablePrefix(tablePrefix.split(StringPool.COMMA));
        // 设置逻辑删除字段名
        strategy.setLogicDeleteFieldName(logicDeleteFieldName);
        // 设置乐观锁字段名
        strategy.setVersionFieldName(versionFieldName);
        // 是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(entityTableFieldAnnotationEnable);

        //设置自动填充配置
        TableFill gmtCreateTime = new TableFill(createTimeFieldName, FieldFill.INSERT);
        TableFill gmtCreateUser = new TableFill(createUserFieldName, FieldFill.INSERT);
        TableFill gmtModifiedTime = new TableFill(updateTimeFieldName, FieldFill.INSERT_UPDATE);
        TableFill gmtModifiedUser = new TableFill(updateUserFieldName, FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills=new ArrayList<>();
        tableFills.add(gmtCreateTime);
        tableFills.add(gmtCreateUser);
        tableFills.add(gmtModifiedTime);
        tableFills.add(gmtModifiedUser);
        strategy.setTableFillList(tableFills);

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
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
