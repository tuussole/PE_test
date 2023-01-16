package com.test.webbank.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    public enum OperationType {DEBIT, CREDIT}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    private OperationType opType;

    @Column(name = "date")
    private Timestamp dateOfTransaction;

    @Column(name = "amount")
    private BigDecimal cashAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_acc_id")
    private Account sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_acc_id")
    private Account recipient;

    public Transaction() {
        dateOfTransaction = new Timestamp(System.currentTimeMillis());
    }

    public Transaction(Client client, OperationType opType, BigDecimal cashAmount, Account sender, Account recipient) {
        this.client = client;
        this.opType = opType;
        this.cashAmount = cashAmount;
        this.sender = sender;
        this.recipient = recipient;
        dateOfTransaction = new Timestamp(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public OperationType getOpType() {
        return opType;
    }

    public void setOpType(OperationType opType) {
        this.opType = opType;
    }

    public Timestamp getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Timestamp dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getRecipient() {
        return recipient;
    }

    public void setRecipient(Account recipient) {
        this.recipient = recipient;
    }
}
