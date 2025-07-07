package SistemaDeEntrega;

public class Repartidor {

    // DNI del repartidor que sirve como identificador único
    private String idRepartidor;

    // Nombre del repartidor
    private String nombreRepartidor;

    // Zona donde trabaja el repartidor
    private String zonaRepartidor;

    // Indica si el repartidor está disponible para nuevas entregas
    private boolean disponible;

    // Teléfono de contacto del repartidor
    private String telefonoRepartidor;

    // Este es el constructor de la clase
    public Repartidor(String idRepartidor, String nombreRepartidor, String zonaRepartidor, boolean disponible, String telefonoRepartidor) {
        this.idRepartidor = idRepartidor;
        this.nombreRepartidor = nombreRepartidor;
        this.zonaRepartidor = zonaRepartidor;
        this.disponible = disponible;
        this.telefonoRepartidor = telefonoRepartidor;
    }

    // Devuelve el ID del repartidor
    public String getIdRepartidor() {
        return idRepartidor;
    }

    // Devuelve el nombre del repartidor
    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    // Devuelve la zona de trabajo del repartidor
    public String getZonaRepartidor() {
        return zonaRepartidor;
    }

    // Indica si el repartidor está disponible
    public boolean isDisponible() {
        return disponible;
    }

    // Devuelve el número de teléfono del repartidor
    public String getTelefonoRepartidor() {
        return telefonoRepartidor;
    }

    // Permite cambiar la disponibilidad del repartidor
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Permite modificar el teléfono del repartidor
    public void setTelefonoRepartidor(String telefonoRepartidor) {
        this.telefonoRepartidor = telefonoRepartidor;
    }
}

