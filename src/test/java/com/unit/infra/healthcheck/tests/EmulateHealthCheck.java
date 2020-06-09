package com.unit.infra.healthcheck.tests;

import com.helpers.entities.enums.PlayerScope;
import com.helpers.entities.stream.playerConfiguration.PlayerConfigurationEntity;
import com.helpers.ui.helpers.browser.BrowserActions;
import com.helpers.ui.helpers.section_objects.third_party.players.stream_player_section.StreamPlayerSection;
import com.infra.BaseTest;
import com.infra.config.data_providers.StreamConfigurationsData;
import com.infra.general_utils.RandomUtil;
import com.infra.test_strategy.annotations.Configuration;
import com.infra.test_strategy.enums.Groups;
import org.openqa.selenium.PageLoadStrategy;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.browser;
import static com.helpers.api.enums.PlaybackModeType.AUTO_PLAY;
import static com.helpers.api.helpers.stream.PlayerConfigurationHelper.createPlayerConfigurationEntity;
import static com.infra.config.GlobalEnvProperties.ENVIRONMENT;
import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;
import static java.text.MessageFormat.format;

public class EmulateHealthCheck extends BaseTest {
    protected BrowserActions browserActions = PF(BrowserActions.class);


    // TODO: This test should be fixed to work with PageLoadStrategy.NONE
    @Test(groups = Groups.MOBILE_ALL)
    @Configuration(pageLoadStrategy = PageLoadStrategy.NORMAL)
    public void testOpenEmulateBrowser() {
        logger.info("browser = " + browser);
        PlayerConfigurationEntity playerConfigurationEntity = createPlayerConfigurationEntityForMobile(AUTO_PLAY.getType());
        StreamPlayerSection stream = openAsProfessional(getEmbeddedPageUrl(playerConfigurationEntity.get_id()), StreamPlayerSection.class);

        stream.waitUntilPlayerAppears();

        Assert.assertTrue(stream.waitIsVideoPausedByScope(PlayerScope.ANY), "playbackModeMobile = " + AUTO_PLAY.getType() + " but video is not playing");
    }

    private PlayerConfigurationEntity createPlayerConfigurationEntityForMobile(String playBackMode) {
        PlayerConfigurationEntity playerConfigurationToSendEntity = new PlayerConfigurationEntity();
        playerConfigurationToSendEntity.setPlayerName(RandomUtil.getUUID("Automation"));
        playerConfigurationToSendEntity.setMonetization(false);
        playerConfigurationToSendEntity.setMuteMobile(true);
        playerConfigurationToSendEntity.setPlaybackModeMobile(playBackMode);
        return createPlayerConfigurationEntity(playerConfigurationToSendEntity);
    }

    private String getEmbeddedPageUrl(String playerId) {
        StreamConfigurationsData streamConfigurationsData = new StreamConfigurationsData();
        String STREAM_PLAYER_CONFIG = streamConfigurationsData.getEmbeddedStreamPlayerPageUrl();

        String env = "stg";
        if (ENVIRONMENT.equals("prd")) {
            env = "prd";
        }
        return format(STREAM_PLAYER_CONFIG, env, playerId);
    }
}
