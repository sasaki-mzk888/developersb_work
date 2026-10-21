package jp.co.developersb.controller.chapter3_controller;

import jakarta.validation.Valid;
import jp.co.developersb.dto.chapter3_dto.CalcForm2;
import jp.co.developersb.dto.chapter3_dto.DrinkForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chapter3-lecture")
public class LectureController3 {
    /* no4_REST APIとは */
    @GetMapping("/message")
    public String message() {
        return "タピオカ";
    }

    /* no7_@RequestParam */
    @GetMapping("/drink")
    public String drink(@RequestParam String name) {
        return "ドリンク名は" + name + "です。";
    }

    /* no8_@RequestParamの応用 */
    @GetMapping("/drink-2")
    public String drinkMulti(@RequestParam String name,@RequestParam int price) {
        return  name +  "は" + price + "円です。";
    }

    @GetMapping("/drink-3")
    public String drinkFlexible(
            @RequestParam(required = false, defaultValue = "不明") String name){
        return  "ドリンク名は" + name + "です。";
    }

    /* no11_DTO */
    @GetMapping("/drink-dto")
    public String drinkDto(DrinkForm form){
        return  form.name() + "は" + form.price() + "円です。";
    }

    /* no15_バリデーション */
    @GetMapping("/calc-valid")
    public String calc(@Valid CalcForm2 form2, BindingResult br) {
        if (br.hasErrors()) {
            return "入力値が不正です。";
        }
        int a = form2.a();
        int b = form2.b();
        int result = a * b;
        return a + " * " + b + "は" + result + "になります。";
    }
}