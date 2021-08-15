package com.example.rabbitmq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class InvoiceCreatedMessage {

    private double amount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate CreatedDate;

    private String currency;

    private String invoiceNumber;

    public InvoiceCreatedMessage() {
    }

    public InvoiceCreatedMessage(double amount, LocalDate createdDate, String currency, String invoiceNumber) {
        this.amount = amount;
        CreatedDate = createdDate;
        this.currency = currency;
        this.invoiceNumber = invoiceNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        CreatedDate = createdDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String toString() {
        return "InvoiceCreatedMessage{" +
                "amount=" + amount +
                ", CreatedDate=" + CreatedDate +
                ", currency='" + currency + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                '}';
    }
}
