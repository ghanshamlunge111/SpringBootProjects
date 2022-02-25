package com.example.EmployeeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.EmployeeManagement.model.Employee;
import com.example.EmployeeManagement.service.EmployeeService;

@Controller
public class EmployeeController {

//	@RequestMapping("/")
//    public String welcome() {
//        return "index";
//    }
	
	@Autowired
	private EmployeeService employeeService;
	
	//Display List of Employees
	@RequestMapping
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		//Create model attribute to bind form data
		
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
		
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value = "id") long id , Model model) {
		// get Employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		//set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
		
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		//Save Employee to database 
		
		employeeService.saveEmployee(employee);
		return "redirect:/";
		
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		//Call delete Employee method
		this.employeeService.deleteEmployeeById(id);
		
		return "redirect:/";
	}
	

}
 




















