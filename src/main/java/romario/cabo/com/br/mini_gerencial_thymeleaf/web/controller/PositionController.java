package romario.cabo.com.br.mini_gerencial_thymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Cargo
 */
@Controller
@RequestMapping("/cargos")
public class PositionController {

    @GetMapping("/cadastrar")
    public String save() {
        return "/cargo/cadastro";
    }

    @GetMapping("/listar")
    public String getAll() {
        return "/cargo/lista";
    }
}
