package umg.carrito.Strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayPalPagoStrategy implements IPagoStrategy {
    private String email;
    private String password;

    @Override
    public boolean procesarPago(double monto) {
        System.out.println("Procesando pago de Q" + monto + " mediante cuenta PayPal: " + email);
        return true; 
    }
}