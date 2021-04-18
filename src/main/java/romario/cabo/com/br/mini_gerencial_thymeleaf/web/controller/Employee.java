package romario.cabo.com.br.mini_gerencial_thymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Funcionário
 */
@Controller
@RequestMapping("/funcionarios")
public class Employee {

    @GetMapping("/cadastrar")
    public String save() {
        return "/funcionario/cadastro";
    }

    @GetMapping("/listar")
    public String getAll() {
        return "/funcionario/lista";
    }
}
