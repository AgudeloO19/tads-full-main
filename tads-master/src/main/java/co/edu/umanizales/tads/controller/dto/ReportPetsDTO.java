package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Location;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportPetsDTO {

    private List<PetsByLocationAndGenderDTO> petsByLocationAndGenderDTO;

    public ReportPetsDTO(List<Location> cities){
        petsByLocationAndGenderDTO = new ArrayList<>();
        for(Location locationPets: cities){
            petsByLocationAndGenderDTO.add(new
                    PetsByLocationAndGenderDTO(locationPets.getName()));
        }
    }

    //método actualizar
    public void updateQuantityPets(String city, String gender){
        for(PetsByLocationAndGenderDTO loc: petsByLocationAndGenderDTO){
            if (loc.getCity().equals(city)) {
                for(GenderPetDTO genderPetDTO: loc.getGenders()){
                    if(genderPetDTO.getGender().equals(gender)){
                        genderPetDTO.setQuantity(genderPetDTO.getQuantity()+1);
                        loc.setTotal(loc.getTotal()+1);
                        return;
                    }
                }
            }
        }
    }
}
