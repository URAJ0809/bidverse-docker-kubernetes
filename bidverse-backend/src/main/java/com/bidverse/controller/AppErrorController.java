package com.bidverse.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

@RestController
public class AppErrorController implements ErrorController {

	private final ErrorAttributes errorAttributes;

	public AppErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	// Handle /error and return JSON with status and message
	@org.springframework.web.bind.annotation.RequestMapping("/error")
	public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
		ServletWebRequest webRequest = new ServletWebRequest(request);
		Map<String, Object> attrs = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
		int status = (attrs.get("status") instanceof Integer) ? (Integer) attrs.get("status") : 500;
		String message = (String) attrs.getOrDefault("error", attrs.getOrDefault("message", "Unexpected error"));
		Map<String, Object> body = Map.of(
			"status", status,
			"error", message,
			"path", request.getRequestURI()
		);
		return ResponseEntity.status(HttpStatus.valueOf(status)).body(body);
	}
}
