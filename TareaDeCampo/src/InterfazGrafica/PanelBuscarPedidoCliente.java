package InterfazGrafica;

import SistemaDeEntrega.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelBuscarPedidoCliente extends JPanel {

    private GestorDeEntregas gestor; // Referencia al gestor para acceder a los pedidos
    private JTable tabla;
    private JTextField campoBuscar;

    public PanelBuscarPedidoCliente(GestorDeEntregas gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout());

        inicializarComponentes(); // Se cargan los campos y botón de búsqueda
    }

    private void inicializarComponentes() {
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        campoBuscar = new JTextField(15);
        JButton botonBuscar = new JButton("Buscar Pedido");

        // Al hacer clic se ejecuta la búsqueda del pedido
        botonBuscar.addActionListener(e -> buscarPedido());

        panelSuperior.add(new JLabel("ID de Pedido:"));
        panelSuperior.add(campoBuscar);
        panelSuperior.add(botonBuscar);

        add(panelSuperior, BorderLayout.NORTH);

        tabla = new JTable(); // Tabla que mostrará el pedido encontrado
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);
    }

    private void buscarPedido() {
        String idPedido = campoBuscar.getText().trim(); // Se obtiene el ID ingresado
        Pedido pedido = gestor.buscarPedido(idPedido); // Se busca el pedido en el sistema

        String[] columnas = {"ID Pedido", "Nombre Cliente", "Dirección", "Teléfono", "Producto", "Cantidad", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        if (pedido != null) {
            Cliente cliente = pedido.getCliente(); // Se accede a los datos del cliente asociado
            modelo.addRow(new Object[]{
                pedido.getIdPedido(),
                cliente.getNombreCliente(),
                cliente.getDireccionCliente(),
                cliente.getTelefonoCliente(),
                pedido.getProducto(),
                pedido.getCantidad(),
                pedido.getEstado() // Se carga el estado actual del pedido
            });
        } else {
            // Si no se encuentra, se muestra mensaje
            JOptionPane.showMessageDialog(this, "Pedido no encontrado.");
        }

        tabla.setModel(modelo); // Se actualiza la tabla con la información
    }
}
