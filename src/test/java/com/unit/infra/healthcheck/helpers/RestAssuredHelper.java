package com.unit.infra.healthcheck.helpers;

import com.helpers.api.clients.BaseAPIClient;
import com.infra.api.rest_assured.RestAssuredWrapper;
import com.infra.general_utils.prototype_factory.Prototype;
import com.infra.logger.PBLogger;
import com.jayway.restassured.response.Response;

import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;

public class RestAssuredHelper extends BaseAPIClient implements Prototype<RestAssuredHelper> {
    static { Prototype.register(RestAssuredHelper.class); }

    public Response createHttpCall(String url){
        return restAssured.
                            ra().
                            get(url);
    }

    public Response createHttpsCall(String url){
        return restAssured.
                            ra().
                            get(url);
    }

    public Boolean isResponseInRange(Response response , int low){
        logger.info("isResponseInRange started");
        int statusCode = response.statusCode();
        if(statusCode< low + 100 && statusCode >= low){
            logger.info("isResponseInRange return code = true");
            return true;
        }
        else{
            logger.info("isResponseInRange return code = false");
            return false;
        }
    }
}
