package com.zju;

import com.zju.meta.Configuration;
import com.zju.util.ConfigurationParser;
import org.junit.Test;

import java.io.File;

/**
 * 配置解析 测试类
 */
public class ConfigurationParserTest {

    @Test
    public void testParseConfiguration() throws Exception {
        File file=new File("src/test/resources/generatorConfig.properties");
        Configuration configuration= ConfigurationParser.parseConfiguration(file,true);
        System.out.println(configuration);
    }
}