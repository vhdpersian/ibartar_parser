package com.ibartar.utils;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
import org.apache.xmlbeans.impl.piccolo.util.LongStack;

import com.ibartar.json.Body;
import com.ibartar.json.Filter;
import com.ibartar.json.Guild;
import com.ibartar.json.Uuid;
import com.ibartar.json.exception.JsonParsingException;
import com.ibartar.mapper.json.MapperHandler;

public class ObjectDispatcher  extends AbstractObjectDispatcher  {
	
   private String urlTemplate_region="";
   private String urlTemplate_guild="";
   private String urlTemplate_Gps="";
   private List<String> guildArr_error=new ArrayList<>();
   private List<Filter> guildArr_no=new ArrayList<>();
   private int totalRow=0;
   
   private  Map<String, Object[]> data_repository;
   private int rownum=0;

   float lat_min=0f;
   float lat_max=0f;
   float lng_min=0f;
   float lng_max=0f;
   float lat_max_counter=0f;
   
   String latitude,longitude="";
   
   List<String> uuid_list=null;
   
  
   Writer out,out2,out3,out4=null;
   
   File fileDir,fileDir2,fileDir3,fileDir4=null;
   
   
   public 	ObjectDispatcher(String urltemplate_region,String urltemplate_guild,String urltemplate_gps,Class<?> resultType,int page)
   {
	   super (resultType,page);
	   this.urlTemplate_region=urltemplate_region;
	   this.urlTemplate_guild=urltemplate_guild;
	   this.urlTemplate_Gps=urltemplate_gps;
	   this.data_repository=new TreeMap<String, Object[]>();
	   data_repository.put(String.valueOf(rownum++),new Object[]{"title","address","description","tel","lat","lng","cat","s_logo"});
  	
   }
   
   
   public ObjectDispatcher(String urlTemplate,float lat_min, float lat_max, float lng_min,float lng_max) throws UnsupportedEncodingException, FileNotFoundException
   {
	   super ();
	   this.lat_max=lat_max;
	   this.lat_min=lat_min;
	   this.lng_min=lng_min;
	   this.lng_max=lng_max;
	   this.lat_max_counter=lat_max;
	   this.urlTemplate_Gps=urlTemplate;
	   page=1;
	   this.data_repository=new TreeMap<String, Object[]>();
	   data_repository.put(String.valueOf(rownum++),new Object[]{"title","address","description","tel","lat","lng","cat","s_logo"});
	   uuid_list=new ArrayList<>();
	   
  	
	    fileDir = new File("c:\\out.txt");
		
		out = new BufferedWriter(new OutputStreamWriter(
			new FileOutputStream(fileDir), "UTF8"));

		
		 fileDir2 = new File("c:\\blist.txt");
			
		out2 = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(fileDir2), "UTF8"));
		
	
	    fileDir3 = new File("c:\\uuid.txt");
		out3 = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(fileDir3), "UTF8"));
		
		fileDir4 = new File("c:\\req.txt");
	    out4 = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(fileDir4), "UTF8"));
		
	   
   }
   
   private Boolean chechDuplicateUuid(String uuid)
   {
	   int count=0;
	   
	   for (String _uuid:uuid_list){
		   
		   if (_uuid==uuid || _uuid.equals(uuid))
		   {
			   count=1;
			   break;
		   }
	   }
	   
	   if (count==1) return true;
	   return false;
	   	   
   }
   
   public void addToRepository(String size_page,Writer w,Writer w2,Writer w3) throws IOException
   {
	  
	  maxPage=Integer.parseInt(size_page);
	  
	  if ((maxPage*10) > 100)
	  {
		  out2.append(MessageFormat.format(this.urlTemplate_Gps,"1",latitude+"9999",longitude+"9999",latitude+"1111",longitude+"1111")).append("\r\n");
	      out2.flush();
	      maxPage=10;
	  }
	  else
	  {
		  maxPage= (maxPage*10) / 10;
		  maxPage=((maxPage*10) %10!=0) ? maxPage+1: maxPage;
	  }
			  
	  for (int i=1;i<=maxPage;i++)
	  {
		  
		   URLTEMPLATE= MessageFormat.format(this.urlTemplate_Gps,i,latitude+"9999",longitude+"9999",latitude+"1111",longitude+"1111");
		   if (URLTEMPLATE!="")
		   {
			  
		        String jsonResponse=callHttpService();
		        try {
			           responseBody=parseJsonResponse(jsonResponse,resultType);
			           if (responseBody.getData().size()>0){
							 System.out.println(URLTEMPLATE);
			         		   for (Object objData :responseBody.getData())
					    	   {
			         		         					         			   
			         			       if(chechDuplicateUuid(((Uuid)objData).getUuid())==false)
			         			      {
			         			    	   
						         			 String category="";
						         			 if ( ((Uuid)objData).getGuilds().size() >0 )
						         			 {
						         			    for(Guild  guild:((Uuid)objData).getGuilds())
						         			    {
						         			    	category=category+"..."+guild.getTitle();
						         			    }
						         				 
						         			 }
					         			 
									         data_repository.put(String.valueOf(rownum++),new Object[]{
												   
											       ((Uuid)objData).getTitle(),
												   ((Uuid)objData).getAddress(),
												   ((Uuid)objData).getDescription(),
												   ((Uuid)objData).getTel_1(),
												   ((Uuid)objData).getLat(),
												   ((Uuid)objData).getLng(),
												   category,
												   ((Uuid)objData).getLogo().getSmall()
												//   ((Uuid)objData).getLogo().getFullSize(),
												   				   			   
											   });
									  
									            String desc=((Uuid)objData).getDescription();
									            if (desc!=null)
									            {
									            	desc=desc.replaceAll("[\\r\\n]", "");
									            }
									         
									            String data= 
									           		 ((Uuid)objData).getTitle()+"@"
									        		 +((Uuid)objData).getAddress()+"@"
									        		 +desc+"@"
									        	     +((Uuid)objData).getTel_1()+"@"
									        		 +((Uuid)objData).getLat()+"@"
									        		 +((Uuid)objData).getLng()+"@"
									        		 +category+"@"
									        		 +((Uuid)objData).getLogo().getSmall();
									       				
									     
									       uuid_list.add(((Uuid)objData).getUuid());
									       out3.append(((Uuid)objData).getUuid()).append("\r\n");
									       out3.flush();
									       									       
									       out.append(data).append("\r\n");
									       out.flush();
													    	   
							    	  }
					    	   }
						
			}
	
							
			} catch (JsonParsingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   }
		   		  
	  }
	   
	   
	   
   }
   
   public void retrieveByGPS() throws IOException
   {
		   
	   System.out.println("runnig.............");
   
	  
   	   
	   while(lng_max>=lng_min)
	   {
		
				
		   while(lat_max_counter>=lat_max)
		  {
			
	
			   latitude=String.format("%.4f",lat_max_counter);
			   longitude =String.format("%.4f",lng_max);

			   URLTEMPLATE= MessageFormat.format(this.urlTemplate_Gps,page,latitude+"9999",longitude+"9999",latitude+"1111",longitude+"1111");
			 
			   if (URLTEMPLATE!="")
			   {
				   
			       
			        String jsonResponse=callHttpService();
			        try {
				           responseBody=parseJsonResponse(jsonResponse,resultType);
				           if (responseBody.getData().size()>0){
				               addToRepository(responseBody.getLast_page(),out,out2,out3);
				           }
				      		
				        }
					
					 catch (JsonParsingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        
			        out4.append(latitude+"9999"+","+longitude+"9999"+ "---"+latitude+"1111"+","+longitude+"1111").append("\r\n");
				    out4.flush();
		         }
			   
			  
				  lat_max_counter=lat_max_counter-0.0001f;
			   
		    }
		   			 
		      lat_max_counter=lat_max;
		   
			  lng_max=lng_max-0.0001f;
				
		
	   
		   }
 
		  out.close();   
		  out2.close();
		  out3.close();
		  out4.close();
		  System.out.println("finish.............");
	  }
	   

 
 
 
   
   public void exportToExcel (String fileName)
   {
	   
	   XSSFWorkbook workbook = new XSSFWorkbook(); 
	     
	    //Create a blank sheet
	    XSSFSheet sheet = workbook.createSheet("1");
	    XSSFSheet sheet2 = workbook.createSheet("2");
	    XSSFSheet sheet3 = workbook.createSheet("3");
	   
	    int rownum_excel=0;
	    
	    for (String filter:guildArr_error)
	    {
	    	Row row = sheet2.createRow(rownum_excel++);
	    	Cell cell = row.createCell(0);
	    	cell.setCellValue(filter);
	    	    	
	    }
	    
	    
	    rownum_excel=0;
	    
	    
	    for (Filter filter:guildArr_no)
	    {
	    	Row row = sheet3.createRow(rownum_excel++);
	    	Cell cell = row.createCell(0);
	    	cell.setCellValue(filter.getKey());
	    	    	
	    }
	    
	    rownum_excel=0;
	    
	   Set<String> keyset = data_repository.keySet();
	  
	
	    for (String key : keyset)
	    {
	    	totalRow++;
	        Row row = sheet.createRow(rownum_excel++);
	        Object [] objArr = data_repository.get(key);
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
	        FileOutputStream out = new FileOutputStream(new File( fileName+".xlsx"));
	        workbook.write(out);
	        out.close();
	        System.out.println("written successfully on disk to "+fileName+".xlsx" );
	        
	        this.data_repository=null;
	        this.data_repository=new TreeMap<String, Object[]>();
	        this.data_repository.put(String.valueOf(rownum++),new Object[]{"title","address","description","tel","lat","lng","cat","s_logo","l_logo"});
	        guildArr_error=null;
	        guildArr_no=null; 
	        guildArr_error=new ArrayList<>();
	        guildArr_no=new ArrayList<>();
	        
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	   
   }
   
   @Override
    public void retreieveDataByRegion() throws IOException 
	{
	         // region= "&#1575;&#1576;&#1585;&#1575;&#1607;&#1740;&#1605;&#8204;&#1570;&#1576;&#1575;&#1583;";
	          
	    ArrayList<String> regionArr = new ArrayList<>();

		// New BufferedReader.
	    File fileDir = new File("c:\\test.txt");
	    FileInputStream is = new FileInputStream(fileDir);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		// Add all lines from file to ArrayList.
		while (true) {
		    String line = reader.readLine();
		    if (line == null) {
			break;
		    }
		    regionArr.add(line);
		}

		// Close it.
		reader.close();
		
		for (String region_name:regionArr)
		{
	          region=region_name;
	         
	         //region="%D8%A7%D9%81%D8%B3%D8%B1%DB%8C%D9%87"; 
	         
	         
	          System.out.println(region);
	          page=1;
	          URLTEMPLATE=MessageFormat.format(this.urlTemplate_region,page,URLEncoder.encode(region, "UTF-8"));
	         // URLTEMPLATE=MessageFormat.format(this.urlTemplate_region,page,region);
			  String jsonResponse=callHttpService();
			  try {
				responseBody=parseJsonResponse(jsonResponse,resultType);
				callUrlRequestGuid();
				exportToExcel(region);
				
			} catch (JsonParsingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(" total record is :"+ totalRow);
		  		 
	 }
	
   
     private void callUrlRequestGuid() throws NumberFormatException, UnsupportedEncodingException
     {
       	 List<Filter>  list_guild = new ArrayList<>();
    	
    	 if (responseBody.getData().size()>0)
    	 {
    		 list_guild=responseBody.getFilters();
    		 
    		 int l=0;
    		 while (l<=list_guild.size()-1)
    		 {
    			Filter filter=list_guild.get(l);
    			
    			 int last_page=Integer.parseInt(filter.getDoc_count());
	    		 if (last_page% 10>0)
	    		 {
	    			 page=1;
	    			 maxPage=(last_page/10)+1;
	    		 }else if (last_page% 10==0)
	    		 {
	    			 page=1;
	    			 maxPage=(last_page/10);
	    		 }
	    		 else
	    		 {
	    			 page=1;
	    			 maxPage=1;
	    		 }
	    
	    		if (maxPage>10)
	    		{
	    			
	    			guildArr_error.add(filter.getKey());
	    		}
	    		
	    		 for (int i=page;i<=maxPage;i++)
	        	 {
	    			 if (i<=10)
	    			 {
			        		 URLTEMPLATE= MessageFormat.format(this.urlTemplate_guild, i,URLEncoder.encode(region, "UTF-8"),Integer.parseInt(filter.getKey()));
			        		 
			        		 String jsonResponse=callHttpService();
			    			  try {
			    				responseBody=parseJsonResponse(jsonResponse,resultType);
			    				//addToRepoitosy();
			    				//callUrlRequestGuid();
			    				
			    				for (Object objData :responseBody.getData())
			    				   {
			    				    								    						    					
			    					 //System.out.println(totalRow++);
			    					 
			    					   data_repository.put(String.valueOf(rownum++),new Object[]{
			    						   
			    						   ((Uuid)objData).getTitle(),
			    						   ((Uuid)objData).getAddress(),
			    						   ((Uuid)objData).getDescription(),
			    						   ((Uuid)objData).getTel_1(),
			    						   ((Uuid)objData).getLat(),
			    						   ((Uuid)objData).getLng(),
			    					       ((Uuid)objData).getGuilds().get(0).getTitle(),
			    						   ((Uuid)objData).getLogo().getSmall(),
			    						   ((Uuid)objData).getLogo().getFullSize(),
			    						   				   			   
			    					   });
			    					   
			    					   
			    					   for (Filter filter_alter:responseBody.getFilters())
			    					   {
			    						   
			    						  if (addAlterFilter(filter_alter,list_guild,guildArr_no)==true)
			    						  {
			    							  
			    							  guildArr_no.add(filter_alter);
			    							  
			    							  list_guild.add(filter_alter);
			    							  
			    						  }
			    						 			    						   
			    					   }
			    					   
			    				   }
			    				
			    				
			    			} catch (JsonParsingException e) {
			    				// TODO Auto-generated catch block
			    				e.printStackTrace();
			    			}
	    			 }
	    			 else
	    			 {
	    				 System.out.println(region+"--"+filter.getKey());
	    			 }
	    			    		  		 
	        		 
	        	 }
	    		 
    			l++;
    			 
    		 }
    		 
    				 
	    	// for(Filter filter:list_guild)
	    	// {
	    		
	    		
	       		
		    		 
	    	 //}
	     }
    	  	 
     }
              
    
     private Boolean addAlterFilter(Filter filter1,List<Filter> lst_filter2,List<Filter> lst_filter3)
     {
    	 int count=0;
    	 for (Filter filter2:lst_filter2)
    	 {
    		 if (filter1.getKey().equals(filter2.getKey()))
			  {
				  count++;
				  break;
			  }
    		   		 
    	 }
    	 
    	 if (count==0)
    	 {
    		 int sw=0;
    		 for(Filter filter3:lst_filter3)
    		 {
    			 if (filter3.getKey().equals(filter1.getKey()))
    			 {
    				 sw++;
    				 break;
    			 }
    		 }
    		 
    		 if (sw==0)
    		 {
    			 return true;
    		 }
    		 
    	 }
    	 
    	 return false;
    	 
     }
     
	 private String  callHttpService()  {
		    	
		     String url= URLTEMPLATE; //MessageFormat.format(URLTEMPLATE, page,region);
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
