package com.unit.infra.healthcheck.tests.json_managing;

import com.infra.config.data_providers.AbstractJsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class JsonDataProviderHealthCheckTests {

    @Test
    public void getValue() {
        TestJsonDataProvider tjcr = new TestJsonDataProvider();
        String value = tjcr.getValue("key-01");
        Assert.assertEquals(value, "value-01");
    }

    @Test
    public void getDefaultValue() {
        TestJsonDataProvider tjcr = new TestJsonDataProvider();
        String value = tjcr.getValue("not-exists");
        Assert.assertEquals(value, "default-value");
    }

    @Test
    public void getArray() {
        TestJsonDataProvider tjcr = new TestJsonDataProvider();
        ArrayList<String> array = tjcr.getArray("array-01");
        Assert.assertEquals(array.get(2), "three");
    }

    @Test
    public void getDefaultArray() {
        TestJsonDataProvider tjcr = new TestJsonDataProvider();
        ArrayList<String> array = tjcr.getArray("non-existing");
        Assert.assertEquals(array.get(0), "default-value");
    }

    @Test
    public void getNestedDefault() {
        TestJsonDataProvider tjcr = new TestJsonDataProvider();
        String value = tjcr.getValue("nested.non-existing");
        Assert.assertEquals(value, "default-value");
    }

    class TestJsonDataProvider extends AbstractJsonDataProvider {
        public TestJsonDataProvider() {
            super("healthcheck-resources/json-config-reader-test.json");
        }
    }
}