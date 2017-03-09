public class ${configuration.domainObjectName}{

<#list columnList as column>
    //${column.remarks}
    private ${column.javaType.baseShortName?cap_first} ${column.javaProperty};

</#list>
<#list columnList as column>
    public ${column.javaType.baseShortName?cap_first} get${column.javaProperty?cap_first}() {
       return this.${column.javaProperty};
    }
    public void set${column.javaProperty?cap_first}(${column.javaType.baseShortName?cap_first} ${column.javaProperty}) {
       this.${column.javaProperty} = ${column.javaProperty};
    }

</#list>

}
