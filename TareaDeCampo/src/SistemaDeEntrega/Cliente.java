package SistemaDeEntrega;

// Clase que representa a un cliente dentro del sistema de entregas
public class Cliente {
    private String idCliente;
    private String nombreCliente;
    private String direccionCliente;
    private String telefonoCliente;

    // Este es el constructor de la clase
    public Cliente(String idCliente, String nombreCliente, String direccionCliente, String telefonoCliente) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.direccionCliente = direccionCliente;
        this.telefonoCliente = telefonoCliente;
    }

    // Métodos getter y setter para acceder y modificar los datos del cliente

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
}

