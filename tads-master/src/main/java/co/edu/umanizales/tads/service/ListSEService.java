package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.RangeKidsDTO;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Node;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@Getter
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        kids = new ListSE();

    }





}
