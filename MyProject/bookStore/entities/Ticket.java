package MyProject.bookStore.entities;

import java.time.LocalDate;

public class Ticket {
    private String customerName;
    private String customerPhone;
    private String bookTitle;
    private int quantity;
    private boolean approved = false;
    private LocalDate requestDate;

    public Ticket(String customerName, String customerPhone, String bookTitle, int quantity) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.requestDate = LocalDate.now();
    }

    public String getBookTitle() {
        return bookTitle;
    }
    public int getQuantity() {
        return quantity;
    }
    public boolean isApproved() {
        return approved;
    }
    public LocalDate getRequestDate() {
        return requestDate;
    }
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "Yêu cầu từ: " + customerName + " | Tên truyện: " + bookTitle + " | Số lượng: " + quantity +
                " | Ngày yêu cầu: " + requestDate + " | Trạng thái: " + (approved ? "Đã chấp nhận" : "Chờ duyệt");
    }
}
