package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.LocationPets;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetsByLocationDTO {
    private LocationPets locationPets;
    private int quantity;
}