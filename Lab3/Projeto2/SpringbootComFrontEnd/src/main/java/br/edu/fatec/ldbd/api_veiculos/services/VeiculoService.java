package br.edu.fatec.ldbd.api_veiculos.services;

import br.edu.fatec.ldbd.api_veiculos.model.Veiculo;
import br.edu.fatec.ldbd.api_veiculos.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

    public List<Veiculo> listarTodos() {
        return repository.findAll();
    }

    public Veiculo salvar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        return repository.findById(id).map(veiculo -> {
            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setMarca(veiculoAtualizado.getMarca());
            veiculo.setPlaca(veiculoAtualizado.getPlaca());
            veiculo.setAno(veiculoAtualizado.getAno());
            return repository.save(veiculo);
        }).orElse(null);
    }
}