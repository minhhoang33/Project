package MyProject.bookStoreService;

import MyProject.InvalidPhoneNumberException;
import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class customerService {
    // xem truyen
    public void viewBooks(List<Book> books) {
        books.sort(Comparator.comparingDouble(Book::getRating).reversed());
        System.out.println("Danh sách truyện:");
        for (Book book : books) {
            System.out.println("Tên: " + book.getTitle() + ", Thể loại: " + book.getGenre() +
                    ", Giá: " + book.getPrice() + ", Đánh giá: " + book.getRating() +
                    ", Số lượng trong kho: " + book.getStock());
        }
    }
    // danh gia truyen
    public void rateBook(Scanner scanner, List<Book> books) {
        System.out.print("Nhập tên truyện muốn đánh giá: ");
        String title = scanner.nextLine();
        Book book = adminService.findBookByTitle(title,books);

        if (book != null) {
            System.out.print("Nhập đánh giá của bạn (từ 1 đến 5): ");
            double customerRating = Double.parseDouble(scanner.nextLine());

            if (customerRating >= 1 && customerRating <= 5) {
                book.addRating(customerRating);
                System.out.println("Cảm ơn bạn đã đánh giá! Đánh giá trung bình hiện tại của truyện là: " + book.getRating());
            } else {
                System.out.println("Đánh giá không hợp lệ. Vui lòng nhập giá trị từ 1 đến 5.");
            }
        } else {
            System.out.println("Truyện không tồn tại.");
        }
    }
// mua truyen
  public void purchaseOrRentBook(Scanner scanner,List<Ticket> tickets,List<Book> books) {
        System.out.print("Nhập tên truyện muốn mua: ");
        String title = scanner.nextLine();
        Book book = adminService.findBookByTitle(title, books);

      if (book != null) {
          System.out.print("Nhập tên của bạn: ");
          String customerName = scanner.nextLine();

          String customerPhone;
          while (true) {
              System.out.print("Nhập số điện thoại của bạn: ");
              customerPhone = scanner.nextLine();
              try {
                  validatePhoneNumber(customerPhone);
                  break;
              } catch (InvalidPhoneNumberException e) {
                  System.out.println(e.getMessage());
              }
          }

            System.out.print("Nhập số lượng truyện muốn mua: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity > 0 && quantity <= book.getStock()) {
                Ticket ticket = new Ticket(customerName,customerPhone, title, quantity);
                tickets.add(ticket);
                System.out.println("So tien ban phai thanh toan la: " + (book.getPrice()*quantity));
                System.out.println("Đỗ Minh Hoàng || Techcombank || 19037623898018");
                System.out.println("Khi bạn chuyển khoản thành công admin sẽ chấp nhận yêu cầu mua hàng");
            } else {
                System.out.println("Số lượng không hợp lệ. Vui lòng kiểm tra lại.");
            }
        } else {
            System.out.println("Truyện không tồn tại.");
        }
    }
    // thue truyen
    public void RenBook(Scanner scanner, List<Book> books, List<TicketRentBook> ticketRentBooks) {
        System.out.print("Nhập tên truyện muốn thuê: ");
        String title = scanner.nextLine();
        Book book = adminService.findBookByTitle(title, books);

        if (book != null) {
            System.out.print("Nhập tên của bạn: ");
            String customerName = scanner.nextLine();

            String customerPhone;
            while (true) {
                System.out.print("Nhập số điện thoại của bạn: ");
                customerPhone = scanner.nextLine();
                try {
                    validatePhoneNumber(customerPhone);
                    break;
                } catch (InvalidPhoneNumberException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("Nhập số lượng truyện muốn thuê: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (quantity > 0 && quantity <= book.getStock()) {
                LocalDate returnDate;
                while (true) {
                    System.out.print("Nhập ngày trả sách (yyyy-MM-dd): ");
                    String returnDateString = scanner.nextLine();
                    try {
                        returnDate = LocalDate.parse(returnDateString, DateTimeFormatter.ISO_LOCAL_DATE);
                        if (returnDate.isAfter(LocalDate.now())) {
                            break; // Ngày trả hợp lệ, thoát khỏi vòng lặp
                        } else {
                            System.out.println("Ngày trả phải lớn hơn ngày hiện tại. Vui lòng nhập lại.");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
                    }
                }

                TicketRentBook ticketRentBook = new TicketRentBook(customerName,customerPhone, title, quantity, returnDate);
                ticketRentBooks.add(ticketRentBook);
                // tinh tien coc
                double deposit = ((book.getPrice() *50/100))*quantity;
                // tinh phi thue truyen
                long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), returnDate);
                int retalFee = Math.toIntExact((daysBetween * 5000)* quantity);
                System.out.println("Số tiền bạn phải cọc trước là(phải cọc trước 50% gtri đơn hàng): " + deposit);
                System.out.println("Đỗ Minh Hoàng || Techcombank || 19037623898018");
                System.out.println("Số ngày bạn thuê truyện là " + daysBetween + " ngày");
                System.out.println("Phí thuê truyện của bạn là: " + retalFee + " Vui lòng thanh toán khi trả sách");
                System.out.println("Khi bạn chuyển khoản thành công admin sẽ chấp nhận yêu cầu thuê truyện");

            } else {
                System.out.println("Số lượng không hợp lệ. Vui lòng kiểm tra lại.");
            }
        } else {
            System.out.println("Truyện không tồn tại.");
        }

    }
    // check phone number
    private static void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {

        String phonePattern = "\\d{10,15}";
        if (!Pattern.matches(phonePattern, phoneNumber)) {
            throw new InvalidPhoneNumberException("Số điện thoại không hợp lệ. Vui lòng nhập lại (chỉ chứa chữ số, từ 10 đến 15 ký tự).");
        }
    }

}
