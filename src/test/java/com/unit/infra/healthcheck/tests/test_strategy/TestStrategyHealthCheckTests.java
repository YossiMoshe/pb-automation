package com.unit.infra.healthcheck.tests.test_strategy;

import com.infra.BaseTest;

import static com.codeborne.selenide.Selenide.$;

/**
 * This class is used only for local healthcheck tests to test different strategies.
 * !!!!!!!!!!!!!!!!! KEEP THIS CLASS COMMENTED OUT, otherwise it will fail the JAR build on Jenkins!!!!!!!!!!!!!!!!!
 */
public class TestStrategyHealthCheckTests extends BaseTest {
//
//    @BeforeClass(alwaysRun = true)
//    public void beforeClass() {
//        logger.info("Opening before class!");
//        browserManager.open("http://google.com");
//    }
//
////    @BeforeMethod
////    public void beforeMethod() {
////        browserManager.open("http://playbuzz.com");
////    }
//
//    @AfterMethod
//    public void afterMethod() {
//        open("http://google.com");
//    }
//
//    @Test(description = "Tests the default strategy.")
//    public void defaultStrategyTest() {
//        browserManager.open("https://alpha.playbuzz.com/eranai10");
//        verifyBrowserType("Chrome");
//    }
//
//    @Test(description = "Tests the strategy creation for the 'browser.all' group.", groups = { Groups.BROWSER_ALL })
//    public void browserAllStrategyTest() {
//        browserManager.open("http://ynet.co.il");
////        verifyBrowserType("Chrome");
//    }
//
//    @Test(description = "Tests the strategy creation for the 'mobile.all' group.", groups = Groups.MOBILE_ALL)
//    public void mobileBrowserAllStrategyTest() {
//        browserManager.open("http://google.com");
////        verifyBrowserType("Chrome");
//    }
//
//    @Test(description = "Tests the browser strategy.", groups = Groups.BROWSER_CHROME)
//    public void chromeBrowserStrategyTest() {
//        browserManager.open("http://google.com");
//        verifyBrowserType("Chrome");
//
//        browserManager.close();
//
//        browserManager.open("http://google.com");
//        verifyBrowserType("Chrome");
//    }
//
//    @Test(description = "Tests the browser strategy.", groups = Groups.BROWSER_FIREFOX)
//    public void firefoxBrowserStrategyTest() {
//        browserManager.open("http://google.com");
//        verifyBrowserType("Firefox");
//
//        browserManager.close();
//
//        browserManager.open("http://google.com");
//        verifyBrowserType("Firefox");
//    }
//
//    @Test(description = "Tests the specific browser strategy.", groups = Groups.BROWSER_SAFARI)
//    public void safariBrowserStrategyTest() {
//        browserManager.open("http://google.com");
//        verifyBrowserType("Safari");
//
//        browserManager.close();
//
//        browserManager.open("http://google.com");
//        verifyBrowserType("Safari");
//    }
//
//    @Test(description = "Tests the specific browser strategy.", groups = Groups.BROWSER_IE)
//    public void ieBrowserStrategyTest() {
//        browserManager.open("http://google.com");
//        verifyBrowserType("Explorer");
//    }
//
//    @Test(description = "Tests the specific browser strategy.", groups = Groups.BROWSER_EDGE)
//    public void edgeBrowserStrategyTest() {
//        browserManager.open("http://google.com");
//        verifyBrowserType("Edge");
//    }
//
//    @Test(description = "Tests the specific mobile browser strategy.", groups = Groups.MOBILE_NEXUS_7)
//    public void nexusMobileBrowserStrategyTest() {
//        browserManager.open("http://google.com");
//        verifyBrowserType("Chrome");
//    }
//
//    @Test(description = "Tests the specific mobile browser strategy.", groups = Groups.MOBILE_IPAD)
//    public void ipadMobileBrowserStrategyTest() {
//        browserManager.open("http://google.com");
//        verifyBrowserType("Safari");
//    }
//
//    @Test(
//            description = "Tests the strategy creation with performance logging capabilities.",
//            groups = Groups.CAPABILITY_PERFORMANCE_LOG
//    )
//    public void performanceLoggingStrategyTest() {
//        PerformanceLoggingHelper helper = PF(PerformanceLoggingHelper.class);
//        browserManager.open("http://google.com");
//        List<JsonReaderWriter> collectedTraffic = helper.getCollectedTraffic();
//        assertFalse(collectedTraffic.isEmpty());
//        verifyBrowserType("Chrome");
//    }
//
//    @Test(
//            description = "Tests the strategy creation for mobile with performance logging capabilities.",
//            groups = { Groups.MOBILE_IPHONE_6, Groups.CAPABILITY_PERFORMANCE_LOG }
//    )
//    public void mobilePerformanceLoggingStrategyTest() {
//        PerformanceLoggingHelper helper = PF(PerformanceLoggingHelper.class);
//        browserManager.open("http://google.com");
//        List<JsonReaderWriter> collectedTraffic = helper.getCollectedTraffic();
//        assertFalse(collectedTraffic.isEmpty());
//        verifyBrowserType("Safari");
//    }
//
//    @Test(groups = { Groups.CAPABILITY_PROXY })
//    public void proxyTest() {
//        PBProxy proxy = browserManager.getProxy();
//        proxy.start();
//        browserManager.open("http://google.com");
//        proxy.stop();
//        Har har = proxy.getHar();
//        List<HarEntry> entries = har.getLog().getEntries();
//        assertNotNull(har.getLog());
//        assertFalse(entries.isEmpty());
//        assertTrue(entries.stream().anyMatch(e -> e.getRequest().getUrl().contains("google")));
//    }
//
//    @Test(groups = { Groups.MOBILE_PIXEL_2_XL, Groups.CAPABILITY_PROXY })
//    public void proxyMobileTest() {
//        PBProxy proxy = browserManager.getProxy();
//        proxy.start();
//        browserManager.open("http://google.com");
//        proxy.stop();
//        Har har = proxy.getHar();
//        List<HarEntry> entries = har.getLog().getEntries();
//        assertNotNull(har.getLog());
//        assertFalse(entries.isEmpty());
//        assertTrue(entries.stream().anyMatch(e -> e.getRequest().getUrl().contains("google")));
//    }
//
//    @Test
//    public void noProxyTest() {
//        PBProxy proxy = browserManager.getProxy();
//        assertNull(proxy);
//    }
//
//    @Test(description = "A test marked as a known bug - should not run.", groups = Groups.BUG_PREFIX + "PB_431432")
//    public void knownBugTest() {
//        fail();
//    }
//
//    private void verifyBrowserType(String type) {
//        type = type.toUpperCase();
//        browserManager.open("https://smallseotools.com/what-is-my-browser/");
////        $(By.id("browser-name")).waitUntil(Condition.exist, 10000);
//        Selenide.sleep(5000);
//        String innerText = $(By.id("browser-name")).innerText();
//        assertTrue(innerText.toUpperCase().contains(type), "expected " + type + " but found " + innerText);
//    }
}
