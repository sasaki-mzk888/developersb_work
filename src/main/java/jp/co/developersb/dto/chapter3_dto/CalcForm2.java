package jp.co.developersb.dto.chapter3_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CalcForm2(
        @NotNull
        @Min(0)
        Integer a,
        @NotNull
        @Min(0)
        Integer b
) {
}