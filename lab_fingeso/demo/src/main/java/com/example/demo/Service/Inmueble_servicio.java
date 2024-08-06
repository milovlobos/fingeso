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

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class Inmueble_servicio{

    @Autowired
    private inmueble_repositorio inmuebleRepo;

    @Autowired
    private Usuariorepositorio userRepo;

    @Autowired
    private boleta_repositorio boletaRepository;

    // Crear un nuevo inmueble
    public Inmueble createInmueble(long userId, String name, String description, long metroCuadrados, String tipo, String direccion, long precio, String fotoUrl) {
        Usuario  user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Establecer el rol de vendedor
        user.setRoll("Vendedor"); // Cambia esto según tu implementación de rol

        // Guardar cambios en el usuario
        userRepo.save(user);

        // Crear el inmueble
        Inmueble inmueble = new Inmueble();
        inmueble.setIdUser(user.getID());
        inmueble.setName(name);
        inmueble.setDescription(description);
        inmueble.setMetros2(metroCuadrados);
        inmueble.setType(tipo);
        inmueble.setDireccion(direccion);
        inmueble.setPrecio(precio);
        inmueble.setFotoUrl(fotoUrl);

        return inmuebleRepo.save(inmueble);
    }

    // Actualizar un inmueble existente
    public Inmueble updateInmueble(long id, String name, String description, long metroCuadrados, String tipo, String direccion, long precio, String fotoUrl) {
        Optional<Inmueble> inmuebleOpt = inmuebleRepo.findById(id);
        if (inmuebleOpt.isPresent()) {
            Inmueble inmueble = inmuebleOpt.get();
            inmueble.setName(name);
            inmueble.setDescription(description);
            inmueble.setMetros2(metroCuadrados);
            inmueble.setType(tipo);
            inmueble.setDireccion(direccion);
            inmueble.setPrecio(precio);
            inmueble.setFotoUrl(fotoUrl);
            return inmuebleRepo.save(inmueble);
        } else {
            throw new NoSuchElementException("Inmueble no encontrado");
        }
    }

    // Eliminar un inmueble
    public void deleteInmueble(long inmuebleId, long userId) {
        Optional<Inmueble> inmuebleOpt = inmuebleRepo.findById(inmuebleId);
        if (inmuebleOpt.isPresent()) {
            Inmueble inmueble = inmuebleOpt.get();
            if (inmueble.getIdUser() == userId) {
                Optional<Usuario> usuarioOpt = userRepo.findById(userId);
                if (usuarioOpt.isPresent() && usuarioOpt.get().getRoll().equals("Vendedor")) {
                    inmuebleRepo.deleteById(inmuebleId);
                } else {
                    throw new SecurityException("El usuario no tiene permisos para eliminar este inmueble");
                }
            } else {
                throw new SecurityException("El usuario no es el propietario del inmueble");
            }
        } else {
            throw new NoSuchElementException("Inmueble no encontrado");
        }
    }

    // Comprar un inmueble
    public boleta comprarInmueble(long inmuebleId, long userId, String metodoPago) {
        Optional<Inmueble> inmuebleOpt = inmuebleRepo.findById(inmuebleId);
        if (inmuebleOpt.isPresent()) {
            Inmueble inmueble = inmuebleOpt.get();
            Optional<Usuario> usuarioOpt = userRepo.findById(userId);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                boleta boleta = new boleta(usuario, inmueble, inmueble.getPrecio(), new Date(), metodoPago);
                return boletaRepository.save(boleta);
            } else {
                throw new NoSuchElementException("Usuario no encontrado");
            }
        } else {
            throw new NoSuchElementException("Inmueble no encontrado");
        }
    }

    // Obtener un inmueble por su ID
    public Inmueble getInmuebleById(long id) {
        Optional<Inmueble> inmuebleOpt = inmuebleRepo.findById(id);
        if (inmuebleOpt.isPresent()) {
            return inmuebleOpt.get();
        } else {
            throw new NoSuchElementException("Inmueble no encontrado");
        }
    }

    // Obtener todos los inmuebles de un usuario
    public List<Inmueble> findByIdUser(Long idUser) {
        return inmuebleRepo.findByIdUser(idUser);
    }

    // Buscar inmuebles por criterios
    public List<Inmueble> searchInmuebles(String tipo, Long precioMin, Long precioMax, String direccion) {
        return inmuebleRepo.searchInmuebles(tipo, precioMin, precioMax, direccion);
    }

    // Paginación de inmuebles
    public List<Inmueble> getInmuebles(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Inmueble> page = inmuebleRepo.findAll(pageable);
        return page.getContent();
    }
}