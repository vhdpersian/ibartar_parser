package com.ibartar;


import java.io.IOException;
import com.ibartar.json.Body;
import com.ibartar.utils.ObjectDispatcher;


public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
			
	//	String URLTEMPLATE=
		//		 "http://ibartar.com/api/v1.1/page/search?guild=142&page={0}&"
			//	 + "area=36.11165112263665,49.7921120300781,35.682241372450484,52.76666036992185&chanel=menu&"
				// + "s_id=e574716e-28c9-4a49-9426-6705e4e0d7a6&device_type=desktop";
		
		String URLTEMPLATE_Region="http://ibartar.com/api/v1.1/page/search?"
				+ "page={0}"
				+ "&area={1}"
				+ "&chanel=main"
				+ "&s_id=87f06bbf-9cd6-40c5-b9ff-3306faea1d77&device_type=desktop";
		
	    String URLTEMPLATE_Guild="http://ibartar.com/api/v1.1/page/search?"
				+ "page={0}"
				+ "&area={1}"
				+ "&guild={2}"
				+ "&chanel=main"
				+ "&s_id=87f06bbf-9cd6-40c5-b9ff-3306faea1d77&device_type=desktop";
	    
	    String URLTEMPLATE_GPS="http://ibartar.com/api/v1.1/page/search?"
	    		+ "page={0}"
	    		+ "&area={1},{2},{3},{4}"
	    		+ "&chanel=menu&s_id=8a61a996-850d-4f13-a64e-a8aa9160b09c&device_type=desktop";
		
		
		  ObjectDispatcher objDispatcher=new ObjectDispatcher(
				   URLTEMPLATE_Region
				  ,URLTEMPLATE_Guild
				  ,URLTEMPLATE_GPS
				  ,Body.class,1);
		  objDispatcher.Startup();
		 
		
	}

	
	

	

	
	
	
	

	
   

}
