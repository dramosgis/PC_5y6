package InterfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import SistemaDeEntrega.GestorDeEntregas;

public class PanelRegistroRepartidor extends JPanel {

    private JTextField campoId, campoNombre, campoZona, campoTelefono;
    private JButton botonRegistrar;
    private GestorDeEntregas gestor; // Referencia al gestor para registrar repartidores

    public PanelRegistroRepartidor(GestorDeEntregas gestor) {
        this.gestor = gestor;
        setLayout(null);

        JLabel lblId = new JLabel("DNI:");
        lblId.setBounds(80, 30, 100, 25);
        campoId = new JTextField();
        campoId.setBounds(180, 30, 200, 25);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(80, 70, 100, 25);
        campoNombre = new JTextField();
        campoNombre.setBounds(180, 70, 200, 25);

        JLabel lblZona = new JLabel("Zona de Trabajo:");
        lblZona.setBounds(80, 110, 100, 25);
        campoZona = new JTextField();
        campoZona.setBounds(180, 110, 200, 25);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(80, 150, 100, 25);
        campoTelefono = new JTextField();
        campoTelefono.setBounds(180, 150, 200, 25);

        botonRegistrar = new JButton("Registrar Repartidor");
        botonRegistrar.setBounds(180, 200, 160, 30);

        // Acción del botón: valida datos y registra el repartidor en el sistema
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = campoId.getText().trim();
                String nombre = campoNombre.getText().trim();
                String zona = campoZona.getText().trim();
                String telefono = campoTelefono.getText().trim();

                // Se valida que el DNI tenga 8 dígitos numéricos exactos
                if (!id.matches("\\d{8}")) {
                    JOptionPane.showMessageDialog(null, "El DNI debe tener exactamente 8 dígitos numéricos.");
                    return;
                }

                // Se verifica que ningún campo esté vacío
                if (nombre.isEmpty() || zona.isEmpty() || telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Complete todos los campos.");
                    return;
                }

                // Se registra el repartidor usando el gestor, siempre como disponible (true)
                gestor.registrarRepartidorSinExcepcion(id, nombre, zona, true, telefono);
                JOptionPane.showMessageDialog(null, "Repartidor registrado correctamente.");
                limpiarCampos(); // Se limpian los campos luego del registro
            }
        });

        add(lblId);
        add(campoId);
        add(lblNombre);
        add(campoNombre);
        add(lblZona);
        add(campoZona);
        add(lblTelefono);
        add(campoTelefono);
        add(botonRegistrar);
    }

    private void limpiarCampos() {
        // Se limpian todos los campos del formulario
        campoId.setText("");
        campoNombre.setText("");
        campoZona.setText("");
        campoTelefono.setText("");
    }
}

