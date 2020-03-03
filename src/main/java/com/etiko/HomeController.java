package com.etiko;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {

	@RequestMapping("/")
	public String load() {
		return "index.jsp";
	}
	
	@RequestMapping("prepare")
	public String prepare(@RequestParam("clientNumber") String clientNumber, @RequestParam("labelNumber") String labelNumber, Model m) throws IOException {

		String hostname = "Unknown";

		try
		{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		   // System.out.println(hostname);
		}
		catch (UnknownHostException ex)
		{
		    System.out.println("Hostname can not be resolved");
		}
		
		
		String group = "";

		//add client subgroup to the read path
		if (clientNumber.length()<3) {
			group = "0";
		}
		else if (clientNumber.length()<4) {
			group = String.valueOf(clientNumber.charAt(0));
		}
		else {
			String n1 = String.valueOf(clientNumber.charAt(0));
			String n2 = String.valueOf(clientNumber.charAt(1));
			group = n1+n2;
		}
		
		
		//add zero before the client number to have 4 digit number
		if (clientNumber.length()<4) {
			for (int x = clientNumber.length(); x<4; x++) {
				clientNumber = "0"+clientNumber;
			}
		}
		
		
		//add zero before the label number to have 3 digit number
		if (labelNumber.length()<4) {
			for (int x = labelNumber.length(); x<3; x++) {
				labelNumber = "0"+labelNumber;
			}
		}
		
		String copiedDirectory = "\\\\"+hostname+"\\roboczy\\ETYKIETY\\"+clientNumber+"\\"+clientNumber+"-"+labelNumber;
		String copiedDirectoryOld = "\\\\"+hostname+"\\roboczy\\ETYKIETY\\"+clientNumber+"\\"+clientNumber+"-"+labelNumber+"\\OLD";
		String pattern = ".*\\\\";
		String inputPath = "\\\\ARCHIWUM\\Archiwum_Etiko\\Archiwum Projektów Etiko\\"+group+"\\"+clientNumber+"\\"+clientNumber+"-"+labelNumber;
		String inputOldPath = "\\\\ARCHIWUM\\Archiwum_Etiko\\Archiwum Projektów Etiko\\"+group+"\\"+clientNumber+"\\"+clientNumber+"-"+labelNumber+"\\OLD";
		
		String outputPath = "\\\\"+hostname+"\\roboczy\\ETYKIETY\\"+clientNumber+"\\"+clientNumber+"-"+labelNumber+"\\";
		String outputOldPath = "\\\\"+hostname+"\\roboczy\\ETYKIETY\\"+clientNumber+"\\"+clientNumber+"-"+labelNumber+"\\OLD\\";
		
		File[] directoryContent = (new File(inputPath)).listFiles();
		
		
		String[] filenames = new String[directoryContent.length];
		File[] copiedFiles = new File[directoryContent.length];
		
	
				
		 FileTime moddate = null;
		 DateFormat df = new SimpleDateFormat("yyyyMMdd");
		 String dateModified = "";
		 String tempname = "";
		 String newname = "";
		 
		 
		new File(copiedDirectory).mkdir();//creates directory
		new File(copiedDirectoryOld).mkdir();//creates directory
		
		for (int a = 0; a<directoryContent.length; a++) {
			filenames[a] = 	directoryContent[a].toString(); //generates file names
			filenames[a] = filenames[a].replaceAll(pattern, ""); //generates file names
			copiedFiles[a]= new File(outputPath+filenames[a]);
			Files.copy(directoryContent[a].toPath(), copiedFiles[a].toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			if (filenames[a].equals("OLD")) {
				File[] directoryOldContent = (new File(inputOldPath)).listFiles();
				String[] filenamesOld = new String[directoryOldContent.length];
				File[] copiedFilesOld = new File[directoryOldContent.length];
				for (int b = 0; b<directoryOldContent.length; b++) {
					filenamesOld[b] = 	directoryOldContent[b].toString();
					filenamesOld[b] = filenamesOld[b].replaceAll(pattern, "");
					copiedFilesOld[b]= new File(outputOldPath+filenamesOld[b]);
					Files.copy(directoryOldContent[b].toPath(), copiedFilesOld[b].toPath(), StandardCopyOption.REPLACE_EXISTING);
					}	
		}
			else {
				
			}
		
		}
		
		
		for (int c = 0; c<directoryContent.length; c++) {
			if (filenames[c].matches(".*.metryka") ) { //zapisuje metryke z nowa nazwa w oldzie i kasuje w katalogu glownym
				
				BasicFileAttributeView attrView = 
		                Files.getFileAttributeView(Paths.get(directoryContent[c].getAbsolutePath()), BasicFileAttributeView.class);
		        BasicFileAttributes attributes = attrView.readAttributes();
		        moddate =  attributes.lastModifiedTime();
		        dateModified = df.format(moddate.toMillis());
		        tempname = directoryContent[c].getName();
		        newname = dateModified+"-"+tempname;
		        File metrykaOld =  new File(outputOldPath+newname);
		        Files.copy(directoryContent[c].toPath(), metrykaOld.toPath(), StandardCopyOption.REPLACE_EXISTING);
		        Files.delete(copiedFiles[c].toPath());
			}	
			
if (filenames[c].matches(".*.pdf") ) { //zapisuje pdfa z nowa nazwa w oldzie i kasuje w katalogu glownym
				
				BasicFileAttributeView attrView = 
		                Files.getFileAttributeView(Paths.get(directoryContent[c].getAbsolutePath()), BasicFileAttributeView.class);
		        BasicFileAttributes attributes = attrView.readAttributes();
		        moddate =  attributes.lastModifiedTime();
		        dateModified = df.format(moddate.toMillis());
		        tempname = directoryContent[c].getName();
		        newname = dateModified+"-"+tempname;
		        File pdfOld =  new File(outputOldPath+newname);
		        Files.copy(directoryContent[c].toPath(), pdfOld.toPath(), StandardCopyOption.REPLACE_EXISTING);
		        Files.delete(copiedFiles[c].toPath());
			}	

if (filenames[c].matches(".*.pdfpla") ) { // kasuje pdfpla w katalogu glownym
    Files.delete(copiedFiles[c].toPath());
}

if (filenames[c].matches(".*.metadata") ) { // kasuje .metadata w katalogu glownym
    Files.delete(copiedFiles[c].toPath());
}

if (filenames[c].matches(".*tmpgrs") ) { // kasuje tmpgrs w katalogu glownym
    Files.delete(copiedFiles[c].toPath());
}
			
		}

		m.addAttribute("result", labelNumber);
		return "result.jsp";
	}

}
