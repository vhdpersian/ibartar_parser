package com.ibartar.utils;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.crypto.Data;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
   
   public void exportToExcel ()
   {
	   
	   try {
		 
		   int rownum=0;
		   Map<String, Object[]> data_out = new TreeMap<String, Object[]>();
		   data_out.put(String.valueOf(rownum),new Object[]{"title","address","description","tel","lat","lng","s_logo","l_logo","cat"});
		   
		   
		   for (int i=1;i<=10;i++)
		   {
			   page=i;
			   
				   for (Object objData : getObject().getData() )
				   {
				
					   data_out.put(String.valueOf(++rownum),new Object[]{
						   
						   ((Uuid)objData).getTitle(),
						   ((Uuid)objData).getAddress(),
						   ((Uuid)objData).getDescription(),
						   ((Uuid)objData).getTel_1(),
						   ((Uuid)objData).getLat(),
						   ((Uuid)objData).getLng(),
						   ((Uuid)objData).getLogo().getSmall(),
						   ((Uuid)objData).getLogo().getFullSize(),
						   ((Uuid)objData).getGuilds().get(0).getTitle()
						   				   			   
					   });
				   }
		   }
		   
		   XSSFWorkbook workbook = new XSSFWorkbook(); 
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Employee Data");
		   
		   Set<String> keyset = data_out.keySet();
		   rownum=0;
	    
	        for (String key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            Object [] objArr = data_out.get(key);
	            int cellnum = 0;
	            for (Object obj : objArr)
	            {
	                 Cell cell = row.createCell(cellnum++);
	                 cell.setCellValue((String)obj);
	               
	            }
	        }
		   	   
		   try
	        {
	            //Write the workbook in file system
	            FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
	            workbook.write(out);
	            out.close();
	            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
		   
	        
		  // Body<?> result=null;
		   //result=getObject();
		   
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
		    	
		     String url=MessageFormat.format(URLTEMPLATE, page);;
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
