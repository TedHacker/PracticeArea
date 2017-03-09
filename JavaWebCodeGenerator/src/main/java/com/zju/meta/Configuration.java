package com.zju.meta;

/**
 * 配置类
 */
public class Configuration {
    private String classPathEntry;
    private String driverClass;
    private String connectionURL;
    private String userId;
    private String password;
    private String javaModelPackage;
    private String sqlMappingPackage;
    private String projectPath;
    private String tableName;
    private String domainObjectName;

    public Configuration() {
    }

    public String getClassPathEntry() {
        return classPathEntry;
    }

    public void setClassPathEntry(String classPathEntry) {
        this.classPathEntry = classPathEntry;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJavaModelPackage() {
        return javaModelPackage;
    }

    public void setJavaModelPackage(String javaModelPackage) {
        this.javaModelPackage = javaModelPackage;
    }

    public String getSqlMappingPackage() {
        return sqlMappingPackage;
    }

    public void setSqlMappingPackage(String sqlMappingPackage) {
        this.sqlMappingPackage = sqlMappingPackage;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDomainObjectName() {
        return domainObjectName;
    }

    public void setDomainObjectName(String domainObjectName) {
        this.domainObjectName = domainObjectName;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "classPathEntry='" + classPathEntry + '\'' +
                ", driverClass='" + driverClass + '\'' +
                ", connectionURL='" + connectionURL + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", javaModelPackage='" + javaModelPackage + '\'' +
                ", sqlMappingPackage='" + sqlMappingPackage + '\'' +
                ", projectPath='" + projectPath + '\'' +
                ", tableName='" + tableName + '\'' +
                ", domainObjectName='" + domainObjectName + '\'' +
                '}';
    }
}
