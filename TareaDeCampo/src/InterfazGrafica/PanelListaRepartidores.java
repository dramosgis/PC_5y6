package InterfazGrafica;

import SistemaDeEntrega.GestorDeEntregas;
import SistemaDeEntrega.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelListaRepartidores extends JPanel {

    private GestorDeEntregas gestor; // Referencia al gestor para acceder a la lista de repartidores
    private JTable tabla;
    private JTextField campoBuscar;

    public PanelListaRepartidores(GestorDeEntregas gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout());

        inicializarComponentes(); // Carga la tabla y el campo de búsqueda
    }

    private void inicializarComponentes() {
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        campoBuscar = new JTextField(15);
        JButton botonBuscar = new JButton("Buscar");

        // Al hacer clic se busca un repartidor por ID, si no se pone nada se muestran todos
        botonBuscar.addActionListener(e -> buscarRepartidor());

        panelSuperior.add(new JLabel("Buscar por ID:"));
        panelSuperior.add(campoBuscar);
        panelSuperior.add(botonBuscar);

        add(panelSuperior, BorderLayout.NORTH);

        tabla = new JTable(); // Tabla donde se muestran los repartidores
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        cargarTodos(); // Carga inicial con todos los repartidores registrados
    }

    private void cargarTodos() {
        // Se muestra la lista de repartidores junto con la cantidad de entregas que tienen asignadas
        String[] columnas = {"ID", "Nombre", "Zona", "Teléfono", "Entregas Asignadas"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Repartidor r : gestor.getRepartidores()) {
            String idRep = r.getIdRepartidor();
            int cantidadEntregas = gestor.obtenerEntregasPorRepartidor(idRep).size();

            modelo.addRow(new Object[]{
                idRep,
                r.getNombreRepartidor(),
                r.getZonaRepartidor(),
                r.getTelefonoRepartidor(),
                cantidadEntregas // Se muestra la cantidad de entregas asignadas
            });
        }

        tabla.setModel(modelo); // Se actualiza la tabla con los datos cargados
    }

    private void buscarRepartidor() {
        String idBuscado = campoBuscar.getText().trim(); // Se obtiene el ID ingresado

        if (idBuscado.isEmpty()) {
            cargarTodos(); // Si no se ingresó nada, se vuelve a mostrar todo
            return;
        }

        String[] columnas = {"ID", "Nombre", "Zona", "Teléfono", "Entregas Asignadas"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        // Se recorre la lista buscando coincidencia exacta por ID (ignorando mayúsculas)
        for (Repartidor r : gestor.getRepartidores()) {
            if (r.getIdRepartidor().equalsIgnoreCase(idBuscado)) {
                int cantidadEntregas = gestor.obtenerEntregasPorRepartidor(r.getIdRepartidor()).size();

                modelo.addRow(new Object[]{
                    r.getIdRepartidor(),
                    r.getNombreRepartidor(),
                    r.getZonaRepartidor(),
                    r.getTelefonoRepartidor(),
                    cantidadEntregas
                });
                break; // Se detiene después de encontrar al repartidor
            }
        }

        tabla.setModel(modelo); // Se muestra solo el repartidor encontrado
    }
}

