// Define o pacote onde a classe está localizada
package modal;

// Declaração da classe Usuario
public class Usuario {

    // Atributos da classe
    private int usuarioId;
    private String login;
    private String senha;
    private String telefone;
    private String email;
    private String bio;
    private byte[] imagemPerfil; // Representação binária da imagem do perfil
    private String tema;

    // Método getter para obter o ID do usuário
    public int getUsuarioId() {
        return usuarioId;
    }

    // Método setter para definir o ID do usuário
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    // Método getter para obter o login do usuário
    public String getLogin() {
        return login;
    }

    // Método setter para definir o login do usuário
    public void setLogin(String login) {
        this.login = login;
    }

    // Método getter para obter a senha do usuário
    public String getSenha() {
        return senha;
    }

    // Método setter para definir a senha do usuário
    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método getter para obter o telefone do usuário
    public String getTelefone() {
        return telefone;
    }

    // Método setter para definir o telefone do usuário
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Método getter para obter o email do usuário
    public String getEmail() {
        return email;
    }

    // Método setter para definir o email do usuário
    public void setEmail(String email) {
        this.email = email;
    }

    // Método getter para obter a biografia do usuário
    public String getBio() {
        return bio;
    }

    // Método setter para definir a biografia do usuário
    public void setBio(String bio) {
        this.bio = bio;
    }

    // Método getter para obter o tema preferido do usuário
    public String getTema() {
        return tema;
    }

    // Método setter para definir o tema preferido do usuário
    public void setTema(String tema) {
        this.tema = tema;
    }

    /**
     * @return the imagemPerfil
     */
    // Método getter para obter a imagem do perfil
    public byte[] getImagemPerfil() {
        return imagemPerfil;
    }

    /**
     * @param imagemPerfil the imagemPerfil to set
     */
    // Método setter para definir a imagem do perfil
    public void setImagemPerfil(byte[] imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }
}
