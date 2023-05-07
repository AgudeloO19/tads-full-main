package co.edu.umanizales.tads.controller.dto;


import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NonNull;

@Data
public class KidDTO {
    private String identification;


    @NotNull
    @Size(min = 5, max = 30,message = "El nombre debe contener un mínimo 5 letras y un máximo de 30")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Sólo se permiten letras en este campo")
    private String name;
    @Min(1)
    @Max(15)
    private byte age;

    @Pattern(regexp = "^[MF]$", message = "El género debe ser M o F")
    private String gender;

    private String codeLocation;
}