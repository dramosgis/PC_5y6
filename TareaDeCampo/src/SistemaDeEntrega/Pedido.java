package SistemaDeEntrega;

public class Pedido {

    // ID único del pedido
    private String idPedido;

    // Cliente que realizó el pedido
    private Cliente cliente;

    // Producto solicitado
    private String producto;

    // Cantidad del producto
    private int cantidad;

    // Fecha en la que se registró el pedido
    private String fechaPedido;

    // Estado actual del pedido (pendiente, en camino, entregado, cancelado)
    private String estado;

    // Este es el constructor de la clase
    public Pedido(String idPedido, Cliente cliente, String producto, int cantidad) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaPedido = "2024-01-01"; // valor por defecto si no se especifica
        this.estado = "pendiente";
    }

    // Sobrecarga del constructor para permitir registrar fecha y estado desde el inicio
    public Pedido(String idPedido, Cliente cliente, String producto, int cantidad, String fechaPedido, String estado) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
    }

    // Devuelve el ID del pedido
    public String getIdPedido() {
        return idPedido;
    }

    // Devuelve el cliente asociado al pedido
    public Cliente getCliente() {
        return cliente;
    }

    // Devuelve el nombre del producto solicitado
    public String getProducto() {
        return producto;
    }

    // Devuelve la cantidad de producto solicitado
    public int getCantidad() {
        return cantidad;
    }

    // Devuelve la fecha de realización del pedido
    public String getFechaPedido() {
        return fechaPedido;
    }

    // Devuelve el estado actual del pedido
    public String getEstado() {
        return estado;
    }

    // Permite cambiar el estado del pedido (ej. a "en camino", "entregado", etc.)
    public void setEstado(String estado) {
        this.estado = estado;
    }
}


