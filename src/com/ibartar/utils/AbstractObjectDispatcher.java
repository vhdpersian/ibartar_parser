package com.ibartar.utils;

import com.ibartar.json.Body;

public  abstract class  AbstractObjectDispatcher {
	
	protected String URLTEMPLATE;
    
	protected  Class<?> resultType=null;
	
	protected int page;
	
	
				public AbstractObjectDispatcher(String urlTemplate, Class<?> resultType,int page) {
					this.URLTEMPLATE = urlTemplate;
					this.resultType = resultType;
					this.page=page;
				} 
				
				public void Startup()
				{
				
					 retrieveObject();
					
				}
				
				public abstract  void retrieveObject();
	
	

}
