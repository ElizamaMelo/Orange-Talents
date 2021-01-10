package orangetalents.com;

import com.fasterxml.jackson.databind.ObjectMapper;
import orangetalents.com.dto.response.request.ContaRequest;
import orangetalents.com.dto.response.response.ContaResponse;
import orangetalents.com.repository.ContaRepository;
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

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc

public class ContaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContaService service;

    @Test
    public void createAccountShouldReturnAccountFromService() throws Exception {

        // given
        String r = "{\"nome\":\"Teste01\",\"cpf\":\"75185489090\",\"email\":\"teste01@gmail.com\",\"dataNascimento\":\"1986-04-12\"}";
        ContaResponse response = new ContaResponse(1L, "Teste01", "teste01@gmail.com");

        // when
        when(service.create(any(ContaRequest.class))).thenReturn(response);


        // then
        mockMvc.perform(post("/api/v1/contas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(r)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.nome").value(response.getNome()))
                .andExpect(jsonPath("$.email").value(response.getEmail()));

    }


    @Test
    public void createAccountShouldReturnHttpStatusCode404EmailBlank() throws Exception {

        // given
        String r = "{\"nome\":\"Teste02\",\"cpf\":\"75185489090\",\"email\":\"\",\"dataNascimento\":\"1986-04-12\"}";
        ContaResponse response = new ContaResponse(1L, "teste02", "");

        // when
        when(service.create(any(ContaRequest.class))).thenReturn(response);

        // then
        mockMvc.perform(post("/api/v1/contas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(r)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void createAccountShouldReturnHttpStatusCode404NomeBlank() throws Exception {

        // given
        String r = "{\"nome\":\"\",\"cpf\":\"75185489090\",\"email\":\"teste03@gmail.com\",\"dataNascimento\":\"1986-04-12\"}";
        ContaResponse response = new ContaResponse(1L, "", "teste03@gmail.com");

        // when
        when(service.create(any(ContaRequest.class))).thenReturn(response);

        // then
        mockMvc.perform(post("/api/v1/contas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(r)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void createAccountShouldReturnHttpStatusCode404CpfBlank() throws Exception {

        // given
        String r = "{\"nome\":\"Teste04\",\"cpf\":\"\",\"email\":\"teste01@gmail.com\",\"dataNascimento\":\"1986-04-12\"}";
        ContaResponse response = new ContaResponse(1L, "teste04", "teste04@gmail.com");

        // when
        when(service.create(any(ContaRequest.class))).thenReturn(response);

        // then
        mockMvc.perform(post("/api/v1/contas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(r)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void createAccountShouldReturnHttpStatusCode404IncorrectEmailFormat() throws Exception {

        // given
        String r = "{\"nome\":\"Teste05\",\"cpf\":\"75185489090\",\"email\":\"teste05@@gmail.com\",\"dataNascimento\":\"1986-04-12\"}";
        ContaResponse response = new ContaResponse(1L, "teste05", "teste05@gmail.com");

        // when
        when(service.create(any(ContaRequest.class))).thenReturn(response);

        // then
        mockMvc.perform(post("/api/v1/contas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(r)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createAccountShouldReturnHttpStatusCode404IncorrectCpfFormat() throws Exception {

        // given
        String r = "{\"nome\":\"Teste06\",\"cpf\":\"751-854-890-90\",\"email\":\"teste06@@gmail.com\",\"dataNascimento\":\"1986-04-12\"}";
        ContaResponse response = new ContaResponse(1L, "teste06", "teste06@gmail.com");

        // when
        when(service.create(any(ContaRequest.class))).thenReturn(response);

        // then
        mockMvc.perform(post("/api/v1/contas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(r)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

