package com.capstone.kuhako.models.ClientModules;

import com.capstone.kuhako.models.Client;
import com.capstone.kuhako.models.ResellerModule.Contracts;
import com.capstone.kuhako.models.ResellerModule.ContractsHistory;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "payDues")
public class PayDues {
    @Id
    @GeneratedValue
    private Long payDues_id;

    @ManyToOne
    @JoinColumn(name="client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name="contracts_id")
    private Contracts contracts;

    @ManyToOne
    @JoinColumn(name="contractsHistory_id")
    private ContractsHistory contractsHistory;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date payDate;

    @Column
    private double amountPayment;

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

    public PayDues(Client client, Contracts contracts, ContractsHistory contractsHistory, Date payDate, double amountPayment, String referenceNumber, String paymentType, byte[] transactionProof, String transactionProofContentType) {
        this.client = client;
        this.contracts = contracts;
        this.contractsHistory = contractsHistory;
        this.payDate = payDate;
        this.amountPayment = amountPayment;
        this.referenceNumber = referenceNumber;
        this.paymentType = paymentType;
        this.transactionProof = transactionProof;
        this.transactionProofContentType = transactionProofContentType;
    }

    public Long getPayDues_id() {
        return payDues_id;
    }

//    public void setPayDues_id(Long payDues_id) {
//        this.payDues_id = payDues_id;
//    }

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

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public double getAmountPayment() {
        return amountPayment;
    }

    public void setAmountPayment(double amountPayment) {
        this.amountPayment = amountPayment;
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
