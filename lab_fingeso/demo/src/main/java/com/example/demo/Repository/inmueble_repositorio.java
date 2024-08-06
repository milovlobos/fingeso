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

    List<Inmueble> findByIdUser(long idUser);

    Optional<Inmueble> findById(long id);

    List<Inmueble> findAll();

    @Query("SELECT i FROM Inmueble i WHERE (:tipo IS NULL OR i.type = :tipo) AND (:precioMin IS NULL OR i.precio >= :precioMin) AND (:precioMax IS NULL OR i.precio <= :precioMax) AND (:direccion IS NULL OR i.direccion LIKE %:direccion%)")
    List<Inmueble> searchInmuebles(@Param("tipo") String tipo, @Param("precioMin") Long precioMin, @Param("precioMax") Long precioMax, @Param("direccion") String direccion);
}
