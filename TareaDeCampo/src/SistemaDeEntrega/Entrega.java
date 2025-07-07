package SistemaDeEntrega;

// Clase que representa una entrega asociada a un pedido y un repartidor
public class Entrega {

    private String idEntrega;
    private String idPedido;
    private String idRepartidor;
    private String fechaEntrega;
    private String estadoEntrega;

    // Este es el constructor de la clase
    public Entrega(String idEntrega, String idPedido, String idRepartidor, String fechaEntrega, String estadoEntrega) {
        this.idEntrega = idEntrega;
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
        this.fechaEntrega = fechaEntrega;
        this.estadoEntrega = estadoEntrega;
    }

    // Métodos para acceder a los datos de la entrega

    public String getIdEntrega() {
        return idEntrega;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public String getIdRepartidor() {
        return idRepartidor;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    // Permite modificar el estado de la entrega (ej. asignada, entregada, etc.)
    public void setEstadoEntrega(String nuevoEstado) {
        this.estadoEntrega = nuevoEstado;
    }
}
