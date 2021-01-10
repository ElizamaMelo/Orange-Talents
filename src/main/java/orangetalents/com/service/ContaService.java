package orangetalents.com.service;

import orangetalents.com.dto.response.request.ContaRequest;
import orangetalents.com.dto.response.response.ContaResponse;
import orangetalents.com.enums.ContaError;
import orangetalents.com.exception.ContaException;
import orangetalents.com.mapper.ContaMapper;
import orangetalents.com.model.Conta;
import orangetalents.com.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private ContaMapper mapper;

    public ContaResponse create(ContaRequest request) {

        existsByEmail(request.getEmail());
        existsByCpf(request.getCpf());

        Conta conta = Conta.builder()
                .nome(request.getNome())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .dataNascimento(request.getDataNascimento())
                .build();

        return mapper.toResponse(repository.save(conta));
    }

    private void existsByEmail(String email) {
        boolean existEmail = repository.existsByEmail(email);
        if (existEmail) {
            throw new ContaException(ContaError.EMAIL_ALREADY_EXISTS.getError());
        }
    }

    private void existsByCpf(String cpf) {
        boolean existCPF = repository.existsByCpf(cpf);
        if (existCPF) {
            throw new ContaException(ContaError.CPF_ALREADY_EXISTS.getError());
        }
    }

}
