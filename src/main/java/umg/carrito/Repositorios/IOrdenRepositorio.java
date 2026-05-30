package umg.carrito.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import umg.carrito.Entidades.Orden;


public interface IOrdenRepositorio extends JpaRepository<Orden, Long> {
    
}