package umg.carrito.Dtos;

import java.util.List;

import lombok.Data;

@Data
public class CarritoDTO {
    private Long id;
    private List<ItemCarritoDTO> items;
    private double total;
}
