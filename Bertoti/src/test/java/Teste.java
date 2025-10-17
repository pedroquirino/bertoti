import Veiculo.Carro;
import Veiculo.Motor;
import Veiculo.PatioDeCarros;
import Veiculo.Roda;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Teste {
    @Test
    public void test1(){

        //Criando modelo de Roda
        Roda rodaSUV = new Roda(22,10,"SUV");

        //Criando modelo de Motor
        Motor motorSUV = new Motor("Diesel",300);

        //Criando Patio de Carros
        PatioDeCarros patioGM =  new PatioDeCarros();

        //Adicionando um Veículo no pátio GM
        patioGM.adicionar(new Carro("SUV","Preto","Tracker",rodaSUV,motorSUV));

        //Verifica se foi inserido UM carro no patio da GM
        assertEquals(1, patioGM.getCarros().size());
    }
    @Test
    public void test2(){
        //Criando duas Rodas
        Roda rodaSUV = new Roda(22,10,"SUV");
        Roda rodaSedan = new Roda(18,8,"Sedan");

        //Criando dois Motores
        Motor motorSUV = new Motor("Diesel",300);
        Motor motorSedan = new Motor("Flex",200);
        PatioDeCarros patioGM =  new PatioDeCarros();

        //Adicionando dois Veículos no pátio da GM
        patioGM.adicionar(new Carro("SUV","Preto","Tracker",rodaSUV,motorSUV));
        patioGM.adicionar(new Carro("Sedan","Branco","Cobalt",rodaSedan,motorSedan));

        //Realizando busca por Modelo "Sedan" no pátio da GM e verificando a posição na Lista
        List<Carro> busca = patioGM.buscarCarrosPorModelo("Sedan");
        assertEquals(busca.get(0).getNome(),"Cobalt");

        //Verificando se existe o carro "Tracker" na Lista de carros pelo Nome do Carro
        Carro carro = patioGM.buscarCarrosPorNome("Tracker");
        assertEquals(carro.getNome(),"Tracker");

    }
}
