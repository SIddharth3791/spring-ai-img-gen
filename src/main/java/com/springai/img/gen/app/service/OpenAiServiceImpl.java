package com.springai.img.gen.app.service;

import java.util.Base64;
import java.util.List;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springai.img.gen.app.model.Question;

@Service
public class OpenAiServiceImpl implements OpenAiService {
	
	private final ImageModel imageModel;
	
	private final ChatModel chatModel;
	
	private final OpenAiAudioSpeechModel aiAudioSpeechModel;
	
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
	
	
	public OpenAiServiceImpl(ImageModel imageModel, ChatModel chatModel, OpenAiAudioSpeechModel aiAudioSpeechModel) {
		this.imageModel = imageModel;
		this.chatModel = chatModel;
		this.aiAudioSpeechModel = aiAudioSpeechModel;
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

	@Override
	public String getDescription(MultipartFile file) {
		var chatOptions = OpenAiChatOptions.builder().model(OpenAiApi.ChatModel.GPT_4_TURBO).build();
		
		var userMsg =  new UserMessage("Explain what do you see in the picture?", List.of(new Media(MimeTypeUtils.IMAGE_JPEG, file.getResource())));
		
		ChatResponse response = chatModel.call(new Prompt(List.of(userMsg), chatOptions));
		return response.getResult().getOutput().toString();
	}

	@Override
	public byte[] getSpeech(Question question) {
		
		OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
												.voice(OpenAiAudioApi.SpeechRequest.Voice.NOVA)
												.speed(1.0f)
												.responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
												.model(OpenAiAudioApi.TtsModel.TTS_1.value)
												.build();
		
		SpeechPrompt speechPrompt = new SpeechPrompt(question.question(), speechOptions);
		
		SpeechResponse response = aiAudioSpeechModel.call(speechPrompt);
		
		return response.getResult().getOutput();
	}

}
















