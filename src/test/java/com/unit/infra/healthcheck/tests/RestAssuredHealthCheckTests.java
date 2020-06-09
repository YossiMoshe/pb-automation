package com.unit.infra.healthcheck.tests;

import com.unit.infra.healthcheck.helpers.RestAssuredHelper;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.unit.infra.healthcheck.tests.enums.HealthCheckUrlEnums.*;


public class RestAssuredHealthCheckTests extends BaseHealthCheckTests {

    RestAssuredHelper restAssuredHelper = new RestAssuredHelper();

    @Test(description = "check connectivity to a non playbuzz end-point with https")
    public void restCallToA_NonPbEndpointHttpTest(){
        logger.info("restCallToA_NonPbEndpointHttpTest started");
        Response httpCall = restAssuredHelper.createHttpsCall(NON_PBZ_URL.val());
        Boolean responseInRange = restAssuredHelper.isResponseInRange(httpCall, 200);
        Assert.assertTrue(responseInRange , "http call to a non PlayBuzz end-point was failed.status code =" + httpCall.statusCode());
    }

    @Test(description = "check connectivity to a playbuzz end-point with https")
    public void restCallToA_PbEndpointTest(){
        Response httpCall = restAssuredHelper.createHttpsCall(PBZ_STG_URL.val());
        Boolean responseInRange = restAssuredHelper.isResponseInRange(httpCall, 200);
        Assert.assertTrue(responseInRange , "https call to a PlayBuzz end-point was failed.status code =" + httpCall.statusCode());
    }

}
