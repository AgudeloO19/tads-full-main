package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.controller.dto.RangePetsDTO;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service

public class RangeServiceDE {
    private List<RangePetsDTO> rangespets;

    public RangeServiceDE()
    {

        rangespets = new ArrayList<>();
        rangespets.add(new RangePetsDTO(1, 3));
        rangespets.add(new RangePetsDTO(4, 6));
        rangespets.add(new RangePetsDTO(7, 9));
        rangespets.add(new RangePetsDTO(10, 12));
        rangespets.add(new RangePetsDTO(14, 15));
    }
}
