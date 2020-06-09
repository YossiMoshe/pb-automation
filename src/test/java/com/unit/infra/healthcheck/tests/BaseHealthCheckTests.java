package com.unit.infra.healthcheck.tests;

import com.infra.logger.PBLogger;

import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;

public class BaseHealthCheckTests {
    protected PBLogger logger = PF(PBLogger.class, this.getClass());
}
