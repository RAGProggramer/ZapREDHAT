package modal;

import java.util.Date;
import java.sql.Time;

/**
 * Classe que representa uma mensagem em uma conversa. Contém atributos como
 * identificador, data, hora, tipo, conteúdo, status, ID da conversa e ID do
 * remetente.
 *
 * @author aguia
 */
public class Mensagem {

    // Atributos da classe
    private int mensagem_id;
    private Date data;
    private Time hora;
    private String tipo;
    private String conteudo;
    private String status;
    private int conversa_id;
    private int remetente_id;

    /**
     * Obtém o identificador da mensagem.
     *
     * @return O identificador da mensagem.
     */
    public int getMensagem_id() {
        return mensagem_id;
    }

    /**
     * Define o identificador da mensagem.
     *
     * @param mensagem_id O identificador a ser definido.
     */
    public void setMensagem_id(int mensagem_id) {
        this.mensagem_id = mensagem_id;
    }

    /**
     * Obtém a data da mensagem.
     *
     * @return A data da mensagem.
     */
    public Date getData() {
        return data;
    }

    /**
     * Define a data da mensagem.
     *
     * @param data A data a ser definida.
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Obtém a hora da mensagem.
     *
     * @return A hora da mensagem.
     */
    public Time getHora() {
        return hora;
    }

    /**
     * Define a hora da mensagem.
     *
     * @param hora A hora a ser definida.
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * Obtém o tipo da mensagem.
     *
     * @return O tipo da mensagem.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo da mensagem.
     *
     * @param tipo O tipo a ser definido.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtém o conteúdo da mensagem.
     *
     * @return O conteúdo da mensagem.
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * Define o conteúdo da mensagem.
     *
     * @param conteudo O conteúdo a ser definido.
     */
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * Obtém o status da mensagem.
     *
     * @return O status da mensagem.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status da mensagem.
     *
     * @param status O status a ser definido.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtém o ID da conversa associada à mensagem.
     *
     * @return O ID da conversa.
     */
    public int getConversa_id() {
        return conversa_id;
    }

    /**
     * Define o ID da conversa associada à mensagem.
     *
     * @param conversa_id O ID da conversa a ser definido.
     */
    public void setConversa_id(int conversa_id) {
        this.conversa_id = conversa_id;
    }

    /**
     * Obtém o ID do remetente da mensagem.
     *
     * @return O ID do remetente.
     */
    public int getRemetente_id() {
        return remetente_id;
    }

    /**
     * Define o ID do remetente da mensagem.
     *
     * @param remetente_id O ID do remetente a ser definido.
     */
    public void setRemetente_id(int remetente_id) {
        this.remetente_id = remetente_id;
    }
}
