package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.RangeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.font.NumericShaper;
import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping(path = "/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private RangeService rangeService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids(){
        return new ResponseEntity<>(new ResponseDTO(
                200,listSEService.getKids().getHead(),null), HttpStatus.OK);
    }

    @GetMapping(path="/invert")
    public ResponseEntity<ResponseDTO> invert()
    {
        try {
            listSEService.getKids().invert();
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"La lista se ha invertido",null ),HttpStatus.OK);
    }

    @GetMapping(path = "/changeextremes")
    public ResponseEntity<ResponseDTO> changeExtremes() {
        listSEService.getKids().changeExtremes();                // hecho en postman
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se han intercambiado los extremos",
                null), HttpStatus.OK);
    }

    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody @Valid KidDTO kidDTO){
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        try {
            listSEService.getKids().addToStart(
                    new Kid(kidDTO.getIdentification(),
                            kidDTO.getName(), kidDTO.getAge(),
                            kidDTO.getGender(), location));
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }

    @PostMapping(path = "/addkid")
    public ResponseEntity<ResponseDTO> addKid(@RequestBody @Valid KidDTO kidDTO){
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        try {
            listSEService.getKids().add(
                    new Kid(kidDTO.getIdentification(),
                            kidDTO.getName(), kidDTO.getAge(),
                            kidDTO.getGender(), location));
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

    }
    @GetMapping(path="/addposition/{position}")
    public ResponseEntity<ResponseDTO> addByPosition(@PathVariable int position, @RequestBody Kid kid) throws ListSEException {
        try {
            listSEService.getKids().addInPos(kid, position);
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
    public ResponseEntity<ResponseDTO> intercalateKidByGender()  {
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
    @GetMapping(path="/avaragebyage")
    public ResponseEntity<ResponseDTO> averageAge(){
        try {
            float averageAge = listSEService.getKids().averageAge();
            return new ResponseEntity<>(new ResponseDTO(
                    200,"la edad promedio de los niños es: " + averageAge,
                    null), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error interno del servidor",
                    null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path="/deletekid/{identification}")
    public ResponseEntity<ResponseDTO>deleteKid(@PathVariable String identification)
    {
        try {
            listSEService.getKids().deleteKidById(identification);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha eliminado al niño",null),HttpStatus.OK);
    }

    @GetMapping(path = "/advanceposition/{code}/{numposition}")
    public ResponseEntity<ResponseDTO> gainPosition(@PathVariable String code, @PathVariable int numposition){
        try {
            listSEService.getKids().gainPosition(code,numposition,listSEService.getKids());
            return new ResponseEntity<>(new ResponseDTO(
                    200,"El niño se ha movido con éxito",
                    null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error interno del servidor", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/loseposition/{code}/{numposition}")
    public ResponseEntity<ResponseDTO> backPosition(@PathVariable String code, @PathVariable int numposition) {
        try {
            listSEService.getKids().backPosition(code, numposition, listSEService.getKids());
            return new ResponseEntity<>(new ResponseDTO(
                    200, "El niño se ha movido con éxito", null), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    400, "El número de posiciones debe ser mayor o igual a 1", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDTO(
                    500, "Error interno del servidor", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path="/addtoendnamechar/{letter}")
    public ResponseEntity<ResponseDTO>addToEndNameChar(@PathVariable String letter)
    {
        try {
            listSEService.getKids().addToEndNameChar(letter);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se han agregado al final los nombres que inician con la letra ingresada",null),HttpStatus.OK);
    }
    @GetMapping(path="/quantitykidsbyagerange")
    public ResponseEntity<ResponseDTO>getQuantityKidsByRange()
    {
        List<QuantityRangeKidsDTO> quantityRangeKidsDTOList= new ArrayList<>();
        for (RangeKidsDTO range: rangeService.getRanges())
        {
            int count= listSEService.getKids().quantityByRangeAge(range.getMinimum(), range.getMaximum());
            if (count>0)
            {
                quantityRangeKidsDTOList.add(new QuantityRangeKidsDTO(range,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,quantityRangeKidsDTOList,null),HttpStatus.OK);
    }

    @GetMapping(path = "/kidsbylocationgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportKisLocationGenders(@PathVariable byte age) {
        ReportKidsDTO report = new ReportKidsDTO(locationService.getLocationByCodeSize(8));
        listSEService.getKids().getReportKidsByLocationGendersByAge(age,report);
        return new ResponseEntity<>(new ResponseDTO(
                200,report,
                null), HttpStatus.OK);
    }






}











