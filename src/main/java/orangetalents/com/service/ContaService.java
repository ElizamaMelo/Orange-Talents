package orangetalents.com.service;

import orangetalents.com.dto.response.request.ContaRequest;
import orangetalents.com.dto.response.response.ContaResponse;
import orangetalents.com.mapper.ContaMapper;
import orangetalents.com.model.Conta;
import orangetalents.com.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContaService {

    @Autowired
    private final ContaRepository contaRepository;

    @Autowired
    private final ContaMapper contaMapper;

    public ContaService(ContaRepository contaRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.contaMapper = contaMapper;
    }

    public ContaResponse createAccount(ContaRequest request) throws Exception {
        try {
            Conta conta = new Conta(request.getNome(), request.getCpf(), request.getEmail(), request.getDataNascimento());
            return contaMapper.toResponse(contaRepository.save(conta));
        } catch (Exception e) {
            throw new Exception("A server error has occurred.", e);
        }
    }

}
