public class ${configuration.domainObjectName}DaoImpl implements ${configuration.domainObjectName}Dao{

@Override
public boolean add(${configuration.domainObjectName} obj) {
long id = this.allocateRecordId();
if (id < 1) {
return false;
}
String sql = "insert into ${configuration.tableName}("+
"<#list columnList as column><#if 0<column_index>,</#if>${column.actualColumnName}</#list>"+
") values ( <#list columnList as column><#if 0<column_index>,</#if>?</#list> ) ";
List params = new ArrayList();
<#list columnList as column>
params.add(obj.get${column.javaProperty?cap_first}());
</#list>
return getSqlSupport().addRecord(sql, params);
}

@Override
public ${configuration.domainObjectName} getById(long id) {
String sql = "select * from ${configuration.tableName} where id = ?";
return queryObject(sql, id);
}

@Override
public ${configuration.domainObjectName} getObjectFromRs(ResultSet rs) throws SQLException {
${configuration.domainObjectName} obj=new ${configuration.domainObjectName}();
<#list columnList as column>
obj.set${column.javaProperty?cap_first}(rs.get${column.javaType.baseShortName?cap_first}("${column.actualColumnName}"));
</#list>
return obj;
}
}