package com.capstone.kuhako.models;

import com.capstone.kuhako.models.JoinModule.Contracts;
import com.capstone.kuhako.models.JoinModule.ContractsHistory;
import com.capstone.kuhako.models.JoinModule.Transactions;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "collector")
public class Collector {
    @Id
    @GeneratedValue
    private Long collector_id;

    @ManyToOne
    @JoinColumn(name="reseller_id", nullable = false)
    private Reseller reseller;

    @OneToMany(mappedBy = "collector", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Client> clients;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column
    private String address;

    @Column
    private String email;

    @OneToMany(mappedBy = "collector")
    @JsonIgnore
    private Set<Contracts> contracts;

    @OneToMany(mappedBy = "collector")
    @JsonIgnore
    private Set<ContractsHistory> contractHistories;

    @OneToMany(mappedBy = "collector")
    @JsonIgnore
    private Set<Transactions> transactions;

 /*   @OneToMany(mappedBy = "collector")
    @JsonIgnore
    private Set<SendCollectors> sendCollectors;*/

    public Collector() {
    }

    public Collector(Reseller reseller, Set<Client> clients, String username, String password, String fullName, String address, String email, Set<Contracts> contracts, Set<ContractsHistory> contractHistories, Set<Transactions> transactions) {
        this.reseller = reseller;
        this.clients = clients;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.contracts = contracts;
        this.contractHistories = contractHistories;
        this.transactions = transactions;
    }

    public Long getCollector_id() {
        return collector_id;
    }

//    public void setCollector_id(Long collector_id) {
//        this.collector_id = collector_id;
//    }


    public Reseller getReseller() {
        return reseller;
    }

    public void setReseller(Reseller reseller) {
        this.reseller = reseller;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

 /*   public Set<SendCollectors> getSendCollectors() {
        return sendCollectors;
    }

    public void setSendCollectors(Set<SendCollectors> sendCollectors) {
        this.sendCollectors = sendCollectors;
    }*/

    public Set<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(Set<Contracts> contracts) {
        this.contracts = contracts;
    }

    public Set<ContractsHistory> getContractHistories() {
        return contractHistories;
    }

    public void setContractHistories(Set<ContractsHistory> contractHistories) {
        this.contractHistories = contractHistories;
    }

    public Set<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transactions> transactions) {
        this.transactions = transactions;
    }
}
