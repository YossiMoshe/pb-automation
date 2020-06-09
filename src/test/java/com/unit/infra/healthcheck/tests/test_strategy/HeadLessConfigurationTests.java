package com.unit.infra.healthcheck.tests.test_strategy;

import com.infra.BaseTest;
import com.infra.config.GlobalEnvProperties;
import com.infra.test_strategy.TestContext;
import com.infra.test_strategy.annotations.Configuration;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HeadLessConfigurationTests extends BaseTest {

    @Test(description = "When not local headless should be true on default, and false when local")
    public void isHeadlessTest() {
        TestContext context = contextManager.getCurrentTestContext();
        assertEquals(context.isHeadless(), GlobalEnvProperties.HEADLESS_DEFAULT_MODE);
    }

    @Test(description = "When specified, should always be headless")
    @Configuration(isHeadless = true)
    public void isHeadlessTrueTest() {
        TestContext context = contextManager.getCurrentTestContext();
        assertTrue(context.isHeadless(), "When specified, headless should always be true");
    }

    @Test(description = "When specified, should always not be headless")
    @Configuration(isHeadless = false)
    public void isHeadlessFalseTest() {
        TestContext context = contextManager.getCurrentTestContext();
        assertFalse(context.isHeadless(), "When specified, headless should always be false");
    }
}
