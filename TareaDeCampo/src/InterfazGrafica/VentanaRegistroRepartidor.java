package InterfazGrafica;

import javax.swing.*;
import SistemaDeEntrega.GestorDeEntregas;

//Esta ventana sirve como contenedor para el formulario de registro de repartidores
public class VentanaRegistroRepartidor extends JFrame {

    public VentanaRegistroRepartidor(GestorDeEntregas gestor) {
        setTitle("Registro de Repartidor");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PanelRegistroRepartidor panel = new PanelRegistroRepartidor(gestor);
        setContentPane(panel);

        setVisible(true);
    }
}