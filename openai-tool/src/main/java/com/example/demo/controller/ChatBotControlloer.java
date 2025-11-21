package com.example.demo.controller;

import com.example.demo.tool.ProductOrderTools;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatBotControlloer {
    @Autowired
    private OpenAiChatModel chatModel;
    @Autowired
    private ProductOrderTools orderTools;

    private static final String systemMessage = """
            (1) 배송중인 상품은 취소할 수 없고, 배송 준비중인 상태에서만 취소가 가능해.
            (2) 주문 취소는 명시적으로 제품 이름과 함께 취소해 달라고 할 때만 취소해.
            (3) 답변은 짥고 명료하게 해 줘.
            """;

    @GetMapping("/api/chat")
    public String getChat(@RequestParam("message") String message) {
        List<Message> messages = List.of(
                new SystemMessage(systemMessage),
                new UserMessage(message)
        );
        var options = ToolCallingChatOptions.builder()
                .toolCallbacks(ToolCallbacks.from(orderTools))
                .build();
        var prompt = new Prompt(messages, options);
        var response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }
}
