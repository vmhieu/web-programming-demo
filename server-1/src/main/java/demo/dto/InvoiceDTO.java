package demo.dto;

import java.util.List;

/**
 * Created by Junkie on 10/12/2021.
 **/
public class InvoiceDTO {
    private int month;
    private int year;
    private String name;
    private String studentCode;
    private float roomFee;
    private float ticketFee;
    private float totalPrice;
    private List<BillDTO> bill;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public float getRoomFee() {
        return roomFee;
    }

    public void setRoomFee(float roomFee) {
        this.roomFee = roomFee;
    }

    public float getTicketFee() {
        return ticketFee;
    }

    public void setTicketFee(float ticketFee) {
        this.ticketFee = ticketFee;
    }

    public List<BillDTO> getBill() {
        return bill;
    }

    public void setBill(List<BillDTO> bill) {
        this.bill = bill;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
