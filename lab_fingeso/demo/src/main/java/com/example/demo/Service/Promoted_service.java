package com.example.demo.Service;

import com.example.demo.Entity.Promoted;
import com.example.demo.Repository.Promoted_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Promoted_service {

    // Inyección de dependencias de los repositorios necesarios para las operaciones de promoción y manejo de inmuebles
    @Autowired
    private Promoted_Repository promotedRepository;



    // Método para promocionar un inmueble en una fecha específica



    public int Top_10(LocalDate date, long propertyId) {
        try {
            Optional<Promoted> existingPromotedOpt = promotedRepository.findByDate(date);
            Promoted promoted;

            if (existingPromotedOpt.isPresent()) {
                promoted = existingPromotedOpt.get();

                // Verificar si la propiedad ya está en la lista
                if (propertyId == promoted.getProperti_id_1() || propertyId == promoted.getProperti_id_2() ||
                        propertyId == promoted.getProperti_id_3() || propertyId == promoted.getProperti_id_4() ||
                        propertyId == promoted.getProperti_id_5() || propertyId == promoted.getProperti_id_6() ||
                        propertyId == promoted.getProperti_id_7() || propertyId == promoted.getProperti_id_8() ||
                        propertyId == promoted.getProperti_id_9() || propertyId == promoted.getProperti_id_10()) {
                    return 0; // Retorna 0 si la propiedad ya está en la lista para esta fecha
                }

                if (promoted.isAvailable()) {
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
                        promoted.setAvailable(false); // Si se llena, cambiar disponibilidad a false
                    }

                    if (!added) {
                        promoted.setAvailable(false); // Si no se pudo agregar, se marca como no disponible
                    }
                }
            } else {
                // Crear una nueva instancia si no existe
                promoted = new Promoted(date, true, propertyId, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            }

            promotedRepository.save(promoted);
            return 1; // Retorna 1 si la operación fue exitosa
        } catch (IllegalArgumentException e) {
            return 0; // Retorna 0 si la propiedad ya estaba en la lista para esa fecha
        } catch (Exception e) {
            // Manejar la excepción y/o registrar el error
            e.printStackTrace();
            return -1; // Retorna -1 si hubo un error durante la operación
        }
    }

    //Metodo para obtener los id de las publicaciones promocionadas
    public Promoted getTop10(LocalDate date) {
        try {
            Optional<Promoted> existingPromotedOpt = promotedRepository.findByDate(date);
            Promoted promoted;
            if (existingPromotedOpt.isPresent()) {
                promoted = existingPromotedOpt.get();
                return promoted;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<LocalDate> getDatesWithNoAvailability() {
        try {
            // Busca todas las instancias de Promoted donde la disponibilidad es false
            List<Promoted> unavailablePromotions = promotedRepository.findByAvailableFalse();

            // Extrae las fechas de esas instancias
            List<LocalDate> datesWithNoAvailability = unavailablePromotions.stream()
                    .map(Promoted::getDate)
                    .collect(Collectors.toList());

            return datesWithNoAvailability;
        } catch (Exception e) {
            // Manejar la excepción y/o registrar el error
            e.printStackTrace();
            throw new RuntimeException("Error al obtener las fechas con disponibilidad false");
        }

    }
}
