package InterfazGrafica;

import SistemaDeEntrega.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelBuscarPedido extends JPanel {

    private GestorDeEntregas gestor; // Referencia al gestor para buscar pedidos y obtener entregas
    private JTextField campoBusqueda;
    private JTable tablaResultado;

    public PanelBuscarPedido(GestorDeEntregas gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());

        panelSuperior.add(new JLabel("Buscar por ID de Pedido: "));
        campoBusqueda = new JTextField(15);
        panelSuperior.add(campoBusqueda);

        JButton botonBuscar = new JButton("Buscar");
        // Al hacer clic, se ejecuta el método que busca el pedido
        botonBuscar.addActionListener(e -> buscarPedido());
        panelSuperior.add(botonBuscar);

        add(panelSuperior, BorderLayout.NORTH);

        tablaResultado = new JTable(); // Tabla para mostrar el resultado de la búsqueda
        JScrollPane scrollPane = new JScrollPane(tablaResultado);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void buscarPedido() {
        String idPedido = campoBusqueda.getText().trim(); // Se toma el ID ingresado
        Pedido pedido = gestor.buscarPedido(idPedido); // Se busca el pedido en el gestor

        String[] columnas = {"ID Pedido", "ID Cliente", "Teléfono Cliente", "Producto", "Cantidad", "ID Repartidor", "Nombre Repartidor", "Teléfono Repartidor", "Fecha de Pedido", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        if (pedido != null) {
            Cliente cliente = pedido.getCliente(); // Se accede al cliente asociado al pedido

            // Datos por defecto por si el pedido aún no tiene entrega asignada
            String idRepartidor = "-";
            String nombreRepartidor = "-";
            String telefonoRepartidor = "-";
            String estadoEntrega = "Sin asignar";

            // Se busca si hay una entrega registrada para este pedido
            ArrayList<Entrega> entregas = gestor.getEntregas();
            for (Entrega entrega : entregas) {
                if (entrega.getIdPedido().equals(pedido.getIdPedido())) {
                    Repartidor repartidor = gestor.buscarRepartidor(entrega.getIdRepartidor());
                    if (repartidor != null) {
                        idRepartidor = repartidor.getIdRepartidor();
                        nombreRepartidor = repartidor.getNombreRepartidor();
                        telefonoRepartidor = repartidor.getTelefonoRepartidor();
                    }
                    estadoEntrega = entrega.getEstadoEntrega(); // Se obtiene el estado actual de la entrega
                    break;
                }
            }

            // Se agrega una fila con los datos completos del pedido y su entrega (si hay)
            modelo.addRow(new Object[]{
                    pedido.getIdPedido(),
                    cliente.getIdCliente(),
                    cliente.getTelefonoCliente(),
                    pedido.getProducto(),
                    pedido.getCantidad(),
                    idRepartidor,
                    nombreRepartidor,
                    telefonoRepartidor,
                    pedido.getFechaPedido(),
                    estadoEntrega
            });
        } else {
            // Si no se encontró el pedido, se muestra un mensaje
            JOptionPane.showMessageDialog(this, "Pedido no encontrado.");
        }

        tablaResultado.setModel(modelo); // Se actualiza la tabla con los resultados
    }
}
