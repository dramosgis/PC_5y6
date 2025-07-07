package InterfazGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import SistemaDeEntrega.*;

public class PanelEstadoPedidos extends JPanel {

    private GestorDeEntregas gestor; // Referencia al gestor para acceder a pedidos y entregas

    public PanelEstadoPedidos(GestorDeEntregas gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout());

        // Se crean dos pestañas: una para pedidos asignados y otra para no asignados
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Asignados", crearTablaConEntrega());
        tabs.addTab("Sin Asignar", crearTablaSinEntrega());

        add(tabs, BorderLayout.CENTER);
    }

    private JScrollPane crearTablaConEntrega() {
        // Tabla con pedidos que ya tienen entrega asignada
        String[] columnas = {"ID Pedido", "ID Cliente", "Producto", "Cantidad", "Repartidor", "Tel. Repartidor", "Fecha de Pedido", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Entrega entrega : gestor.getEntregas()) {
            Pedido pedido = gestor.buscarPedido(entrega.getIdPedido());
            Repartidor repartidor = gestor.buscarRepartidor(entrega.getIdRepartidor());

            // Solo se agregan pedidos que estén correctamente asociados a un repartidor
            if (pedido != null && repartidor != null) {
                modelo.addRow(new Object[]{
                    pedido.getIdPedido(),
                    pedido.getCliente().getIdCliente(),
                    pedido.getProducto(),
                    pedido.getCantidad(),
                    repartidor.getNombreRepartidor(),
                    repartidor.getTelefonoRepartidor(),
                    pedido.getFechaPedido(),
                    entrega.getEstadoEntrega()
                });
            }
        }

        JTable tabla = new JTable(modelo);
        return new JScrollPane(tabla); // Se devuelve la tabla dentro de un scroll
    }

    private JScrollPane crearTablaSinEntrega() {
        // Tabla con pedidos que no tienen entrega registrada
        String[] columnas = {"ID Pedido", "ID Cliente", "Producto", "Cantidad","Fecha de Pedido", "Estado Pedido"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        ArrayList<Pedido> pedidos = gestor.getPedidos();
        ArrayList<Entrega> entregas = gestor.getEntregas();
        ArrayList<String> idsConEntrega = new ArrayList<>();

        // Se guardan los IDs de pedidos que ya tienen entrega
        for (Entrega entrega : entregas) {
            idsConEntrega.add(entrega.getIdPedido());
        }

        // Se recorren todos los pedidos y se agregan solo los que no tienen entrega
        for (Pedido pedido : pedidos) {
            if (!idsConEntrega.contains(pedido.getIdPedido())) {
                modelo.addRow(new Object[]{
                    pedido.getIdPedido(),
                    pedido.getCliente().getIdCliente(),
                    pedido.getProducto(),
                    pedido.getCantidad(),
                    pedido.getFechaPedido(),
                    pedido.getEstado()
                });
            }
        }

        JTable tabla = new JTable(modelo);
        return new JScrollPane(tabla);
    }
}
