package com.example.demo.Service;


import com.example.demo.Entity.ballot;
import com.example.demo.Entity.Property;
import com.example.demo.Entity.User;
import com.example.demo.Repository.Ballor_Repository;
import com.example.demo.Repository.User_Repository;
import com.example.demo.Repository.Property_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class Property_Service {

    // Inyección de dependencias de los repositorios necesarios para las operaciones de inmueble, usuario, ballot y promociones
    @Autowired
    private Property_Repository PropertyRepo;

    @Autowired
    private User_Repository userRepo;

    @Autowired
    private Ballor_Repository ballotRepository;


    // Método para crear un nuevo inmueble
    public Property createProperty(long userId, String name, String description, long meter2, String type, String dress, long price, String photoUrl, LocalDate end_date) {
        // Buscar el usuario por su ID, lanzar excepción si no se encuentra

        // Crear y configurar un nuevo inmueble con los detalles proporcionados
        Property property = new Property(false,false,photoUrl,price,dress,type,meter2,description,name,userId, end_date);

        // Guardar el inmueble en la base de datos y devolverlo
        return PropertyRepo.save(property);
    }

    // Método para actualizar un inmueble existente
    public Property updateProperty(long id, String name, String description, long meter2, String type, String dress, long price, String photoUrl, boolean checkAdmin, boolean check) {
        // Buscar el inmueble por su ID, si no se encuentra lanzar excepción
        Optional<Property> inmuebleOpt = PropertyRepo.findById(id);
        if (inmuebleOpt.isPresent()) {
            // Si el inmueble existe, actualizar sus propiedades
            Property property = inmuebleOpt.get();
            property.setPropertyName(name);
            property.setPropertyDescription(description);
            property.setPropertyMeter2(meter2);
            property.setPropertyType(type);
            property.setPropertyDress(dress);
            property.setPropertyPrice(price);
            property.setPropertyPhotoURL(photoUrl);
            property.setPropertyCheck_Admin(checkAdmin); // Actualizar valor de verificado por admin
            property.setPropertyCheck(check);           // Actualizar valor de verificado por usuario

            // Guardar los cambios en el inmueble y devolverlo
            return PropertyRepo.save(property);
        } else {
            // Lanzar excepción si el inmueble no se encuentra
            throw new NoSuchElementException("Inmueble no encontrado");
        }
    }

    // Método para eliminar un inmueble
    public void deleteProperty(long PropertyId, long userId) {
        // Buscar el inmueble por su ID
        Optional<Property> inmuebleOpt = PropertyRepo.findById(PropertyId);
        if (inmuebleOpt.isPresent()) {
            Property property = inmuebleOpt.get();
            // Verificar si el usuario que solicita la eliminación es el propietario del inmueble
            if (property.getPropertyIdUser() == userId) {
                // Buscar el usuario por su ID y verificar si tiene el rol de "Vendedor"
                Optional<User> usuarioOpt = userRepo.findById(userId);

                    // Eliminar el inmueble si se cumplen todas las condiciones
                    PropertyRepo.deleteById(PropertyId);
                } else {
                    // Lanzar excepción si el usuario no tiene permisos para eliminar el inmueble
                    throw new SecurityException("El usuario no tiene permisos para eliminar este inmueble");
                }
            } else {
                // Lanzar excepción si el usuario no es el propietario del inmueble
                throw new SecurityException("El usuario no es el propietario del inmueble");
            }

        // Lanzar excepción si el inmueble no se encuentra
        throw new NoSuchElementException("Inmueble no encontrado");
    }

    // Método para comprar un inmueble
    public ballot BuyProperty(long PropertyId, long userId, String method_payment) {
        // Descripción del pago de la compra del inmueble
        String descripcion  = "Compra de inmueble";

        // Buscar el inmueble por su ID
        Optional<Property> PropertyOpt = PropertyRepo.findById(PropertyId);
        if (PropertyOpt.isPresent()) {
            Property property = PropertyOpt.get();
            // Buscar el usuario por su ID
            Optional<User> usuarioOpt = userRepo.findById(userId);
            if (usuarioOpt.isPresent()) {
                User user = usuarioOpt.get();
                // Crear una nueva ballot para registrar el pago de la compra del inmueble
                ballot ballot = new ballot(user.getID(), property.getId(), property.getPropertyPrice(), new Date(), method_payment, descripcion);

                // Marcar el inmueble como verificado al completarse la compra
                property.setPropertyCheck(true);
                PropertyRepo.save(property);

                // Guardar la ballot en la base de datos y devolverla
                return ballotRepository.save(ballot);
            } else {
                // Lanzar excepción si el usuario no se encuentra
                throw new NoSuchElementException("Usuario no encontrado");
            }
        } else {
            // Lanzar excepción si el inmueble no se encuentra
            throw new NoSuchElementException("Inmueble no encontrado");
        }
    }

    // Método para obtener un inmueble por su ID
    public Property getPropertyById(long id) {
        // Buscar el inmueble por su ID, si no se encuentra lanzar excepción
        Optional<Property> inmuebleOpt = PropertyRepo.findById(id);
        if (inmuebleOpt.isPresent()) {
            return inmuebleOpt.get();
        } else {
            throw new NoSuchElementException("Inmueble no encontrado");
        }
    }

    // Método para obtener todos los inmuebles de un usuario por su ID
    public List<Property> findByIdUser(Long idUser) {
        // Devolver la lista de inmuebles pertenecientes al usuario con el ID proporcionado
        return PropertyRepo.findByPropertyIdUser(idUser);
    }

    // Método para buscar inmuebles por criterios de búsqueda
    public List<Property> searchProperty(String type, Long priceMin, Long priceMax, String dress) {
        // Buscar y devolver la lista de inmuebles que coincidan con los criterios de búsqueda
        return PropertyRepo.searchProperty(type, priceMin, priceMax, dress);
    }

    // Método para obtener una lista paginada de inmuebles
    public List<Property> getProperty(int pageNumber, int pageSize) {
        // Configurar la paginación usando `PageRequest` y obtener la página de inmuebles
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Property> page = PropertyRepo.findAll(pageable);
        // Devolver la lista de inmuebles en la página solicitada
        return page.getContent();
    }
}
