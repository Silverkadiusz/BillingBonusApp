package com.example.billingappvol2.testdata;

import com.example.billingappvol2.employee.Employee;
import com.example.billingappvol2.employee.EmployeeRepository;
import com.example.billingappvol2.work.WorkDone;
import com.example.billingappvol2.work.WorkDoneRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Profile("dev")
@Component
public class TestdataProvider {

    private final EmployeeRepository employeeRepository;
    private final WorkDoneRepository workDoneRepository;

    public TestdataProvider(EmployeeRepository employeeRepository, WorkDoneRepository workDoneRepository) {
        this.employeeRepository = employeeRepository;
        this.workDoneRepository = workDoneRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void insertTestData() {

        Employee employee1 = employeeRepository.findById(1L).orElseThrow();
        Employee employee3 = employeeRepository.findById(3L).orElseThrow();
        Employee employee5 = employeeRepository.findById(5L).orElseThrow();

        List<WorkDone> workDoneList = Arrays.asList(
                new WorkDone(employee1, LocalDate.now(), 90, false),
                new WorkDone(employee1, LocalDate.now(), 90, true),
                new WorkDone(employee3, LocalDate.now(), 14, true),
                new WorkDone(employee3, LocalDate.now(), 67, true),
                new WorkDone(employee3, LocalDate.now(), 90, false),
                new WorkDone(employee3, LocalDate.now(), 20, true),
                new WorkDone(employee5, LocalDate.now(), 23, true),
                new WorkDone(employee5, LocalDate.now(), 30, true),
                new WorkDone(employee5, LocalDate.now(), 12, false)
        );

        workDoneRepository.saveAll(workDoneList);
    }
}
