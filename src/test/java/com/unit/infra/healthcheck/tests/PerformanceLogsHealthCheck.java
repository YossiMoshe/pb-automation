package com.unit.infra.healthcheck.tests;

import com.infra.BaseTest;
import com.helpers.traffic_logs.TrafficLogsCollector;
import com.infra.test_strategy.enums.Groups;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;
import static org.awaitility.Awaitility.with;

public class PerformanceLogsHealthCheck extends BaseTest {

    private static final String start_url = "https://www.playbuzz.com/";
    TrafficLogsCollector collector = PF(TrafficLogsCollector.class);

    @Test(groups = {Groups.CAPABILITY_PERFORMANCE_LOG})
    public void performanceLogTest() {
        openAsProfessional(start_url);
        with()
                .alias("no trafficLogs was created")
                .pollInSameThread()
                .await()
                .atMost(timeouts.small(), TimeUnit.SECONDS).until(() -> collector.getTrafficLogs().size()  > 0);
        Assert.assertTrue(collector.getTrafficLogs().size()  > 0);
    }
}
