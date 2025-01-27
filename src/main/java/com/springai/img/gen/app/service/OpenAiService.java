package com.springai.img.gen.app.service;

import com.springai.img.gen.app.model.Question;

public interface OpenAiService {
	
	Byte[] getImage(Question question);

}
