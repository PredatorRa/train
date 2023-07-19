package com.miker.train.generator.gen;

import com.miker.train.generator.util.DbUtil;
import com.miker.train.generator.util.Field;
import com.miker.train.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ServerGenerator {
    static boolean readOnly = true;
    static String vuePath = "admin/src/views/main/";
    static String serverPath = "[module]/src/main/java/com/miker/train/[module]/";
    static String pomPath = "generator/pom.xml";
    static String module = "";
    static {
        new File(serverPath).mkdirs();
    }

    public static void main(String[] args) throws Exception {
        String generatorPath = getGeneratorPath();
        //获取模块名
        module = generatorPath.replace("src/main/resources/generator-config-", "").replace(".xml", "");
        serverPath = serverPath.replace("[module]", module);

        //获取表名、实体名
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("generator/" + generatorPath);
        Node table = document.selectSingleNode("//table");
        Node tableName = table.selectSingleNode("@tableName");
        Node domainObjectName = table.selectSingleNode("@domainObjectName");
        System.out.println(tableName.getText() + "/" + domainObjectName.getText());

        Node connectionURL = document.selectSingleNode("//@connectionURL");
        Node userId = document.selectSingleNode("//@userId");
        Node password = document.selectSingleNode("//@password");
        System.out.println("url: " + connectionURL.getText());
        System.out.println("user: " + userId.getText());
        System.out.println("password: " + password.getText());
        DbUtil.url = connectionURL.getText();
        DbUtil.user = userId.getText();
        DbUtil.password = password.getText();


        //映射；示例：表名 miker_test
        // Domain = MikerTest
        String Domain = domainObjectName.getText();
        // domain = mikerTest
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        // do_main = miker-test
        String do_main = tableName.getText().replaceAll("_", "-");
        // 表中文名
        String tableNameCn = DbUtil.getTableComment(tableName.getText());
        List<Field> fieldList = DbUtil.getColumnByTableName(tableName.getText());
        Set<String> typeSet = getFieldJavaTypeSet(fieldList);
        // 组装参数
        Map<String, Object> param = new HashMap<>();
        param.put("module", module);
        param.put("Domain", Domain);
        param.put("domain", domain);
        param.put("do_main", do_main);
        param.put("fieldList", fieldList);
        param.put("typeSet", typeSet);
        param.put("tableNameCn", tableNameCn);
        param.put("readOnly", readOnly);
        System.out.println("组装参数：" + param);

//         gen(Domain, param, "service", "service");
         gen(Domain, param, "controller/admin", "adminController");
         gen(Domain, param, "req", "saveReq");
         gen(Domain, param, "req", "queryReq");
         gen(Domain, param, "resp", "queryResp");


        genVue(do_main, param);

    }

    public static void gen(String Domain, Map<String, Object> param, String packageName, String target) throws Exception {
        FreemarkerUtil.initConfig(target + ".ftl");
        String toPath = serverPath + packageName + "/";
        new File(toPath).mkdirs();
        String Target = target.substring(0, 1).toUpperCase() + target.substring(1);
        String fileName = toPath + Domain + Target + ".java";
        FreemarkerUtil.generator(fileName, param);
    }

    private static void genVue(String do_main, Map<String, Object> param) throws IOException, TemplateException {
        FreemarkerUtil.initConfig("vue.ftl");
        new File(vuePath+module).mkdirs();
        String fileName = vuePath + module + "/" + do_main + ".vue";
        System.out.println("开始生成：" + fileName);
        FreemarkerUtil.generator(fileName, param);
    }

    public static String getGeneratorPath() throws Exception {
        SAXReader saxReader = new SAXReader();
        Map<String, String> map = new HashMap<>();
        map.put("pom", "http://maven.apache.org/POM/4.0.0");
        saxReader.getDocumentFactory().setXPathNamespaceURIs(map);
        Document document = saxReader.read(pomPath);
        Node node = document.selectSingleNode("//pom:configurationFile");
        System.out.println(node.getText());
        return node.getText();
    }

    public static Set<String> getFieldJavaTypeSet(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (Field field : fieldList) {
            String javaType = field.getJavaType();
            set.add(javaType);
        }
        return set;
    }
}
