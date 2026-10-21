package jp.co.developersb.dto.chapter5_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductReqest5(
        @NotBlank(message = "商品名の入力は必須です")
        String name,

        @NotNull(message = "値段の入力は必須です")
        @Min(value = 1,message = "値段は1円以上で入力してください")
        Integer price,

        @NotNull(message = "在庫数の入力は必須です")
        @Min(value = 0,message = "在庫数は0以上で入力してください")
        Integer stock
) {
}
