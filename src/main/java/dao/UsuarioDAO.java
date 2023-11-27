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

    public void createUsuariosTable() {
        // Define o SQL para criar a tabela "Usuarios" com colunas específicas
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Usuarios ("
                + "usuario_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "login VARCHAR(50) NOT NULL,"
                + "senha VARCHAR(50) NOT NULL,"
                + "telefone VARCHAR(15),"
                + "email VARCHAR(100),"
                + "bio TEXT,"
                + "imagemPerfil VARCHAR(255),"
                + "tema VARCHAR(50)"
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

    public void createUsuario(Usuario usuario) {
        // Define a consulta SQL para inserir um novo usuário
        String query = "INSERT INTO Usuarios (login, senha, telefone, email, bio, imagemPerfil, tema) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = conexao.conexao();
            stmt = con.prepareStatement(query);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getBio());
            stmt.setBytes(6, usuario.getImagemPerfil());
            stmt.setString(7, usuario.getTema());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conexao.desconecta(con, stmt);
        }
    }

    public Usuario getUsuarioById(int usuarioId) {
        // Define a consulta SQL para obter um usuário por ID
        String query = "SELECT * FROM Usuarios WHERE usuario_id = ?";
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        try {
            con = conexao.conexao();
            stmt = con.prepareStatement(query);
            stmt.setInt(1, usuarioId);
            resultSet = stmt.executeQuery();

            if (resultSet.next()) {
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
            ex.printStackTrace();
        } finally {
            conexao.desconecta(con, stmt, resultSet);
        }
        return null;
    }

    public void updateUsuario(Usuario usuario) {
    // Define a consulta SQL para atualizar um usuário com base no ID
    String query = "UPDATE Usuarios SET login = ?, senha = ?, telefone = ?, email = ?, bio = ?, imagemPerfil = ?, tema = ? WHERE usuario_id = ?";
    
    Connection con = null;
    PreparedStatement stmt = null;

    try {
        con = conexao.conexao();
        stmt = con.prepareStatement(query);
        stmt.setString(1, usuario.getLogin());
        stmt.setString(2, usuario.getSenha());
        stmt.setString(3, usuario.getTelefone());
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getBio());
        stmt.setBytes(6, usuario.getImagemPerfil());
        stmt.setString(7, usuario.getTema());
        stmt.setInt(8, usuario.getUsuarioId());
        stmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        conexao.desconecta(con, stmt);
    }
}

public void deleteUsuario(int usuarioId) {
    // Define a consulta SQL para excluir um usuário com base no ID
    String query = "DELETE FROM Usuarios WHERE usuario_id = ?";
    
    Connection con = null;
    PreparedStatement stmt = null;

    try {
        con = conexao.conexao();
        stmt = con.prepareStatement(query);
        stmt.setInt(1, usuarioId);
        stmt.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        conexao.desconecta(con, stmt);
    }
}

public List<Usuario> getAllUsuarios() {
    // Define a consulta SQL para obter todos os usuários
    String query = "SELECT * FROM Usuarios";
    List<Usuario> usuarios = new ArrayList<>();

    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet resultSet = null;

    try {
        con = conexao.conexao();
        stmt = con.prepareStatement(query);
        resultSet = stmt.executeQuery();

        while (resultSet.next()) {
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
        ex.printStackTrace();
    } finally {
        conexao.desconecta(con, stmt, resultSet);
    }

    return usuarios; // Retorna a lista de usuários
}

}
