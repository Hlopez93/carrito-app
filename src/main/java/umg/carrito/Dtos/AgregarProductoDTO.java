package umg.carrito.Dtos;

import lombok.Data;

@Data
public class AgregarProductoDTO {
    private Long carritoId;
    private Long productoId;
    private int cantidad;
}
