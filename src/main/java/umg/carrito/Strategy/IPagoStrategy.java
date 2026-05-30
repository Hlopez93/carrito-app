package umg.carrito.Strategy;

public interface IPagoStrategy {
    // Retorna un booleano indicando si el pago fue exitoso o no
    boolean procesarPago(double monto);
}