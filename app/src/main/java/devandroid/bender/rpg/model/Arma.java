package devandroid.bender.rpg.model;

public class Arma {
    private int id;
    private String nome;
    private Integer dano;
    private Integer quantidadeDeMaos;
    private Integer equipado;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDano() {
        return dano;
    }

    public void setDano(Integer dano) {
        this.dano = dano;
    }

    public Integer getQuantidadeDeMaos() {
        return quantidadeDeMaos;
    }

    public void setQuantidadeDeMaos(Integer quantidadeDeMaos) {
        this.quantidadeDeMaos = quantidadeDeMaos;
    }

    public Integer getEquipado() {
        return equipado;
    }

    public void setEquipado(Integer equipado) {
        this.equipado = equipado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
