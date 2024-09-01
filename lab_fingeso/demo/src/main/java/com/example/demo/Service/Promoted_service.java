package com.example.demo.Service;

import com.example.demo.Entity.Promoted;
import com.example.demo.Repository.Promoted_Repository;
import com.example.demo.Repository.User_Repository;
import com.example.demo.Repository.Property_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class Promoted_service {

    // Inyección de dependencias de los repositorios necesarios para las operaciones de promoción y manejo de inmuebles
    @Autowired
    private Promoted_Repository promotedRepository;

    @Autowired
    private Property_Repository inmuebleRepo;
    @Autowired
    private com.example.demo.Repository.boleta_repositorio boleta_repositorio;
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private User_Service userService;

    // Método para promocionar un inmueble en una fecha específica



    public Promoted Top_10(LocalDate date, long propertyId) {
        try {
            Optional<Promoted> existingPromotedOpt = promotedRepository.findByDate(date);
            Promoted promoted;

            if (existingPromotedOpt.isPresent()) {
                promoted = existingPromotedOpt.get();

                if (promoted.isDisponibilidad()) {
                    boolean added = false;

                    // Lógica para añadir el PropertyId
                    if (promoted.getProperti_id_1() == 0) {
                        promoted.setProperti_id_1(propertyId);
                        added = true;
                    } else if (promoted.getProperti_id_2() == 0) {
                        promoted.setProperti_id_2(propertyId);
                        added = true;
                    } else if (promoted.getProperti_id_3() == 0) {
                        promoted.setProperti_id_3(propertyId);
                        added = true;
                    } else if (promoted.getProperti_id_4() == 0) {
                        promoted.setProperti_id_4(propertyId);
                        added = true;
                    } else if (promoted.getProperti_id_5() == 0) {
                        promoted.setProperti_id_5(propertyId);
                        added = true;
                    } else if (promoted.getProperti_id_6() == 0) {
                        promoted.setProperti_id_6(propertyId);
                        added = true;
                    } else if (promoted.getProperti_id_7() == 0) {
                        promoted.setProperti_id_7(propertyId);
                        added = true;
                    } else if (promoted.getProperti_id_8() == 0) {
                        promoted.setProperti_id_8(propertyId);
                        added = true;
                    } else if (promoted.getProperti_id_9() == 0) {
                        promoted.setProperti_id_9(propertyId);
                        added = true;
                    } else if (promoted.getProperti_id_10() == 0) {
                        promoted.setProperti_id_10(propertyId);
                        added = true;
                        promoted.setDisponibilidad(false); // Si se llena, cambiar disponibilidad a false
                    }

                    if (!added) {
                        promoted.setDisponibilidad(false); // Si no se pudo agregar, se marca como no disponible

                    }
                }

            } else {
                // Crear una nueva instancia si no existe
                promoted = new Promoted(date, true, propertyId, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            }

            return promotedRepository.save(promoted);
        } catch (Exception e) {
            // Manejar la excepción y/o registrar el error
            e.printStackTrace();
            throw new RuntimeException("Error al procesar la promoción");
        }
    }
}
