package com.example.demo.controller;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    @Autowired
    private OpenAiChatModel chatModel;

    @Autowired
    private ChatMemory chatMemory;

    @PostMapping("/api/chats")
    public String postChat(@RequestParam("id") String id, @RequestParam("message") String message) {
        if (chatMemory.get(id).isEmpty()) {
            chatMemory.add(id, new SystemMessage("간단하고 명료하게 답변해 줘"));
        }

        chatMemory.add(id, new UserMessage(message));
        var prompt = new Prompt(chatMemory.get(id));

        var response = chatModel.call(prompt);
        var assistantMessage = response.getResult().getOutput();
        chatMemory.add(id, assistantMessage);
        return assistantMessage.getText();
    }
}
