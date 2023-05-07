package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Pet {

    private  String race;

    private byte agepet;

    private  String name;

    private String genderpet;

    private Location locationpet;

    private  String carnet;


}
