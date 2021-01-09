package orangetalents.com.mapper;

import orangetalents.com.dto.response.response.ContaResponse;
import orangetalents.com.model.Conta;
import org.springframework.stereotype.Service;

@Service
public class ContaMapper {

    public ContaResponse toResponse(Conta conta) {
        return new ContaResponse(
                conta.getId(),
                conta.getNome(),
                conta.getEmail());

    }
}
