package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
// @RestController -> 모든 경우에 @ResponseBody를 붙여주는 효과
// 모든 요청에 @ResponseBody를 붙이고 싶다면 @RestController 사용한다.

@RequestMapping("hello")
//클래스 차원에서 url경로를 지정하고 싶다면 @RequestMapping을 클래스 위에 선언하면서 경로를 지정한다.
public class HelloController {
//    @ResponseBody가 없고, return 타입이 String이면 templates 하위에 html 파일을 return한다.
//    @ResponseBody가 없는 경우, 화면을 return한다.

//    즉, data만을 return할 경우에는 @ResponseBody를 붙인다.
//    1) 아래처럼 String을 리턴하는 method인 경우, string을 그대로 리턴한다.
//    2) 객체를 리턴하는 method인 경우, json을 리턴한다.

    @GetMapping("string")
    @ResponseBody
//    Controller에서는 method 선언의 의미가 없음
//    사용자와 interaction은 url로 하기 때문에
//    굳이 선언하지 않아도 됨
    public String helloString() {
        return "hello_string";
    }
    @GetMapping("json")
    @ResponseBody
    public String helloJson() {
        return "hello_string";
    }

    @GetMapping("screen")
    public String helloScreen() {
        return "screen";
    }


//        사용자가 넘긴 데이터 받는 방식 2가지
//        1) parameter 방식 : ?{key}={value}
//        ex) localhost:8080/member/?id=1
//        2) path variable 방식 활용
//        (url을 통해 자원의 구조를 명확하게 표현할 수 있기 때문에 더 RESTful API 디자인에 적합하다!)
//        ex) localhost:8080/member/1

    @GetMapping("screen-model-param")
//    1) parameter 호출 방식
//    ?name=honggildong의 방식으로 호출
//    ex) http://localhost:8080/hello/screen-model-param/?name=sinae
    public String helloScreenModelParam(@RequestParam(value = "name") String inputName, Model model) {
//        화면에 data를 넘기고 싶을 때에는 model객체 사용
//        model에 key:value형식으로 전달
        model.addAttribute("myData", inputName);
        return "screen";
    }

    @GetMapping("screen-model-path/{id}")
//    2) path variable 호출 방식
    public String helloScreenModelPath(@PathVariable int id, Model model) {
        model.addAttribute("myData", id);
        return "screen";
    }

}
