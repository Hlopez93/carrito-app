package umg.carrito.Servicios;

import umg.carrito.Entidades.Carrito;
import umg.carrito.Entidades.Orden;
import umg.carrito.Repositorios.ICarritoRepositorio;
import umg.carrito.Repositorios.IOrdenRepositorio;
import umg.carrito.Strategy.IPagoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdenService {

    @Autowired
    private ICarritoRepositorio carritoRepository;

    @Autowired
    private IOrdenRepositorio ordenRepository;

    @Transactional
    public Orden generarYProcesarOrden(Long carritoId, IPagoStrategy metodoPago) {
        // 1. Buscar el carrito
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        if (carrito.getItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío, no se puede generar orden");
        }

        // 2. Crear la Orden en estado Pendiente
        Orden orden = new Orden();
        orden.setCarrito(carrito);
        orden.setTotal(carrito.getTotal());
        orden.setEstado("PENDIENTE");
        orden = ordenRepository.save(orden);

        // 3. Aplicar el patrón Strategy para el pago
        boolean pagoExitoso = metodoPago.procesarPago(orden.getTotal());

        if (pagoExitoso) {
            orden.setEstado("PAGADA");
            // Aquí se podría vaciar el carrito o marcarlo como inactivo
        } else {
            orden.setEstado("FALLIDA");
        }

        return ordenRepository.save(orden);
    }
}