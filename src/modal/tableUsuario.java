// Importa as classes necessárias para a implementação da tabela
package modal;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

// Classe que representa um modelo de tabela para a entidade Usuario
public class tableUsuario extends AbstractTableModel {

    // Lista que armazena os dados da tabela
    public List<Usuario> dados = new ArrayList<>();

    // Array de Strings que representa o nome das colunas da tabela
    private String[] colunas = {"usuario_id", "login", "email"};

    // Sobrescreve o método para obter o nome da coluna
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    // Sobrescreve o método para obter o número de linhas na tabela
    @Override
    public int getRowCount() {
        return dados.size();
    }

    // Sobrescreve o método para obter o número de colunas na tabela
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    // Sobrescreve o método para obter o valor de uma célula na tabela
    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            // Caso a coluna seja 0, retorna o valor da propriedade usuario_id
            case 0:
                return dados.get(linha).getUsuarioId();
            // Caso a coluna seja 1, retorna o valor da propriedade login
            case 1:
                return dados.get(linha).getLogin();
            // Caso a coluna seja 2, retorna o valor da propriedade email
            case 2:
                return dados.get(linha).getEmail();
        }
        // Retorna null se a coluna não for encontrada
        return null;
    }

    // Sobrescreve o método para obter a classe da coluna
    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            // Para as colunas 0, 1 e 2, retorna a classe String
            case 0:
            case 1:
            case 2:
                return String.class;
        }
        // Retorna null se a coluna não for encontrada
        return null;
    }

    // Método para adicionar uma nova linha à tabela
    public void addRow(Usuario q) {
        // Adiciona o objeto Usuario à lista de dados
        this.dados.add(q);
        // Notifica a tabela sobre a inserção da nova linha
        fireTableRowsInserted(dados.size() - 1, dados.size() - 1);
    }
}
