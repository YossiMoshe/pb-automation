package com.unit.infra.healthcheck.tests;

import com.infra.config.data_providers.DbData;
import com.infra.general_utils.db_manager.DbConnection;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import static com.infra.general_utils.prototype_factory.PrototypeFactory.PF;

public class DbHealthCheckTests {

    DbData dbData = PF(DbData.class);
    DbConnection dbConnection;

    @AfterClass(alwaysRun = true)
    public void closeConnection(){
        dbConnection.closeDBConnection();
    }

    @Test
    public void dbConnectionTest(){
        dbConnection = new DbConnection(dbData.getStgCoreDbConnection());
        Assert.assertNotNull(dbConnection.createDBConnection());
    }
}
