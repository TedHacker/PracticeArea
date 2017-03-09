package com.zju;

import com.google.common.collect.Lists;
import com.zju.meta.Configuration;
import com.zju.meta.TableColumn;
import com.zju.util.JavaTypeResolver;
import com.zju.util.StringUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 *
 */
public class CodeGenerator {
    public static void generate(Configuration configuration) {
//        File file = new File(configuration.getClassPathEntry());
//        if (!file.exists()) {
//            throw new RuntimeException("Class not found:"+file.getPath());
//        }
//        URL url;
//        try {
//            url = file.toURI().toURL();
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("uri error");
//        }
//        ClassLoader parent = Thread.currentThread().getContextClassLoader();
//        URLClassLoader ucl = new URLClassLoader(new URL[]{url}, parent);
        Connection connection;
        ResultSet rs=null;
        DatabaseMetaData databaseMetaData=null;
        List<TableColumn> columns= Lists.newArrayList();
        TableColumn column;
        JavaTypeResolver javaTypeResolver=new JavaTypeResolver();
        try {
            Class.forName(configuration.getDriverClass());
            connection = DriverManager.getConnection(configuration.getConnectionURL(), configuration.getUserId(), configuration.getPassword());
            databaseMetaData = connection.getMetaData();
            rs = databaseMetaData.getColumns("", "", configuration.getTableName(), "%");
            boolean supportsIsAutoIncrement = false;
            boolean supportsIsGeneratedColumn = false;
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            for (int i = 1; i <= colCount; i++) {
                if ("IS_AUTOINCREMENT".equals(rsmd.getColumnName(i))) {
                    supportsIsAutoIncrement = true;
                }
                if ("IS_GENERATEDCOLUMN".equals(rsmd.getColumnName(i))) {
                    supportsIsGeneratedColumn = true;
                }
            }
            while (rs.next()) {
                column = new TableColumn();
                column.setJdbcType(rs.getInt("DATA_TYPE"));
                column.setLength(rs.getInt("COLUMN_SIZE"));
                column.setActualColumnName(rs.getString("COLUMN_NAME"));
                column
                        .setNullable(rs.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
                column.setScale(rs.getInt("DECIMAL_DIGITS"));
                column.setRemarks(rs.getString("REMARKS"));
                column.setDefaultValue(rs.getString("COLUMN_DEF"));

                if (supportsIsAutoIncrement) {
                    column.setIsAutoIncrement("YES".equals(rs.getString("IS_AUTOINCREMENT")));
                }
                if (supportsIsGeneratedColumn) {
                    column.setIsGeneratedColumn("YES".equals(rs.getString("IS_GENERATEDCOLUMN")));
                }
                column.setJavaType(javaTypeResolver.get(column));
                column.setJdbcTypeName(javaTypeResolver.calculateJdbcTypeName(column));
                column.setJavaProperty(StringUtils.getCamelCaseString(column.getActualColumnName(), false));
                columns.add(column);
        }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
        }
        try {
            FileGenerator.writeFile(configuration,columns);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
