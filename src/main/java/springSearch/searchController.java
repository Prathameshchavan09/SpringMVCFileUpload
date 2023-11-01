package springSearch;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class searchController {

	
	@RequestMapping("/home")
	public String home() {
		
		return "search";
		
	}
	
	
	@RequestMapping("/searchBar")
	public RedirectView view(@RequestParam("searchUserData") String searched ) {
		
		
		
		String url = "https://www.google.com/search?q="+searched;
		
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(url);
		
		if(searched.isEmpty()) {
			redirectView.setUrl("home");
			}
			
		
		return redirectView;
		
		
		
	}
	
	
	
	
	
	@RequestMapping(path="/fileUploadprocess" , method=RequestMethod.POST)
	public String fileUpload() {
		
		return "fileUpload";
	}
	
	
	
	
	
	@RequestMapping(path="/uploadFile" , method=RequestMethod.POST)
	public String browseFile(@RequestParam("fileData") CommonsMultipartFile UserDropFile ,Model model,HttpSession s) {
		
		
		System.out.println(UserDropFile.getSize());
		System.out.println(UserDropFile.getContentType());
		System.out.println(UserDropFile.getName());
		System.out.println(UserDropFile.getOriginalFilename());
		
		
		byte[] data = UserDropFile.getBytes();
		
//		We have to upload file on server
		
//		String path = s.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "resources" + File.separator + "css" +File.separator + UserDropFile.getOriginalFilename();
		String path = "C:\\files" + File.separator + UserDropFile.getOriginalFilename();
		System.out.println(path);
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
			System.out.println("File Uploaded..");
		} catch (IOException e) {
			
			e.printStackTrace();
			System.out.println(e);
		}
		
		
		
		
		
		
		model.addAttribute("Size" , "FileName " + UserDropFile.getName());
		
		return "fileUploadedSucc";
	}
	
	
	
}



