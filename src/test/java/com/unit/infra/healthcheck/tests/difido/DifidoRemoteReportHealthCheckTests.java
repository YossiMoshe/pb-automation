package com.unit.infra.healthcheck.tests.difido;

import com.infra.report.difido.DifidoRemoteReport;
import com.infra.report.difido.entities.results.ReportData;
import com.unit.infra.healthcheck.tests.BaseHealthCheckTests;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class DifidoRemoteReportHealthCheckTests extends BaseHealthCheckTests {

    @Test(description = "Verifies API call to Difido server to get all reports.")
    public void getReportsFromServerTest() {
        DifidoRemoteReport remoteReport = PF(DifidoRemoteReport.class);
        ArrayList<ReportData> allReports = remoteReport.getAllReports();
        assertNotNull(allReports);
    }

    @Test(description = "Verifies report link exist based on unique description.")
    public void getReportLinkTest() {
        DifidoRemoteReport remoteReport = PF(DifidoRemoteReport.class);
        String link = remoteReport.getReportLinkThatContainsDescriptionString("");
        assertTrue(link.contains("automation-test-reporter"));
    }
}
