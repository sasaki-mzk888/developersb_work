package jp.co.developersb.controller.chapter3_controller;

import jakarta.validation.Valid;
import jp.co.developersb.dto.chapter3_dto.CalcForm;
import jp.co.developersb.dto.chapter3_dto.UserForm;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chapter3-exercise")
public class ExerciseController3 {
    /* no5_@REST Controller 練習問題1 */
    @GetMapping("/message")
    public String message() {
        return "どらやき";
    }

    /* no9_@RequestParam 練習問題2 */
    @GetMapping("/drink")
    public String drink(
            @RequestParam (required = true) String name,
            @RequestParam (required = false, defaultValue = "M") String size
    ) {
        return  name +  "の" + size + "サイズですね";
    }

    /* no12_@DTO 練習問題3 */
    @GetMapping("/calc-dto")
    public String calcDto(CalcForm form) {
        int a = form.a();
        int b = form.b();
        int result = form.a() * form.b();
        return a + " * " + b + "は" + result + "になります。";
    }

    /* no15_@バリデーション 練習問題4 */
    @GetMapping("/user-valid")
    public String userValid(@Valid UserForm form, BindingResult br) {
        if (br.hasErrors()) {
            return "IDまたはパスワードを正しく設定してください。";
        }
        return "ログインに成功しました!";
    }
}
