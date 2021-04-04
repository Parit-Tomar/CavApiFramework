package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//write code to get place id
		//execute this only when place id is null
		
		StepDefination n=new StepDefination();
		if(StepDefination.place_id==null)
		{	
		n.add_place_payload_with("Shetty", "French", "Asia");
		n.user_calls_with_http_request("AddPlaceAPI", "POST");
		n.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
	}

}
