package umg.carrito.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import umg.carrito.Entidades.Carrito;

public interface ICarritoRepositorio extends JpaRepository<Carrito, Long>{
    
}
