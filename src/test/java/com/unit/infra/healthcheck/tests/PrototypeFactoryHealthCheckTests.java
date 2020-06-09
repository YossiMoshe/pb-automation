package com.unit.infra.healthcheck.tests;

import com.infra.general_utils.prototype_factory.PrototypeImplementationExample;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;

public class PrototypeFactoryHealthCheckTests {

    @Test
    public void getPrototypeFromFactoryTest() {
        PrototypeImplementationExample ch = PF(PrototypeImplementationExample.class);
        Assert.assertSame("Did something", ch.doSomething());
    }
}
