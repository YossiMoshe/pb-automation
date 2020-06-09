package com.unit.helpers.healthcheck.tests;

import com.helpers.api.helpers.ono.adunits.entities.adunits.AdUnit;
import com.helpers.api.helpers.ono.adunits.configurations.PBAdUnitLayout;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;
import static org.testng.Assert.*;

public class PBAdUnitLayoutTests {

    @Test(description = "Verifies the ability to read a configuration from the file")
    public void getConfigurationTest() {
        PBAdUnitLayout layout = PF(PBAdUnitLayout.class);
        ArrayList<AdUnit> adUnits = layout.getStoryDesktopOrganic();
        assertFalse(adUnits.isEmpty());
        assertEquals(adUnits.get(0).getPlacement(), "ad-top-1");
    }
}
