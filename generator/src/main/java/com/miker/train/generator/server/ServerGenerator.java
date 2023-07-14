package com.miker.train.generator.server;

import com.miker.train.generator.util.FreemarkerUtil;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {
    static String serverPath = "[module]/src/main/java/com/miker/train/[module]/";
    static String pomPath = "generator/pom.xml";
    static {
        new File(serverPath).mkdirs();
    }

    public static void main(String[] args) throws Exception {
        String generatorPath = getGeneratorPath();
        //获取模块名
        String module = generatorPath.replace("src/main/resources/generator-config-", "").replace(".xml", "");
        serverPath = serverPath.replace("[module]", module);

        //获取表名、实体名
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("generator/"+generatorPath);
        Node table = document.selectSingleNode("//table");
        Node tableName = table.selectSingleNode("@tableName");
        Node domainObjectName = table.selectSingleNode("@domainObjectName");
        System.out.println(tableName.getText()+"/"+domainObjectName.getText());

        //映射；示例：表名 miker_test
        // Domain = MikerTest
        String Domain = domainObjectName.getText();
        // domain = mikerTest
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        // do_main = miker-test
        String do_main = tableName.getText().replaceAll("_", "-");
        // 组装参数
        Map<String, Object> param = new HashMap<>();
        param.put("Domain", Domain);
        param.put("domain", domain);
        param.put("do_main", do_main);
        System.out.println("组装参数：" + param);

        gen(Domain,param,"service");
        gen(Domain,param,"controller");
    }

    public static void gen(String Domain,Map<String,Object> param,String target) throws Exception {
        FreemarkerUtil.initConfig(target+".ftl");
        String toPath = serverPath+target+"/";
        new File(toPath).mkdirs();
        String Target = target.substring(0,1).toUpperCase()+target.substring(1);
        String fileName = toPath+Domain+Target+".java";
        FreemarkerUtil.generator(fileName,param);
    }

    public static String getGeneratorPath() throws Exception {
        SAXReader saxReader = new SAXReader();
        Map<String, String> map = new HashMap<>();
        map.put("pom","http://maven.apache.org/POM/4.0.0");
        saxReader.getDocumentFactory().setXPathNamespaceURIs(map);
        Document document = saxReader.read(pomPath);
        Node node = document.selectSingleNode("//pom:configurationFile");
        System.out.println(node.getText());
        return node.getText();
    }
}
