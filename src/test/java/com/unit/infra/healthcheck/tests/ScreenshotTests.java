package com.unit.infra.healthcheck.tests;

import com.infra.BaseTest;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertTrue;

public class ScreenshotTests extends BaseTest {

    @Test()
    public void screenShotTakenLocal() {
        String screenshotName = "test-screenshot-1";
        openAsProfessional("https://www.google.com");
        Selenide.screenshot(screenshotName);
        File screenshot = Screenshots.getLastScreenshot();
        report.step("Adding screenshot...");
        report.addImage(screenshot, "test-screenshot");
        assertTrue(screenShotExits(screenshotName));
    }

    private boolean screenShotExits(String screenshotName) {
        String screenShotFilePath = "./build/reports/tests/";
        String screenShotFileSuffix = ".png";
        return new File(screenShotFilePath + screenshotName + screenShotFileSuffix).exists();
    }
}
