package co.edu.umanizales.tads.controller.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class KidByLocationAndGenderDTO {

    private String city;
    private List<GenderDTO> genders;
    private int total;

    public KidByLocationAndGenderDTO(String city) {
        this.city = city;
        this.total = 0;
        this.genders = new ArrayList<>();
        this.genders.add(new GenderDTO("M", 0));
        this.genders.add(new GenderDTO("F", 0));
    }
}
