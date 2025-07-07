package SistemaDeEntrega;

// Clase que agrupa todas las excepciones personalizadas del sistema
public class Excepciones {

    // Excepción lanzada cuando se intenta registrar un ID ya existente
    public static class ExcepcionIdDuplicado extends Exception {
        public ExcepcionIdDuplicado(String mensaje) {
            super(mensaje);
        }
    }

    // Excepción lanzada cuando los datos ingresados no son válidos
    public static class ExcepcionDatosInvalidos extends Exception {
        public ExcepcionDatosInvalidos(String mensaje) {
            super(mensaje);
        }
    }
    
    // Se activa cuando no se encuentra un repartidor al intentar hacer una operación relacionada
    public static class ExcepcionRepartidorNoEncontrado extends Exception {
        public ExcepcionRepartidorNoEncontrado(String mensaje) {
            super(mensaje);
        }
    }
}
