package com.bookit.step_definitions;

import com.bookit.utilities.DBUtility;
import com.bookit.utilities.Environment;
import org.junit.After;
import org.junit.Before;

public class Hooks {

@Before
    public void dbSetup(){
    DBUtility.createDBConnection(Environment.DB_HOST,Environment.DB_USERNAME,Environment.DB_PASSWORD);
}


@After
    public void dbTearDown(){
    DBUtility.destroy();
}





}
