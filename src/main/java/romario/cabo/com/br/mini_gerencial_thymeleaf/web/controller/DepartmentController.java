package romario.cabo.com.br.mini_gerencial_thymeleaf.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Department;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl.DepartmentServiceImpl;

@Controller
@RequestMapping("/departamentos")
public class DepartmentController {

	private final DepartmentServiceImpl departmentService;

	DepartmentController(DepartmentServiceImpl departmentService) {
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
	public String salvar(Department department, RedirectAttributes attr) {

		if (departmentService.departmentAlreadyExists(department.getName())) {
			attr.addFlashAttribute("fail", "Departamento já cadastrado em nossa base de dados!");
		} else {
			departmentService.save(department);
			attr.addFlashAttribute("success", "Departamento salvo com sucesso.");
		}

		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable Long id, ModelMap model) {
		model.addAttribute("department", departmentService.findById(id));
		return "/departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Department department, RedirectAttributes attr) {

		if (departmentService.departmentAlreadyExists(department.getId())) {
			attr.addFlashAttribute("fail", "Departamento não localizado em nossa base de dados!");
		} else {
			departmentService.save(department);
			attr.addFlashAttribute("success", "Departamento atualizado com sucesso.");
		}

		return "redirect:/departamentos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id, ModelMap model) {

		if (departmentService.departmentAlreadyExists(id)) {
			model.addAttribute("fail", "Departamento não localizado em nossa base de dados!");
		} else if (departmentService.departmentHasLinkedPositions(id)) {
			model.addAttribute("fail", "Departamento não pode ser removido. Possui cargo(s) vinculado(s).");
		} else {
			departmentService.delete(id);
			model.addAttribute("success", "Departamento excluído com sucesso.");
		}

		return listar(model);
	}
}
