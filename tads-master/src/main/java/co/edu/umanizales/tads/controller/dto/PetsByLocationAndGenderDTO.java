package co.edu.umanizales.tads.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PetsByLocationAndGenderDTO {
    private  String city;
    private List<GenderPetDTO>genders;
    private int total;


    public PetsByLocationAndGenderDTO(String city) {
        this.city = city;
        this.total = 0;
        this.genders = new ArrayList<>();
        this.genders.add(new GenderPetDTO('M', 0));
        this.genders.add(new GenderPetDTO('F', 0));
    }

}
