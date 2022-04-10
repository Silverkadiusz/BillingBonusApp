package com.example.billingappvol2.work;

import com.example.billingappvol2.employee.Employee;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class WorkDone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public WorkDone() {
    }

    public WorkDone(Employee employee, LocalDate date, int timeInMinutes, boolean additional) {
        this.employee = employee;
        this.date = date;
        this.timeInMinutes = timeInMinutes;
        this.additional = additional;
    }

    private int timeInMinutes;

    private boolean additional;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public boolean isAdditional() {
        return additional;
    }

    public void setAdditional(boolean additional) {
        this.additional = additional;
    }
}
