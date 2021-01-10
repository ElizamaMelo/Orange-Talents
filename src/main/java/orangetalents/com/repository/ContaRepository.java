package orangetalents.com.repository;

import orangetalents.com.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
