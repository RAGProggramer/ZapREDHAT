package dao;

import Conexao.conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modal.Usuario;

public class UsuarioDAO {

    // Método para criar a tabela "Usuarios" no banco de dados
    public void createUsuariosTable() {
        // Define o SQL para criar a tabela "Usuarios" com colunas específicas
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Usuarios ("
                + "usuario_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "login VARCHAR(50) NOT NULL,"
                + "senha VARCHAR(50) NOT NULL,"
                + "telefone VARCHAR(15),"
                + "email VARCHAR(100),"
                + "bio TEXT,"
                + "imagemPerfil longblob,"
                + "tema VARCHAR(50)"
                + ")";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexao.conexao(); // Obtém uma conexão com o banco de dados
            stmt = con.prepareStatement(createTableSQL); // Prepara a instrução SQL
            stmt.executeUpdate(); // Executa a instrução para criar a tabela
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime detalhes do erro, se ocorrer
        } finally {
            conexao.desconecta(con, stmt); // Desconecta do banco de dados, mesmo se ocorrer uma exceção
        }
    }

    // Método para inserir um novo usuário no banco de dados
    public void createUsuario(Usuario usuario) {
        // Define a consulta SQL para inserir um novo usuário
        String query = "INSERT INTO Usuarios (login, senha, telefone, email, bio, imagemPerfil, tema) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexao.conexao(); // Obtém uma conexão com o banco de dados
            stmt = con.prepareStatement(query); // Prepara a instrução SQL
            // Define os parâmetros da consulta com base no objeto Usuario fornecido
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getBio());
            stmt.setBytes(6, usuario.getImagemPerfil());
            stmt.setString(7, usuario.getTema());
            stmt.executeUpdate(); // Executa a instrução para inserir o usuário
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime detalhes do erro, se ocorrer
        } finally {
            conexao.desconecta(con, stmt); // Desconecta do banco de dados, mesmo se ocorrer uma exceção
        }
    }

    // Método para obter um usuário pelo ID
    public Usuario getUsuariosById(int usuarioId) {
        // Define a consulta SQL para obter um usuário por ID
        String query = "SELECT * FROM Usuarios WHERE usuario_id = ?";

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            con = conexao.conexao(); // Obtém uma conexão com o banco de dados
            stmt = con.prepareStatement(query); // Prepara a instrução SQL
            stmt.setInt(1, usuarioId); // Define o parâmetro da consulta
            resultSet = stmt.executeQuery(); // Executa a consulta

            if (resultSet.next()) {
                // Constrói um objeto Usuario com base nos dados do ResultSet
                Usuario usuario = new Usuario();
                usuario.setUsuarioId(resultSet.getInt("usuario_id"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setTelefone(resultSet.getString("telefone"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setBio(resultSet.getString("bio"));
                usuario.setImagemPerfil(resultSet.getBytes("imagemPerfil"));
                usuario.setTema(resultSet.getString("tema"));
                return usuario;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime detalhes do erro, se ocorrer
        } finally {
            conexao.desconecta(con, stmt, resultSet); // Desconecta do banco de dados, mesmo se ocorrer uma exceção
        }
        return null;
    }

    // Método para atualizar um usuário no banco de dados
    public void updateUsuario(Usuario usuario) {
        // Define a consulta SQL para atualizar um usuário com base no ID
        String query = "UPDATE Usuarios SET login = ?, senha = ?, telefone = ?, email = ?, bio = ?, imagemPerfil = ?, tema = ? WHERE usuario_id = ?";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexao.conexao(); // Obtém uma conexão com o banco de dados
            stmt = con.prepareStatement(query); // Prepara a instrução SQL
            // Define os parâmetros da consulta com base no objeto Usuario fornecido
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getBio());
            stmt.setBytes(6, usuario.getImagemPerfil());
            stmt.setString(7, usuario.getTema());
            stmt.setInt(8, usuario.getUsuarioId()); // Define o parâmetro da consulta
            stmt.executeUpdate(); // Executa a instrução para atualizar o usuário
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime detalhes do erro, se ocorrer
        } finally {
            conexao.desconecta(con, stmt); // Desconecta do banco de dados, mesmo se ocorrer uma exceção
        }
    }

    // Método para excluir um usuário do banco de dados pelo ID
    public void deleteUsuario(int usuarioId) {
        // Define a consulta SQL para excluir um usuário com base no ID
        String query = "DELETE FROM Usuarios WHERE usuario_id = ?";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexao.conexao(); // Obtém uma conexão com o banco de dados
            stmt = con.prepareStatement(query); // Prepara a instrução SQL
            stmt.setInt(1, usuarioId); // Define o parâmetro da consulta
            stmt.executeUpdate(); // Executa a instrução para excluir o usuário
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime detalhes do erro, se ocorrer
        } finally {
            conexao.desconecta(con, stmt); // Desconecta do banco de dados, mesmo se ocorrer uma exceção
        }
    }

    // Método para obter todos os usuários do banco de dados
    public List<Usuario> getAllUsuarios() {
        // Define a consulta SQL para obter todos os usuários
        String query = "SELECT * FROM Usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            con = conexao.conexao(); // Obtém uma conexão com o banco de dados
            stmt = con.prepareStatement(query); // Prepara a instrução SQL
            resultSet = stmt.executeQuery(); // Executa a consulta

            while (resultSet.next()) {
                // Constrói objetos Usuario com base nos dados do ResultSet e adiciona à lista
                Usuario usuario = new Usuario();
                usuario.setUsuarioId(resultSet.getInt("usuario_id"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setTelefone(resultSet.getString("telefone"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setBio(resultSet.getString("bio"));
                usuario.setImagemPerfil(resultSet.getBytes("imagemPerfil"));
                usuario.setTema(resultSet.getString("tema"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Imprime detalhes do erro, se ocorrer
        } finally {
            conexao.desconecta(con, stmt, resultSet); // Desconecta do banco de dados, mesmo se ocorrer uma exceção
        }

        return usuarios; // Retorna a lista de usuários
    }
}
