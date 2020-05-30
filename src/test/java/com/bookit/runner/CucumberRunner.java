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
        plugin = {
                "json:target\\cucmber.json"

        }



)
public class CucumberRunner {





}
