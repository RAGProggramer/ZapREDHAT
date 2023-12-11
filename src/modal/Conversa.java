package modal;

/**
 * Classe que representa uma conversa entre dois usuários. Cada conversa possui
 * um identificador único, IDs dos dois usuários envolvidos e um apelido
 * associado à conversa. Esta classe fornece métodos de acesso (getters e
 * setters) para cada atributo.
 *
 * @author aguia
 */
public class Conversa {

    // Atributos da classe
    private int conversa_id;
    private int usuario1_id;
    private int usuario2_id;
    private String apelido;

    // Métodos de acesso para conversa_id
    public int getConversa_id() {
        return conversa_id;
    }

    public void setConversa_id(int conversa_id) {
        this.conversa_id = conversa_id;
    }

    // Métodos de acesso para usuario1_id
    public int getUsuario1_id() {
        return usuario1_id;
    }

    public void setUsuario1_id(int usuario1_id) {
        this.usuario1_id = usuario1_id;
    }

    // Métodos de acesso para usuario2_id
    public int getUsuario2_id() {
        return usuario2_id;
    }

    public void setUsuario2_id(int usuario2_id) {
        this.usuario2_id = usuario2_id;
    }

    // Métodos de acesso para apelido
    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
}
