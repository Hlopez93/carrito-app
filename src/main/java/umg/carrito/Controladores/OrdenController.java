package umg.carrito.Controladores;

import umg.carrito.Dtos.OrdenRequestDTO;
import umg.carrito.Dtos.OrdenResponseDTO;
import umg.carrito.Entidades.Orden;
import umg.carrito.Servicios.OrdenService;
import umg.carrito.Strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    // 1. NUEVO ENDPOINT: Ver métodos de pago aceptados
    // GET: http://localhost:8080/api/ordenes/metodos-pago
    @GetMapping("/metodos-pago")
    public ResponseEntity<List<String>> obtenerMetodosPagoAceptados() {
        List<String> metodos = Arrays.asList(
            "TARJETA (Visa/Mastercard)", 
            "PAYPAL", 
            "EFECTIVO", 
            "TRANSFERENCIA_BANCARIA", 
            "DEPOSITO_MONETARIO"
        );
        return ResponseEntity.ok(metodos);
    }

    // 2. ENDPOINT ACTUALIZADO: Checkout con Monto Visible
    // POST: http://localhost:8080/api/ordenes/checkout
    @PostMapping("/checkout")
    public ResponseEntity<OrdenResponseDTO> checkout(@RequestBody OrdenRequestDTO dto) {
        IPagoStrategy estrategiaSeleccionada;

        // Selección de estrategia según el requerimiento simulado
        switch (dto.getMetodoPago().toUpperCase()) {
            case "TARJETA":
                estrategiaSeleccionada = new TarjetaPagoStrategy(
                    dto.getNombreTitular(), dto.getNumeroTarjeta(), dto.getCvv(), dto.getFechaExpiracion()
                );
                break;
            case "PAYPAL":
                estrategiaSeleccionada = new PayPalPagoStrategy(dto.getEmail(), dto.getPassword());
                break;
            default:
                throw new IllegalArgumentException("Método de pago no soportado en la estrategia actual");
        }

        // Procesar orden
        Orden ordenProcesada = ordenService.generarYProcesarOrden(dto.getCarritoId(), estrategiaSeleccionada);
        
        // Mapear al DTO de respuesta para asegurar visibilidad del monto en Postman
        OrdenResponseDTO response = new OrdenResponseDTO();
        response.setOrdenId(ordenProcesada.getId());
        response.setFechaOrden(ordenProcesada.getFechaOrden());
        response.setMontoTotalAPagar(ordenProcesada.getTotal()); // <-- Asignación explícita
        response.setEstadoPago(ordenProcesada.getEstado());
        response.setCarritoId(ordenProcesada.getCarrito().getId());

        return ResponseEntity.ok(response);
    }
}