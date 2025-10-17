import Veiculo.Carro;
import Veiculo.Motor;
import Veiculo.PatioDeCarrosDAO;
import Veiculo.Roda;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TesteDAO {

    PatioDeCarrosDAO dao = new PatioDeCarrosDAO();
    //Metodo para realizar antes dos testes para limpar a tabela caso já exista
    @Before
    public void setUp() throws SQLException {
        dao = new PatioDeCarrosDAO();
        dao.limparTabelas();
    }

    @Test
public void test() throws SQLException {

        //Teste Para adicionar Rodas, Motores e Carros
        dao.criarBancoDeDados();
        dao.criarTabelas();

        Roda roda;
        dao.addRoda(roda= new Roda(22,10,"SUV"));
        assertNotNull(roda);
        dao.addRoda(roda= new Roda(18,8,"Sedan"));
        assertNotNull(roda);

        Motor motor;
        dao.addMotor(motor = new Motor("Diesel",300));
        assertNotNull(motor);
        dao.addMotor(motor = new Motor("Flex",200));
        assertNotNull(motor);

        Carro carro;
        dao.addCarro(carro = new Carro("SUV","Preto","Tracker",dao.buscarRodaPorId(1),dao.buscarMotorPorId(1)));
        dao.addCarro(carro = new Carro("Sedan","Branco","Cobalt",dao.buscarRodaPorId(2),dao.buscarMotorPorId(2)));
        dao.addCarro(carro = new Carro("Hatch","Azul","Onix",dao.buscarRodaPorId(2),dao.buscarMotorPorId(2)));
        dao.addCarro(carro = new Carro("SUV","Vermelha","S10",dao.buscarRodaPorId(1),dao.buscarMotorPorId(1)));

        List<Carro> todosOsCarros = dao.listarTodosOsCarros();
        assertEquals(4, todosOsCarros.size());

        dao.deletarCarro("Cobalt");

        todosOsCarros = dao.listarTodosOsCarros();
        assertEquals(3, todosOsCarros.size());

        System.out.println("\nTeste Finalizado");
    }
}
