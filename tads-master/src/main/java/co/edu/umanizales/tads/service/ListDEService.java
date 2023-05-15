package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.exception.ListDEExeption;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.NodeDE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ListDEService {
    private ListDE pets;

    public ListDEService() {
        pets = new ListDE();
    }

    public List<Pet> getPetslist(){
        List<Pet>petList=new ArrayList<>();
        NodeDE temp= pets.getHead();
        while(temp!=null)
        {
            petList.add(temp.getData());
            temp=temp.getNext();
        }
        return petList;
    }

    public void deletePetByIdentification(String code) throws ListSEException {
        pets.deletePetByIdentification(code);
    }
}
