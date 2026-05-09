package umg.carrito.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import umg.carrito.Dtos.AgregarProductoDTO;
import umg.carrito.Entidades.Carrito;
import umg.carrito.Servicios.CarritoService;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {
    
    @Autowired
    private CarritoService carritoService;

    // Endpoint para crear un carrito nuevo
    @PostMapping("/crear")
    public ResponseEntity<Carrito> crearCarrito() {
        return ResponseEntity.ok(carritoService.crearCarrito());
    }

    // Endpoint para agregar producto al carrito
    @PostMapping("/agregar")
    public ResponseEntity<Carrito> agregarProducto(@RequestBody AgregarProductoDTO dto) {
        Carrito carritoActualizado = carritoService.agregarProducto(
            dto.getCarritoId(), 
            dto.getProductoId(), 
            dto.getCantidad()
        );
        return ResponseEntity.ok(carritoActualizado);
    }
}
