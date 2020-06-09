package com.unit.infra.healthcheck.tests;

import com.infra.BaseTest;
import org.testng.annotations.Test;

public class SelenideHealthCheck extends BaseTest{

    @Test
    public void testOpenBrowser(){
        openAsProfessional("http://google.com");
    }
}
