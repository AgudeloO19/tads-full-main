package co.edu.umanizales.tads.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class QuantityRangePetsDto {

    private RangePetsDTO range;
    int quantity;
}
