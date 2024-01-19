package com.encore.basic.controller;

import com.encore.basic.domain.Hello;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
// 모든 요청에 @ResponseBody를 붙이고 싶다면 @RestController 사용한다.

@RequestMapping("hello")
//클래스 차원에서 url경로를 지정하고 싶다면 @RequestMapping을 클래스 위에 선언하면서 경로를 지정한다.
public class HelloController {
//    @ResponseBody가 없고, return 타입이 String이면 templates 하위에 html 파일을 return한다.
//    @ResponseBody가 없는 경우, 화면을 return한다.

//    즉, data만을 return할 경우에는 @ResponseBody를 붙인다.
//    1) 아래처럼 String을 리턴하는 method인 경우, string을 그대로 리턴한다.
//    2) 객체를 리턴하는 method인 경우, json을 리턴한다.

    //    -------------------------------------------------------
//    1. GET 요청
//    String 을 리턴한다
    @GetMapping("string")
//    @RequestMapping(value = "string", method = RequestMethod.GET)
    @ResponseBody
//    Controller에서는 method 선언의 의미가 없음
//    사용자와 interaction은 url로 하기 때문에
//    굳이 선언하지 않아도 됨
    public String helloString() {
        return "hello_string";
    }

    //    -------------------------------------------------------
//    2. GET 요청
//    json 을 리턴한다.
//    ex) {"name":sinae, "email":xxx}
    @GetMapping("json")
    @ResponseBody
    public Hello helloJson() {
        Hello hello = new Hello();
        hello.setName("sinae");
        hello.setEmail("sinae@naver.com");
        hello.setPassword("1234");
        System.out.println(hello);
        return hello;
    }

    //    -------------------------------------------------------
//    3. GET 요청
//    html 을 리턴한다.
    @GetMapping("screen")
    public String helloScreen() {
        return "screen";
    }
//    -------------------------------------------------------

//        사용자가 넘긴 데이터 받는 방식 2가지
//        1) parameter 방식 : ?{key}={value}
//        ex) localhost:8080/member/?id=1
//        2) path variable 방식 활용
//        (url을 통해 자원의 구조를 명확하게 표현할 수 있기 때문에 더 RESTful API 디자인에 적합하다!)
//        ex) localhost:8080/member/1

    //    4. GET 요청
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

    //    Form태그로 x-www 데이터 처리
//    form 태그 입력을 위한 화면을 사용자에게 전달
    @GetMapping("form-screen")
    public String formScreenMethod() {
        return "form_screen";
    }

    //    방법1.
    @PostMapping("/form-post-handle1")
    @ResponseBody
//    Form 태그를 통한 body 데이터 형태가 {key1}={value1}&{key2}={value2} 형식임
    public String formPostHandle1(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "email") String email,
                                  @RequestParam(value = "password") String password) {
//        System.out.println(name);
//        System.out.println(email);
//        System.out.println(password);
        return "정상처리";
    }

    //    방법2.
    @PostMapping("/form-post-handle2")
    @ResponseBody
//    Spring에서 Hello 클래스의 인스턴스를 자동 mapping해서 생성
//    <form>-data 형식 즉, x-www-url 인코딩 형식의 경우 사용
//    이를 data binding 이라고 함! (Hello 클래스에는 Setter 필수)
//    (DTO를 통해서 받겠다) : 사용자와의 최접접, 사용자와 데이터를 주고받는 곳
//    엔티티에서는 setter 설정 안 하고 DTO에서만 설정을 한다 ..?
    public String formPostHandle2(Hello hello) {
        System.out.println(hello);
        return "정상처리";
    }

    //    json 데이터 처리
    @GetMapping("json-screen")
    public String jsonScreen() {
        return "json_screen";
    }

    //    방법1.
    @PostMapping("/json-post-handle1")
    @ResponseBody
//    @RequestBody는 json으로 post 요청이 들어왔을 경우, body에서 data를 꺼내기 위해 사용
    public String jsonPostHandle1(@RequestBody Map<String, String> body) {
        System.out.println(body.get("name"));
        System.out.println(body.get("email"));
        System.out.println(body.get("password"));


        Hello hello = new Hello();
        hello.setName((body.get("name")));
        hello.setEmail((body.get("email")));
        hello.setPassword((body.get("password")));
        return "ok";
    }

    //    방법2.
    @PostMapping("/json-post-handle2")
    @ResponseBody
    public String jsonPostHandle2(@RequestBody JsonNode body) {
        Hello hello = new Hello();
        hello.setName((body.get("name").asText()));
        hello.setEmail((body.get("email").asText()));
        hello.setPassword((body.get("password").asText()));
        return "ok";
    }

    //    방법3.
    @PostMapping("/json-post-handle3")
    @ResponseBody
    public String jsonPostHandle3(@RequestBody Hello hello) {
        System.out.println(hello);
        System.out.println(hello.getName());
        System.out.println(hello.getEmail());
        System.out.println(hello.getPassword());
        return "ok";
    }

    @PostMapping("httpservlet")
    @ResponseBody
    public String httpServletTest(HttpServletRequest httpServletRequest) {
//        HttpServletRequest 객체에서 header정보 추출
        System.out.println(httpServletRequest.getContentType());
        System.out.println(httpServletRequest.getMethod());
//        session : 로그인 (auth) 정보에서 필요한 정보값을 추출할 때 많이 사용
        System.out.println(httpServletRequest.getSession());
        System.out.println(httpServletRequest.getHeader("Accept"));

//        HttpServletRequest 객체에서 body정보 추출
//        httpServletRequest.getReader()를 통해 BufferedReader로 받아 직접 파싱
        System.out.println(httpServletRequest.getParameter("test1"));
        System.out.println(httpServletRequest.getParameter("test2"));

        return "ok";
    }

//        Controller와 jsp의 조합 (그 전에는 Controller와 Thymeleaf의 조합)
    @GetMapping("/hello-servlet-jsp-get")
    public String helloServletJspGet(Model model) {
        model.addAttribute("myData", "jsp test data");
        return "hello-jsp";
    }
}
