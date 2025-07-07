package InterfazGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import SistemaDeEntrega.GestorDeEntregas;
import SistemaDeEntrega.Pedido;
import SistemaDeEntrega.Cliente;

public class PanelClientesYProductos extends JPanel {

    private GestorDeEntregas gestor; // Acceso al gestor para consultar clientes y pedidos
    private DefaultTableModel modelo;
    private JTable tabla;
    private JTextField campoBuscar;

    public PanelClientesYProductos(GestorDeEntregas gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout());

        agregarBarraBusqueda(); // Se agregan los controles de búsqueda
        inicializarTabla();     // Se carga la tabla con todos los pedidos al inicio
    }

    private void agregarBarraBusqueda() {
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));

        campoBuscar = new JTextField(15);
        JButton botonBuscar = new JButton("Buscar por ID");

        // Si se ingresa un ID, se muestra solo ese cliente; si está vacío, se muestran todos
        botonBuscar.addActionListener((ActionEvent e) -> {
            String idBuscado = campoBuscar.getText().trim();
            if (!idBuscado.isEmpty()) {
                actualizarTablaConCliente(idBuscado);
            } else {
                actualizarTablaConTodos();
            }
        });

        panelSuperior.add(new JLabel("ID Cliente:"));
        panelSuperior.add(campoBuscar);
        panelSuperior.add(botonBuscar);

        add(panelSuperior, BorderLayout.NORTH);
    }

    private void inicializarTabla() {
        String[] columnas = {"ID Cliente", "Nombre", "Producto", "Cantidad"};
        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        actualizarTablaConTodos(); // Se llena la tabla con todos los pedidos
    }

    private void actualizarTablaConTodos() {
        modelo.setRowCount(0); // Limpiar tabla antes de cargar nuevos datos
        for (Pedido pedido : gestor.getPedidos()) {
            Cliente cliente = pedido.getCliente(); // Se toma el cliente asociado al pedido
            modelo.addRow(new Object[]{
                cliente.getIdCliente(),
                cliente.getNombreCliente(),
                pedido.getProducto(),
                pedido.getCantidad()
            });
        }
    }

    private void actualizarTablaConCliente(String idCliente) {
        modelo.setRowCount(0);
        boolean encontrado = false;

        // Se recorren los pedidos y se filtran solo los del cliente con ID buscado
        for (Pedido pedido : gestor.getPedidos()) {
            Cliente cliente = pedido.getCliente();
            if (cliente.getIdCliente().equalsIgnoreCase(idCliente)) {
                encontrado = true;
                modelo.addRow(new Object[]{
                    cliente.getIdCliente(),
                    cliente.getNombreCliente(),
                    pedido.getProducto(),
                    pedido.getCantidad()
                });
            }
        }

        // Si no se encontró el cliente, se muestra un mensaje de alerta
        if (!encontrado) {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado o sin pedidos.");
        }
    }
}
