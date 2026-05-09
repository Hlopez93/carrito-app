package umg.carrito.Dtos;

import lombok.Data;

@Data
public class ItemCarritoDTO {
    private String nombreProducto;
    private int cantidad;
    private double subTotal;
}
