package com.unit.infra.healthcheck.tests;

import com.infra.general_utils.properties_reader.AbstractPropertiesReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

public class PropertyReaderHealthCheckTest extends BaseHealthCheckTests {
    private TestPropertiesReader propertiesReader = new TestPropertiesReader();

    @Test(description = "check upload file config")
    public void uploadFileConfigTest(){
        Properties properties = propertiesReader.getProperties();
        Assert.assertTrue(properties.size() > 0 , "couldn't upload config file");
    }

    @Test(description = "check if config values are available ")
    public void getConfigValueTest(){
        String testResult = propertiesReader.getProperty("test1");
        Assert.assertEquals(testResult, "pass", "failed to get config value");
    }

    @Test(description = "check if config values can be set ")
    public void setConfigValueTest(){
        String newValue = "new value";
        propertiesReader.setProperty("test2" , newValue);
        String testResult = propertiesReader.getProperty("test2");
        Assert.assertEquals(testResult, newValue, "failed to set config value");
    }

    private class TestPropertiesReader extends AbstractPropertiesReader {
        private static final String propertiesFilePath = "healthcheck-resources/config.properties";

        TestPropertiesReader() {
            super(propertiesFilePath);
        }

        @Override
        public Properties getProperties() {
            return super.getProperties();
        }

        @Override
        protected String getProperty(String propertyKey) {
            return super.getProperty(propertyKey);
        }

        @Override
        protected void setProperty(String key, String newValue) {
            super.setProperty(key, newValue);
        }
    }
}
