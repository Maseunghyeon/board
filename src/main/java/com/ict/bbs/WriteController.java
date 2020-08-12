package com.ict.bbs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WriteController {

	
	@RequestMapping("writeForm")
	public String writeform() {
		
		return "write";
	}
}
