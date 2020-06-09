package com.unit.infra.healthcheck.tests.test_strategy;

import com.helpers.ui.helpers.section_objects.story_viewer_sections.BaseViewerSection;
import com.infra.BaseTest;
import com.infra.config.data_providers.StoryData;
import com.infra.test_strategy.TestRetryAnalyzer;
import com.infra.test_strategy.annotations.Configuration;
import org.openqa.selenium.PageLoadStrategy;
import org.testng.annotations.Test;

import java.text.MessageFormat;

import static com.infra.config.GlobalEnvProperties.BASIC_URL;
import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;
import static org.testng.Assert.*;

public class PageLoadStrategyTests extends BaseTest {

    private StoryData data = PF(StoryData.class);
    private final String itemURL = MessageFormat.format("https://{0}{1}", BASIC_URL, data.getOnoStory());

    @Test(description = "When no Configuration annotation is present, the default page load strategy should be 'NONE'.")
    public void pageLoadStrategyDefaultExistsTest() {
        assertEquals(context().getPageLoadStrategy(), PageLoadStrategy.NONE);
    }

    @Test(description = "When page load strategy is 'NONE', there should be a 'wait' on elements.")
    @Configuration(pageLoadStrategy = PageLoadStrategy.NONE)
    public void pageLoadStrategyNoneTest() {
        BaseViewerSection story = openAsProfessional(itemURL, BaseViewerSection.class);
        assertNotEquals(story.getParticlesNumber(), 0);
    }

    @Test(
        description = "Verifies that the pageLoadStrategy is set to normal after first retry.",
        retryAnalyzer = TestRetryAnalyzer.class
    )
    public void normalPageLoadStrategyOnRetryTest() {
        if (context().getRetryCounter() == 1) {
            fail();
        }

        assertEquals(context().getPageLoadStrategy(), PageLoadStrategy.NORMAL);
    }
}
