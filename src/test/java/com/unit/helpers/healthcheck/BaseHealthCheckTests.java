package com.unit.helpers.healthcheck;

import com.helpers.network.Bmp;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseHealthCheckTests {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void startCapturingTraffic(){
        Bmp.proxyServer.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
        Bmp.proxyServer.newHar("11");
    }

    protected Har collectTraffic(){
        return Bmp.proxyServer.getHar();
    }

}
