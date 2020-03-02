package com.etiko;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
		
		InputStream inStream = null;
	    OutputStream outStream = null;

		String pattern = ".*\\\\";
		String inputPath = "F:\\POMOCE NOWE\\PROGRAMOWANIE_JAVA\\SPRING\\CPP\\ARCH\\0\\0001\\0001-300";
		String outputPath = "F:\\POMOCE NOWE\\PROGRAMOWANIE_JAVA\\SPRING\\CPP\\ROBO\\0001\\0001-300";
		File[] directoryContent = (new File(inputPath)).listFiles();
		String[] filenames = new String[directoryContent.length];
		String[] abc = new String[directoryContent.length];
		for (int a = 0; a<directoryContent.length; a++) {
			filenames[a] = 	directoryContent[a].toString();
			filenames[a] = filenames[a].replaceAll(pattern, "");
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
		
		m.addAttribute("result", filenames[3]);
		
		return "result.jsp";
	}

}
