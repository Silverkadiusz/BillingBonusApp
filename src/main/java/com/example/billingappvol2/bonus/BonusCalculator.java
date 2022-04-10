package com.example.billingappvol2.bonus;

import com.example.billingappvol2.employee.Employee;
import com.example.billingappvol2.work.WorkDone;
import com.example.billingappvol2.work.WorkDoneRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BonusCalculator {

    public WorkDoneRepository workDoneRepository;

    public BonusCalculator(WorkDoneRepository workDoneRepository) {
        this.workDoneRepository = workDoneRepository;
    }

    public List<EmployeeWithBonus> calculateBonusForEmployees(List<Employee> employees) {
        return employees
                .stream()
                .map(this::createEmployeeWithBonus)
                .collect(Collectors.toList());
    }

    private EmployeeWithBonus createEmployeeWithBonus(Employee e) {
        BigDecimal bonus = calculateBonusForEmployee(e);
        return new EmployeeWithBonus(e, bonus, e.getBaseSalary().add(bonus));
    }

    private BigDecimal calculateBonusForEmployee(Employee employee) {
        YearMonth currentMonth = YearMonth.now();
        List<WorkDone> workDone = workDoneRepository.findByEmployeeAndDateBetween(employee, currentMonth.atDay(1), currentMonth.atEndOfMonth());

        int extraWorkInMinutes = workDone.stream()
                .filter(WorkDone::isAdditional)
                .mapToInt(WorkDone::getTimeInMinutes)
                .sum();

        int extraPayment = (int) (extraWorkInMinutes / 60.0 * 30);
        extraPayment = Math.min(extraPayment, 500);
        return BigDecimal.valueOf(extraPayment);
    }
}
