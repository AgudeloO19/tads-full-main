package co.edu.umanizales.tads.controller.dto;


import co.edu.umanizales.tads.model.Location;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportKidsDTO {
    private List<KidByLocationAndGenderDTO> kidByLocationAndGenderDTO;

    public ReportKidsDTO(List<Location> cities){
         kidByLocationAndGenderDTO= new ArrayList<>();
        for(Location location: cities){
            kidByLocationAndGenderDTO.add(new
                    KidByLocationAndGenderDTO(location.getName()));  // tengo que cambiar el error
        }
    }

    //método actualizar
    public void updateQuantity(String city, char gender){
        for(KidByLocationAndGenderDTO loc: kidByLocationAndGenderDTO){
            if (loc.getCity().equals(city)) {
                for(GenderDTO genderDTO: loc.getGenders()){
                    if(genderDTO.getGender()==gender){
                        genderDTO.setQuantity(genderDTO.getQuantity()+1);
                        loc.setTotal(loc.getTotal()+1);
                        return;
                    }
                }
            }
        }
    }
}