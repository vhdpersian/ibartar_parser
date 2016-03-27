package com.ibartar;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import com.ibartar.mapper.json.MapperHandler;
import com.ibartar.utils.ObjectDispatcher;
import com.ibartar.utils.ObjectDispatcher;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		String URLTEMPLATE=
				 "http://ibartar.com/api/v1.1/page/search?guild=142&page={0}&"
				 + "area=36.11165112263665,49.7921120300781,35.682241372450484,52.76666036992185&chanel=menu&"
				 + "s_id=e574716e-28c9-4a49-9426-6705e4e0d7a6&device_type=desktop";
		
		  ObjectDispatcher objDispatcher=new ObjectDispatcher(URLTEMPLATE,Body.class,1);
		  objDispatcher.Startup();
		 
		
	}
	
	
	

	
   

}
