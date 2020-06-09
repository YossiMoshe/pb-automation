package com.unit.infra.healthcheck.tests.data_providers;

import com.infra.config.data_providers.MediaData;
import com.infra.config.data_providers.StoryData;
import com.infra.config.data_providers.UserData;
import com.unit.infra.healthcheck.tests.BaseHealthCheckTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;

public class DataProvidersHealthCheckTests extends BaseHealthCheckTests {

    @Test
    public void userDataMainUsernameTest() {
        UserData data = PF(UserData.class);
        Assert.assertEquals(data.getAdminUserName(), "alexpreview39@gmail.com");
    }

    @Test
    public void mediaDataTest() {
        MediaData data = PF(MediaData.class);
        Assert.assertEquals(data.getImageLinkJPG(), "https://image.ibb.co/eBXcFv/2806223.jpg");
    }

    @Test
    public void storyDataTest() {
        StoryData data = PF(StoryData.class);
        Assert.assertEquals(data.getPbItemsStoriesStoryWith2Videos(), "/item/64728a63-969d-40e7-98f5-9d86958e419d");
    }
}
