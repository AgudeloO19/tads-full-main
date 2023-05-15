package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter

public class Pet {

    private  String race;

    private byte agepet;

    private  String name;

    private String genderpet;

    private Location locationpet;

    private  String carnet;


}
