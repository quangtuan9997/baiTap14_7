package baitap14_07.controller;

import baitap14_07.repository.DepartmentRepository;
import baitap14_07.service.DepartmentService;
import baitap14_07.service.dto.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentService departmentService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/view")
    public ModelAndView findAll(Pageable pageable) {
        Page<DepartmentDTO> listDepartment = departmentService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("department/index");
        modelAndView.addObject("listDepartment", listDepartment);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createDepartment(@RequestParam("name") String name,
                                         @RequestParam("description") String description) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName(name);
        departmentDTO.setDescription(description);
        departmentService.save(departmentDTO);
        ModelAndView modelAndView = new ModelAndView("department/create");
        modelAndView.addObject("message", "Create success!!");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createView() {
        ModelAndView modelAndView = new ModelAndView("department/create");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteById(@PathVariable("id") Long id, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("department/index");
        Page<DepartmentDTO> listDepartment = departmentService.findAll(pageable);
        if (!departmentRepository.findById(id).isPresent()) {
            return modelAndView.addObject("message", "No ConTen");
        }
        departmentService.delete(id);
        return modelAndView.addObject("listDepartment", listDepartment);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editView(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("department/edit");
        Optional<DepartmentDTO> departmentDTO = departmentService.findOne(id);
        if (departmentDTO.isPresent()) {
            return modelAndView.addObject("department", departmentDTO.get());
        }
        return new ModelAndView("department/index").addObject("message", "Entity not found");
    }

    @PostMapping("/edit")
    public ModelAndView update(@RequestParam("id") Long id,
                               @RequestParam("name") String name,
                               @RequestParam("description") String description, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("department/index");
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName(name);
        departmentDTO.setId(id);
        departmentDTO.setDescription(description);
        departmentService.save(departmentDTO);
        Page<DepartmentDTO> listDepartment = departmentService.findAll(pageable);
        modelAndView.addObject("listDepartment", listDepartment);
        return modelAndView;
    }
}
