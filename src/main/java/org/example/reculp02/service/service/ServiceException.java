package org.example.reculp02.service.service;

// Extiende RuntimeException para evitar tener que declarar "throws" en todos lados
public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}