package com.miker.train.generator.server;

import com.miker.train.generator.util.FreemarkerUtil;
import freemarker.template.TemplateException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {
    static String toPath = "generator\\src\\main\\java\\com\\miker\\train\\generator\\test\\";

    static String pomPath = "generator/pom.xml";
    static {
        new File(toPath).mkdirs();
    }

    public static void main(String[] args) throws Exception {
        String generatorPath = getGeneratorPath();

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("generator/"+generatorPath);
        Node table = document.selectSingleNode("//table");
        Node tableName = table.selectSingleNode("@tableName");
        Node domainObjectName = table.selectSingleNode("@domainObjectName");
        System.out.println(tableName.getText()+"/"+domainObjectName.getText());
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
