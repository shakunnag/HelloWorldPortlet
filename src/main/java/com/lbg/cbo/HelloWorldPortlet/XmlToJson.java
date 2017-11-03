package com.lbg.cbo.HelloWorldPortlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.XML;

public class XmlToJson {
    public static int PRETTY_FACTOR=4;
    public static void main(String[] args) throws Exception {
        String jsonFileName = "feature.json";
        try {
            File xmlFile = new File("ff4j.xml");
            System.out.println(xmlFile);
            InputStream inputStream = new FileInputStream(xmlFile);
            StringBuilder builder = new StringBuilder();
            int ptr;
            while ((ptr = inputStream.read()) != -1) {
                builder.append((char) ptr);
            }

            String xml = builder.toString();
            System.out.println(xml);
            JSONObject jsonObj = XML.toJSONObject(xml);
            System.out.print(jsonObj);
            FileWriter fileWriter =
                    new FileWriter(jsonFileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);
            bufferedWriter.write(jsonObj.toString(PRETTY_FACTOR));
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                            + jsonFileName + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}