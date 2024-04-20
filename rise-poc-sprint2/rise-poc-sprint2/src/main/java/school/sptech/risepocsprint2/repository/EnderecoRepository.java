package school.sptech.risepocsprint2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.risepocsprint2.entity.Endereco;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    Optional<Endereco> findAllByCep(String cep);
}
