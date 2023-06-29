package Proj;


	// WRTIE TO JSON IMPL1 
	/* 
	 l.log(res.toString());
	 
	 String currentJsonArrayAsString;
	try {
		currentJsonArrayAsString = new String(Files.readAllBytes(path));
		
		ObjectMapper mapper = new ObjectMapper();
		 
	    try (FileWriter fileWriter = new FileWriter(path.toFile(), false)) {
	    	
	    	String json;
			try {
				json = mapper.writeValueAsString(res);
				json = "{" + json + "}";
				l.log(json);
				JSONObject jsonObject = new JSONObject(json);
		        JSONArray jsonArray = new JSONArray(currentJsonArrayAsString);
		        jsonArray.put(jsonObject);

		        try {
					fileWriter.write(jsonArray.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
				}
				
				
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    	
		
		
	} 
	catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}// .readString(path, StandardCharsets.UTF_8);
	}
	 */
	
	//WRTIE TO JSON IMPL2 
	
	//List<Map<String, Object>> data = mapper.convertValue(res,String);   //  new TypeReference<ArrayList<Map<String, ArrayList<String>>>>(){});
		//mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		 /*
		File Results = new File("Results",".json");
			if(Results.exists())
			{try {  
			 	
		        mapper.writeValue(Results, res);

		    } catch (IOException e) {  
		        e.printStackTrace();
		        l.log(KeyWordAndDate + " ERROR ON WRITTING TO JSON ");
		    }  }
			else {
				
				File Resultss = new File("Results.json");
				try {
				mapper.writeValue(Paths.get("Results.json").toFile(), json);
				}
				
				catch (IOException e) {  
			        e.printStackTrace();
			        l.log(KeyWordAndDate + " ERROR ON WRITTING TO JSON ");
				
			}
		
				}
		

		}
		
		else {
			Result res = new Result(KeyWordAndDate, "NO RESULTS");
			
			l.log(res.toString());
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			 
			File Results = new File("Results",".json");
				if(Results.exists())
				{try {  
				 	
			        mapper.writeValue(Results, res);

			    } catch (IOException e) {  
			        e.printStackTrace();
			        l.log(KeyWordAndDate + " ERROR ON WRITTING TO JSON ");
			    }  }
				else {
					
					File Resultss = new File("Results.json");
					try {
					mapper.writeValue(Paths.get("Results.json").toFile(), res);
					}
					
					catch (IOException e) {  
				        e.printStackTrace();
				        l.log(KeyWordAndDate + " ERROR ON WRITTING TO JSON ");
					
				}
			
					}
			
			
			
		}

	*/
	
	
	
	
	
}
