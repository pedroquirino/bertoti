package Veiculo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PatioDeCarrosDAO {

    //Criando Banco de Dados
    public void criarBancoDeDados() throws SQLException{
        try (Connection conexao = DriverManager.getConnection("jdbc:sqlite:veiculos.db")) {
            System.out.println("Banco de Dados criado com sucesso!");
        }
    }
    public Connection conectar() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:veiculos.db");
    }

    //Criar Tabelas Roda, Motor e Carro
    public void criarTabelas() throws SQLException{
        try (Connection conexao = conectar();
             Statement stmt = conexao.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS roda (\n" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "aro INTEGER NOT NULL," +
                    "durabilidadePorAno INTEGER NOT NULL," +
                    "modelo TEXT NOT NULL UNIQUE)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS motor (\n" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "combustivel TEXT NOT NULL UNIQUE," +
                    "potencia INTEGER NOT NULL)";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS carro (\n" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "modelo TEXT NOT NULL," +
                    "cor TEXT NOT NULL," +
                    "nome TEXT NOT NULL UNIQUE," +
                    "roda_id INTEGER NOT NULL," +
                    "motor_id INTEGER NOT NULL," +
                    "FOREIGN KEY (roda_id) REFERENCES roda(id)," +
                    "FOREIGN KEY (motor_id) REFERENCES motor(id))";
            stmt.executeUpdate(sql);

            System.out.println("Tabelas Roda, Motor e Carro criada com sucesso!");
        }
    }
    //metodos para Adicionar roda, motor e carro a suas respectivas tabelas
    public void addRoda(Roda roda) throws SQLException {
        String sql = "INSERT INTO roda (aro, durabilidadePorAno, modelo) VALUES (?,?,?)";

        try (
                Connection conexao = this.conectar();
                PreparedStatement pstmt = conexao.prepareStatement(sql)
        ) {
            pstmt.setInt(1, roda.getAro());
            pstmt.setInt(2, roda.getDurabilidadeAno());
            pstmt.setString(3, roda.getModelo());
            pstmt.executeUpdate();
            System.out.printf("Roda %s adicionada com sucesso!\n", roda.getModelo());
        }
    }
    public void addMotor(Motor motor) throws SQLException {
        String sql = "INSERT INTO motor (combustivel, potencia) VALUES (?,?)";

        try (
                Connection conexao = this.conectar();
                PreparedStatement pstmt = conexao.prepareStatement(sql)
        ) {
            pstmt.setString(1, motor.getCombustivel());
            pstmt.setInt(2, motor.getPotencia());
            pstmt.executeUpdate();
            System.out.printf("Motor %s adicionado com sucesso!\n", motor.getCombustivel());
        }
    }
    public void addCarro(Carro carro) throws SQLException {
        String sql = "INSERT INTO carro (modelo, cor, nome, roda_id, motor_id) VALUES (?,?,?,?,?)";

        try (
                Connection conexao = this.conectar();
                PreparedStatement pstmt = conexao.prepareStatement(sql)
        ) {
            pstmt.setString(1, carro.getModelo());
            pstmt.setString(2, carro.getCor());
            pstmt.setString(3, carro.getNome());
            pstmt.setInt(4,carro.getRoda().getId());
            pstmt.setInt(5,carro.getMotor().getId());
            pstmt.executeUpdate();
            System.out.printf("Carro %s adicionado com sucesso!\n", carro.getNome());
        }
    }
    //Metodos para buscar Roda por modelo, Motor por combustivel e Carro por nome
    public Roda buscarRodaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM roda WHERE id = ?";
        try (Connection conexao = this.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Roda(rs.getInt("id"), rs.getInt("aro"),
                            rs.getInt("durabilidadePorAno"), rs.getString("modelo"));
                }
            }
        }
        System.out.println("Nenhuma roda foi encontrada!");
        return null;
    }

    public Motor buscarMotorPorId(int id) throws SQLException {
        String sql = "SELECT * FROM motor WHERE id = ?";
        try (Connection conexao = this.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Motor(rs.getInt("id"), rs.getString("combustivel"), rs.getInt("potencia"));
                }
            }
        }
        System.out.println("Nenhum motor foi encontrado!");
        return null;
    }
    public Carro buscarCarroPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM carro WHERE nome = ?";

        try (Connection conexao = this.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, nome);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int rodaId = rs.getInt("roda_id");
                    int motorId = rs.getInt("motor_id");

                    Roda rodaDoCarro = buscarRodaPorId(rodaId);
                    Motor motorDoCarro = buscarMotorPorId(motorId);

                    if (rodaDoCarro != null && motorDoCarro != null) {
                        return new Carro(
                                rs.getString("modelo"), rs.getString("cor"),
                                rs.getString("nome"), rodaDoCarro, motorDoCarro);
                    }
                }
            }
        }
        System.out.println("Nenhum carro encontrado com o nome: " + nome);
        return null;
    }

    public boolean deletarCarro(String nome) throws SQLException {
        String sql = "DELETE FROM carro WHERE nome = ?";

        try (Connection conexao = this.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            int rowsAfected = pstmt.executeUpdate();
            if (rowsAfected > 0) {
                System.out.printf("Carro %s foi deletado com sucesso!\n", nome);
            } else {
                System.out.printf("Não foi encontrado o carro com o nome %s.\n", nome);
            }
            return rowsAfected > 0;
        }
    }

    //Metodo para listar carros adicionados
    public List<Carro> listarTodosOsCarros() throws SQLException {
        List<Carro> carrosEncontrados = new LinkedList<Carro>();
        String sql = "SELECT * FROM carro";

        try (Connection conexao = this.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int rodaId = rs.getInt("roda_id");
                int motorId = rs.getInt("motor_id");

                Roda rodaDoCarro = buscarRodaPorId(rodaId);
                Motor motorDoCarro = buscarMotorPorId(motorId);

                if (rodaDoCarro != null && motorDoCarro != null) {
                    Carro carro = new Carro(
                            rs.getString("modelo"), rs.getString("cor"), rs.getString("nome"),
                            rodaDoCarro,motorDoCarro);
                    carrosEncontrados.add(carro);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return carrosEncontrados;
    }
    public void limparTabelas() throws SQLException {
        try (Connection conexao = conectar();
             Statement stmt = conexao.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS carro");
            stmt.executeUpdate("DROP TABLE IF EXISTS roda");
            stmt.executeUpdate("DROP TABLE IF EXISTS motor");
            System.out.println("Tabelas limpas com sucesso!");
        }
    }
}
