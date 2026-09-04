package com.libreria.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class LibroControllerIntegrationTest {
    @Autowired
    MockMvc mvc;

    @Test
    public void registrarLibroCorrecto() throws Exception {
        mvc.perform(post("/api/libros")
                .with(user("john").password("1234").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "titulo": "El principito",
                            "autor": "Antoine de Saint-Exupery",
                            "precio": 12.50
                        }
                        """))
                .andExpect(status().isCreated());
    }
}
