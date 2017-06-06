package Class;

/**
 * Created by Administrador on 19/05/2017.
 */

public class Usuario {
    private long id;
    private String nomeUser;
    private String loginUser;
    private String senhaUser;

    public Usuario() {
    }

    @Override
    public String toString() {
        return nomeUser + "\n"+ "Login: " + loginUser;
    }

    public long getId(long aLong) {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getSenhaUser() {
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser;
    }
}
