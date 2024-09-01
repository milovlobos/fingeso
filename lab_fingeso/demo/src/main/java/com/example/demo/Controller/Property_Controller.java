package com.example.demo.Controller;

import com.example.demo.Entity.ballot;
import com.example.demo.Entity.Property;
import com.example.demo.Service.Property_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/property")
public class Property_Controller {

    @Autowired
    private Property_Service Property_service;

    // Crear un nuevo inmueble
    @PostMapping("/create")
    public Property createProperty(@RequestBody Property property) {
        // Los atributos verificado_admin y verificado se establecerán por defecto como false
        return Property_service.createProperty(
                property.getPropertyIdUser(),
                property.getPropertyName(),
                property.getPropertyDescription(),
                property.getPropertyMeter2(),
                property.getPropertyType(),
                property.getPropertyDress(),
                property.getPropertyPrice(),
                property.getPropertyPhotoURL(),
                property.getPropertyEnd_Date()
        );
    }

    // Actualizar un inmueble existente
    @PutMapping("/update/{id}")
    public Property updateProperty(@PathVariable long id, @RequestBody Property property) {
        return Property_service.updateProperty(
                id,
                property.getPropertyName(),
                property.getPropertyDescription(),
                property.getPropertyMeter2(),
                property.getPropertyType(),
                property.getPropertyDress(),
                property.getPropertyPrice(),
                property.getPropertyPhotoURL(),
                property.isPropertyCheck_Admin(),  // Nuevo parámetro
                property.isPropertyCheck()        // Nuevo parámetro
        );
    }

    // Eliminar un inmueble
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable long id, @RequestParam long id_user) {
        Property_service.deleteProperty(id, id_user);
        return ResponseEntity.noContent().build();
    }

    // Obtener un inmueble por su ID
    @GetMapping("/getById")
    public Property getPropertyById(@RequestParam("id") long id) {
        return Property_service.getPropertyById(id);
    }


    // Obtener todos los inmuebles de un usuario
    @GetMapping("/user/{idUser}")
    public List<Property> getPropertyByUserId(@PathVariable Long idUser) {
        return Property_service.findByIdUser(idUser);
    }

    // Buscar inmuebles por criterios
    @GetMapping("/search")
    public List<Property> searchProperty(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "priceMin", required = false) Long priceMin,
            @RequestParam(value = "priceMax", required = false) Long priceMax,
            @RequestParam(value = "dress", required = false) String dress) {
        return Property_service.searchProperty(type, priceMin, priceMax, dress);
    }

    // Paginación de inmuebles
    @GetMapping("/page")
    public Page<Property> getProperty(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("pageSize") int pageSize) {
        return (Page<Property>) Property_service.getProperty(pageNumber, pageSize);
    }

    // Comprar un inmueble
    @PostMapping("/buy/{PropertyId}")
    public ballot BuyProperty(@PathVariable long PropertyId, @RequestParam long userId, @RequestParam String methodPayment) {
        return Property_service.BuyProperty(PropertyId, userId, methodPayment);
    }
}