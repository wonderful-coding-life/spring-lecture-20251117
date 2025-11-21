package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OpenAiApplication implements ApplicationRunner {
    @Autowired
    private OpenAiChatModel chatModel;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String answer = chatModel.call("서울 올림픽은 몇회 올림픽이야?");
        log.info("answer = {}", answer);
    }
}
