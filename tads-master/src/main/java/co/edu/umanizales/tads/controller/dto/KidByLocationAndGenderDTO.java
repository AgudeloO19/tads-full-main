package co.edu.umanizales.tads.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class KidByLocationAndGenderDTO {

    private GenderDTO genderDTO;

    private KidByLocationDTO kidByLocationDTO;
}
