package dao;

import Conexao.conexao;
import modal.Conversa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ConversaDAO {

    public void createConversaTable() {
        // Define o SQL para criar a tabela "Usuarios" com colunas específicas
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Conversas ("
                + "conversa_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "usuario1_id INT NOT NULL,"
                + "usuario2_id INT NOT NULL,"
                + "apelido VARCHAR(50)"
                + "FOREING KEY (usuario1_id) REFERENCES Usuarios(usuario_id)"
                + "FOREING KEY (usuario2_id) REFERENCES Usuarios(usuario_id)"
                + ")";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexao.conexao();
            stmt = con.prepareStatement(createTableSQL);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conexao.desconecta(con, stmt);
        }
    }
    public void createConversa(Conversa c) throws SQLException {
        try (Connection con = conexao.conexao(); PreparedStatement stmt = con.prepareStatement("INSERT INTO conversas (usuario1_id, usuario2_id, apelido) VALUES (?, ?, ?)")) {
            stmt.setInt(1, c.getUsuario1_id());
            stmt.setInt(2, c.getUsuario2_id());
            stmt.setString(3, c.getApelido());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Conversa salva com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a conversa: " + ex.getMessage());
            throw ex; // Lançar a exceção para tratamento superior
        }
    }

    public List<Conversa> readConversas() throws SQLException {
        List<Conversa> conversas = new ArrayList<>();

        try (Connection con = conexao.conexao(); PreparedStatement stmt = con.prepareStatement("SELECT * FROM conversas"); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Conversa c = new Conversa();
                c.setConversa_id(rs.getInt("conversa_id"));
                c.setUsuario1_id(rs.getInt("usuario1_id"));
                c.setUsuario2_id(rs.getInt("usuario2_id"));
                c.setApelido(rs.getString("apelido"));

                conversas.add(c);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler conversas: " + ex.getMessage());
            throw ex; // Lançar a exceção para tratamento superior
        }

        return conversas;
    }

    public Conversa getConversasById(int conversaId) throws SQLException {
        try (Connection con = conexao.conexao(); PreparedStatement stmt = con.prepareStatement("SELECT * FROM conversas WHERE conversa_id = ?")) {
            stmt.setInt(1, conversaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Conversa c = new Conversa();
                    c.setConversa_id(rs.getInt("conversa_id"));
                    c.setUsuario1_id(rs.getInt("usuario1_id"));
                    c.setUsuario2_id(rs.getInt("usuario2_id"));
                    c.setApelido(rs.getString("apelido"));
                    return c;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao obter conversa por ID: " + ex.getMessage());
            throw ex; // Lançar a exceção para tratamento superior
        }

        return null;
    }

    public void updateConversa(Conversa c) throws SQLException {
        try (Connection con = conexao.conexao(); PreparedStatement stmt = con.prepareStatement("UPDATE conversas SET usuario1_id = ?, usuario2_id = ?, apelido = ? WHERE conversa_id = ?")) {
            stmt.setInt(1, c.getUsuario1_id());
            stmt.setInt(2, c.getUsuario2_id());
            stmt.setString(3, c.getApelido());
            stmt.setInt(4, c.getConversa_id());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Conversa atualizada com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma conversa encontrada para atualizar");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar a conversa: " + ex.getMessage());
            throw ex; // Lançar a exceção para tratamento superior
        }
    }

    public void deleteConversa(int conversaId) throws SQLException {
        try (Connection con = conexao.conexao(); PreparedStatement stmt = con.prepareStatement("DELETE FROM conversas WHERE conversa_id = ?")) {
            stmt.setInt(1, conversaId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Conversa excluída com sucesso");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma conversa encontrada para excluir");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir a conversa: " + ex.getMessage());
            throw ex; // Lançar a exceção para tratamento superior
        }
    }

    public List<Conversa> getAllConversas() {
        String query = "SELECT * FROM Conversas";
        List<Conversa> conversas = new ArrayList<>();

        try (Connection con = conexao.conexao(); PreparedStatement preparedStatement = con.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Conversa conversa = new Conversa();
                conversa.setConversa_id(resultSet.getInt("conversa_id"));
                conversa.setUsuario1_id(resultSet.getInt("usuario1_id"));
                conversa.setUsuario2_id(resultSet.getInt("usuario2_id"));
                conversa.setApelido(resultSet.getString("apelido"));
                conversas.add(conversa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conversas;
    }
}
