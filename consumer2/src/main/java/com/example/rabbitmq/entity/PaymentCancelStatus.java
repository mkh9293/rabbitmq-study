package com.example.rabbitmq.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PaymentCancelStatus {

    private boolean cancelStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate cancelDate;

    private String invoiceNumber;

    public PaymentCancelStatus() {
    }

    public PaymentCancelStatus(boolean cancelStatus, LocalDate cancelDate, String invoiceNumber) {
        this.cancelStatus = cancelStatus;
        this.cancelDate = cancelDate;
        this.invoiceNumber = invoiceNumber;
    }

    public boolean isCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(boolean cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public LocalDate getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDate cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String toString() {
        return "PaymentCalcelStatus{" +
                "cancelStatus=" + cancelStatus +
                ", cancelDate=" + cancelDate +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                '}';
    }
}
