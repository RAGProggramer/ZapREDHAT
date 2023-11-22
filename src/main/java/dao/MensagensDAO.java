package dao;

import Conexao.conexao;
import modal.Menssagens;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MensagensDAO {

    public void create(Menssagens m) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Mensagens (data, hora, tipo, conteudo, status, conversa_id, remetente_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setDate(1, (Date) m.getData());
            stmt.setTime(2, m.getHora());
            stmt.setString(3, m.getTipo());
            stmt.setString(4, m.getConteudo());
            stmt.setString(5, m.getStatus());
            stmt.setInt(6, m.getConversa_id());
            stmt.setInt(7, m.getRemetente_id());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao enviar mensagem: " + ex);
            System.out.println(ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public List<Menssagens> readMensagens() throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Menssagens> mensagens = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Mensagens");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Menssagens m = new Menssagens();
                m.setMensagemId(rs.getInt("mensagem_id"));
                m.setData(rs.getDate("data"));
                m.setHora(rs.getTime("hora"));
                m.setTipo(rs.getString("tipo"));
                m.setConteudo(rs.getString("conteudo"));
                m.setStatus(rs.getString("status"));
                m.setConversa_id(rs.getInt("conversa_id"));
                m.setRemetente_id(rs.getInt("remetente_id"));

                mensagens.add(m);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler mensagens: " + ex);
            System.out.println(ex);
        } finally {
            conexao.desconecta(con, stmt, rs);
        }

        return mensagens;
    }

    public Menssagens getMensagensById(int mensagemId) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM Mensagens WHERE mensagem_id = ?");
            stmt.setInt(1, mensagemId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Menssagens m = new Menssagens();
                m.setMensagemId(rs.getInt("mensagem_id"));
                m.setData(rs.getDate("data"));
                m.setHora(rs.getTime("hora"));
                m.setTipo(rs.getString("tipo"));
                m.setConteudo(rs.getString("conteudo"));
                m.setStatus(rs.getString("status"));
                m.setConversa_id(rs.getInt("conversa_id"));
                m.setRemetente_id(rs.getInt("remetente_id"));

                return m;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao obter mensagem por ID: " + ex);
            System.out.println(ex);
        } finally {
            conexao.desconecta(con, stmt, rs);
        }

        return null;
    }

    public void updateMensagens(Menssagens m) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Mensagens SET data = ?, hora = ?, tipo = ?, conteudo = ?, status = ?, conversa_id = ?, remetente_id = ? WHERE mensagem_id = ?");
            stmt.setDate(1, (Date) m.getData());
            stmt.setTime(2, m.getHora());
            stmt.setString(3, m.getTipo());
            stmt.setString(4, m.getConteudo());
            stmt.setString(5, m.getStatus());
            stmt.setInt(6, m.getConversa_id());
            stmt.setInt(7, m.getRemetente_id());
            stmt.setInt(8, m.getMensagem_id());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Mensagem atualizada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar mensagem: " + ex);
            System.out.println(ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public void deleteMensagens(int mensagemId) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Mensagens WHERE mensagem_id = ?");
            stmt.setInt(1, mensagemId);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Mensagem excluída com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir mensagem: " + ex);
            System.out.println(ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
}