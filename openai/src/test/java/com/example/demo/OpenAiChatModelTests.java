package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.MimeTypeUtils;

@SpringBootTest
public class OpenAiChatModelTests {
    private static final Logger log = LoggerFactory.getLogger(OpenAiChatModelTests.class);

    @Autowired
    private OpenAiChatModel chatModel;

    @Test
    public void testChatModelWithOptions() {
        String message = "무지개 색을 새로운 방식으로 설명 해 줘.";
        var chatOptions = OpenAiChatOptions.builder()
                .model("gpt-5.1")
                .N(2)
                .topP(0.3)
                .temperature(0.8)
                .build();
        var prompt = new Prompt(new UserMessage(message), chatOptions);
        var response = chatModel.call(prompt);
        log.info("answer = {}", response.getResult().getOutput().getText());
    }

    @Test
    public void testChatModelWithImage() {
        var resource = new ClassPathResource("/image/Disney_World_1.jpg");
        var media = Media.builder()
                .data(resource)
                .mimeType(MimeTypeUtils.IMAGE_JPEG)
                .build();
        var message = UserMessage.builder()
                .text("사진 속의 멋진 풍경을 시로 써 주세요.")
                .media(media)
                .build();
        var chatOptions = OpenAiChatOptions.builder()
                .model("gpt-5.1").build();

        var prompt = new Prompt(message, chatOptions);

        var response = chatModel.call(prompt);
        log.info("{}", response.getResult().getOutput().getText());
    }
}
