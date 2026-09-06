package com.javaremotero69.spring_fe.example_3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
    ModelMap e o clasa care mosteneste functionalitile lui LinkedHashMap. Prin urmare,
    arhitectura clasei e similara cu cea de Map.

    Se comporta ca un builder class, prin urmare toate apelurile de actualizare alui unui obiect,
    returneaza chiar obiectul curent.
    Astfel, putem avea o structura similara cu cea de la streamuri.
 */

@Controller
@RequestMapping("/api/v3")
public class UserController {

    @GetMapping("/user/info")
    public String getUserInfo(ModelMap modelMap) {
        modelMap
                .addAttribute("message", "Hello team!")
                .addAttribute("isVisible", true)
                .addAttribute("items", List.of("Item 1", "Item 2", "Item 3"))
                .addAttribute("user", new User("John", "john@yahoo.com"));

        return "example-3-user";
    }
}
