package com.wispa.webserver.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DomainController {

	@RequestMapping("/")
	@ResponseBody
	public String helloWorld()
	{
		return "My Hello World!";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	@ResponseBody
	public String helloWorld2()
	{
		return "My Hello World 2";
	}
}
