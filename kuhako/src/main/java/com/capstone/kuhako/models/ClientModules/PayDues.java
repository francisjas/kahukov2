package com.capstone.kuhako.models.ClientModules;

import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.Collector;
import com.capstone.kuhako.models.JoinModule.Contracts;
import com.capstone.kuhako.models.JoinModule.ContractsHistory;

import javax.persistence.*;

@Entity
@Table(name = "payDues")
public class PayDues {
    @Id
    @GeneratedValue
    private Long payDues_id;

    @OneToOne
    @JoinColumn(name="client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name="contracts_id")
    private Contracts contracts;

    @ManyToOne
    @JoinColumn(name="contractsHistory_id")
    private ContractsHistory contractsHistory;

    @ManyToOne
    @JoinColumn(name="transactionHistory_id")
    private TransactionHistory transactionHistory;

    @Column
    private double itemPrice;

    @Column
    private String referenceNumber;

    @Column
    private String paymentType;

    @Lob
    @Column(name = "transaction_proof", columnDefinition="LONGBLOB")
    private byte[] transactionProof;

    @Column(name = "transaction_proof_content_type")
    private String transactionProofContentType;

    public PayDues() {
    }

    public PayDues(Client client, Contracts contracts, ContractsHistory contractsHistory, TransactionHistory transactionHistory, double itemPrice, String referenceNumber, String paymentType, byte[] transactionProof, String transactionProofContentType) {
        this.client = client;
        this.contracts = contracts;
        this.contractsHistory = contractsHistory;
        this.transactionHistory = transactionHistory;
        this.itemPrice = itemPrice;
        this.referenceNumber = referenceNumber;
        this.paymentType = paymentType;
        this.transactionProof = transactionProof;
        this.transactionProofContentType = transactionProofContentType;
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Contracts getContracts() {
        return contracts;
    }

    public void setContracts(Contracts contracts) {
        this.contracts = contracts;
    }

    public ContractsHistory getContractsHistory() {
        return contractsHistory;
    }

    public void setContractsHistory(ContractsHistory contractsHistory) {
        this.contractsHistory = contractsHistory;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public byte[] getTransactionProof() {
        return transactionProof;
    }

    public void setTransactionProof(byte[] transactionProof) {
        this.transactionProof = transactionProof;
    }

    public String getTransactionProofContentType() {
        return transactionProofContentType;
    }

    public void setTransactionProofContentType(String transactionProofContentType) {
        this.transactionProofContentType = transactionProofContentType;
    }
}
