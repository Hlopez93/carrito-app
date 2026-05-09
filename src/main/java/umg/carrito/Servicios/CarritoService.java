package umg.carrito.Servicios;

import umg.carrito.Entidades.Carrito;
import umg.carrito.Entidades.ItemCarrito;
import umg.carrito.Entidades.Producto;
import umg.carrito.Repositorios.ICarritoRepositorio;
import umg.carrito.Repositorios.IProductoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CarritoService {

    @Autowired
    private ICarritoRepositorio carritoRepository;

    @Autowired
    private IProductoRepositorio productoRepository;

    @Transactional
    public Carrito agregarProducto(Long carritoId, Long productoId, int cantidad) {
        // 1. Buscar el carrito (según el diagrama de secuencia)
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // 2. Buscar el producto
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // 3. Validación de Negocio (Mejora: validar stock)
        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
        }

        // 4. Crear el ItemCarrito
        ItemCarrito nuevoItem = new ItemCarrito();
        nuevoItem.setProducto(producto);
        nuevoItem.setCantidad(cantidad);
        nuevoItem.setPrecioUnitario(producto.getPrecio());
        nuevoItem.setCarrito(carrito);

        // 5. Agregar al carrito y recalcular
        carrito.getItems().add(nuevoItem);
        carrito.calcularTotal();

        // 6. Actualizar stock del producto (Mejora operativa)
        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);

        // 7. Guardar carrito actualizado
        return carritoRepository.save(carrito);
    }
    
    // Método para crear un carrito inicial
    public Carrito crearCarrito() {
        return carritoRepository.save(new Carrito());
    }
}