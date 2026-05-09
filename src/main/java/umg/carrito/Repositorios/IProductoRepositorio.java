package umg.carrito.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import umg.carrito.Entidades.Producto;

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto, Long>{

    
}