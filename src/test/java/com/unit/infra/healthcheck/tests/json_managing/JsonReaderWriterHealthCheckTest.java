package com.unit.infra.healthcheck.tests.json_managing;

import com.infra.general_utils.json_managing.JsonReaderWriter;
import com.unit.infra.healthcheck.tests.BaseHealthCheckTests;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JsonReaderWriterHealthCheckTest extends BaseHealthCheckTests {
    private String jsonFilePath = "healthcheck-resources/json-reader-writer-test.json";
    private File jsonFile = new File(getClass().getClassLoader().getResource(jsonFilePath).getFile());

    @Test
    public void testGetValueWithFile() {
        JsonReaderWriter reader = new JsonReaderWriter(jsonFile);
        String testVal = reader.getValue("key-01");
        Assert.assertEquals(testVal, "value-01");
    }

    @Test
    public void testGetValueWithFilePath() {
        JsonReaderWriter reader = new JsonReaderWriter(jsonFilePath);
        String testVal = reader.getValue("key-01");
        Assert.assertEquals(testVal, "value-01");
    }

    @Test
    public void testGetValueWithJSONObject() {
        String input = "{\"testKey\":\"testValue\"}";
        JSONObject json = parseJSONObject(input);
        JsonReaderWriter readerWriter = new JsonReaderWriter(json);
        Assert.assertEquals(readerWriter.getValue("testKey"), "testValue");
    }

    private JSONObject parseJSONObject(String input) {
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        try {
            json = (JSONObject) parser.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return json;
    }

    @Test
    public void testGetValueWithObjectMapping() {
        JsonReaderWriter reader = new JsonReaderWriter(jsonFilePath);
        GenericObject obj = reader.getValue("object-01", GenericObject.class);
        Integer id = 123456;
        Assert.assertEquals(id, obj.getId());
        Assert.assertEquals("Tom", obj.getName());
    }

    @Test
    public void testGetArray() {
        JsonReaderWriter reader = new JsonReaderWriter(jsonFilePath);
        ArrayList<GenericObject> array = reader.getArray("array-02", GenericObject.class);
        GenericObject obj3 = new GenericObject();
        obj3.setId(3);
        obj3.setName("Three");
        Assert.assertEquals(obj3.getId(), array.get(2).getId());
        Assert.assertEquals(obj3.getName(), array.get(2).getName());
    }

    @Test
    public void testGetValueWithNonExistingKey() {
        JsonReaderWriter reader = new JsonReaderWriter(jsonFilePath);
        GenericObject obj = reader.getValue("object-02", GenericObject.class);
        Assert.assertNull(obj);
    }

    @Test
    public void setValueTest() {
        JsonReaderWriter readerWriter = new JsonReaderWriter(jsonFilePath);
        String newValue = "new-value";
        readerWriter.setValue("key-01", newValue);
        String value = readerWriter.getValue("key-01");
        Assert.assertEquals(value, newValue);
    }

    @Test
    public void setObjectValueTest() {
        JsonReaderWriter readerWriter = new JsonReaderWriter(jsonFilePath);
        GenericObject newObjValue = new GenericObject();
        newObjValue.setId(111);
        newObjValue.setName("new");
        readerWriter.setValue("key-01", newObjValue);
        GenericObject obj = readerWriter.getValue("key-01", GenericObject.class);
        Assert.assertTrue(obj.getId().equals(newObjValue.getId()) && obj.getName().equals(newObjValue.getName()));
    }

    @Test
    public void addToArrayTest() {
        JsonReaderWriter readerWriter = new JsonReaderWriter(jsonFilePath);
        String newValue = "new-value-in-array";
        readerWriter.addToArray("array-01", newValue);
        ArrayList<String> array = readerWriter.getArray("array-01");
        Assert.assertTrue(array.contains(newValue));
    }

    @Test
    public void toJsonStringTest() {
        JsonReaderWriter readerWriter = new JsonReaderWriter(jsonFilePath);
        String jsonFileAsString = loadFileAsString();
        Assert.assertEquals(removeWhiteSpaces(readerWriter.getJsonAsString()), removeWhiteSpaces(jsonFileAsString));
    }

    @Test
    public void stringToJsonObject() throws ParseException {
        String input = "{\"testKey\":\"testValue\"}";
        JSONParser parser = new JSONParser();
        JSONObject json = null;
        json = (JSONObject) parser.parse(input);
        JsonReaderWriter readerWriter = new JsonReaderWriter(json);
        Assert.assertEquals(readerWriter.getValue("testKey"), "testValue");
    }

    private String loadFileAsString() {
        String res = null;
        try {
            res = Files.lines(jsonFile.toPath()).collect(Collectors.joining());
        } catch (IOException e) {
            // Do nothing
        }

        return res;
    }

    private String removeWhiteSpaces(String s) {
        return s.replaceAll("\\s+", "");
    }

    public class GenericObject implements Serializable {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}