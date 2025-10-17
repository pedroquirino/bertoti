package Veiculo;

import java.util.LinkedList;
import java.util.List;

public class PatioDeCarros {

    private List<Carro> carros =  new LinkedList<Carro>();
    public void  adicionar(Carro carro){
        carros.add(carro);
    }
    public List<Carro> getCarros(){
        return carros;
    }
    public List<Carro> buscarCarrosPorModelo(String modelo){
        List<Carro> carrosPorModeloEncontrados = new LinkedList<Carro>();
        for (Carro carro : carros) {
            if (carro.getModelo().equals(modelo)) {
                carrosPorModeloEncontrados.add(carro);
            }
        }
        return carrosPorModeloEncontrados;
    }
    public Carro buscarCarrosPorNome(String nome){
        for (Carro carro : carros) {
            if (carro.getNome().equals(nome)) {
                return carro;
            }
        }
        return null;
    }
}
