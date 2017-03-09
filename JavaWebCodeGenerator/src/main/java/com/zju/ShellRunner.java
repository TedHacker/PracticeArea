package com.zju;

import com.google.common.collect.Maps;
import com.zju.meta.Configuration;
import com.zju.util.ConfigurationParser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * 支持命令行运行项目
 */
public class ShellRunner {

    private static final String CONFIG_FILE = "-configfile"; //配置文件

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("args is empty");
            System.exit(0);
            return;
        }
        Map<String, String> arguments = parseCommandLine(args);
        if (!arguments.containsKey(CONFIG_FILE)) {
            System.out.println("args do not contain configfile");
            return;
        }
        String configfile = arguments.get(CONFIG_FILE);
        File configurationFile = new File(configfile);
        if (!configurationFile.exists()) {
            System.out.println("configfile is not exist");
            return;
        }
        Configuration configuration= ConfigurationParser.parseConfiguration(configurationFile);
        CodeGenerator.generate(configuration);
    }

    private static Map<String, String> parseCommandLine(String[] args) {
        Map<String, String> arguments = Maps.newHashMap();
        for (int i = 0; i < args.length; ++i) {
            if (CONFIG_FILE.equalsIgnoreCase(args[i])) {
                if ((i + 1) < args.length) {
                    arguments.put(CONFIG_FILE, args[i + 1]);
                } else {
                    System.out.println("args error");
                    System.exit(-1);
                }
            }
        }
        return arguments;
    }
}
