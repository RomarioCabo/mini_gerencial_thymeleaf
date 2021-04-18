package romario.cabo.com.br.mini_gerencial_thymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departamentos")
public class DepartmentController {

    @GetMapping("/cadastrar")
    public String save() {
        return "/departamento/cadastro";
    }

    @GetMapping("/listar")
    public String getAll() {
        return "/departamento/lista";
    }
}
