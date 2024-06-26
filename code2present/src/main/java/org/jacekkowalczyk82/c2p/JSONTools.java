package org.jacekkowalczyk82.c2p;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.apache.commons.io.FileUtils;
import org.jacekkowalczyk82.c2p.model.Presentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class JSONTools {

    private static ObjectMapper jsonMapper;

    static {
        jsonMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
        jsonMapper.registerModule(javaTimeModule);
    }


    public static Map<String, Object> readJSONToMap(String filePath) {

        Map<String, Object> testData;
        try {
            InputStream is = new FileInputStream(new File(filePath));

            testData = (Map) jsonMapper.readValue(is, HashMap.class);

            return testData;

        } catch (Exception e) {
            String errMessage = "Failed to read data from file: " + filePath;
            e.printStackTrace();
            throw new RuntimeException(errMessage);
        }

    }

    public static Presentation readJSONToPresentation(String filePath) {

        Presentation objectPresentation;
        try {
            InputStream is = new FileInputStream(new File(filePath));

            objectPresentation = (Presentation) jsonMapper.readValue(is, Presentation.class);

            return objectPresentation;

        } catch (Exception e) {
            String errMessage = "Failed to read data from file: " + filePath;
            e.printStackTrace();
            throw new RuntimeException(errMessage);
        }

    }


    public static void writeMapToJsonFile(Map<String, Object> objToSave, String filePath) {
        try {
            ObjectWriter ow = jsonMapper.writer().withDefaultPrettyPrinter();
            String prettyJson = ow.writeValueAsString(objToSave);

            FileUtils.writeStringToFile(new File(filePath), prettyJson, Charset.forName("UTF-8"));

        } catch (Exception e) {
            String failMsg = "Failed to create json or save to file";
            e.printStackTrace();
        }
    }

    public static void writePresentationToJsonFile(Presentation presentation, String filePath) {
        try {
            ObjectWriter ow = jsonMapper.writer().withDefaultPrettyPrinter();
            String prettyJson = ow.writeValueAsString(presentation);

            FileUtils.writeStringToFile(new File(filePath), prettyJson, Charset.forName("UTF-8"));

        } catch (Exception e) {
            String failMsg = "Failed to create json or save to file";
            e.printStackTrace();
        }
    }



    public static String getObjectAsJsonString(Map<String, Object> objectMap) {
        try {
            String json = jsonMapper.writeValueAsString(objectMap);
            return json;

        } catch (Exception e) {
            String failMsg = "Failed to create json or save to file";
            e.printStackTrace();
            return null;
        }
    }

}