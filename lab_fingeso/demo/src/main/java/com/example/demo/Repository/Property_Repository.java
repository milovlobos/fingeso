package com.example.demo.Repository;
import com.example.demo.Entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface Property_Repository extends JpaRepository<Property, Long> {

    // Método para obtener una lista de inmuebles que pertenecen a un usuario específico por su ID
    List<Property> findByPropertyIdUser(long PropertyIdUser);

    // Método para obtener un inmueble específico por su ID
    Optional<Property> findById(long id);

    // Método para obtener una lista de todos los inmuebles en la base de datos
    List<Property> findAll();

    // Método personalizado para buscar inmuebles según criterios específicos:
    // - tipo: Filtra por el tipo de inmueble, si es NULL, no aplica filtro.
    // - precioMin: Filtra los inmuebles con un precio mayor o igual al valor dado, si es NULL, no aplica filtro.
    // - precioMax: Filtra los inmuebles con un precio menor o igual al valor dado, si es NULL, no aplica filtro.
    // - direccion: Filtra los inmuebles que contienen la dirección dada como parte del texto, si es NULL, no aplica filtro.
    @Query("SELECT i FROM Property i WHERE (:type IS NULL OR i.propertyType = :type) AND (:priceMin IS NULL OR i.propertyPrice >= :priceMin) AND (:priceMax IS NULL OR i.propertyPrice <= :priceMax) AND (:dress IS NULL OR i.propertyDress LIKE %:dress%)")
    List<Property> searchProperty(@Param("type") String type, @Param("priceMin") Long priceMin, @Param("priceMax") Long priceMax, @Param("dress") String dress);
}
