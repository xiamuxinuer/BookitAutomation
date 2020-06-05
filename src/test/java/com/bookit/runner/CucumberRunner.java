package com.bookit.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;




@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com\\bookit\\step_definitions",
        features = "src/test/resources/features",
        tags = "@api",
        dryRun = false,
        plugin = {"html:target\\default-report",
                "json:target\\cucumber.json",
                "rerun:target\\rerun_loginTest.txt"}


)
public class CucumberRunner {





}
