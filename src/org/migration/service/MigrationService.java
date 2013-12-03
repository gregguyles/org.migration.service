package org.migration.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

@Path("/migrate")
public class MigrationService {
	
	String logDir = "/home/gregor/googleDrive/workspace/org.migration.service/data.log";
	BufferedReader logReader;

	/*****************************************************
	* leatRecord return the oldest record of the datalog *
	******************************************************/
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String lastRecord(){
		String currentLine, lastLine = null;
		String[] strArr = null;
		try {
			logReader = new BufferedReader(new FileReader(logDir));
			while ((currentLine = logReader.readLine()) != null)
	    		lastLine = currentLine;
			strArr = lastLine.split(",");
	    } catch (IOException e) {
			e.printStackTrace();
	    }
	 return strArr[0];
  }
  
	/*************************************************************
	* updateData receives a String and appends it to the datalog *
	**************************************************************/
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public String updateData(String newRecords){
		try {
	   		FileWriter fileWritter = new FileWriter(logDir, true);
	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
	        bufferWritter.write(newRecords);
	        bufferWritter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "OK";
	}

	
	/*************************************************
	 * activate receives the oldest record value and *
	 * starts the local service with the next nuber  *
	 *************************************************/
	@Path("/activate")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public String activate(String oldestRecord){
		int startingRecord = Integer.parseInt(oldestRecord) + 1;
		
		/********************************************
		* Start data collection with startingRecord *
		*********************************************/
		
		return "OK1";
	}
	
}