package orangetalents.com;

import com.fasterxml.jackson.databind.ObjectMapper;
import orangetalents.com.dto.response.request.ContaRequest;
import orangetalents.com.dto.response.response.ContaResponse;
import orangetalents.com.service.ContaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaService service;

    @Test
    public void createAccountShouldReturnAccountFromService() throws Exception {

        // given
        ContaRequest request = new ContaRequest("conta01", 00000000000L, "conta01@gmail.com", "01.01.2020");
        ContaResponse response = new ContaResponse(1L, "conta01", "conta01@gmail.com");

        // when
        when(service.createAccount(any(ContaRequest.class))).thenReturn(response);

        // then
        mockMvc.perform(post("/api/v1/contas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.email").value(response.getEmail()));
    }

    @Test
    public void createAccountShouldReturnHttpStatusCode404() throws Exception {

        // given
        ContaRequest request = new ContaRequest("", 00000000000L, "conta01@gmail.com", "01.01.2020");
        ContaResponse response = new ContaResponse(1L, "conta01", "conta01@gmail.com");

        // when
        when(service.createAccount(any(ContaRequest.class))).thenReturn(response);

        // then
        mockMvc.perform(post("/api/v1/contas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }
}
