package SistemaDeEntrega;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorDeEntregas {

    // Lista de todos los clientes registrados en el sistema
    private ArrayList<Cliente> clientes;

    // Lista de todos los pedidos que se han creado
    private ArrayList<Pedido> pedidos;

    // Lista con todos los repartidores que están en el sistema
    private ArrayList<Repartidor> repartidores;

    // Lista que almacena todas las entregas realizadas
    private ArrayList<Entrega> entregas;

    // Mapa que asocia el ID del repartidor con sus entregas
    private HashMap<String, ArrayList<Entrega>> entregasPorRepartidor;

    // Este es el constructor de la clase
    public GestorDeEntregas() {
        clientes = new ArrayList<>();
        pedidos = new ArrayList<>();
        repartidores = new ArrayList<>();
        entregas = new ArrayList<>();
        entregasPorRepartidor = new HashMap<>();
    }

    // Registra un cliente sin validaciones ni excepciones
    public void registrarClienteSinExcepcion(String id, String nombre, String direccion, String telefono) {
        Cliente nuevo = new Cliente(id, nombre, direccion, telefono);
        clientes.add(nuevo);
    }

    // Registra un nuevo pedido directamente (sin validación)
    public void registrarPedidoSinExcepcion(String id, Cliente cliente, String producto, int cantidad, String fecha, String estado) {
        Pedido nuevo = new Pedido(id, cliente, producto, cantidad, fecha, estado);
        pedidos.add(nuevo);
    }

    // Registra un repartidor directamente, sin comprobar duplicados
    public void registrarRepartidorSinExcepcion(String id, String nombre, String zona, boolean disponible, String telefono) {
        Repartidor nuevo = new Repartidor(id, nombre, zona, disponible, telefono);
        repartidores.add(nuevo);
    }

    // Crea una nueva entrega y la asocia a un repartidor y pedido
    public void registrarEntregaSinExcepcion(String idEntrega, Pedido pedido, Repartidor repartidor, String fecha, String estado) {
        Entrega nueva = new Entrega(idEntrega, pedido.getIdPedido(), repartidor.getIdRepartidor(), fecha, estado);
        entregas.add(nueva);

        String idRep = repartidor.getIdRepartidor();

        if (!entregasPorRepartidor.containsKey(idRep)) {
            entregasPorRepartidor.put(idRep, new ArrayList<>());
        }
        entregasPorRepartidor.get(idRep).add(nueva);

        pedido.setEstado("Asignado");
    }

    // Se usa cuando el repartidor toma un pedido manualmente
    public void asignarEntregaDesdeRepartidor(String idEntrega, Pedido pedido, Repartidor repartidor, String fecha) {
        if (pedido == null || repartidor == null) {
            return;
        }

        Entrega nueva = new Entrega(idEntrega, pedido.getIdPedido(), repartidor.getIdRepartidor(), fecha, "en curso");
        entregas.add(nueva);

        entregasPorRepartidor.computeIfAbsent(repartidor.getIdRepartidor(), k -> new ArrayList<>()).add(nueva);

        pedido.setEstado("en camino");
    }

    // Devuelve la lista de clientes
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    // Devuelve la lista de pedidos
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    // Devuelve la lista de repartidores
    public ArrayList<Repartidor> getRepartidores() {
        return repartidores;
    }

    // Devuelve la lista de entregas
    public ArrayList<Entrega> getEntregas() {
        return entregas;
    }

    // Devuelve el mapa de entregas por cada repartidor
    public HashMap<String, ArrayList<Entrega>> getEntregasPorRepartidor() {
        return entregasPorRepartidor;
    }

    // Busca un cliente por su ID
    public Cliente buscarCliente(String id) {
        for (Cliente c : clientes) {
            if (c.getIdCliente().equals(id)) {
                return c;
            }
        }
        return null;
    }

    // Busca un pedido según su ID
    public Pedido buscarPedido(String id) {
        for (Pedido p : pedidos) {
            if (p.getIdPedido().equals(id)) {
                return p;
            }
        }
        return null;
    }

    // Busca un repartidor según su ID
    public Repartidor buscarRepartidor(String id) {
        for (Repartidor r : repartidores) {
            if (r.getIdRepartidor().equals(id)) {
                return r;
            }
        }
        return null;
    }

    // Devuelve la lista de entregas realizadas por un repartidor
    public ArrayList<Entrega> obtenerEntregasPorRepartidor(String idRepartidor) {
        return entregasPorRepartidor.getOrDefault(idRepartidor, new ArrayList<>());
    }

    // Cuenta cuántas entregas hay en cierto estado (ej. "pendiente", "en curso", etc.)
    public int contarEntregasPorEstado(String estado) {
        int contador = 0;
        for (Entrega e : entregas) {
            if (e.getEstadoEntrega().equalsIgnoreCase(estado)) {
                contador++;
            }
        }
        return contador;
    }

    // Recorre los pedidos para hallar cuál es el producto más solicitado
    public String obtenerProductoMasPedido() {
        HashMap<String, Integer> contadorProductos = new HashMap<>();
        for (Pedido p : pedidos) {
            contadorProductos.put(p.getProducto(),
                    contadorProductos.getOrDefault(p.getProducto(), 0) + p.getCantidad());
        }

        String productoMasPedido = null;
        int maxCantidad = 0;

        for (String producto : contadorProductos.keySet()) {
            int cantidad = contadorProductos.get(producto);
            if (cantidad > maxCantidad) {
                maxCantidad = cantidad;
                productoMasPedido = producto;
            }
        }

        return productoMasPedido;
    }

    // Verifica si las credenciales del administrador son válidas
    public boolean validarAccesoAdministrador(String id, String contrasena) {
        return id.equals("admin") && contrasena.equals("123456");
    }
}

