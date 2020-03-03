package com.etiko;

import java.io.File;
import java.io.IOException;
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



@Controller
public class HomeController {
	
	Project project = new Project();

	@RequestMapping("/")
	public String load() {
		return "index.jsp";
	}
	
	@RequestMapping("prepare")
	public String prepare(Model m) throws IOException {
		
//		InputStream inStream = null;
//	    OutputStream outStream = null;

		String directory = "F:\\POMOCE NOWE\\PROGRAMOWANIE_JAVA\\SPRING\\CPP\\ROBO\\0001\\0001-300";
		String pattern = ".*\\\\";
		String inputPath = "F:\\POMOCE NOWE\\PROGRAMOWANIE_JAVA\\SPRING\\CPP\\ARCH\\0\\0001\\0001-300";
		String inputOldPath = "F:\\POMOCE NOWE\\PROGRAMOWANIE_JAVA\\SPRING\\CPP\\ARCH\\0\\0001\\0001-300\\OLD";
		
		String outputPath = "F:\\POMOCE NOWE\\PROGRAMOWANIE_JAVA\\SPRING\\CPP\\ROBO\\0001\\0001-300\\";
		String outputOldPath = "F:\\POMOCE NOWE\\PROGRAMOWANIE_JAVA\\SPRING\\CPP\\ROBO\\0001\\0001-300\\OLD\\";
		
		File[] directoryContent = (new File(inputPath)).listFiles();
		File[] directoryOldContent = (new File(inputOldPath)).listFiles();
		
		String[] filenames = new String[directoryContent.length];
		File[] copiedFiles = new File[directoryContent.length];
		
		String[] filenamesOld = new String[directoryOldContent.length];
		File[] copiedFilesOld = new File[directoryOldContent.length];
				
		 FileTime moddate = null;
		 DateFormat df = new SimpleDateFormat("yyyyMMdd");
		 String dateModified = "";
		 String tempname = "";
		 String newname = "";
		 
		 
		new File(directory).mkdir();//creates directory
		for (int a = 0; a<directoryContent.length; a++) {
			filenames[a] = 	directoryContent[a].toString();
			filenames[a] = filenames[a].replaceAll(pattern, "");
			copiedFiles[a]= new File(outputPath+filenames[a]);
			Files.copy(directoryContent[a].toPath(), copiedFiles[a].toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			if (filenames[a].equals("OLD")) {
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
		
		
		
//		new File(outputPath).mkdir();//creates directory
//		for (int b = 0; b<directoryContent.length; b++) {
//			
//		File inp = new File(directoryContent[b].toString());
//		File out = new File(outputPath+"\\"+abc[b]);
//		
//		inStream = new FileInputStream(inp);
//        outStream = new FileOutputStream(out);
//
//        byte[] buffer = new byte[1024];
//
//        int length;
//		
//        while ((length = inStream.read(buffer)) > 0){  //copy the file content in bytes
//
//            outStream.write(buffer, 0, length);
//
//        }
//
//        inStream.close();
//        outStream.close();
//		}
		
		//directoryContent[2].createNewFile();
		
		m.addAttribute("result", newname);
		
		return "result.jsp";
	}

}
