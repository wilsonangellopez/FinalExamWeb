package com.automation.training.runners;
//
//
//import cucumber.api.CucumberOptions;
//

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions( 
features= "src/main/java/com/automation/training/features/FeaturesTestOne.feature",
glue = {"com.automation"},
tags = {"@TestOne"},
plugin = {
"pretty",
"html:target/cucumber-reports/cucumber-pretty",
"json:target/cucumber-reports/CucumberTestReport.json"
})
//AbstractTestNGCucumberTests
public class RunnerTestOne  extends AbstractTestNGCucumberTests{

}
