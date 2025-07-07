package InterfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import SistemaDeEntrega.GestorDeEntregas;
import SistemaDeEntrega.Cliente;

public class FormularioPedido extends JFrame {

    private JTextField campoCantidad;
    private JButton botonConfirmar;
    private JComboBox<String> comboProducto;
    private GestorDeEntregas gestor; // Referencia al gestor para registrar el pedido
    private String idCliente;        // ID del cliente que realiza el pedido

    public FormularioPedido(GestorDeEntregas gestor, String idCliente) {
        this.gestor = gestor;
        this.idCliente = idCliente;

        setTitle("Registrar Pedido");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        agregarComponentes(); // Se cargan los campos y botones del formulario
        setVisible(true);
    }

    private void agregarComponentes() {
        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(80, 30, 100, 25);
        comboProducto = new JComboBox<>(new String[] {
            "Laptop", "Celular", "Tablet", "Audífonos", "Impresora", "Mouse", "Teclado"
        });
        comboProducto.setBounds(180, 30, 200, 25);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(80, 70, 100, 25);
        campoCantidad = new JTextField();
        campoCantidad.setBounds(180, 70, 200, 25);

        botonConfirmar = new JButton("Confirmar Pedido");
        botonConfirmar.setBounds(180, 120, 160, 30);

        // Lógica del botón para registrar el pedido en el sistema
        botonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String producto = (String) comboProducto.getSelectedItem();
                String cantidadStr = campoCantidad.getText().trim();

                if (producto == null || cantidadStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos.");
                    return;
                }

                try {
                    int cantidad = Integer.parseInt(cantidadStr); // Se valida que sea número
                    String nuevoIdPedido = generarIdPedido(); // Se genera ID único tipo P001, P002, etc.
                    Cliente cliente = gestor.buscarCliente(idCliente); // Se recupera el cliente desde el gestor
                    gestor.registrarPedidoSinExcepcion(nuevoIdPedido, cliente, producto, cantidad, "2025-07-01", "pendiente"); // Se registra el pedido
                    JOptionPane.showMessageDialog(null, "Pedido registrado correctamente con ID: " + nuevoIdPedido);
                    dispose(); // Se cierra la ventana
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Cantidad debe ser un número entero.");
                }
            }
        });

        add(lblProducto);
        add(comboProducto);
        add(lblCantidad);
        add(campoCantidad);
        add(botonConfirmar);
    }

    private String generarIdPedido() {
        // Se genera un ID secuencial para el nuevo pedido según la cantidad actual
        int total = gestor.getPedidos().size() + 1;
        return String.format("P%03d", total);
    }
}



