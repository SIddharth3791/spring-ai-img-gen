package com.springai.img.gen.app.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springai.img.gen.app.model.Question;
import com.springai.img.gen.app.service.OpenAiService;

@RestController
public class ImageGenController {
	
	private OpenAiService openAiService;
	
	public ImageGenController(OpenAiService openAiService) {
		this.openAiService = openAiService;
	}
	
	@PostMapping(value ="/image", produces = MediaType.IMAGE_PNG_VALUE)
	public Byte[] getImage(@RequestBody Question question) {
		return null;
	}

}
