package com.zju.util;

import com.zju.meta.Configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 解析配置文件
 */
public class ConfigurationParser {

    private static final String CLASS_PATH_ENTRY = "class.path.entry";
    private static final String DRIVER_CLASS = "driver.class";
    private static final String CONNECTION_URL = "connection.url";
    private static final String USER_ID = "user.id";
    private static final String USER_PASSWORD = "user.password";
    private static final String JAVA_MODEL_PACKAGE = "java.model.package";
    private static final String SQL_MAPPING_PACKAGE = "sql.mapping.package";
    private static final String PROJECT = "project";
    private static final String TABLE_NAME = "table.name";
    private static final String DOMAIN_OBJECT_NAME = "domain.object.name";

    public static Configuration parseConfiguration(File inputFile) throws IOException {
        FileReader fr = new FileReader(inputFile);
        Properties prop = new Properties();
        prop.load(fr);
        Configuration config = new Configuration();
        for (Object key : prop.keySet()) {
            if (CLASS_PATH_ENTRY.equalsIgnoreCase(key.toString())) {
                config.setClassPathEntry(prop.get(key).toString());
            } else if (DRIVER_CLASS.equalsIgnoreCase(key.toString())) {
                config.setDriverClass(prop.get(key).toString());
            } else if (CONNECTION_URL.equalsIgnoreCase(key.toString())) {
                config.setConnectionURL(prop.get(key).toString());
            } else if (USER_ID.equalsIgnoreCase(key.toString())) {
                config.setUserId(prop.get(key).toString());
            } else if (USER_PASSWORD.equalsIgnoreCase(key.toString())) {
                config.setPassword(prop.get(key).toString());
            } else if (JAVA_MODEL_PACKAGE.equalsIgnoreCase(key.toString())) {
                config.setJavaModelPackage(prop.get(key).toString());
            } else if (SQL_MAPPING_PACKAGE.equalsIgnoreCase(key.toString())) {
                config.setSqlMappingPackage(prop.get(key).toString());
            } else if (PROJECT.equalsIgnoreCase(key.toString())) {
                config.setProjectPath(prop.get(key).toString());
            } else if (TABLE_NAME.equalsIgnoreCase(key.toString())) {
                config.setTableName(prop.get(key).toString());
            } else if (DOMAIN_OBJECT_NAME.equalsIgnoreCase(key.toString())) {
                config.setDomainObjectName(prop.get(key).toString());
            }
        }
        return config;
    }
}
