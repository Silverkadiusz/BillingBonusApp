package com.example.billingappvol2.work;

import com.example.billingappvol2.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;

public interface WorkDoneRepository extends JpaRepository<WorkDone, Long> {

    List<WorkDone> findByEmployeeAndDateBetween(Employee employee, LocalDate start, LocalDate end);

}
