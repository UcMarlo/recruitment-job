package com.recruitment.job.api;

import com.recruitment.job.RecruitmentJobApplicationIntegrationBaseTest;
import com.recruitment.job.domain.user.port.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc()
public class UsersRestControllerIntegrationTest extends RecruitmentJobApplicationIntegrationBaseTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void shouldReturnNotFoundWhenUserDoesNotExist() throws Exception {
        //given
        String login = "nonExistingUserLogin";
        when(userRepository.findUserByLogin(login)).thenReturn(Optional.empty());

        //when & then
        mockMvc.perform(get("/api/v1/users/{login}", login))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void shouldReturnInternalServerErrorForUnhandledException() throws Exception {
        //given
        when(userRepository.findUserByLogin(any())).thenThrow(new RuntimeException("An unknown exception"));

        //when & then
        mockMvc.perform(get("/api/v1/users/{login}", "login"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(""))
                .andReturn();
    }
}
