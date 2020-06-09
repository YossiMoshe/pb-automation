package com.unit.infra.healthcheck.tests.proxy;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.infra.browser.CookieManager;
import com.infra.config.GlobalEnvProperties;
import com.infra.proxy.PBProxy;
import com.infra.test_strategy.enums.Groups;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;
import static org.testng.Assert.*;

public class BMPStandaloneTests {
    private CookieManager cookieManager = PF(CookieManager.class);

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        WebDriverRunner.closeWebDriver();
    }

    @Test(groups = Groups.CAPABILITY_PROXY)
    public void simpleTest() {
        PBProxy proxy = PF(PBProxy.class);
        WebDriver driver = getWebDriver(proxy);
        WebDriverRunner.setWebDriver(driver);
        Selenide.open("https://www.google.com");
        cookieManager.addExCoAutomationCookie();
        cookieManager.addExCoBotCookie();
        proxy.start();
        proxy.waitForRequest(20, "google");
        assertTrue(proxy.wasRequestToURLSent("google"));
    }

    private WebDriver getWebDriver(PBProxy proxy) {
        WebDriver driver = null;
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setProxy(proxy.getSeleniumProxy());
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        if (GlobalEnvProperties.isLocalHub()) {
            try {
                options.setAcceptInsecureCerts(true);
                driver = new RemoteWebDriver(new URL(GlobalEnvProperties.SELENIUM_REMOTE_PATH), options);
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.MINUTES);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else {
            driver = new ChromeDriver(options);
        }

        return driver;
    }
}
