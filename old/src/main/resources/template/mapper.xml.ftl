<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

<#if enableCache>
    <!-- 开启二级缓存 -->
    <cache type="org.mybatis.caches.ehcache.LoggingEhcache"/>

</#if>
<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
<#list table.fields as field>
<#if field.keyFlag><#--生成主键排在第一位-->
        <id column="${field.columnName}" property="${field.propertyName}" />
</#if>
</#list>
<#list table.commonFields as field><#--生成公共字段 -->
        <result column="${field.columnName}" property="${field.propertyName}" />
</#list>
<#list table.fields as field>
<#if !field.keyFlag><#--生成普通字段 -->
        <result column="${field.columnName}" property="${field.propertyName}" />
</#if>
</#list>
    </resultMap>

</#if>
<#if baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
<#list table.commonFields as field>
        ${field.columnName},
</#list>
        ${table.fieldNames}
    </sql>
</#if>

<#if cfg.isUseMyBatis>
    <select id="selectByPrimaryKey" parameterType="java.lang.Long" resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${table.name}
        where <#list table.fields as field><#if field.keyFlag>${field.columnName} = ${'#'}{${field.propertyName}}</#if></#list>
    </select>

    <delete id="deleteByPrimaryKey" parameterType="java.lang.Long">
        delete from ${table.name}
        where <#list table.fields as field><#if field.keyFlag>${field.columnName} = ${'#'}{${field.propertyName}}</#if></#list>
    </delete>

    <insert id="insert"<#if (table.fields?size==1)> keyColumn="${table.fields[0].name}" keyProperty="${table.fields[0].propertyName}" parameterType="${package.Entity}.${entity}" useGeneratedKeys="true"</#if>>
        insert into ${table.name}
        ( <#list table.fields as field>${field.columnName}<#if field_index%3==2>${"\n        "}</#if><#sep>,</#list>)
        values (<#list table.fields as field>${'#'}{${field.propertyName}}<#if field_index%3==2>${"\n        "}</#if><#sep>,</#list>)
    </insert>

    <insert id="insertSelective"<#if (table.fields?size==1)> keyColumn="${table.fields[0].name}" keyProperty="${table.fields[0].propertyName}" parameterType="${package.Entity}.${entity}" useGeneratedKeys="true"</#if>>
        insert into ${table.name}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list table.fields as field>
                <if test="${field.propertyName} != null">${field.columnName},</if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list table.fields as field>
                <if test="${field.propertyName} != null">${'#'}{${field.propertyName}},</if>
            </#list>
        </trim>
    </insert>

    <update id="updateByPrimaryKeySelective" parameterType="${package.Entity}.${entity}">
        update ${table.name}
        <set>
            <#list table.fields as field>
                <if test="${field.propertyName} != null">
                    ${field.columnName} = ${'#'}{${field.propertyName}},
                </if>
            </#list>
        </set>
        where <#list table.fields as field><#if field.keyFlag>${field.columnName} = ${'#'}{${field.propertyName}}</#if></#list>
    </update>

    <update id="updateByPrimaryKey" parameterType="${package.Entity}.${entity}">
        update ${table.name}
        <set>
            <#list table.fields as field>
                <#if !field.keyFlag>
            ${field.columnName} =  ${'#'}{${field.propertyName}},
                </#if>
            </#list>
        </set>
        where <#list table.fields as field><#if field.keyFlag>${field.columnName} = ${'#'}{${field.propertyName}}</#if></#list>
    </update>
</#if>
</mapper>
