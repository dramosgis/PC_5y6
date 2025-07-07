package InterfazGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

import SistemaDeEntrega.*;

public class VentanaRepartidor extends JFrame {

    private JTextField campoId, campoNombre;
    private JButton botonValidar;
    private GestorDeEntregas gestor; // Se usa para buscar repartidores, pedidos y entregas
    private JTable tablaEntregas, tablaPedidosDisponibles;
    private Repartidor repartidorAutenticado; // Guarda el repartidor que validó su acceso

    public VentanaRepartidor(GestorDeEntregas gestor) {
        this.gestor = gestor;

        setTitle("Acceso Repartidor");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        agregarComponentes(); // Carga los campos, botones y tablas

        setVisible(true);
    }

    private void agregarComponentes() {
        JLabel lblId = new JLabel("ID Repartidor:");
        lblId.setBounds(40, 30, 120, 25);
        campoId = new JTextField();
        campoId.setBounds(160, 30, 180, 25);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(40, 70, 120, 25);
        campoNombre = new JTextField();
        campoNombre.setBounds(160, 70, 180, 25);

        botonValidar = new JButton("Validar Datos");
        botonValidar.setBounds(370, 50, 150, 30);

        // Acción que valida si el repartidor existe por ID y nombre
        botonValidar.addActionListener((ActionEvent e) -> {
            String id = campoId.getText().trim();
            String nombre = campoNombre.getText().trim();

            Repartidor encontrado = null;
            for (Repartidor r : gestor.getRepartidores()) {
                if (r.getIdRepartidor().equals(id) && r.getNombreRepartidor().equalsIgnoreCase(nombre)) {
                    encontrado = r;
                    break;
                }
            }

            if (encontrado != null) {
                repartidorAutenticado = encontrado; // Se guarda el repartidor que se autenticó
                cargarEntregas(id);                 // Carga las entregas que ya tiene asignadas
                cargarPedidosDisponibles();         // Muestra pedidos sin asignar que puede tomar
            } else {
                JOptionPane.showMessageDialog(null, "Repartidor no encontrado o datos incorrectos.");
            }
        });

        add(lblId);
        add(campoId);
        add(lblNombre);
        add(campoNombre);
        add(botonValidar);

        tablaEntregas = new JTable(); // Tabla que mostrará las entregas ya asignadas
        JScrollPane scroll1 = new JScrollPane(tablaEntregas);
        scroll1.setBounds(40, 130, 700, 150);
        add(scroll1);

        tablaPedidosDisponibles = new JTable(); // Tabla con pedidos aún no tomados
        JScrollPane scroll2 = new JScrollPane(tablaPedidosDisponibles);
        scroll2.setBounds(40, 300, 700, 200);
        add(scroll2);
    }

    private void cargarEntregas(String idRepartidor) {
        ArrayList<Entrega> lista = gestor.getEntregasPorRepartidor().get(idRepartidor);

        String[] columnas = {"ID Entrega", "Cliente", "Dirección", "Teléfono", "Producto", "Cantidad", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        if (lista != null) {
            for (Entrega entrega : lista) {
                Pedido pedido = gestor.buscarPedido(entrega.getIdPedido());
                Cliente cliente = pedido.getCliente();

                // Se cargan los datos del pedido y cliente asociado a cada entrega
                modelo.addRow(new Object[]{
                    entrega.getIdEntrega(),
                    cliente.getNombreCliente(),
                    cliente.getDireccionCliente(),
                    cliente.getTelefonoCliente(),
                    pedido.getProducto(),
                    pedido.getCantidad(),
                    entrega.getEstadoEntrega()
                });
            }
        }

        tablaEntregas.setModel(modelo); // Se muestra la tabla con las entregas del repartidor
    }

    private void cargarPedidosDisponibles() {
        String[] columnas = {"ID Pedido", "Dirección", "Cliente", "Producto", "Cantidad", "Estado", "Acción"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Solo la columna "Acción" será editable (botón)
            }
        };

        ArrayList<Pedido> pedidos = gestor.getPedidos();
        ArrayList<Entrega> entregas = gestor.getEntregas();
        ArrayList<String> pedidosAsignados = new ArrayList<>();

        // Se registra el ID de todos los pedidos que ya tienen entrega
        for (Entrega entrega : entregas) {
            pedidosAsignados.add(entrega.getIdPedido());
        }

        // Se agregan a la tabla solo los pedidos que aún no han sido asignados
        for (Pedido pedido : pedidos) {
            if (!pedidosAsignados.contains(pedido.getIdPedido())) {
                modelo.addRow(new Object[]{
                    pedido.getIdPedido(),
                    pedido.getCliente().getDireccionCliente(),
                    pedido.getCliente().getNombreCliente(),
                    pedido.getProducto(),
                    pedido.getCantidad(),
                    pedido.getEstado(),
                    "Tomar" // Botón que el repartidor puede presionar para aceptar el pedido
                });
            }
        }

        tablaPedidosDisponibles.setModel(modelo);

        // Se configura la columna "Acción" para mostrar un botón funcional en cada fila
        tablaPedidosDisponibles.getColumn("Acción").setCellRenderer(new ButtonRenderer());
        tablaPedidosDisponibles.getColumn("Acción").setCellEditor(new ButtonEditor(new JCheckBox(), gestor, repartidorAutenticado));
    }
}

