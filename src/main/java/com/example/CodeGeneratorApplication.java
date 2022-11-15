package com.example;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.io.File;
import java.util.ArrayList;
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
    @Value("${app.Swagger2}")
    private boolean Swagger2;
    @Value("${app.entity-table-field-annotation-enable}")
    private boolean entityTableFieldAnnotationEnable;

    // 项目目录
    private String projectPath = System.getProperty("user.dir");

    @Override
    public void run(String... args) throws Exception {

        String[] split = packageName.split(StringPool.BACK_SLASH + StringPool.DOT);
        String pathname = projectPath + servicePath + File.separator + String.join(File.separator, split);
        deleteFile(new File(pathname));
        deleteFile(new File(projectPath + mapperPath));

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
        // gc.setDateType(DateType.ONLY_DATE);
        // 设置数据层接口名，%s为占位符  代表数据库中的表名或模块名 "%sDao"
        if(StringUtils.isNotBlank(mapperName)) {
            gc.setMapperName(mapperName);
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
            pc.setMapper(mapperPackageName);
        }
        // Controller 包名
        if(StringUtils.isNotBlank(controllerPackageName)) {
            pc.setController(controllerPackageName);
        }

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + mapperPath + (StringUtils.isNotBlank(mapperXmlPackage) ? (mapperXmlPackage + File.separator) : "")
                        + tableInfo.getEntityName() + mapperSuffix + StringPool.DOT_XML;
            }
        });
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
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
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
        // 公共父类
        // 你自己的父类控制器,没有就不用设置!
        strategy.setSuperControllerClass(superControllerClass);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns(superEntityColumns);
        strategy.setInclude(tableNames);
        strategy.setControllerMappingHyphenStyle(true);
        // 设置数据库表的前缀名称
        // strategy.setTablePrefix(pc.getModuleName() + StringPool.UNDERSCORE);
        String[] split1 = tablePrefix.split(StringPool.COMMA);
        for (String s : split1) {
            strategy.setTablePrefix(s);
        }
        // 设置逻辑删除字段名
        strategy.setLogicDeleteFieldName(logicDeleteFieldName);
        // 设置乐观锁字段名
        strategy.setVersionFieldName(versionFieldName);
        // 是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(entityTableFieldAnnotationEnable);

        //设置自动填充配置
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills=new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
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
