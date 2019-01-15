package com.library.domain;

import java.io.Serializable;
import java.util.Date;

public class Lend implements Serializable {

    private long ser_num;
    private long bookId;
    private long readerId;
    private Date lendDate;
    private Date backDate;

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public void setSer_num(long ser_num) {
        this.ser_num = ser_num;
    }

    public long getReaderId() {
        return readerId;
    }

    public long getBookId() {
        return bookId;
    }

    public Date getBackDate() {
        return backDate;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public long getSer_num() {
        return ser_num;
    }
}
