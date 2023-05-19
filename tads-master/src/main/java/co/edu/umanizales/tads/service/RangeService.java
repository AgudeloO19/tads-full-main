package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.RangeKidsDTO;
import co.edu.umanizales.tads.model.Ranges;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class RangeService {
    private List<RangeKidsDTO>ranges;

    public RangeService() {

        ranges=new ArrayList<>();
        ranges.add(new RangeKidsDTO(1,3));
        ranges.add(new RangeKidsDTO(4,6));
        ranges.add(new RangeKidsDTO(7,9));
        ranges.add(new RangeKidsDTO(10,12));
        ranges.add(new RangeKidsDTO(14,15));

    }



}