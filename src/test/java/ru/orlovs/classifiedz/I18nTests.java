package ru.orlovs.classifiedz;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class I18nTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsEnglishIfHeaderEn() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .content("{}")
                .header("Accept-Language", "en")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(400))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message", endsWith(" is required")));
    }

    @Test
    void returnsRussianIfHeaderRu() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .content("{}")
                .header("Accept-Language", "ru")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(400))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message", startsWith("Обязательное поле: ")));
    }

    @Test
    void returnsEnglishIfHeaderNotSet() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(400))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message", endsWith(" is required")));
    }

    @Test
    void returnsEnglishIfHeaderNotGuessed() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .content("{}")
                .header("Accept-Language", "WTF")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(400))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message", endsWith(" is required")));
    }
}
