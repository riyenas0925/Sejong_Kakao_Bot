package dev.riyenas.chatbot.skill;

import dev.riyenas.chatbot.domain.SkillResponse;
import dev.riyenas.chatbot.domain.SkillResponseData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkillResponseTest {
    private static Logger log = LoggerFactory.getLogger(SkillResponseTest.class);

    @Test
    @DisplayName("Skill Response 테스트")
    public void test() {
        SkillResponse skillResponse = new SkillResponseData()
                .addData("key", "value");
        log.info(skillResponse.toString());
    }
}
