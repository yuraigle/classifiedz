package ru.orlovs.classifiedz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void canLoginAsAdmin() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .content("{\"email\": \"admin@adz.me\", \"password\": \"password\"}")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").isString());
    }

    @Test
    void tryLoginInvalidForm() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .content("{\"email\": \"invalid_mail\", \"password\": \"password\"}")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(400))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].field", is("email")));

        mockMvc.perform(post("/api/auth/login")
                .content("{\"email\": \"admin@adz.me\"}")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(400))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].field", is("password")));
    }

    @Test
    void tryLoginWrongPassword() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .content("{\"email\": \"admin@adz.me\", \"password\": \"wrong1\"}")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(400))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].message", is("Bad credentials")));
    }

    @Test
    void tryAccessWithoutToken() throws Exception {
        mockMvc.perform(get("/api/users"))
                .andExpect(status().is(403));
    }
}
