package umg.carrito.Entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaOrden = LocalDateTime.now();
    private double total;
    private String estado; // "PENDIENTE", "PAGADA", "FALLIDA"

    @OneToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;
}