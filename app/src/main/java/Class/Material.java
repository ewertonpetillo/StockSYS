package Class;


public class Material {
    private long id;
    private String descricao;
    private String unMedida;
    private int estoqueMin;
    private int estoqueIni;
    private int estoqueAtual;

    public Material() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnMedida() {
        return unMedida;
    }

    public void setUnMedida(String unMedida) {
        this.unMedida = unMedida;
    }

    public int getEstoqueMin() {
        return estoqueMin;
    }

    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    public int getEstoqueIni() {
        return estoqueIni;
    }

    public void setEstoqueIni(int estoqueIni) {
        this.estoqueIni = estoqueIni;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    @Override
    public String toString() {
        return  descricao + " - " +
                estoqueAtual + " " + unMedida;
    }
}

