package com.example;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Java2Mysql {
	public static Map<String, String> javaProperty2SqlColumnMap = new HashMap<>();

	//下边是对应的oracle的生成语句，类型都是oracle，如果是mysql还需要改。
	static {
		javaProperty2SqlColumnMap.put("Integer", "int");
		javaProperty2SqlColumnMap.put("Long", "bigint(20)");
		javaProperty2SqlColumnMap.put("BigDecimal", "decimal(10, 2)");
		javaProperty2SqlColumnMap.put("Double", "double(10,2)");
		javaProperty2SqlColumnMap.put("Float", "float(10,2)");
		javaProperty2SqlColumnMap.put("Boolean", "tinyint(1)");
		javaProperty2SqlColumnMap.put("Date", "datetime");
		javaProperty2SqlColumnMap.put("LocalDate", "date");
		javaProperty2SqlColumnMap.put("LocalDateTime", "datetime");
		javaProperty2SqlColumnMap.put("String", "VARCHAR(255)");
		javaProperty2SqlColumnMap.put("tinyint", "tinyint(1)");
	}

	public static void main(String[] args) throws IOException {
		// createTable(VersionPo.class, null);
	}

	public static String createTable(Class<?> clz, String tableName) throws IOException {
		// 判断类上是否有次注解
		boolean clzHasAnno = clz.isAnnotationPresent(TableName.class);
		String prikey = null;
		if (clzHasAnno) {
			// 获取类上的注解
			TableName annotation = clz.getAnnotation(TableName.class);
			// 输出注解上的类名
			String tableNameAnno = annotation.value();
			if(tableNameAnno != null && !"".equals(tableNameAnno)){
				tableName = tableNameAnno.toLowerCase();
			}else{
				throw new RuntimeException("没有类名");
			}
		}
		Field[] fields;
		fields = clz.getDeclaredFields();
		String param;
		String column;
		StringBuilder sb;
		sb = new StringBuilder(50);
		sb.append("create table `").append(tableName).append("` ( \r\n");
		File file = null;
		boolean flag = false;
		String idStr = "";
		for (Field f : fields) {
			column = f.getName();
			if (column.equals("serialVersionUID")) {
				continue;
			}

			boolean fieldHasAnno = f.isAnnotationPresent(TableId.class);
			if(fieldHasAnno){
				TableId fieldAnno = f.getAnnotation(TableId.class);
				//输出注解属性
				String name = fieldAnno.value();
				if(name != null && !"".equals(name)){
					flag = true;
					param = f.getType().getSimpleName();
					name = name.toLowerCase();
					sb.append(name);
					sb.append(" ").append(javaProperty2SqlColumnMap.get(param)).append(" NOT NULL ");
					if (fieldAnno.type().equals(IdType.AUTO)) {
						sb.append("AUTO_INCREMENT ");
					}
					fieldHasAnno = f.isAnnotationPresent(ApiModelProperty.class);
					if(fieldHasAnno) {
						ApiModelProperty tmp = f.getAnnotation(ApiModelProperty.class);
						//输出注解属性
						String value = tmp.value();
						if(value != null && !"".equals(value)){
							sb.append("COMMENT ").append("'").append(value).append("'");
						}
					}
					sb.append(",\n");
					idStr = "PRIMARY KEY (" + name + "),\n";
				}
			}

			fieldHasAnno = f.isAnnotationPresent(TableField.class);
			if(fieldHasAnno){
				TableField fieldAnno = f.getAnnotation(TableField.class);
				//输出注解属性
				String  name = fieldAnno.value().toLowerCase();
				if(name != null && !"".equals(name)){
					column = name;
				}
			}else{
				continue; //没有column注解的过滤掉
			}

			param = f.getType().getSimpleName();
			sb.append(column);//一般第一个是主键
			if (Arrays.asList("is_delete", "type", "status", "state").contains(column) && "Integer".equals(param)) {
				sb.append(" ").append(javaProperty2SqlColumnMap.get("tinyint")).append(" NULL ");
			} else {
				sb.append(" ").append(javaProperty2SqlColumnMap.get(param)).append(" NULL ");
			}
			fieldHasAnno = f.isAnnotationPresent(ApiModelProperty.class);
			if(fieldHasAnno) {
				ApiModelProperty tmp = f.getAnnotation(ApiModelProperty.class);
				//输出注解属性
				String value = tmp.value();
				if(value != null && !"".equals(value)){
					sb.append("COMMENT ").append("'").append(value).append("'");
				}
			}
			// if(prikey == null || "".equals(prikey)){
			//     if (firstId) {//类型转换
			//         sb.append(" PRIMARY KEY ");
			//         firstId = false;
			//     }
			// }else{
			//     if(prikey.equals(column.toLowerCase())){
			//         sb.append(" PRIMARY KEY ");
			//     }
			// }
			sb.append(",\n ");
		}
		if (flag) {
			sb.append(idStr);
		}
		String sql = null;
		sql = sb.toString();
		//去掉最后一个逗号
		int lastIndex = sql.lastIndexOf(",");
		if (lastIndex != -1) {
			sql = sql.substring(0, lastIndex) + sql.substring(lastIndex + 1);
		}

		sql = sql.substring(0, sql.length() - 1) + " ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;\r\n";
		System.out.println("sql :");
		System.out.println(sql);
		return sql;
	}
}