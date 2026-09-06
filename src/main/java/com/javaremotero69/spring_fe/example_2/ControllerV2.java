package com.javaremotero69.spring_fe.example_2;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/*
    Go ahead and boot a Spring app with the default
    starters (web, thymeleaf) and other dev deps of
    your choice (lombok).

    Create a controller with 3 endpoints and through
    the thymeleaf engines associate different views to
    your HTML templates.
    Make use of the 3 methods to return a view.
 */

/*
    Partajarea informatiilor se intampla intre request si pagina HTML.
    Ceea ce inseamna ca pentru comunicarea datelor intre requesturi e necesar,
    fie un pas intermediar care sa salveze local informatia si sa o trimita catre al doilea request,
    fie sa utilizeze alt mecanism de partajare globala.
 */

@Controller
@RequestMapping("/api/v2")
public class ControllerV2 {

    @GetMapping("/welcome/one")         // http://localhost:8080/api/v2/welcome/one
    public String welcomeOne(Model model) {
        model.addAttribute("welcomeMessage", "Hello One!");
        return "example-2-welcome";
    }

    @GetMapping("/welcome/two")         // http://localhost:8080/api/v2/welcome/two
    public ModelAndView welcomeTwo() {
        // Etapa 1: Initializarea obiectului MAV
        ModelAndView mav = new ModelAndView("example-2-welcome");

        // Etapa 2: Configuratia obiectului MAV
        mav.addObject("welcomeMessage", "Hello Two!");

        // Etapa 3: Returnarea obiectului MAV
        return mav;
    }

    // http://localhost:8080/api/v2/welcome/redirect -> http://localhost:8080/api/v2/welcome/one
    @GetMapping("/welcome/redirect")
    public RedirectView redirectToOne() {
        return new RedirectView("/api/v2/welcome/one");
    }
}
