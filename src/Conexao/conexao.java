package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe de conexão com o banco de dados MySQL.
 *
 * @author Matheus
 */
public class conexao {

    // Informações de conexão
    private static String drive = "com.mysql.jdbc.Driver";
    public static String nomeBanco = "MARIADB";
    public static String url = "jdbc:mysql://10.9.0.253:3306/PortDoHomen";
    public static String user = "root";
    public static String pass = "CariocaAK.47";

    // Objetos de conexão com o banco de dados
    public static Connection con;
    public static Statement stam;
    public static ResultSet rs;

    /**
     * Construtor padrão da classe.
     */
    public conexao() {

    }

    /**
     * Estabelece a conexão com o banco de dados MySQL.
     *
     * @return Objeto Connection representando a conexão estabelecida.
     * @throws SQLException Em caso de falha na conexão.
     */
    public static Connection conexao() throws SQLException {
        try {
            // Configura o driver JDBC
            System.setProperty("jdbc.Driver", drive);

            // Estabelece a conexão com o banco de dados
            con = DriverManager.getConnection(url, user, pass);

            System.out.println("Conectado com sucesso");
            // new arquivoLog(data.getDataHoraString()+" - Conexão realizada com sucesso");
        } catch (SQLException ex) {
            // Trata exceção em caso de falha na conexão
            JOptionPane.showMessageDialog(null, "Falha na conexão com o Banco de Dados: \n" + ex.getMessage());
            System.out.println("Falha na conexão com o Banco de Dados: \n" + ex.getMessage());
        }
        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * Desconecta do banco de dados.
     *
     * @param conn Objeto Connection a ser desconectado.
     */
    public static void desconecta(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Desconecta do banco de dados.
     *
     * @param conn Objeto Connection a ser desconectado.
     * @param stmt Objeto PreparedStatement a ser desconectado.
     */
    public static void desconecta(Connection conn, PreparedStatement stmt) {
        desconecta(conn);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            // Trata exceção em caso de falha no fechamento do Statement
        }
    }

    /**
     * Desconecta do banco de dados.
     *
     * @param conn Objeto Connection a ser desconectado.
     * @param stmt Objeto PreparedStatement a ser desconectado.
     * @param rs Objeto ResultSet a ser desconectado.
     */
    public static void desconecta(Connection conn, PreparedStatement stmt, ResultSet rs) {
        desconecta(conn, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            // Trata exceção em caso de falha no fechamento do ResultSet
        }
    }

    /**
     * Executa uma consulta SQL no banco de dados.
     *
     * @param sql A consulta SQL a ser executada.
     */
    public static void executaSQL(String sql) {
        try {
            // Cria um Statement para execução da consulta
            stam = conexao().createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);

            // Executa a consulta e obtém o ResultSet
            rs = stam.executeQuery(sql);
            //new arquivoLog(data.getDataHoraString()+" - Execusão do SQL realizada com sucesso");
        } catch (SQLException ex) {
            // Trata exceção em caso de falha na execução da consulta SQL
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL: \n" + ex.getMessage());
        }
    }
}
