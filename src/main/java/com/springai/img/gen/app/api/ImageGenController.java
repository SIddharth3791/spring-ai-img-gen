package com.springai.img.gen.app.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springai.img.gen.app.model.Question;
import com.springai.img.gen.app.service.OpenAiService;

@RestController
public class ImageGenController {
	
	private OpenAiService openAiService;
	
	public ImageGenController(OpenAiService openAiService) {
		this.openAiService = openAiService;
	}
	
	@PostMapping(value ="/image", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getImage(@RequestBody Question question) {
		return openAiService.getImage(question);
	}
	
	@PostMapping(value="/vision", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> upload(@Validated @RequestParam() MultipartFile file, @RequestParam() String name){
		return ResponseEntity.ok(openAiService.getDescription(file));
	}

}
