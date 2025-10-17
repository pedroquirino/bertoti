package Veiculo;

public class Motor {
    private Integer id;
    private String combustivel;
    private Integer potencia;

    public Motor(Integer id,String combustivel, Integer potencia) {
        this.id = id;
        this.combustivel = combustivel;
        this.potencia = potencia;
    }
    public Motor(String combustivel, Integer potencia) {
        this.combustivel = combustivel;
        this.potencia = potencia;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCombustivel() {
        return combustivel;
    }
    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
    public Integer getPotencia() {
        return potencia;
    }
    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }
    @Override
    public String toString() {
        return ("Potencia: "+getPotencia()+
                "\nCombustivel: "+getCombustivel());
    }
    public void injetarCombustivel(String combustivel){
    }
}
