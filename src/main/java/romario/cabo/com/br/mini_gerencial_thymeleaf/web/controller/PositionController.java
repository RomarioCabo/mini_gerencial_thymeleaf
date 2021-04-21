package romario.cabo.com.br.mini_gerencial_thymeleaf.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Department;
import romario.cabo.com.br.mini_gerencial_thymeleaf.domain.Position;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl.DepartmentServiceImpl;
import romario.cabo.com.br.mini_gerencial_thymeleaf.service.impl.PositionServiceImpl;

/*
 * Cargo
 */
@Controller
@RequestMapping("/cargos")
public class PositionController {

	private final PositionServiceImpl positionService;
	private final DepartmentServiceImpl departmentService;

	PositionController(PositionServiceImpl positionService, DepartmentServiceImpl departmentService) {
		this.positionService = positionService;
		this.departmentService = departmentService;
	}

	@GetMapping("/cadastrar")
	public String cadastrar(Position position) {
		return "/cargo/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("positions", positionService.findAll());
		return "/cargo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Position position, RedirectAttributes attr) {

		if (positionService.positionAlreadyExists(position.getName())) {
			attr.addFlashAttribute("fail", "Cargo já cadastrado em nossa base de dados!");
		} else {
			positionService.save(position);
			attr.addFlashAttribute("success", "Cargo salvo com sucesso.");
		}

		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("position", positionService.findById(id));

		return "cargo/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Position position, RedirectAttributes attr) {

		if (positionService.positionAlreadyExists(position.getId())) {
			attr.addFlashAttribute("fail", "Cargo não localizado em nossa base de dados!");
		} else {
			positionService.save(position);
			attr.addFlashAttribute("success", "Cargo atualizado com sucesso.");
		}
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		
		if (positionService.positionHasLinkedEmployees(id)) {
			attr.addFlashAttribute("fail", "Cargo não pode ser removido. Possui funcionário(s) vinculado(s).");
		} else {
			positionService.delete(id);
			attr.addFlashAttribute("success", "Cargo excluido com sucesso.");
		}
		return "redirect:/cargos/listar";
	}

	@ModelAttribute("departments")
	public List<Department> listaDeDepartamentos() {
		return departmentService.findAll();
	}
}
