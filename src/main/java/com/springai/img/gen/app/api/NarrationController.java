package com.springai.img.gen.app.api;


import java.io.File;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springai.img.gen.app.service.OpenAiService;

@RestController
public class NarrationController {
	
	private OpenAiService openAiService;
	
	public NarrationController(OpenAiService openAiService) {
		this.openAiService = openAiService;
	}
	
	@PostMapping(value="/explain",  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} , produces = "audio/mpeg")
	public byte[] explainImageToMe(@Validated @RequestParam MultipartFile file, @RequestParam String name) {
			return openAiService.explainMe(file);
	}

}
