package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
public class AudioModelTests {
    private static final Logger log = LoggerFactory.getLogger(AudioModelTests.class);

    @Autowired
    private OpenAiAudioTranscriptionModel transcriptionModel;

    @Autowired
    private OpenAiAudioSpeechModel speechModel;

    @Test
    public void testAudioTranscription() {
        var resource = new ClassPathResource("/audio/voc_kart_rider.mp3");
        var transcript = transcriptionModel.call(resource);
        log.info("voc = {}", transcript);
    }

    @Test
    public void testAudioSpeechModel() throws IOException {
        var message = "이번역은 선릉, 선릉역입니다. 내리실 문은 오른쪽 입니다. 멀티캠퍼스로 가실 분들은 이번역에서 내리시기 바랍니다.";

        OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
                .model("tts-1-hd") // tts-1, tts-1-hd, gpt-4o-mini-tts (not ready yet for spring ai)
                .voice(OpenAiAudioApi.SpeechRequest.Voice.ONYX) // default ALLOY?
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .build();

        TextToSpeechPrompt prompt = new TextToSpeechPrompt(message, speechOptions);
        var response = speechModel.call(prompt);

        Files.write(Paths.get("D:\\archive\\audio\\speech.mp3"), response.getResult().getOutput());
    }
}
