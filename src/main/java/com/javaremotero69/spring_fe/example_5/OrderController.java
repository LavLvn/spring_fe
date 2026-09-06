package com.javaremotero69.spring_fe.example_5;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v5/orders")
public class OrderController {

    @GetMapping("/create")
    public String getOrderForm(final ModelMap modelMap) {
        modelMap.addAttribute("orderForm", new Order());
        return "example-5-orders";
    }

    @PostMapping("/create")
    public String createOrder(@Valid @ModelAttribute("orderForm") final Order submittedOrder,
                              BindingResult bindingResult) {

        // daca exista erori, vom incarca IAR formularul de order
        if(bindingResult.hasErrors()) {
            System.out.println("ERROR FOUND -> " + bindingResult.getFieldError());
            // si vom redirectiona la pagina de form
            return "example-5-orders";
        }

        // daca nu sunt erori, if-ul va fi ignorat, si vom afisa obiectul
        System.out.println(submittedOrder);

        // mergand mai departe la pagina finala
        return "example-5-display";
    }
}

/*
    Java Bean Validation = este mecanismul intern din Spring care ne permite sa facem validari
    la nivelul claselor de obiect pe baza constraints-urilor

    @Valid = odata salvate informatiile in obiect, anotatia declanseaza mecanismul de validare

    @NotBlank, @Min, @Max, etc = validari elementare la nivel de atribut
 */
