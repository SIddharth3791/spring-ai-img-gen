package com.springai.img.gen.app.service;

import java.util.Base64;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springai.img.gen.app.model.Question;

@Service
public class OpenAiServiceImpl implements OpenAiService {
	
	private final ImageModel imageModel;
	
	@Value("${img.response-format}")
	private String responseFormat;
	
	@Value("${img.model-type}")
	private String modelType;
	
	@Value("${img.height}")
	private int imgHeight;
	
	@Value("${img.width}")
	private int imgWidth;
	
	@Value("${img.quality}")
	private String imgQulity;
	
	@Value("${img.style}")
	private String imgStyle;
	
	public OpenAiServiceImpl(ImageModel imageModel) {
		this.imageModel = imageModel;
	}

	@Override
	public byte[] getImage(Question question) {
		
		var prompOptions = OpenAiImageOptions.builder()
							.withHeight(imgHeight).withWidth(imgWidth)
							.withResponseFormat(responseFormat)
							.withModel(modelType)
							.withQuality(imgQulity)
							.withStyle(imgStyle)
							.build();
		ImagePrompt imagePrompt = new ImagePrompt(question.question(), prompOptions);
		
		var imageResponse = imageModel.call(imagePrompt);
		
		
		return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
	}

}
