package com.bookit.step_definitions;

import com.bookit.utilities.DBUtility;
import com.bookit.utilities.Driver;
import com.bookit.utilities.Environment;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

@Before("@db")
    public void dbSetup(){
    DBUtility.createDBConnection(Environment.DB_HOST,Environment.DB_USERNAME,Environment.DB_PASSWORD);
}


@After("@db")
    public void dbTearDown(){
    DBUtility.destroy();
}


    @After("@ui")
    public void uiTearDown() {
        Driver.closeDriver();
    }


    @Before("@ui")
    public void uiSetup(){
        Driver.getDriver().manage().window().maximize();
    }











}
