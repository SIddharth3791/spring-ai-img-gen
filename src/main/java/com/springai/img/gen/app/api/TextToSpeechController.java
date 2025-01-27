package com.springai.img.gen.app.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springai.img.gen.app.model.Question;
import com.springai.img.gen.app.service.OpenAiService;

@RestController
public class TextToSpeechController {
	
	private OpenAiService openAiService;
	
	public TextToSpeechController( OpenAiService openAiService) {
		this.openAiService = openAiService;
	}
	
	
	@PostMapping(value="/talk", produces = "audio/mpeg")
	public byte[] talkTalk(@RequestBody Question question) {
		return openAiService.getSpeech(question);
	}

}
