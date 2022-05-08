package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions (features="/home/appusharishgmai/eclipse-workspace-new/phase-three-project/src/test/resources/features/APIAutomation.feature",
glue= {"com.steps"})
public class TestRunner {
	


}
