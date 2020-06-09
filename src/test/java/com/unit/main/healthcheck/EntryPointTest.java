package com.unit.main.healthcheck;

import com.infra.config.GlobalEnvProperties;
import com.unit.infra.healthcheck.tests.BaseHealthCheckTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EntryPointTest extends BaseHealthCheckTests {

    @Test
    public void justASimpleTest1() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testGlobalEnv() {
        String env = System.getProperty("env");
        env = (env != null) ? env : "stg";
        Assert.assertEquals(GlobalEnvProperties.ENVIRONMENT, env);
    }

    @Test
    public void testGlobalIsLocal() {
        Assert.assertTrue(GlobalEnvProperties.IS_LOCAL,"GlobalEnvProperties.IS_LOCAL default value is not true");
    }
}
