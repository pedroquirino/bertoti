package br.edu.fatec.ldbd.api_veiculos.repository;

import br.edu.fatec.ldbd.api_veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
}
