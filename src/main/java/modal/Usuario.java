
package modal;

public class Usuario {
    private int usuarioId;
    private String login;
    private String senha;
    private String telefone;
    private String email;
    private String bio;
    private byte[] imagemPerfil;
    private String tema;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * @return the imagemPerfil
     */
    public byte[] getImagemPerfil() {
        return imagemPerfil;
    }

    /**
     * @param imagemPerfil the imagemPerfil to set
     */
    public void setImagemPerfil(byte[] imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }
}
