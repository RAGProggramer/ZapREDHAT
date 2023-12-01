package dao;

import Conexao.conexao;
import modal.Mensagem;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MensagemDAO {

    Mensagem m = new Mensagem();

    public void createMensagensTable() throws SQLException {
        // Define o SQL para criar a tabela "Mensagems" com colunas específicas
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Mensagens ("
                + "mensagem_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "conversa_id INT NOT NULL,"
                + "remetente_id INT NOT NULL,"
                + "tipo VARCHAR(25) NOT NULL,"
                + "conteudo VARCHAR(250) NOT NULL,"
                + "data Date,"
                + "status VARCHAR(25),"
                + "hora time,"
                + "FOREING KEY (conversa_id) REFERENCES Conversas(conversa_id)"
                + "FOREING KEY (remetente_id) REFERENCES Usuarios(usuario_id)"
                + ")";
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(createTableSQL);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "########: " + ex);
            System.out.println(ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public void createMensagem(Mensagem m) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            createMensagensTable();

            stmt = con.prepareStatement("INSERT INTO `mensagens`(data, hora, tipo, conteudo, status, conversa_id, remetente_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setDate(1, new java.sql.Date(m.getData().getTime()));
            stmt.setTime(2, m.getHora());
            stmt.setString(3, m.getTipo());
            stmt.setString(4, m.getConteudo());
            stmt.setString(5, m.getStatus());
            stmt.setInt(6, m.getConversa_id());
            stmt.setInt(7, m.getRemetente_id());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "@!Erro ao enviar mensagem: " + ex);
            System.out.println(ex);
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public List<Mensagem> readMensagens() throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Mensagem> mensagens = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Mensagens");
            rs = stmt.executeQuery();

            while (rs.next()) {

                m.setMensagem_id(rs.getInt("mensagem_id"));
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

    public Mensagem getMensagensById(int mensagemId) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM Mensagens WHERE mensagem_id = ?");
            stmt.setInt(1, mensagemId);
            rs = stmt.executeQuery();

            if (rs.next()) {

                m.setMensagem_id(rs.getInt("mensagem_id"));
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

    public void updateMensagem(Mensagem m) throws SQLException {
        Connection con = conexao.conexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Mensagens SET data = ?, hora = ?, tipo = ?, conteudo = ?, status = ?, conversa_id = ?, remetente_id = ? WHERE mensagem_id = ?");
            stmt.setDate(1, new java.sql.Date(m.getData().getTime()));
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

    public void deleteMensagem(int mensagemId) throws SQLException {
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

    public List<Mensagem> getAllMensagens() {
        // Define a consulta SQL para obter todos os usuários
        String query = "SELECT * FROM mensagens";
        List<Mensagem> Menssagens = new ArrayList<>();

        try (Connection con = conexao.conexao(); PreparedStatement preparedStatement = con.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Mensagem menssagen = new Mensagem();
                menssagen.setConversa_id(resultSet.getInt("Conversa_id"));
                menssagen.setData(resultSet.getDate("data"));
                menssagen.setHora(resultSet.getTime("hora"));
                menssagen.setTipo(resultSet.getString("tipo"));
                menssagen.setStatus(resultSet.getString("status"));
                Menssagens.add(menssagen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Menssagens;
    }
}
