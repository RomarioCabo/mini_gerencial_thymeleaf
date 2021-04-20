package romario.cabo.com.br.mini_gerencial_thymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Department;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.ServiceCrud;

@Controller
@RequestMapping("/departamentos")
public class DepartmentController {

	private final ServiceCrud<Department> departmentService;

	DepartmentController(ServiceCrud<Department> departmentService) {
		this.departmentService = departmentService;
	}

	@GetMapping("/cadastrar")
	public String cadastar(Department department) {
		return "/departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departments", departmentService.findAll());
		return "/departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Department department) {
		departmentService.save(department);
		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("department", departmentService.findById(id));
		return "/departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Department department) {
		departmentService.save(department);
		return "redirect:/departamentos/cadastrar";
	}
}
