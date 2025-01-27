package com.springai.img.gen.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.springai.img.gen.app.model.Question;

public interface OpenAiService {
	
	byte[] getImage(Question question);
	
	String getDescription(MultipartFile file);
	
	byte[] getSpeech(Question question);
	
	byte[] explainMe(MultipartFile file);

}
