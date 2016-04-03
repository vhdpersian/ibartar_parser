package com.ibartar.utils;

import java.io.IOException;

import com.ibartar.json.Body;

public  abstract class  AbstractObjectDispatcher {
	
	protected String URLTEMPLATE;
    
	protected  Class<?> resultType=null;
	
	protected int page;
	
	protected int  maxPage;
	
	protected String region="";
	
	protected Body<?> responseBody;
	
	protected String urlTemplate="";
	
	public AbstractObjectDispatcher()
	{
		
		
	}
	
				public AbstractObjectDispatcher(Class<?> resultType,int page) {
					this.resultType = resultType;
					this.page=page;
				} 
				
				public void Startup() throws IOException
				{
				
					retrieveByGPS();
					
				//	retreieveDataByRegion();
					
				}
				public abstract  void retrieveByGPS() throws IOException;
				
				public abstract  void retreieveDataByRegion() throws IOException;
	
	

}
