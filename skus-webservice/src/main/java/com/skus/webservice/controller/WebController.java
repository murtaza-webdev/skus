package com.skus.webservice.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

	@RequestMapping(value = "isRunning", method = RequestMethod.GET)
	public String checkStatus() {
		return "Skus Webservice is UP";
	}
	
	@GetMapping("/location")
    public String location(Map<String, Object> model) {
		LOGGER.info("WebController.location request ");
        return "location";
    }
}
