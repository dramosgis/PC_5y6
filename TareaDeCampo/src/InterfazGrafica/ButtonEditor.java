package InterfazGrafica;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.EventObject;
import java.util.UUID;

import SistemaDeEntrega.*;

public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private String label;
    private boolean clicked;
    private GestorDeEntregas gestor; // Se guarda una referencia al gestor para acceder a los pedidos
    private Repartidor repartidor;   // Repartidor que se usará para registrar la entrega

    public ButtonEditor(JCheckBox checkBox, GestorDeEntregas gestor, Repartidor repartidor) {
        super(checkBox);
        this.gestor = gestor;
        this.repartidor = repartidor;
        button = new JButton();
        button.setOpaque(true);

        // Al hacer clic en el botón, se termina la edición de la celda para ejecutar la lógica
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        // Se configura el botón con el texto correspondiente cuando se selecciona la celda
        label = (value == null) ? "Tomar" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            // Al hacer clic, se obtiene el ID del pedido desde la fila seleccionada
            JTable table = (JTable) button.getParent();
            int selectedRow = table.getSelectedRow();
            String idPedido = table.getValueAt(selectedRow, 0).toString();

            // Se busca el pedido por su ID en el gestor
            Pedido pedido = gestor.buscarPedido(idPedido);
            if (pedido != null) {
                // Si se encuentra, se genera un ID único para la entrega y se registra
                String idEntrega = generarIdUnico();
                String fechaEntrega = LocalDate.now().toString();
                gestor.registrarEntregaSinExcepcion(idEntrega, pedido, repartidor, fechaEntrega, "asignada");

                // Se muestra mensaje de confirmación (esto sí es parte visual)
                JOptionPane.showMessageDialog(button, "Pedido asignado correctamente.");
            }
        }
        clicked = false; // Se reinicia el estado del clic para evitar repeticiones
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false; // Se asegura que no quede el clic activado
        return super.stopCellEditing();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true; // Siempre se permite editar la celda
    }

    private String generarIdUnico() {
        // Se genera un ID de entrega con formato "E" seguido de un código corto aleatorio
        return "E" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}
