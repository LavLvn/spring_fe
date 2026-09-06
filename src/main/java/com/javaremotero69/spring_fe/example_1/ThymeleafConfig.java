package com.javaremotero69.spring_fe.example_1;

/*
    #1
    Go ahead and boot a Spring app with the default starters (web,
    thymeleaf) and other dev dependencies of your choice (lombok).

    Create a controller and define explicitly which are the thymeleaf
    engines and ways to configure them

    Configuratia celor 3 nivele de thymeleaf se realizeaza intr-un mod compus.
    Fiecare metoda Bean o va defini pe urmatoare.
    templateResolver -> templateEngine -> viewResolver

    Clasa ThymeleafConfig apartine contextului Spring prin utilizarea stereotipului
    specific @Configuration.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfig {

    /*
        Clasa copil ClassLoaderTemplateResolver mosteneste pe AbstractConfigurableTemplateResolver.
        Clasa parinte AbstractConfigurableTemplateResolver mosteneste pe AbstractTemplateResolver.
        Clasa bunic AbstractTemplateResolver IMPLEMENTEAZA ITemplateResolver.

        Concluzionand, clasa copil ClassLoaderTemplateResolver IMPLEMENTEAZA ITemplateResolver.

        templateResolver -> templateEngine -> viewResolver
            - in SpringTemplateEngine exista un setter pt ITemplateResolver
            - in ThymeleafViewResolver exista un setter pt SpringTemplateEngine

        Fun fact:
            Cum relationezi 2 clase fara a folosi mostenirea?
                Le compunem.
                Principiul compunerii: Mai multe clase se pot defini una pe cealalta prin compunerea lor la nivel de atribute.
                class Catel {
                    private List<Mancare> mancareList;
                    // alte atribute de clase de obiect
                }

                class Mancare {
                    private String marcaMancare;
                    private int pret;
                    // alte atribute primitive
                }

                De ce?
                    Pentru ca putem colecta o serie de atribute sub un context comun.
     */
    @Bean
    public ITemplateResolver templateResolver() {
        // Etapa 1: initializare a obiectului de configurat/returnat
        ClassLoaderTemplateResolver templateResolverConfigurer = new ClassLoaderTemplateResolver();

        // Etapa 2: configurarea obiectului
        templateResolverConfigurer.setPrefix("templates/");         // root path to the HTMLs templates starting from resources
        templateResolverConfigurer.setSuffix(".html");              // file extension of templates
        templateResolverConfigurer.setTemplateMode("HTML");        // version of HTML
        templateResolverConfigurer.setCharacterEncoding("UTF-8");   // standard char encoding

        // Etapa 3: returnarea obiectului
        return templateResolverConfigurer;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        // Etapa 1: initializare a obiectului de configurat/returnat
        SpringTemplateEngine templateEngineConfigurer = new SpringTemplateEngine();

        // Etapa 2: configurarea obiectului
        templateEngineConfigurer.setTemplateResolver(templateResolver());       // compunere prin setter

        // Etapa 3: returnarea obiectului
        return templateEngineConfigurer;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        // Etapa 1: initializare a obiectului de configurat/returnat
        ThymeleafViewResolver viewResolverConfigurer = new ThymeleafViewResolver();

        // Etapa 2: configurarea obiectului
        viewResolverConfigurer.setTemplateEngine(templateEngine());             // compunere prin setter

        // Etapa 3: returnarea obiectului
        return viewResolverConfigurer;
    }
}
