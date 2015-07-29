package com.lin.action.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.lin.web.BaseController;

/**
 * 
 * @ClassName: FileUploadController
 * @Description: 文件上传
 * @author xuelin
 * @date Jul 29, 2015 10:14:26 AM
 *
 */
@Controller
public class FileUploadController extends BaseController {

	@RequestMapping("toUpload.asp")
	public String toUpload1() {
		return "upload";
	}

	@RequestMapping("upload1.asp")
	public void upload1(@RequestParam("file") CommonsMultipartFile multFile) {
		System.out.println("contentType: " + multFile.getContentType());
		System.out.println("fileName: " + multFile.getName());
		System.out.println("orFileName: " + multFile.getOriginalFilename());
		System.out.println("size: " + multFile.getSize());

		try {
			long st = System.currentTimeMillis();
			transferFileTo(multFile, new File("c:/upload/" + multFile.getOriginalFilename()));
			long en = System.currentTimeMillis();
			System.out.println(en - st);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("upload2.asp")
	public void upload1(HttpServletRequest request) {
		long st = System.currentTimeMillis();

		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		multiRequest.getFileNames();
		for(Iterator<String> iter = multiRequest.getFileNames(); iter.hasNext();){
			String name = iter.next();
			MultipartFile multFile = multiRequest.getFile(name);
			System.out.println("key name: " + name);
			try {
				transferFileTo(multFile, new File("c:/upload/" + multFile.getOriginalFilename()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		long en = System.currentTimeMillis();
		System.out.println(en - st);
	}

}
