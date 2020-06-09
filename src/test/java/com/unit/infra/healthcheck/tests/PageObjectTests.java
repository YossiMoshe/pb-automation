package com.unit.infra.healthcheck.tests;

import com.helpers.ui.helpers.section_objects.story_viewer_sections.BaseViewerSection;
import com.infra.BaseTest;
import com.infra.config.data_providers.StoryData;
import com.infra.test_strategy.enums.Groups;
import org.testng.annotations.Test;

import java.text.MessageFormat;

import static com.infra.config.GlobalEnvProperties.BASIC_URL;
import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;
import static org.testng.Assert.*;

public class PageObjectTests extends BaseTest {

    private StoryData data = PF(StoryData.class);
    private final String itemURL = MessageFormat.format("https://{0}{1}", BASIC_URL, data.getOnoStory());

    @Test(description = "Should return the title of the page.")
    public void getTitleTest() {
        BaseViewerSection story = openAsProfessional(itemURL, BaseViewerSection.class);
        assertNotNull(story.getTitle(), "The title does not exist.");
        assertNotEquals(story.getTitle(), "", "The title is an empty string.");
    }

    @Test(description = "Should return the expected UserAgent", groups = Groups.BROWSER_CHROME)
    public void getUserAgentTest() {
        BaseViewerSection story = openAsProfessional(itemURL, BaseViewerSection.class);
        assertNotNull(story.getUserAgent(), "The UserAgent could not be retrieved.");
    }
}
