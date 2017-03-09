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
 * @author ted on 17/3/8.
 */
public class FileGenerator {

    private static String sf = "%s/%s/%s%s";

    public static void writeFile(com.zju.meta.Configuration configuration, List<TableColumn> columns) throws IOException, TemplateException {
        //获取项目根目录路径
        File r=new File("");
        //String path=Class.class.getClass().getResource("/").getPath();
        String path=r.getAbsolutePath();
        System.out.println("path:"+path);
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(path + "/ftl")); //需要文件夹绝对路径
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map root = new HashMap();
        root.put("configuration", configuration);
        root.put("columnList", columns);
        System.out.println(JSON.toJSONString(columns));
        writeSingleFile(cfg, root, "DaoImpl.ftl", configuration.getProjectPath(), configuration.getSqlMappingPackage().replace(".", "/"), configuration.getDomainObjectName(), "DaoImpl.java");
        writeSingleFile(cfg, root, "Dao.ftl", configuration.getProjectPath(), configuration.getSqlMappingPackage().replace(".", "/"), configuration.getDomainObjectName(), "Dao.java");
        writeSingleFile(cfg, root, "Meta.ftl", configuration.getProjectPath(), configuration.getJavaModelPackage().replace(".", "/"), configuration.getDomainObjectName(), ".java");
    }

    public static boolean writeSingleFile(Configuration cfg, Map root, String template, String projectPath, String packagePath, String fileName, String suffix) throws IOException, TemplateException {
        Template temp = cfg.getTemplate(template);
        File file = new File(String.format(sf, projectPath, packagePath, fileName, suffix));
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        if (file.createNewFile()) {
            System.out.println(String.format("创建单个文件%s成功!", file.getPath()));
        } else {
            System.out.println(String.format("创建单个文件%s失败!", file.getPath()));
            return false;
        }
        Writer out = null;
        try {
            out = new FileWriter(file);
            temp.process(root, out);
            out.flush();
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
