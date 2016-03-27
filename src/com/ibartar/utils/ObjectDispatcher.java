package com.ibartar.utils;



import java.io.IOException;
import java.text.MessageFormat;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.ibartar.json.Body;
import com.ibartar.json.Uuid;
import com.ibartar.json.exception.JsonParsingException;
import com.ibartar.mapper.json.MapperHandler;

public class ObjectDispatcher  extends AbstractObjectDispatcher  {
	
   public 	ObjectDispatcher(String urlTemplate, Class<?> resultType,int page)
   {
	   	   super (urlTemplate,resultType,page);
   }
   
   public void retrieveObject()
   {
	   
	   try {
		   Body<?> result=null;
		   result=getObject();
		   
		} catch (JsonParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
   }
   
   public Body<?> getObject() throws JsonParsingException 
	{
		 Body<?> ibartarResponse=null;
		 		 
		  try {
			  String jsonResponse=callHttpService();
			  ibartarResponse=parseJsonResponse(jsonResponse,resultType);

			return ibartarResponse;
			
		} catch (JsonParsingException e) {
			// TODO Auto-generated catch block
			throw new JsonParsingException(e);
		}
		  		 
	 }
	
	 private String  callHttpService()  {
		    	
		     String url=MessageFormat.format(URLTEMPLATE, 1);;
	    	 String responseBody="";

			 HttpClient httpclient = new DefaultHttpClient();
		        try {
		            HttpGet httpget = new HttpGet(url);

		            System.out.println("executing request " + httpget.getURI());

		            // Create a response handler
		            ResponseHandler<String> responseHandler = new BasicResponseHandler();
		            // Body contains your json stirng
		             responseBody = httpclient.execute(httpget, responseHandler);
		    

		        } catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {

		         httpclient.getConnectionManager().shutdown();
		            
		            return responseBody;
		        }
		        
		}
	 
	 
	 private Body<?>  parseJsonResponse(final String jsonResponse,
				final Class resultTypeClass) throws JsonParsingException 
		{
				
		     	Body<?> ibartarResponse=null;
		
		     	try {
					
		     		ibartarResponse = (Body<?>) MapperHandler.INSTANCE
							.getObjectMapper().readValue(
										jsonResponse,
										MapperHandler.INSTANCE
												.getObjectMapper()
												.getTypeFactory()
												.constructParametricType(
														Body.class,
														Uuid.class));
			     		
		     		return ibartarResponse;
		     	
		     	}
		     		catch (IOException e) {
		    			throw new JsonParsingException(e);
		    		}
				     	
			}
	
	
}
