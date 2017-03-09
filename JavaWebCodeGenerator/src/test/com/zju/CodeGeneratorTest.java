package com.zju;

import com.zju.meta.Configuration;
import com.zju.util.ConfigurationParser;
import org.junit.Test;

import java.io.File;

/**
 * 代码生成 测试类
 */
public class CodeGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        File file=new File("src/test/resources/generatorConfig.properties");
        Configuration configuration= ConfigurationParser.parseConfiguration(file);
        System.out.println(configuration);
        CodeGenerator.generate(configuration);
    }
}