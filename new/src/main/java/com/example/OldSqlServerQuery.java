/*
 * Copyright (c) 2011-2021, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.example;

import com.baomidou.mybatisplus.generator.config.querys.AbstractDbQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SqlServer 表数据查询 适用于sqlserver2008以前
 *
 * @author hubin
 * @since 2018-01-16
 */
public class OldSqlServerQuery extends AbstractDbQuery {

    @Override
    public String tablesSql() {
        return "SELECT \n" +
                "    a.TABLE_NAME,\n" +
                "    a.COMMENTS\n" +
                "FROM \n" +
                "    (SELECT\n" +
                "        so.name AS TABLE_NAME,\n" +
                "        CAST(c.value AS VARCHAR(500)) AS COMMENTS\n" +
                "     FROM \n" +
                "        sysobjects so\n" +
                "     LEFT JOIN \n" +
                "        sysproperties c ON c.id = so.id \n" +
                "        AND c.smallid = 0 \n" +
                "        AND c.name = 'MS_Description'  -- 明确注释类型\n" +
                "     WHERE \n" +
                "        so.xtype IN ('U','V')  -- 优化类型判断\n" +
                "    ) a\n" +
                "WHERE \n" +
                "    1=1\n";
    }


    @Override
    public String tableFieldsSql() {
        return "SELECT \n" +
                "    a.name AS TABLE_NAME,\n" +
                "    b.name AS COLUMN_NAME,\n" +
                "    CAST(c.value AS NVARCHAR(500)) AS COMMENTS,\n" +
                "    t.name AS DATA_TYPE,\n" +
                "    (SELECT CASE WHEN count(1) = 1 THEN 'PRI' ELSE '' END \n" +
                "     FROM syscolumns sc\n" +
                "     JOIN sysobjects so ON so.parent_obj = sc.id\n" +
                "     JOIN sysindexkeys sik ON sc.colid = sik.colid AND sc.id = sik.id\n" +
                "     JOIN sysindexes si ON si.id = sc.id AND si.indid = sik.indid\n" +
                "     WHERE sc.id = OBJECT_ID(a.name)\n" +
                "       AND so.xtype = 'PK'\n" +
                "       AND sc.name = b.name) AS [KEY],\n" +
                "    COLUMNPROPERTY(b.id, b.name, 'IsIdentity') AS isIdentity\n" +
                "FROM \n" +
                "    (SELECT name, id FROM sysobjects WHERE xtype IN ('U','V')) a\n" +
                "INNER JOIN \n" +
                "    syscolumns b ON b.id = a.id\n" +
                "LEFT JOIN \n" +
                "    systypes t ON b.xusertype = t.xusertype\n" +
                "LEFT JOIN \n" +
                "    sysproperties c ON c.id = b.id AND c.smallid = b.colid AND c.name = 'MS_Description'\n" +
                "WHERE \n" +
                "    a.name = '%s' \n" +
                "    AND t.name != 'sysname'\n";
    }

    @Override
    public String tableName() {
        return "TABLE_NAME";
    }


    @Override
    public String tableComment() {
        return "COMMENTS";
    }


    @Override
    public String fieldName() {
        return "COLUMN_NAME";
    }


    @Override
    public String fieldType() {
        return "DATA_TYPE";
    }


    @Override
    public String fieldComment() {
        return "COMMENTS";
    }


    @Override
    public String fieldKey() {
        return "KEY";
    }


    @Override
    public boolean isKeyIdentity(ResultSet results) throws SQLException {
        return 1 == results.getInt("isIdentity");
    }
}
