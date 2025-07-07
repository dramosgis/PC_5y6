package InterfazGrafica;

import SistemaDeEntrega.Cliente;
import SistemaDeEntrega.GestorDeEntregas;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JTextField campoId, campoNombre, campoDireccion, campoTelefono;
    private JButton botonRegistrar, botonMenu;
    private GestorDeEntregas gestor; // Se usa para registrar clientes y acceder al resto del sistema

    public VentanaPrincipal(GestorDeEntregas gestor) {
        this.gestor = gestor;

        setTitle("Sistema de Entregas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setLayout(new BorderLayout());

        agregarTitulo();      // Agrega el título superior
        agregarFormulario();  // Carga el formulario de registro y acciones
        agregarBotonMenu();   // Agrega el botón de menú para repartidor y administrador

        setVisible(true);
    }

    private void agregarTitulo() {
        JLabel titulo = new JLabel("Bienvenido al Sistema de Compra FAST", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        getContentPane().add(titulo, BorderLayout.NORTH);
    }

    private void agregarFormulario() {
        JPanel panelCentral = new JPanel(null);
        int labelWidth = 100, fieldWidth = 250, height = 25;

        // Campos de entrada para registrar cliente
        JLabel lblId = new JLabel("DNI:");
        lblId.setBounds(83, 30, labelWidth, height);
        campoId = new JTextField();
        campoId.setBounds(193, 30, fieldWidth, height);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(83, 70, labelWidth, height);
        campoNombre = new JTextField();
        campoNombre.setBounds(193, 70, fieldWidth, height);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(83, 110, labelWidth, height);
        campoDireccion = new JTextField();
        campoDireccion.setBounds(193, 110, fieldWidth, height);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(83, 150, labelWidth, height);
        campoTelefono = new JTextField();
        campoTelefono.setBounds(193, 150, fieldWidth, height);

        botonRegistrar = new JButton("Registrar y Hacer Pedido");
        botonRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        botonRegistrar.setBounds(83, 210, 200, 30);

        // Acción del botón: valida campos, registra cliente y abre formulario de pedido
        botonRegistrar.addActionListener(e -> {
            String id = campoId.getText().trim();
            String nombre = campoNombre.getText().trim();
            String direccion = campoDireccion.getText().trim();
            String telefono = campoTelefono.getText().trim();

            if (id.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.");
                return;
            }

            if (!id.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 dígitos.");
                return;
            }

            gestor.registrarClienteSinExcepcion(id, nombre, direccion, telefono); // Registro sin validaciones
            JOptionPane.showMessageDialog(this, "Cliente registrado correctamente.");
            new FormularioPedido(gestor, id); // Se abre ventana para registrar pedido

            // Se limpian los campos después del registro
            campoId.setText("");
            campoNombre.setText("");
            campoDireccion.setText("");
            campoTelefono.setText("");
        });

        // Permite al usuario ingresar su DNI si ya está registrado
        JButton botonYaRegistrado = new JButton("¿Ya estás registrado?");
        botonYaRegistrado.setFont(new Font("Tahoma", Font.PLAIN, 12));
        botonYaRegistrado.setBounds(83, 259, 200, 28);

        botonYaRegistrado.addActionListener(e -> {
            String dni = JOptionPane.showInputDialog(this, "Ingrese su DNI:");

            if (dni != null && !dni.trim().isEmpty()) {
                if (gestor.buscarCliente(dni.trim()) != null) {
                    new FormularioPedido(gestor, dni.trim()); // Va directo al formulario de pedido
                } else {
                    JOptionPane.showMessageDialog(this, "DNI no registrado.");
                }
            }
        });
        panelCentral.add(botonYaRegistrado);

        // Permite que un cliente vea sus pedidos usando su DNI
        JButton botonBuscarPedidoCliente = new JButton("Encuentre su pedido");
        botonBuscarPedidoCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
        botonBuscarPedidoCliente.setBounds(309, 211, 185, 28);
        botonBuscarPedidoCliente.addActionListener(e -> {
            JFrame ventana = new JFrame("Buscar Pedido del Cliente");
            ventana.setSize(800, 300);
            ventana.setLocationRelativeTo(null);
            ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventana.getContentPane().add(new PanelBuscarPedidoCliente(gestor));
            ventana.setVisible(true);
        });
        panelCentral.add(botonBuscarPedidoCliente);

        // Botón para salir del sistema
        JButton botonSalir = new JButton("Salir");
        botonSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
        botonSalir.setBounds(309, 259, 185, 28);
        botonSalir.addActionListener(e -> System.exit(0));
        panelCentral.add(botonSalir);

        // Se agregan todos los elementos al panel principal
        panelCentral.add(lblId);
        panelCentral.add(campoId);
        panelCentral.add(lblNombre);
        panelCentral.add(campoNombre);
        panelCentral.add(lblDireccion);
        panelCentral.add(campoDireccion);
        panelCentral.add(lblTelefono);
        panelCentral.add(campoTelefono);
        panelCentral.add(botonRegistrar);

        getContentPane().add(panelCentral, BorderLayout.CENTER);
    }

    private void agregarBotonMenu() {
        botonMenu = new JButton("☰");
        botonMenu.setFont(new Font("SansSerif", Font.BOLD, 18));
        botonMenu.setFocusPainted(false);
        botonMenu.setMargin(new Insets(5, 10, 5, 10));

        JPanel panelSuperiorDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSuperiorDerecho.add(botonMenu);
        getContentPane().add(panelSuperiorDerecho, BorderLayout.EAST);

        // Menú con acceso para el panel de repartidor y de administrador
        JPopupMenu menu = new JPopupMenu();
        JMenuItem opcionRepartidor = new JMenuItem("Acceso Repartidor");
        JMenuItem opcionAdministrador = new JMenuItem("Acceso Administrador");

        opcionRepartidor.addActionListener(e -> new VentanaRepartidor(gestor));
        opcionAdministrador.addActionListener(e -> new VentanaAdministrador(gestor));

        menu.add(opcionRepartidor);
        menu.add(opcionAdministrador);

        botonMenu.addActionListener(e -> menu.show(botonMenu, 0, botonMenu.getHeight()));
    }
}


