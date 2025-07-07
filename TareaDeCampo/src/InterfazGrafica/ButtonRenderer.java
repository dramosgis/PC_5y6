package InterfazGrafica;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


//Esta clase solo se encarga de mostrar un botón dentro de la celda, sin manejar ninguna acción
public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        setText((value == null) ? "Tomar" : value.toString());
        return this;
    }
}
