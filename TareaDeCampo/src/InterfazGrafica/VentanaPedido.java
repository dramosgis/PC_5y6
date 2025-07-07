package InterfazGrafica;

import SistemaDeEntrega.Cliente;
import SistemaDeEntrega.GestorDeEntregas;

import javax.swing.*;
import java.awt.*;

public class VentanaPedido extends JFrame {

    private GestorDeEntregas gestor; // Referencia al gestor para registrar el pedido
    private Cliente cliente;         // Cliente asociado al pedido

    private JTextField campoProducto;
    private JTextField campoCantidad;
    private JTextField campoFecha;
    private JButton botonRegistrarPedido;

    public VentanaPedido(GestorDeEntregas gestor, Cliente cliente) {
        this.gestor = gestor;
        this.cliente = cliente;

        setTitle("Registrar Pedido");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        initComponentes(); // Se cargan los campos del formulario

        setVisible(true);
    }

    private void initComponentes() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Producto
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Producto:"), gbc);

        campoProducto = new JTextField();
        gbc.gridx = 1;
        add(campoProducto, gbc);

        // Cantidad
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Cantidad:"), gbc);

        campoCantidad = new JTextField();
        gbc.gridx = 1;
        add(campoCantidad, gbc);

        // Fecha
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Fecha (YYYY-MM-DD):"), gbc);

        campoFecha = new JTextField();
        gbc.gridx = 1;
        add(campoFecha, gbc);

        // Botón Registrar Pedido
        botonRegistrarPedido = new JButton("Registrar Pedido");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(botonRegistrarPedido, gbc);

        // Acción del botón que ejecuta el registro
        botonRegistrarPedido.addActionListener(e -> {
            registrarPedido();
        });
    }

    private void registrarPedido() {
        String producto = campoProducto.getText().trim();
        String fecha = campoFecha.getText().trim();
        int cantidad;

        // Validar que los campos no estén vacíos
        if (producto.isEmpty() || fecha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos");
            return;
        }

        // Validar que la cantidad sea numérica
        try {
            cantidad = Integer.parseInt(campoCantidad.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida");
            return;
        }

        // Se genera un ID único para el pedido usando timestamp
        String idPedido = generarIdPedido();

        // Se registra el pedido directamente usando el gestor
        gestor.registrarPedidoSinExcepcion(idPedido, cliente, producto, cantidad, fecha, "pendiente");

        JOptionPane.showMessageDialog(this, "Pedido registrado correctamente");
        limpiarCampos(); // Se limpian los campos luego del registro
    }

    private String generarIdPedido() {
        // Se usa el tiempo actual para generar un ID único tipo "PED{milisegundos}"
        return "PED" + System.currentTimeMillis();
    }

    private void limpiarCampos() {
        // Limpia los campos del formulario
        campoProducto.setText("");
        campoCantidad.setText("");
        campoFecha.setText("");
    }
}

