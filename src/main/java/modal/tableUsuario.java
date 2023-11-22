package modal;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class tableUsuario extends AbstractTableModel {
    public List<Usuario> dados = new ArrayList<>();
    private String[] colunas = {"usuario_id", "login", "email"};

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getUsuarioId();
            case 1:
                return dados.get(linha).getLogin();
            case 2:
                return dados.get(linha).getEmail();
        }
        return null;
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
            case 1:
            case 2:
                return String.class;
        }
        return null;
    }

    public void addRow(Usuario q) {
        this.dados.add(q);
        fireTableRowsInserted(dados.size() - 1, dados.size() - 1);
    }
}
