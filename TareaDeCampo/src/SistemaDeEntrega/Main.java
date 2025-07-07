package SistemaDeEntrega;

import InterfazGrafica.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        GestorDeEntregas gestor = new GestorDeEntregas();
        
        //Datos por defecto para poder demostrar el funcionamientos del sistema

        // Clientes
        gestor.registrarClienteSinExcepcion("76341285", "María López", "Av. Siempre Viva 123", "987654321");
        gestor.registrarClienteSinExcepcion("85201347", "Carlos Mendoza", "Jr. Los Laureles 456", "912345678");
        gestor.registrarClienteSinExcepcion("71593264", "Lucía Ríos", "Calle Primavera 789", "913213213");
        gestor.registrarClienteSinExcepcion("80471653", "José Torres", "Psje. Independencia 45", "921312312");
        gestor.registrarClienteSinExcepcion("79634821", "Ana Morales", "Calle Amazonas 999", "934567890");
        
        // Pedidos
        gestor.registrarPedidoSinExcepcion("P001", gestor.buscarCliente("76341285"), "Laptop", 1, "2024-05-25", "pendiente");
        gestor.registrarPedidoSinExcepcion("P002", gestor.buscarCliente("85201347"), "Celular", 2, "2024-05-26", "pendiente");
        gestor.registrarPedidoSinExcepcion("P003", gestor.buscarCliente("71593264"), "Impresora", 1, "2024-05-27", "pendiente");
        gestor.registrarPedidoSinExcepcion("P004", gestor.buscarCliente("80471653"), "Tablet", 3, "2024-05-28", "pendiente");
        gestor.registrarPedidoSinExcepcion("P005", gestor.buscarCliente("79634821"), "Audífonos", 2, "2024-05-29", "pendiente");
        gestor.registrarPedidoSinExcepcion("P006", gestor.buscarCliente("76341285"), "Mouse", 1, "2024-06-01", "pendiente");
        gestor.registrarPedidoSinExcepcion("P007", gestor.buscarCliente("85201347"), "Teclado", 1, "2024-06-02", "pendiente");
        gestor.registrarPedidoSinExcepcion("P008", gestor.buscarCliente("71593264"), "Monitor", 1, "2024-06-03", "pendiente");
        gestor.registrarPedidoSinExcepcion("P009", gestor.buscarCliente("85201347"), "Laptop", 1, "2024-06-04", "pendiente");
        gestor.registrarPedidoSinExcepcion("P010", gestor.buscarCliente("76341285"), "Impresora", 2, "2024-06-05", "pendiente");

        // Repartidores
        gestor.registrarRepartidorSinExcepcion("73829164", "Carlos Gómez", "Zona Norte", true, "987654321");
        gestor.registrarRepartidorSinExcepcion("70918432", "Lucía Ramos", "Zona Sur", true, "912345678");
        gestor.registrarRepartidorSinExcepcion("79438215", "Diego Vargas", "Zona Este", true, "956781234");

        // Entregas
        gestor.registrarEntregaSinExcepcion("E001", gestor.buscarPedido("P001"), gestor.buscarRepartidor("73829164"), "2024-06-01", "completada");
        gestor.registrarEntregaSinExcepcion("E002", gestor.buscarPedido("P002"), gestor.buscarRepartidor("70918432"), "2024-06-03", "en curso");
        gestor.registrarEntregaSinExcepcion("E003", gestor.buscarPedido("P003"), gestor.buscarRepartidor("73829164"), "2024-06-04", "fallida");
        gestor.registrarEntregaSinExcepcion("E004", gestor.buscarPedido("P004"), gestor.buscarRepartidor("79438215"), "2024-06-05", "completada");
        gestor.registrarEntregaSinExcepcion("E005", gestor.buscarPedido("P005"), gestor.buscarRepartidor("79438215"), "2024-06-07", "en curso");


        // Iniciar la interfaz
        javax.swing.SwingUtilities.invokeLater(() -> new VentanaPrincipal(gestor));
    }
}

