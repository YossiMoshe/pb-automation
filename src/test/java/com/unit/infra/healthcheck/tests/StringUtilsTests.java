package com.unit.infra.healthcheck.tests;

import com.infra.general_utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class StringUtilsTests extends BaseHealthCheckTests {
    @Test()
    public void testingAddParamToURL() {
        String url = "http://alpha.playbuzz.com/?A=1";
        String key="key";
        String value="value";
        String resUrl = StringUtils.addQueryParamToURL(url,key,value);
        Assert.assertEquals(resUrl,url+"&"+key+"="+value);
    }


    @Test()
    public void testingAddParamsToURL() {
        String url = "http://alpha.playbuzz.com/";
        HashMap<String, String> params = new HashMap<>();
        params.put("key1", "val1");
        params.put("key2", "val2");
        params.put("key3", "val3");

        String resUurl = StringUtils.addQueryParamsToURL(url, params);
        Assert.assertEquals(resUurl,url+"?key1=val1&key2=val2&key3=val3");
    }
}
