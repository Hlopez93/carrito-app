package umg.carrito.Servicios;

import umg.carrito.Entidades.Producto;
import umg.carrito.Repositorios.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private IProductoRepositorio productoRepository;

    // Obtener todos los productos del catálogo
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // Buscar un producto por ID
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
    }

    // Guardar o crear un nuevo producto
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Eliminar un producto
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}