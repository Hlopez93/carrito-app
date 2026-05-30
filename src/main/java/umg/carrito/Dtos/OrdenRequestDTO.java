package umg.carrito.Dtos;

import lombok.Data;

@Data
public class OrdenRequestDTO {
    private Long carritoId;
    private String metodoPago; // "TARJETA" o "PAYPAL"
    
    // Datos de tarjeta (opcionales si elige tarjeta)
    private String nombreTitular;
    private String numeroTarjeta;
    private String cvv;
    private String fechaExpiracion;
    
    // Datos de PayPal (opcionales si elige paypal)
    private String email;
    private String password;
}