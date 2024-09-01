package com.example.demo.Service;

import com.example.demo.Entity.User;
import com.example.demo.Entity.ballot;
import com.example.demo.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.demo.Entity.Security.sha256;

@Service
public class User_Service {

    // Inyección de dependencias de los repositorios necesarios para las operaciones de usuario y ballot
    @Autowired
    private User_Repository userRepo;

    @Autowired
    private com.example.demo.Repository.boleta_repositorio boleta_repositorio;

    // Método para registrar un nuevo usuario
    public User register(String name, String password, String email) {

        String hashPassword;

        //Verificacion de que los datos hayan sido rellenados
        if( name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            return null;
        }



        // Verificar si ya existe un usuario con el mismo email
        User existe = userRepo.findByUserEmail(email);
        if (existe != null) {
            // Si el usuario ya existe, se retorna null
            return null;
        }

        //Cifrar contraseña
        hashPassword = sha256(password);

        // Crear un nuevo objeto Usuario con los datos proporcionados y sin premium
        User user = new User(name, hashPassword, email, false,false,false);

        // Si no existe, se guarda el nuevo usuario en la base de datos y se retorna
        return userRepo.save(user);
    }

    // Método para realizar el login de un usuario
    public int login(String email, String password) {


        String hashPassword;

        // Buscar el usuario por email
        User user = userRepo.findByUserEmail(email);

        // Obtener cifrado
        hashPassword = sha256(password);

        if (user != null) {
            // Si el usuario existe, verificar que la contraseña sea correcta
            if (hashPassword.equals(user.getUserPassword())) {
                // Si la contraseña es correcta , retornar 1 (login exitoso)
                return 1;
            }
        }
        // Si el login falla, retornar 0
        return 0;
    }

    // Método para obtener un usuario por su ID
    public User getUserbyId(long id) {
        // Buscar el usuario por ID y retornarlo (asume que el usuario siempre existe)
        return userRepo.findById(id).get();
    }

    // Método para convertir un usuario a premium
    public int GetPremium(long id, String metodoPago) {
        // Buscar el usuario por ID
        User user = userRepo.findById(id).get();
        if (user.isUserPremium()) {
            // Si el usuario ya es premium, imprimir un mensaje y no hacer nada más
            System.out.println("Usuario ya es Premium");
            // retorna 0 si no se realizo el upgrade a premium
            return 0;
        }

        // Establecer el precio de la membresía premium
        String descripcion = "Pago membresia premium";
        long precio = 5000;

        // Cambiar el estado del usuario a premium y guardarlo en la base de datos
        user.setUserPremium(true);
        userRepo.save(user);

        // Crear una nueva ballot para el pago de la membresía premium
        ballot ballot = new ballot(user.getID(), precio, new Date(), metodoPago, descripcion);

        // Guardar la ballot en la base de datos
        boleta_repositorio.save(ballot);

        // Retornar 1 indicando que la operación fue exitosa
        return 1;
    }

    public User getUserbyEmail(String email) {
        return userRepo.findByUserEmail(email);
    }
}
