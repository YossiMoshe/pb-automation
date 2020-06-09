package com.unit.infra.healthcheck.tests.proxy;

import com.infra.BaseTest;
import com.infra.config.data_providers.StoryData;
import com.infra.proxy.PBProxy;
import com.infra.test_strategy.annotations.Configuration;
import com.infra.test_strategy.enums.Groups;
import org.openqa.selenium.PageLoadStrategy;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import static com.infra.config.GlobalEnvProperties.BASIC_URL;
import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;
import static org.testng.Assert.assertTrue;

public class BMPTests extends BaseTest {

    private StoryData data = PF(StoryData.class);
    private String url = MessageFormat.format("http://{0}{1}", BASIC_URL, data.getOnoStory());

    @Test(groups = Groups.CAPABILITY_PROXY)
    @Configuration(pageLoadStrategy = PageLoadStrategy.NONE)
    public void simpleProxyTest() {
        PBProxy proxy = browserManager.getProxy();
        proxy.start();
        openAsProfessional(url);
        proxy.waitForRequest(1, TimeUnit.MINUTES, "connatix");
        assertTrue(proxy.wasRequestToURLSent("connatix"), "No Connatix request sent.");
    }
}
