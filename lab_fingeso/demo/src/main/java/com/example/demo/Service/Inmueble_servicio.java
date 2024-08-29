package com.example.demo.Service;


import com.example.demo.Entity.boleta;
import com.example.demo.Entity.Inmueble;
import com.example.demo.Entity.Usuario;
import com.example.demo.Repository.boleta_repositorio;
import com.example.demo.Repository.Usuariorepositorio;
import com.example.demo.Repository.inmueble_repositorio;
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
public class Inmueble_servicio {

    // Inyección de dependencias de los repositorios necesarios para las operaciones de inmueble, usuario, boleta y promociones
    @Autowired
    private inmueble_repositorio inmuebleRepo;

    @Autowired
    private Usuariorepositorio userRepo;

    @Autowired
    private boleta_repositorio boletaRepository;


    // Método para crear un nuevo inmueble
    public Inmueble createInmueble(long userId, String name, String description, long metroCuadrados, String tipo, String direccion, long precio, String fotoUrl, LocalDate Fecha_termino) {
        // Buscar el usuario por su ID, lanzar excepción si no se encuentra

        // Crear y configurar un nuevo inmueble con los detalles proporcionados
        Inmueble inmueble = new Inmueble(false,false,fotoUrl,precio,direccion,tipo,metroCuadrados,description,name,userId, Fecha_termino);

        // Guardar el inmueble en la base de datos y devolverlo
        return inmuebleRepo.save(inmueble);
    }

    // Método para actualizar un inmueble existente
    public Inmueble updateInmueble(long id, String name, String description, long metroCuadrados, String tipo, String direccion, long precio, String fotoUrl, boolean verificadoAdmin, boolean verificado) {
        // Buscar el inmueble por su ID, si no se encuentra lanzar excepción
        Optional<Inmueble> inmuebleOpt = inmuebleRepo.findById(id);
        if (inmuebleOpt.isPresent()) {
            // Si el inmueble existe, actualizar sus propiedades
            Inmueble inmueble = inmuebleOpt.get();
            inmueble.setName(name);
            inmueble.setDescription(description);
            inmueble.setMetros2(metroCuadrados);
            inmueble.setType(tipo);
            inmueble.setDireccion(direccion);
            inmueble.setPrecio(precio);
            inmueble.setFotoUrl(fotoUrl);
            inmueble.setVerificado_admin(verificadoAdmin); // Actualizar valor de verificado por admin
            inmueble.setVerificado(verificado);           // Actualizar valor de verificado por usuario

            // Guardar los cambios en el inmueble y devolverlo
            return inmuebleRepo.save(inmueble);
        } else {
            // Lanzar excepción si el inmueble no se encuentra
            throw new NoSuchElementException("Inmueble no encontrado");
        }
    }

    // Método para eliminar un inmueble
    public void deleteInmueble(long inmuebleId, long userId) {
        // Buscar el inmueble por su ID
        Optional<Inmueble> inmuebleOpt = inmuebleRepo.findById(inmuebleId);
        if (inmuebleOpt.isPresent()) {
            Inmueble inmueble = inmuebleOpt.get();
            // Verificar si el usuario que solicita la eliminación es el propietario del inmueble
            if (inmueble.getIdUser() == userId) {
                // Buscar el usuario por su ID y verificar si tiene el rol de "Vendedor"
                Optional<Usuario> usuarioOpt = userRepo.findById(userId);

                    // Eliminar el inmueble si se cumplen todas las condiciones
                    inmuebleRepo.deleteById(inmuebleId);
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
    public boleta comprarInmueble(long inmuebleId, long userId, String metodoPago) {
        // Descripción del pago de la compra del inmueble
        String descripcion  = "Compra de inmueble";

        // Buscar el inmueble por su ID
        Optional<Inmueble> inmuebleOpt = inmuebleRepo.findById(inmuebleId);
        if (inmuebleOpt.isPresent()) {
            Inmueble inmueble = inmuebleOpt.get();
            // Buscar el usuario por su ID
            Optional<Usuario> usuarioOpt = userRepo.findById(userId);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                // Crear una nueva boleta para registrar el pago de la compra del inmueble
                boleta boleta = new boleta(usuario.getID(), inmueble.getId(), inmueble.getPrecio(), new Date(), metodoPago, descripcion);

                // Marcar el inmueble como verificado al completarse la compra
                inmueble.setVerificado(true);
                inmuebleRepo.save(inmueble);

                // Guardar la boleta en la base de datos y devolverla
                return boletaRepository.save(boleta);
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
    public Inmueble getInmuebleById(long id) {
        // Buscar el inmueble por su ID, si no se encuentra lanzar excepción
        Optional<Inmueble> inmuebleOpt = inmuebleRepo.findById(id);
        if (inmuebleOpt.isPresent()) {
            return inmuebleOpt.get();
        } else {
            throw new NoSuchElementException("Inmueble no encontrado");
        }
    }

    // Método para obtener todos los inmuebles de un usuario por su ID
    public List<Inmueble> findByIdUser(Long idUser) {
        // Devolver la lista de inmuebles pertenecientes al usuario con el ID proporcionado
        return inmuebleRepo.findByIdUser(idUser);
    }

    // Método para buscar inmuebles por criterios de búsqueda
    public List<Inmueble> searchInmuebles(String tipo, Long precioMin, Long precioMax, String direccion) {
        // Buscar y devolver la lista de inmuebles que coincidan con los criterios de búsqueda
        return inmuebleRepo.searchInmuebles(tipo, precioMin, precioMax, direccion);
    }

    // Método para obtener una lista paginada de inmuebles
    public List<Inmueble> getInmuebles(int pageNumber, int pageSize) {
        // Configurar la paginación usando `PageRequest` y obtener la página de inmuebles
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Inmueble> page = inmuebleRepo.findAll(pageable);
        // Devolver la lista de inmuebles en la página solicitada
        return page.getContent();
    }
}
