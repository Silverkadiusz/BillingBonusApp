package com.example.billingappvol2;

import com.example.billingappvol2.bonus.BonusCalculator;
import com.example.billingappvol2.bonus.EmployeeWithBonus;
import com.example.billingappvol2.employee.Employee;
import com.example.billingappvol2.employee.EmployeeRepository;
import com.example.billingappvol2.work.WorkDone;
import com.example.billingappvol2.work.WorkDoneRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    private final EmployeeRepository employeeRepository;
    private final WorkDoneRepository workDoneRepository;
    private final BonusCalculator bonusCalculator;

    public MainController(EmployeeRepository employeeRepository,
                          WorkDoneRepository workDoneRepository,
                          BonusCalculator bonusCalculator) {
        this.employeeRepository = employeeRepository;
        this.workDoneRepository = workDoneRepository;
        this.bonusCalculator = bonusCalculator;
    }

    @GetMapping("/")
    public String homepage(Model model) {
        List<Employee> employees = employeeRepository.findAllByStillHiredTrue();
        List<EmployeeWithBonus> employeeWithBonuses = bonusCalculator.calculateBonusForEmployees(employees);

        model.addAttribute("employeeWithBonuses", employeeWithBonuses);
        WorkDone workDone = new WorkDone();
        workDone.setDate(LocalDate.now());
        model.addAttribute("workDone", workDone);
        model.addAttribute("employees", employeeRepository.findAllByStillHiredTrue());

        return "index";
    }

    @PostMapping("/")
    public String addWorkDone(WorkDone workDone) {
        workDoneRepository.save(workDone);
        return "redirect:/";
    }

}
