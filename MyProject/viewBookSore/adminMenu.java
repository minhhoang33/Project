package MyProject.viewBookSore;

import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;
import MyProject.bookStoreService.adminService;

import java.util.List;
import java.util.Scanner;

public class adminMenu {
    public void displayMenuAdmin(Scanner scanner, List<Book> books, List<Ticket> tickets, List<TicketRentBook> ticketRentBooks) {
        adminService adminService = new adminService();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1 - Thêm truyện");
            System.out.println("2 - Sửa truyện");
            System.out.println("3 - Xóa truyện");
            System.out.println("4 - Xác nhận yêu cầu mua truyện ");
            System.out.println("5 - Xác nhận yêu cầu thuê truyện");
            System.out.println("0 - Quay lại ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> adminService.addBook(scanner, books);
                case 2 -> adminService.editBook(scanner, books);
                case 3 -> adminService.deleteBook(scanner, books);
                case 4 -> adminService.approveTicket(scanner,tickets,books);
                case 5 -> adminService.approveRentBook(scanner,books,ticketRentBooks);
                case 0 -> {
                    System.out.println("Đã trở lại Menu chính.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }

    }
}
