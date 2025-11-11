package org.example.reculp02.service.service;

/**
 * Excepción personalizada para los servicios de la aplicación.
 *
 * Esta clase extiende RuntimeException para evitar la necesidad de
 * declarar "throws" en cada método, pero sigue siendo adecuada para
 * capturar y manejar errores de negocio dentro de la capa de servicio.
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Crea una nueva excepción de servicio con un mensaje descriptivo.
     *
     * @param message Mensaje que describe el error
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Crea una nueva excepción de servicio con un mensaje y la causa original.
     *
     * @param message Mensaje que describe el error
     * @param cause   Causa original del error
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Crea una excepción de servicio directamente desde otra excepción.
     *
     * @param cause Causa original del error
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
