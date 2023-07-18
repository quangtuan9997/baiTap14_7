package baitap14_07.controller;

import baitap14_07.domain.Employee;
import baitap14_07.repository.EmployeeRepository;
import baitap14_07.service.DepartmentService;
import baitap14_07.service.EmployeeService;
import baitap14_07.service.RoleService;
import baitap14_07.service.dto.DepartmentDTO;
import baitap14_07.service.dto.EmployeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final RoleService roleService;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository, DepartmentService departmentService, RoleService roleService) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.roleService = roleService;
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(name = "name", required = false, defaultValue = "") String name, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("employee/index");
        Page<EmployeeDTO> employeeDTOS = employeeService.findAll(name, pageable);
        modelAndView.addObject("listEmployee", employeeDTOS);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showAdd() {
        ModelAndView modelAndView = new ModelAndView("employee/add");
        modelAndView.addObject("departments", departmentService.findAll());
        modelAndView.addObject("roles", roleService.findAll());
        modelAndView.addObject("employee", new EmployeeDTO());
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView doAdd(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("employee/add", bindingResult.getModel());
            modelAndView.addObject("employee", employeeDTO);
            modelAndView.addObject("departments", departmentService.findAll());
            modelAndView.addObject("roles", roleService.findAll());
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("employee/add");
        modelAndView.addObject("departments", departmentService.findAll());
        modelAndView.addObject("roles", roleService.findAll());
        if (employeeRepository.findOneByEmailIgnoreCase(employeeDTO.getEmail()).isPresent()) {
            return modelAndView.addObject("message1", "Email is already in use!");
        }
        employeeService.save(employeeDTO);
        modelAndView.addObject("message1", "Create Success!!!");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@Valid @PathVariable("id") Long id, BindingResult bindingResult) {
        Optional<EmployeeDTO> employeeDTO = employeeService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        if (!employeeDTO.isPresent()) {
            return modelAndView.addObject("massage", "Entity not found");
        }
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView1 = new ModelAndView("employee/add", bindingResult.getModel());
            modelAndView.addObject("employee", employeeDTO);
            modelAndView.addObject("departments", departmentService.findAll());
            modelAndView.addObject("roles", roleService.findAll());
            return modelAndView1;
        }
        modelAndView.addObject("employeeUpload", employeeDTO.get());
        List<DepartmentDTO> listDepart = departmentService.findAll();
        modelAndView.addObject("listDepartment", listDepart);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView doEdit(@ModelAttribute("employeeUpload") EmployeeDTO employeeDTO) {
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        if (!employeeRepository.existsById(employeeDTO.getId())) {
            return modelAndView.addObject("massage1", "Entity not found");
        }
        Optional<Employee> employee = employeeRepository.findOneByEmailIgnoreCase(employeeDTO.getEmail());
        if (employee.isPresent() && !employee.get().getId().equals(employeeDTO.getId())) {
            return modelAndView.addObject("massage2", "Email already exists!!! ");
        }
        employeeService.save(employeeDTO);
        return modelAndView.addObject("massage3", "update success!!!");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView doDelete(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee/index");
        if (employeeRepository.existsById(id)) {
            employeeService.delete(id);
            return modelAndView.addObject("message1", "delete success!!!");
        }
        return modelAndView.addObject("message2", "Entity not found");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("employee/detail");
        Optional<EmployeeDTO> employeeDTO = employeeService.findOne(id);
        if (employeeDTO.isPresent()) {
            modelAndView.addObject("employee", employeeDTO.get());
            return modelAndView;
        }
        return modelAndView.addObject("massage", "Entity not found!!!");
    }
}

