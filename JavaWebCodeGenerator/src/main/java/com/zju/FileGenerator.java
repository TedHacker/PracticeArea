package com.zju;

import com.alibaba.fastjson.JSON;
import com.zju.meta.TableColumn;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件生成器
 */
public class FileGenerator {

    private static String sf = "%s/%s/%s%s";

    public static void writeFile(com.zju.meta.Configuration configuration, List<TableColumn> columns) throws IOException, TemplateException {
        System.out.println("开始生成文件!");
        File r=new File("");
        //测试环境获取项目根目录路径
        //String path=Class.class.getClass().getResource("/").getPath();
        //Jar包获取根目录路径
        String path=r.getAbsolutePath();
        //System.out.println("path:"+path);
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(path + "/ftl")); //需要文件夹绝对路径
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map root = new HashMap();
        root.put("configuration", configuration);
        root.put("columnList", columns);
        writeSingleFile(cfg, root, "DaoImpl.ftl", configuration.getProjectPath(), configuration.getSqlMappingPackage().replace(".", "/"), configuration.getDomainObjectName(), "DaoImpl.java",configuration.getOverwrite());
        writeSingleFile(cfg, root, "Dao.ftl", configuration.getProjectPath(), configuration.getSqlMappingPackage().replace(".", "/"), configuration.getDomainObjectName(), "Dao.java",configuration.getOverwrite());
        writeSingleFile(cfg, root, "Meta.ftl", configuration.getProjectPath(), configuration.getJavaModelPackage().replace(".", "/"), configuration.getDomainObjectName(), ".java",configuration.getOverwrite());
    }

    public static boolean writeSingleFile(Configuration cfg, Map root, String template, String projectPath, String packagePath, String fileName, String suffix,Boolean overwrite) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(template);
        File file = new File(String.format(sf, projectPath, packagePath, fileName, suffix));
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        if(file.exists()){
            if(!overwrite) {
                System.out.println(String.format("创建单个文件%s失败! 文件已存在", file.getPath()));
                return false;
            }
        }else {
            if (file.createNewFile()) {
                System.out.println(String.format("创建单个文件%s成功!", file.getPath()));
            } else {
                System.out.println(String.format("创建单个文件%s失败!", file.getPath()));
                return false;
            }
        }
        Writer out = null;
        try {
            out = new FileWriter(file);
            temp.process(root, out);
            out.flush();
            System.out.println(String.format(sf+"完成.", projectPath, packagePath, fileName, suffix));
        } catch (Exception e) {
            System.out.println(String.format("写入%s失败!", file.getPath()));
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return true;
    }
}
