package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageModelTests {
    private static final Logger log = LoggerFactory.getLogger(ImageModelTests.class);

    @Autowired
    private OpenAiChatModel chatModel;

    @Autowired
    private OpenAiImageModel imageModel;

    @Test
    public void testImageModel() {
        var message_old = """
            화성 표면에서 탐사 로버가 움직이고 있으며, 그 옆에는 2족 보행 로봇이 함께 탐사 활동을 하고 있다.
            붉은 모래 언덕과 먼지 낀 하늘이 배경이며, 태양빛이 낮게 비추는 오후의 분위기.
            실제 사진처럼 보이는 고해상도 장면, 자연스러운 그림자와 질감.
            """;
        var message = """
            화성 탐사 현장을 다큐멘터리 사진처럼 표현.
            실제 NASA 탐사 사진처럼 로버의 금속 질감과 먼지 낀 렌즈 표현이 사실적이다.
            2족 보행 로봇이 로버 옆에서 탐사를 돕는 장면.
            """;
        var response = imageModel.call(new ImagePrompt(message));
        log.info("download from {}", response.getResult().getOutput().getUrl());
    }
}
