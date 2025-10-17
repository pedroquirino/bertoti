package Veiculo;

public class Roda {
    private Integer aro, id;
    private Integer durabilidadeAno;
    private String modelo;

    public  Roda(Integer id, Integer aro, Integer durabilidadeAno, String modelo) {
        this.id = id;
        this.aro = aro;
        this.durabilidadeAno = durabilidadeAno;
        this.modelo = modelo;
    }
    public  Roda(Integer aro, Integer durabilidadeAno, String modelo) {
        this.aro = aro;
        this.durabilidadeAno = durabilidadeAno;
        this.modelo = modelo;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAro() {
        return aro;
    }
    public void setAro(Integer aro) {
        this.aro = aro;
    }
    public Integer getDurabilidadeAno() {
        return durabilidadeAno;
    }
    public void setDurabilidadeAno(Integer durabilidadeAno) {
        this.durabilidadeAno = durabilidadeAno;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    @Override
    public String toString() {
        return ("Modelo: "+getModelo()+
                "\nAro: "+getAro()+
                "\nDurabilidade em anos: "+getDurabilidadeAno());
    }

    public void girar(){

    }
}
