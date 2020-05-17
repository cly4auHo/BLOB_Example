package com.servletController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.service.BlobService;

@Controller
public class ImageServlet {
	@Autowired
	HttpServletResponse resp;

	@RequestMapping(value = "/imageServlet", method = RequestMethod.GET)
	public String getImage() {
		OutputStream out = null;
		BlobService service = new BlobService();
		InputStream stream = service.getImage(1);
		try {
			out = resp.getOutputStream();
			resp.setContentType("image/gif");
			byte[] buffer = new byte[1024];

			int c;

			while ((c = stream.read(buffer)) > 0) {
				out.write(buffer, 0, c);
			}
			out.close();
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "blob";
	}
	
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public String image() {
		
		return "blob";
	}
}
