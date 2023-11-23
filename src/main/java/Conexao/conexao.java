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
 *
 * @author Matheus
 */
public class conexao {

    private static String drive = "com.mysql.jdbc.Driver";
    public static String nomeBanco = "MARIADB";
    public static String url = "jdbc:mysql://10.9.0.253:3306/PortDoHomen";
    public static String user = "root";
    public static String pass = "CariocaAK.47";
    public static Connection con;
    public static Statement stam;
    public static ResultSet rs;

    public conexao() {

    }

    public static Connection conexao() throws SQLException {
        try {
            System.setProperty("jdbc.Driver", drive);
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado com sucesso");
            // new arquivoLog(data.getDataHoraString()+" - Conexão realizada com sucesso");
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Falha na conexão com o Banco de Dados: \n" + ex.getMessage());
            System.out.println("Falha na conexão com o Banco de Dados: \n" + ex.getMessage());
        }
        return DriverManager.getConnection(url, user, pass);
    }

    public static void desconecta(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void desconecta(Connection conn, PreparedStatement stmt) {
        desconecta(conn);

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void desconecta(Connection conn, PreparedStatement stmt, ResultSet rs) {
        desconecta(conn, stmt);

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void executaSQL(String sql) {
        try {
            stam = conexao().createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stam.executeQuery(sql);
            //new arquivoLog(data.getDataHoraString()+" - Execusão do SQL realizada com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao execurar SQL: \n" + ex.getMessage());

        }
    }

}
