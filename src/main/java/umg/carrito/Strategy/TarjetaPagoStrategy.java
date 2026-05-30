package umg.carrito.Strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TarjetaPagoStrategy implements IPagoStrategy {
    private String nombreTitular;
    private String numeroTarjeta;
    private String cvv;
    private String fechaExpiracion;

    @Override
    public boolean procesarPago(double monto) {
        // Simulación de pasarela de pago para tarjeta de crédito/débito
        System.out.println("Procesando pago de Q" + monto + " con Tarjeta de Crédito/Débito (" + numeroTarjeta + ")");
        return true; // Simulación de éxito
    }
}