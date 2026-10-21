package jp.co.developersb.dto.chapter5_dto;

public record ProductResponse5(
        Long id,
        String name,
        Integer price,
        Integer stock
) {
}
