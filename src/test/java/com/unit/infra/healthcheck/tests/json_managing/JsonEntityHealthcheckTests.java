package com.unit.infra.healthcheck.tests.json_managing;

import com.infra.general_utils.json_managing.BaseJsonEntity;
import com.unit.infra.healthcheck.tests.BaseHealthCheckTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JsonEntityHealthcheckTests extends BaseHealthCheckTests {

    @Test
    public void getStringValueTest() {
        ConcreteJsonEntity entity = new ConcreteJsonEntity();
        Assert.assertEquals(entity.getUsername(), "Alex");
    }

    @Test
    public void setStringValueTest() {
        ConcreteJsonEntity entity = new ConcreteJsonEntity();
        String newUsername = "Hanan";
        entity.setUsername(newUsername);
        Assert.assertEquals(entity.getUsername(), newUsername);
    }

    @Test
    public void getNonStringValueTest() {
        ConcreteJsonEntity entity = new ConcreteJsonEntity();
        Integer actualId = 123456;
        Assert.assertEquals(entity.getId(), actualId);
    }

    @Test
    public void setNonStringValueTest() {
        ConcreteJsonEntity entity = new ConcreteJsonEntity();
        Integer newId = 111111;
        entity.setId(newId);
        Assert.assertEquals(entity.getId(), newId);
    }

    @Test
    public void getArrayValueTest() {
        ConcreteJsonEntity entity = new ConcreteJsonEntity();
        ArrayList<String> actualChildren = new ArrayList<>();
        actualChildren.add("Selenium");
        actualChildren.add("Chrome");
        actualChildren.add("C#");
        Assert.assertTrue(isEqualArray(actualChildren, entity.getChildren()));
    }

    @Test
    public void setArrayValueTest() {
        ConcreteJsonEntity entity = new ConcreteJsonEntity();
        ArrayList<String> newChildren = new ArrayList<>();
        newChildren.add("Yuval");
        newChildren.add("Hanan");
        newChildren.add("Eran");
        entity.setChildren(newChildren);
        Assert.assertTrue(isEqualArray(newChildren, entity.getChildren()));
    }

    @Test
    public void addToArrayTest() {
        ConcreteJsonEntity entity = new ConcreteJsonEntity();
        String newChild = "Java";
        entity.addChild(newChild);
        Assert.assertTrue(entity.getChildren().contains(newChild));
    }

    @Test
    public void getEntityAsJsonStringTest() {
        ConcreteJsonEntity entity = new ConcreteJsonEntity();
        String actualString = loadFileAsString(ConcreteJsonEntity.filePath);
        Assert.assertEquals(removeWhiteSpaces(actualString), removeWhiteSpaces(entity.getJsonAsString()));
    }

    private boolean isEqualArray(ArrayList<String> arr1, ArrayList<String> arr2) {
        boolean equal = true;
        for(String element : arr1) {
            if(!arr2.contains(element)) {
                equal = false;
                break;
            }
        }

        return equal;
    }

    private String loadFileAsString(String filePath) {
        String res = null;
        try {
            File file = new File(getClass().getClassLoader().getResource(filePath).getFile());
            res = Files.lines(Paths.get(file.getPath())).collect(Collectors.joining());
        } catch (IOException e) {
            // Do nothing
        }

        return res;
    }

    private String removeWhiteSpaces(String s) {
        return s.replaceAll("\\s+","");
    }

    private class ConcreteJsonEntity extends BaseJsonEntity {

        static final String filePath = "healthcheck-resources/json-entity-test.json";
        private static final String usernameKey = "user.name";
        private static final String idKey = "user.id";
        private static final String childrenKey = "user.children";

        private String username;
        private Integer id;
        private ArrayList<String> children = new ArrayList<>();

        public ConcreteJsonEntity() {
            super(filePath);
        }

        public String getUsername() {
            return username = (username != null) ? username : this.readerWriter.getValue(usernameKey);
        }

        public void setUsername(String username) {
            this.username = username;
            this.readerWriter.setValue(usernameKey, username);
        }

        public Integer getId() {
            return id = (id != null) ? id : this.readerWriter.getValue(idKey, Integer.class);
        }

        public void setId(Integer id) {
            this.id = id;
            this.readerWriter.setValue(idKey, id);
        }

        public ArrayList<String> getChildren() {
            return children = !children.isEmpty() ? children : this.readerWriter.getArray(childrenKey);
        }

        public void setChildren(ArrayList<String> children) {
            this.children = children;
            this.readerWriter.setValue(childrenKey, children);
        }

        public void addChild(String childName) {
            this.children.add(childName);
            this.readerWriter.addToArray(childrenKey, childName);
        }
    }
}
