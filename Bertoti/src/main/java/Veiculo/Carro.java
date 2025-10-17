package Veiculo;

public class Carro {
    private String modelo;
    private String cor;
    private String nome;
    private Roda roda;
    private Motor motor;

    public Carro(String modelo, String cor, String nome, Roda roda, Motor motor) {
        this.modelo = modelo;
        this.cor = cor;
        this.nome = nome;
        this.roda = roda;
        this.motor = motor;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Roda getRoda() {
        return roda;
    }
    public void setRoda(Roda roda) {
        this.roda = roda;
    }
    public Motor getMotor() {
        return motor;
    }
    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    @Override
    public String toString() {
        return ("\nModelo: "+getModelo()+
                "\nCor: "+getCor()+
                "\nNome: "+getNome()+
                "\nRoda: "+getRoda()+
                "\nMotor: "+getMotor());
    }
    public  void ligar(){
    }
    public void desligar(){
    }
    public void acelerar(){
    }
}
