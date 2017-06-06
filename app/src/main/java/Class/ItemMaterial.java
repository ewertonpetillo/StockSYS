package Class;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by ewerton on 30/05/17.
 */

public class ItemMaterial {
    private Long id;
    private String data;
    private Integer quantidade;
    private Servidor servidor;
    private Material materiais;

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public Material getMateriais() {
        return materiais;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public void setMateriais(Material materiais) {
        this.materiais = materiais;
    }
}
