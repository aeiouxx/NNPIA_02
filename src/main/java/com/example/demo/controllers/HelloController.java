package com.example.demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
// @RestController je složená anotace obsahující @Controller a @ResponseBody
// automatická serializace objektů na HttpResponse (jsou-li vraceny z request handleru)
public class HelloController {
    @GetMapping("")
    public String helloWorld() {
        return "Hello world from Spring boot application.";
    }

    @GetMapping("/example/**")
    public String wildcardMapping(HttpServletRequest blabla) {
        String fullUrl = blabla.getRequestURI();
        String wildcard = fullUrl.replaceFirst("^/example/", "");
        return "Wildcard Text: " + wildcard;
    }

    @GetMapping("/string/{input}")
    public String getStringWithPathParam(@PathVariable String input) {
        return "Přijatý řetězec: " + input;
    }
    @RequestMapping(value = "/stringrequest/{input}", method = RequestMethod.GET)
    public String getStringWithRequestMapping(@PathVariable String input) {
        return "Přijatý řetězec: " + input;
    }
    @GetMapping("/stringquery")
    public String getStringWithQueryParam(@RequestParam(name = "input") String input) {
        return "Přijatý řetězec: " + input;
    }
}
