package jp.co.developersb.dto.chapter3_dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserForm(
        @NotNull
        @Size(min = 4, max = 8)
        String id,

        @NotNull
        @Size(min = 8)
        String password
) {
}