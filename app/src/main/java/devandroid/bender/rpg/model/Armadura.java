package devandroid.bender.rpg.model;

public class Armadura {
    private int id;
    private String nome;
    private Integer defesa;
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

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
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
