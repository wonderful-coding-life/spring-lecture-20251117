package com.example.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAiEmbeddingTests {
    private static final Logger log = LoggerFactory.getLogger(OpenAiEmbeddingTests.class);

    @Autowired
    private OpenAiEmbeddingModel embeddingModel;

    private String text = """
            제품 사용 전 반드시 배터리를 완전히 충전해 주세요.
            전원 버튼을 3초간 길게 눌러 전원을 켜면 LED 표시등이 점등됩니다.
            먼지통이 가득 차면 흡입력이 떨어질 수 있으므로, 청소 후 반드시 먼지통과 필터를 세척하세요.
            필터는 완전히 건조된 후 다시 장착해야 하며, 젖은 상태로 사용할 경우 고장의 원인이 될 수 있습니다.
            배터리는 약 500회 충전이 가능합니다. 장시간 사용하지 않을 때는 완충 후 서늘한 곳에 보관하세요.
            """;

    @Test
    public void testSimpleEmbedding() {
        var embed = embeddingModel.embed(text);
        log.info("embed = {}", embed);
    }
}
