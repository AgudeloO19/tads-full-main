package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.KidByLocationDTO;
import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids().getHead(),null), HttpStatus.OK);
    }

    @GetMapping("/invert")
    public ResponseEntity<ResponseDTO> invert(){
        listSEService.invert();                           // hecho en postman
        return new ResponseEntity<>(new ResponseDTO(
                200,"SE ha invertido la lista",
                null), HttpStatus.OK);

    }

    @GetMapping(path = "/changeextremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.getKids().changeExtremes();                // hecho en postman
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @PostMapping(path = "/addkid")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody KidDTO kidDTO){
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        listSEService.getKids().add(                                      // hecho en postman
                new Kid(kidDTO.getIdentification(),
                        kidDTO.getName(), kidDTO.getAge(),
                        kidDTO.getGender(), location));
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }
    @GetMapping(path="/addposition/{position}")
    public ResponseEntity<ResponseDTO> addByPosition(@PathVariable int position, @RequestBody Kid kid) throws ListSEException {
        try {
            listSEService.getKids().addInpos(kid, position);
            return new ResponseEntity<>(new ResponseDTO(200,
                    "El niño fue añadido en la posición", null), HttpStatus.OK);             // hecho en postman
        } catch (IndexOutOfBoundsException e) {
            throw new ListSEException("La posición especificada no existe en la lista");
        } catch (NullPointerException e) {
            throw new ListSEException("La lista está vacía");
        }
    }


    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation() {
        List<KidByLocationDTO> kidsByLocationDTOList = new ArrayList<>();           // hecho en postman
        for (Location loc : locationService.getLocations()) {
            int count = listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
            if (count > 0) {
                kidsByLocationDTOList.add(new KidByLocationDTO(loc, count));
            }
        }
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(
                200, kidsByLocationDTOList,
                null), HttpStatus.OK);
    }

    @GetMapping(path="/deletekidbyage/{age}")
    public ResponseEntity<ResponseDTO>deleteKidByAge(@PathVariable byte age)
    {
        try {
            listSEService.getKids().deleteKidByAge(age);
        } catch (ListSEException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"El niño se ha eliminado",null),HttpStatus.OK);
    }

    //Niños al inicio y niñas al final
    @GetMapping("/orderboystostart")
    public ResponseEntity<ResponseDTO> orderBoysToStart() {
        try {
            listSEService.getKids().orderBoysToStart();
            return new ResponseEntity<>(new ResponseDTO(
                    200, "Se han añadido los niños al inicio, las niñas al final.",
                    null), HttpStatus.OK);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ocurrió un error al ordenar los niños.", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Intercalar niño, niña, niño, niña
    @GetMapping(path="/boyintercalategirl")
    public ResponseEntity<ResponseDTO> boyIntercalateGirl()  {
        try {
            listSEService.getKids().intercalateKidByGender();
            return new ResponseEntity<>(new ResponseDTO(200, "Los niños se han intercalado.",
                    null), HttpStatus.OK);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Ocurrió un error al intercalar los niños", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Obtener el promedio de edad de los niños de la lista
    @GetMapping(path="/averageage")
    public ResponseEntity<ResponseDTO> averageAge() {
        listSEService.getKids().avarageAge();
        return new ResponseEntity<>(new ResponseDTO(
                200, "Se ha calculado el promedio de edad",
                null), HttpStatus.OK);
    }








}