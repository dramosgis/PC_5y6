package InterfazGrafica;

import SistemaDeEntrega.GestorDeEntregas;
import javax.swing.*;
import java.awt.*;

public class VentanaAdministrador extends JFrame {

    private GestorDeEntregas gestor; // Referencia al gestor principal del sistema
    private JPanel panelContenido;   // Panel donde se cargan dinámicamente las distintas vistas

    public VentanaAdministrador(GestorDeEntregas gestor) {
        this.gestor = gestor;

        setTitle("Panel de Administración");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        agregarBotones();        // Se agregan los botones de navegación superior
        agregarPanelContenido(); // Se crea el área central donde se mostrarán los paneles

        setVisible(true);
    }

    private void agregarBotones() {
        JPanel botones = new JPanel(new GridLayout(1, 5));

        // Cada botón carga un panel relacionado a una función del sistema

        JButton botonEstadoPedidos = new JButton("Estado de Pedidos");
        botonEstadoPedidos.addActionListener(e -> mostrarPanel(new PanelEstadoPedidos(gestor)));
        botones.add(botonEstadoPedidos);

        JButton botonClientesYProductos = new JButton("Clientes y Productos");
        botonClientesYProductos.addActionListener(e -> mostrarPanel(new PanelClientesYProductos(gestor)));
        botones.add(botonClientesYProductos);

        JButton botonBuscarPedido = new JButton("Buscar Pedido");
        botonBuscarPedido.addActionListener(e -> mostrarPanel(new PanelBuscarPedido(gestor)));
        botones.add(botonBuscarPedido);

        JButton botonRegistrarRepartidor = new JButton("Registrar Repartidor");
        botonRegistrarRepartidor.addActionListener(e -> mostrarPanel(new PanelRegistroRepartidor(gestor)));
        botones.add(botonRegistrarRepartidor);

        JButton botonListaRepartidores = new JButton("Lista de Repartidores");
        botonListaRepartidores.addActionListener(e -> mostrarPanel(new PanelListaRepartidores(gestor)));
        botones.add(botonListaRepartidores);

        add(botones, BorderLayout.NORTH); // Se colocan los botones en la parte superior
    }

    private void agregarPanelContenido() {
        // Se prepara el espacio central donde se mostrará cada sección seleccionada
        panelContenido = new JPanel(new BorderLayout());
        add(panelContenido, BorderLayout.CENTER);
    }

    private void mostrarPanel(JPanel nuevoPanel) {
        // Método que actualiza dinámicamente el panel central con el panel recibido
        panelContenido.removeAll();
        panelContenido.add(nuevoPanel, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }
}


