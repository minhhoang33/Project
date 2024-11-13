package MyProject.viewBookSore;

import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;
import MyProject.bookStoreService.customerService;

import java.util.List;
import java.util.Scanner;

public class customerMenu {
    public void displayMenuCustomer(Scanner scanner, List<Book> books, List<Ticket> tickets, List<TicketRentBook> ticketRentBooks) {
        customerService customerService = new customerService();
        while (true) {
            System.out.println("=====Chào mừng bạn đến với cửa hàng sách !=====");
            System.out.println("1 - Xem danh sách truyện");
            System.out.println("2 - Đánh giá truyện");
            System.out.println("3 - Mua truyện");
            System.out.println("4 - Thuê truyện");
            System.out.println("0 - Quay lại");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> customerService.viewBooks(books);
                case 2 -> customerService.rateBook(scanner, books);
                case 3 -> customerService.purchaseOrRentBook(scanner,tickets,books);
                case 4 -> customerService.RenBook(scanner,books,ticketRentBooks);
                case 0 -> {
                    System.out.println("Đã trở lại Menu chính.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
