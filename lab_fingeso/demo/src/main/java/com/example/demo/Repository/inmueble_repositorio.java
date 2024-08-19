package com.example.demo.Repository;
import com.example.demo.Entity.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface inmueble_repositorio extends JpaRepository<Inmueble, Long> {

    // Método para obtener una lista de inmuebles que pertenecen a un usuario específico por su ID
    List<Inmueble> findByIdUser(long idUser);

    // Método para obtener un inmueble específico por su ID
    Optional<Inmueble> findById(long id);

    // Método para obtener una lista de todos los inmuebles en la base de datos
    List<Inmueble> findAll();

    // Método personalizado para buscar inmuebles según criterios específicos:
    // - tipo: Filtra por el tipo de inmueble, si es NULL, no aplica filtro.
    // - precioMin: Filtra los inmuebles con un precio mayor o igual al valor dado, si es NULL, no aplica filtro.
    // - precioMax: Filtra los inmuebles con un precio menor o igual al valor dado, si es NULL, no aplica filtro.
    // - direccion: Filtra los inmuebles que contienen la dirección dada como parte del texto, si es NULL, no aplica filtro.
    @Query("SELECT i FROM Inmueble i WHERE (:tipo IS NULL OR i.type = :tipo) AND (:precioMin IS NULL OR i.precio >= :precioMin) AND (:precioMax IS NULL OR i.precio <= :precioMax) AND (:direccion IS NULL OR i.direccion LIKE %:direccion%)")
    List<Inmueble> searchInmuebles(@Param("tipo") String tipo, @Param("precioMin") Long precioMin, @Param("precioMax") Long precioMax, @Param("direccion") String direccion);
}
