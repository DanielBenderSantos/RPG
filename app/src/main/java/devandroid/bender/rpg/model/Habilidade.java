package devandroid.bender.rpg.model;

public class Habilidade {

    private int id;
    private String nome;
    private double tempoDeRegarga;
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

    public double getTempoDeRegarga() {
        return tempoDeRegarga;
    }

    public void setTempoDeRegarga(double tempoDeRegarga) {
        this.tempoDeRegarga = tempoDeRegarga;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
