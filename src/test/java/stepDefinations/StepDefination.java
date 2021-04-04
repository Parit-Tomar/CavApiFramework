package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination {
	
	
	
	@Given("^Add details for custom alerts$")
    public void add_details_for_custom_alerts() throws Throwable {
		RestAssured.baseURI= "https://10.10.30.71:443";
		String response=given().relaxedHTTPSValidation().log().all().header("Content-Type", "application/json")
				.body("{\r\n" + 
						"    \"trNum\": \"11165\",\r\n" + 
						"    \"alertMessage\": \"Custom alert\\n executed manually\",\r\n" + 
						"    \"alertSeverity\": \"1\",\r\n" + 
						"    \"thresholdValue\": \"-1.0\",\r\n" + 
						"    \"alertValue\": \"13.0\",\r\n" + 
						"    \"alertValueStr\": \"Condition_1: AV-11\\nCondition_2: AV-13\",\r\n" + 
						"    \"prevAlertValue\": \"-1.0\",\r\n" + 
						"    \"prevSeverity\": \"1\",\r\n" + 
						"    \"state\": \"1\",\r\n" + 
						"    \"status\": \"1\",\r\n" + 
						"    \"alertId\": \"1121\",\r\n" + 
						"    \"ruleName\": \"Parit_Custom_Postman\",\r\n" + 
						"    \"tier\": \"AppTier\",\r\n" + 
						"    \"server\": \"QAServer40\",\r\n" + 
						"    \"instance\": \"-\",\r\n" + 
						"    \"ruleDesc\": \"-\",\r\n" + 
						"    \"vectorName\": \"AppTier>QAServer40\",\r\n" + 
						"    \"threshType\": \"1\",\r\n" + 
						"    \"alertType\": \"0\",\r\n" + 
						"    \"eventType\": \"1\",\r\n" + 
						"    \"minorThreshold\": \"2.0\",\r\n" + 
						"    \"majorThreshold\": \"5.0\",\r\n" + 
						"    \"criticalThreshold\": \"8.0\",\r\n" + 
						"    \"timeWindow\": \"60\",\r\n" + 
						"    \"numSamples\": \"1\",\r\n" + 
						"    \"timeZone\": \"Asia/Kolkata\",\r\n" + 
						"    \"topologyName\": \"ContinuousMonitoring\",\r\n" + 
						"    \"policyName\": \"ParitRest\",\r\n" + 
						"    \"sourceProtocol\": \"https\",\r\n" + 
						"    \"sourceIP\": \"172.18.0.1\",\r\n" + 
						"    \"sourcePort\": \"443\",\r\n" + 
						"    \"conditionList\": [\r\n" + 
						"        {\r\n" + 
						"            \"name\": \"Condition_1\",\r\n" + 
						"            \"conditionId\": 0,\r\n" + 
						"            \"baselineName\": \"NA\",\r\n" + 
						"            \"operator\": 8,\r\n" + 
						"            \"groupInfo\": {\r\n" + 
						"                \"id\": 10108,\r\n" + 
						"                \"name\": \"ba\"\r\n" + 
						"            },\r\n" + 
						"            \"graphInfo\": {\r\n" + 
						"                \"id\": 18,\r\n" + 
						"                \"name\": \"\"\r\n" + 
						"            },\r\n" + 
						"            \"conditionCommonVectorMap\": {\r\n" + 
						"                \"AppTier>QAServer40\": {\r\n" + 
						"                    \"currentValue\": 25.035,\r\n" + 
						"                    \"prevValue\": 0,\r\n" + 
						"                    \"totalVector\": 1,\r\n" + 
						"                    \"satisfied\": true,\r\n" + 
						"                    \"baselineValue\": 2,\r\n" + 
						"                    \"perVectorMap\": {\r\n" + 
						"                        \"AppTier>QAServer40\": {\r\n" + 
						"                            \"loadValue\": -1,\r\n" + 
						"                            \"satisfied\": true,\r\n" + 
						"                            \"currentValue\": 22.03,\r\n" + 
						"                            \"thresholdPerVectorMap\": {}\r\n" + 
						"                        }\r\n" + 
						"                    }\r\n" + 
						"                }\r\n" + 
						"            },\r\n" + 
						"            \"metaData\": \"Tier>Server\"\r\n" + 
						"        }\r\n" + 
						"    ],\r\n" + 
						"    \"ruleExpression\": \"Condition_1\",\r\n" + 
						"    \"ruleLevel\": \"1\",\r\n" + 
						"    \"severityLevel\": \"1\",\r\n" + 
						"    \"derivedExpression\": \"-\",\r\n" + 
						"    \"eventState\": \"1\"\r\n" + 
						"}").when().post("/DashboardServer/web/AlertDataService/genCustomAlert")
				.then().assertThat().log().all().statusCode(200).body("statusCode", equalTo(201)).body("ruleName", equalTo("Parit_Custom_Postman"))
				.extract().response().asString();
				
				System.out.println(response);
				JsonPath js=new JsonPath(response); //for parsing Json
				String alertId=js.getString("alertID");
				
				System.out.println(alertId);
				
				//Update Place 
				
				String newalertVal = "22.2";
				
				given().log().all().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.body("{\r\n" + 
						"    \"trNum\": \"11165\",\r\n" + 
						"    \"alertMessage\": \"Custom alert\\n executed manually\",\r\n" + 
						"    \"alertSeverity\": \"2\",\r\n" + 
						"    \"thresholdValue\": \"-1.0\",\r\n" + 
						"    \"alertValue\": \""+newalertVal+"\",\r\n" + 
						"    \"alertValueStr\": \"Condition_1: AV-11\\nCondition_2: AV-13\",\r\n" + 
						"    \"prevAlertValue\": \"-1.0\",\r\n" + 
						"    \"prevSeverity\": \"1\",\r\n" + 
						"    \"state\": \"1\",\r\n" + 
						"    \"status\": \"4\",\r\n" + 
						"    \"alertId\": \"1121\",\r\n" + 
						"    \"ruleName\": \"Parit_Custom_Postman\",\r\n" + 
						"    \"tier\": \"AppTier\",\r\n" + 
						"    \"server\": \"QAServer40\",\r\n" + 
						"    \"instance\": \"-\",\r\n" + 
						"    \"ruleDesc\": \"-\",\r\n" + 
						"    \"vectorName\": \"AppTier>QAServer40\",\r\n" + 
						"    \"threshType\": \"1\",\r\n" + 
						"    \"alertType\": \"0\",\r\n" + 
						"    \"eventType\": \"1\",\r\n" + 
						"    \"minorThreshold\": \"2.0\",\r\n" + 
						"    \"majorThreshold\": \"5.0\",\r\n" + 
						"    \"criticalThreshold\": \"8.0\",\r\n" + 
						"    \"timeWindow\": \"60\",\r\n" + 
						"    \"numSamples\": \"1\",\r\n" + 
						"    \"timeZone\": \"Asia/Kolkata\",\r\n" + 
						"    \"topologyName\": \"ContinuousMonitoring\",\r\n" + 
						"    \"policyName\": \"ParitRest\",\r\n" + 
						"    \"sourceProtocol\": \"https\",\r\n" + 
						"    \"sourceIP\": \"172.18.0.1\",\r\n" + 
						"    \"sourcePort\": \"443\",\r\n" + 
						"    \"conditionList\": [\r\n" + 
						"        {\r\n" + 
						"            \"name\": \"Condition_1\",\r\n" + 
						"            \"conditionId\": 0,\r\n" + 
						"            \"baselineName\": \"NA\",\r\n" + 
						"            \"operator\": 8,\r\n" + 
						"            \"groupInfo\": {\r\n" + 
						"                \"id\": 10108,\r\n" + 
						"                \"name\": \"ba\"\r\n" + 
						"            },\r\n" + 
						"            \"graphInfo\": {\r\n" + 
						"                \"id\": 18,\r\n" + 
						"                \"name\": \"\"\r\n" + 
						"            },\r\n" + 
						"            \"conditionCommonVectorMap\": {\r\n" + 
						"                \"AppTier>QAServer40\": {\r\n" + 
						"                    \"currentValue\": 25.035,\r\n" + 
						"                    \"prevValue\": 0,\r\n" + 
						"                    \"totalVector\": 1,\r\n" + 
						"                    \"satisfied\": true,\r\n" + 
						"                    \"baselineValue\": 2,\r\n" + 
						"                    \"perVectorMap\": {\r\n" + 
						"                        \"AppTier>QAServer40\": {\r\n" + 
						"                            \"loadValue\": -1,\r\n" + 
						"                            \"satisfied\": true,\r\n" + 
						"                            \"currentValue\": 22.03,\r\n" + 
						"                            \"thresholdPerVectorMap\": {}\r\n" + 
						"                        }\r\n" + 
						"                    }\r\n" + 
						"                }\r\n" + 
						"            },\r\n" + 
						"            \"metaData\": \"Tier>Server\"\r\n" + 
						"        }\r\n" + 
						"    ],\r\n" + 
						"    \"ruleExpression\": \"Condition_1\",\r\n" + 
						"    \"ruleLevel\": \"1\",\r\n" + 
						"    \"severityLevel\": \"1\",\r\n" + 
						"    \"derivedExpression\": \"-\",\r\n" + 
						"    \"eventState\": \"1\"\r\n" + 
						"}").
				when().post("/DashboardServer/web/AlertDataService/genCustomAlert")
				.then().log().all().assertThat().statusCode(200).body("statusCode", equalTo(201));
    }
	
}
