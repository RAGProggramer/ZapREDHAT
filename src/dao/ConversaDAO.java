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

    // Método para criar a tabela "Conversas" no banco de dados
    public void createConversaTable() {
        // Define o SQL para criar a tabela "Conversas" com colunas específicas
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Conversas ("
                + "conversa_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "usuario1_id INT NOT NULL,"
                + "usuario2_id INT NOT NULL,"
                + "apelido VARCHAR(50),"
                + "FOREIGN KEY (usuario1_id) REFERENCES Usuarios(usuario_id)," // Adiciona chave estrangeira para usuario1_id referenciando Usuarios(usuario_id)
                + "FOREIGN KEY (usuario2_id) REFERENCES Usuarios(usuario_id)" // Adiciona chave estrangeira para usuario2_id referenciando Usuarios(usuario_id)
                + ")";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexao.conexao(); // Obtém uma conexão com o banco de dados
            stmt = con.prepareStatement(createTableSQL); // Prepara a instrução SQL
            stmt.executeUpdate(); // Executa a instrução para criar a tabela
        } catch (SQLException ex) {
            ex.printStackTrace(); // Trata exceção imprimindo o rastreamento no console
        } finally {
            conexao.desconecta(con, stmt); // Fecha a conexão e o statement no bloco finally
        }
    }

    // Método para inserir uma nova conversa no banco de dados
    public void createConversa(Conversa c) throws SQLException {
        try (Connection con = conexao.conexao(); PreparedStatement stmt = con.prepareStatement("INSERT INTO conversas (usuario1_id, usuario2_id, apelido) VALUES (?, ?, ?)")) {
            stmt.setInt(1, c.getUsuario1_id());
            stmt.setInt(2, c.getUsuario2_id());
            stmt.setString(3, c.getApelido());

            stmt.executeUpdate(); // Executa a instrução SQL para inserir a conversa

            JOptionPane.showMessageDialog(null, "Conversa salva com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a conversa: " + ex.getMessage());
            throw ex; // Lança a exceção para tratamento superior
        }
    }

    // Método para obter todas as conversas no banco de dados
    public List<Conversa> readConversas() throws SQLException {
        List<Conversa> conversas = new ArrayList<>();

        try (Connection con = conexao.conexao(); PreparedStatement stmt = con.prepareStatement("SELECT * FROM conversas"); ResultSet rs = stmt.executeQuery()) {
            // Executa a consulta SQL e itera sobre o conjunto de resultados
            while (rs.next()) {
                Conversa c = new Conversa();
                // Preenche o objeto Conversa com os dados do resultado da consulta
                c.setConversa_id(rs.getInt("conversa_id"));
                c.setUsuario1_id(rs.getInt("usuario1_id"));
                c.setUsuario2_id(rs.getInt("usuario2_id"));
                c.setApelido(rs.getString("apelido"));

                conversas.add(c); // Adiciona a conversa à lista
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler conversas: " + ex.getMessage());
            throw ex; // Lança a exceção para tratamento superior
        }

        return conversas; // Retorna a lista de conversas
    }

    // Método para obter uma conversa pelo ID no banco de dados
    public Conversa getConversasById(int conversaId) throws SQLException {
        try (Connection con = conexao.conexao(); PreparedStatement stmt = con.prepareStatement("SELECT * FROM conversas WHERE conversa_id = ?")) {
            stmt.setInt(1, conversaId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Conversa c = new Conversa();
                    // Preenche o objeto Conversa com os dados do resultado da consulta
                    c.setConversa_id(rs.getInt("conversa_id"));
                    c.setUsuario1_id(rs.getInt("usuario1_id"));
                    c.setUsuario2_id(rs.getInt("usuario2_id"));
                    c.setApelido(rs.getString("apelido"));
                    return c;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao obter conversa por ID: " + ex.getMessage());
            throw ex; // Lança a exceção para tratamento superior
        }

        return null; // Retorna nulo se nenhuma conversa for encontrada
    }

    // Método para atualizar uma conversa no banco de dados
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
            throw ex; // Lança a exceção para tratamento superior
        }
    }

    // Método para excluir uma conversa pelo ID no banco de dados
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
            throw ex; // Lança a exceção para tratamento superior
        }
    }

    // Método para obter todas as conversas no banco de dados
    public List<Conversa> getAllConvercomentariosas() {
        String query = "SELECT * FROM Conversas";
        List<Conversa> conversas = new ArrayList<>();

        try (Connection con = conexao.conexao(); PreparedStatement preparedStatement = con.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Conversa conversa = new Conversa();
                // Preenche o objeto Conversa com os dados do resultado da consulta
                conversa.setConversa_id(resultSet.getInt("conversa_id"));
                conversa.setUsuario1_id(resultSet.getInt("usuario1_id"));
                conversa.setUsuario2_id(resultSet.getInt("usuario2_id"));
                conversa.setApelido(resultSet.getString("apelido"));
                conversas.add(conversa); // Adiciona a conversa à lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trata exceção imprimindo o rastreamento no console
        }

        return conversas; // Retorna a lista de conversas
    }
}
