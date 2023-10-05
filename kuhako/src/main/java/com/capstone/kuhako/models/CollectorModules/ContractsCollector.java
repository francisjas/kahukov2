package com.capstone.kuhako.models.CollectorModules;

import javax.persistence.*;

@Entity
@Table(name = "contractsCollector")
public class ContractsCollector {
    @Id
    private Long id;

    @Column
    private String username;
    @Column
    private double debtRemaining;

    public ContractsCollector() {
    }

    public ContractsCollector(Long id, String username, double debtRemaining) {
        this.id = id;
        this.username = username;
        this.debtRemaining = debtRemaining;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getDebtRemaining() {
        return debtRemaining;
    }

    public void setDebtRemaining(double debtRemaining) {
        this.debtRemaining = debtRemaining;
    }
}
