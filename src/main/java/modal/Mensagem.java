
package modal;

import java.util.Date;
import java.sql.Time;

/**
 *
 * @author aguia
 */
public class Mensagem {

  private int mensagem_id;
    private Date data;	
    private Time hora;
    private String tipo;
    private String conteudo;
    private String status;
    private int conversa_id;
    private int remetente_id;

    /**
     * @return the mensagem_id
     */
    public int getMensagem_id() {
        return mensagem_id;
    }

    /**
     * @param mensagem_id the mensagem_id to set
     */
    public void setMensagem_id(int mensagem_id) {
        this.mensagem_id = mensagem_id;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

  

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the conteudo
     */
    public String getConteudo() {
        return conteudo;
    }

    /**
     * @param conteudo the conteudo to set
     */
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the conversa_id
     */
    public int getConversa_id() {
        return conversa_id;
    }

    /**
     * @param conversa_id the conversa_id to set
     */
    public void setConversa_id(int conversa_id) {
        this.conversa_id = conversa_id;
    }
  
    /**
     * @return the hora
     */
    public Time getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * @return the remetente_id
     */
    public int getRemetente_id() {
        return remetente_id;
    }

    /**
     * @param remetente_id the remetente_id to set
     */
    public void setRemetente_id(int remetente_id) {
        this.remetente_id = remetente_id;
    }

    public void setData(String formattedDate) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
            
            
            
}
