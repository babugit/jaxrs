package com.jaxrs.beginning;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
 
@Path("/temperature")
public class Temperature {

	@Path("{to}/{val}/{format}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response toCelsius(@PathParam("to") String to, @PathParam("val") String val, @PathParam("format") String format) {
		
		String entity = "";
		String from = to.equalsIgnoreCase("celsius") ? "fahrenheit" : "celsius";
		to = to == null || to == "" ? "celsius" : to;
		double input = val == null || val == "" ? 155.0 : new Double(val);
		double output = to.equalsIgnoreCase("celsius") ? (((input - 32) * 5) / 9) : ((input * 9) / 5) + 32;
		
		if ("xml".equals(format)) {
			entity = "<output>" + "<" + from.toLowerCase() + ">" + input + "</" + from.toLowerCase() + ">" + "<" + to.toLowerCase() + ">" + output + "</" + to.toLowerCase() + ">" + "</output>";			
		} else {
			JSONObject jsonObject = new JSONObject();			
			jsonObject.put(from.toUpperCase(), input);
			jsonObject.put(to.toUpperCase(), output);
			entity = jsonObject.toString();
		}
		
		return Response	
		// Set the status and Put your entity here.
				.ok(entity.toString())
				// Add the Content-Type header to tell Jersey which format it
				// should marshall the entity into.
				.header(HttpHeaders.CONTENT_TYPE,
						"json".equals(format) ? MediaType.APPLICATION_JSON
								: MediaType.APPLICATION_XML).build();

	}
}