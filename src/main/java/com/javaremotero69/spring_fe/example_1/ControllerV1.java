package com.javaremotero69.spring_fe.example_1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    Path-ul de resursa e construit in felul urmator:
        http://localhost:8080
        /api/v1
        path-ul de request specific
        => http://localhost:8080/api/v1/greeting

    De ce Controller si nu RestController?
        @Controller:
            - e un stereotip String care specifica faptul ca o clasa are capacitatea de a stoca requesturi HTTP.
            - produce ca si raspuns generic un "text/html" care poate fi convertit prin wrapper-ul ResponseEntity
            la obiecte JSON
            - o alta alternativa STANDARD, este utilizarea anotatiei @ResponseBody la nivelul fiecarui request
            - diferenta majora intre utilizarea lui ResponseEntity si a lui @ResponseBody e faptul ca prima ne ofera
            mai multa libertate de configuratie

        @RestController:
            - e un stereotip String care specifica faptul ca o clasa are capacitatea de a stoca requesturi HTTP.
            - e o combinatie intre cele 2 anotatii, @Controller si @ResponseBody
            - prin urmare, raspunsurile pe care le produce sunt mereu de tipul "APPLICATION/JSON"
            - in contextul acestei anotatii, varianta de raspuns dorita e de regula sub forma unor clase de obiect Java
            - aceste clase de obiect, popular se numesc POJO (plain old java objects), MODEL, ENTITY

        Thymeleaf nu poate procesa datele sub forma de obiecte JSON.
        In general, acesta mapeaza requesturile la pagini statice HTML, in general in situatia GET-urilor.

        Pt requesturi care nu afiseaza pagini intregi, si pur simplu actioneaza pe anumite zone restranse (POST, PUT, DELETE ...),
        acestea intorc ca raspuns un string de reincarcare a paginii principale (a GET-ului).


        Comunicarea datelor intre BE si FE:
            1. aplicatie Spring BE -> aplicatie Angular FE
                - anotatii: @RestController - se adauga pe clasele controller, @CorsOrigin("fe URL") - se adauga pe repo
                - cele 2 proiecte se comporta ca 2 servere independente, amandoua ruleaza pe port-uri diferite
                - comunicarea datelor intre cele 2 servere se face prin requesturi HTTP care transmit obiecte JSON
                - cele 2 proiecte nu au acelasi contract de URL-uri:
                    - in BE noi avem in general requesturi HTTP pt toate operatiile CRUD ale unui context
                    - in FE o sa avem o singura pagina HTML pe arhitectura SPA (single page app) care va avea mai multe faze
                    tranzitorii in functie de o serie de elemente web
                    Elementele web vor fi cele care mapeaza requesturile din BE in partea de service. In component se va implementa
                    logica de business, iar in HTML se vor utiliza resursele cerute.

            2. aplicatie Spring BE -> aplicate Thymeleaf FE
                - anotatii: @Controller
                - comunicarea datelor intre cele 2 arii ale proiectului, se va face pe baza template-urilor HTML
                - acestea ne permit sa partajam informatia prin doua mecanisme (Model sau ModelMap)
                - in cazul de fata, requesturi HTTP de tipul GET sunt mapate la pagini statice individuale HTML, iar requesturile
                HTTP ce fac diverse operatii (PUT, POST, DELETE..) sunt mapate la anumite web elemente care in general ca si raspuns
                reincarca pagina statica curenta sau apeleaza un GET pt actualizare
 */

@Controller
@RequestMapping("/api/v1")
public class ControllerV1 {

    @GetMapping("/greeting")
    public String getGreeting(Model model) {
        String greetingMessage = "Hello, this is my first Thymeleaf + Spring project!";
        model.addAttribute("greetingMessage", greetingMessage);
        // model adauga atribute in zona de partajare sub forma de perechi
        // primul argument al metodei este cheia de identificare in FE
        // al doilea argument este valoarea
        return "example-1-greeting";
    }
}
