package umg.carrito.Dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrdenResponseDTO {
    private Long ordenId;
    private LocalDateTime fechaOrden;
    private double montoTotalAPagar; // <-- Aquí se reflejará explícitamente el monto
    private String estadoPago;
    private Long carritoId;
}