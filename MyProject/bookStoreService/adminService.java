package MyProject.bookStoreService;

import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;

import java.util.List;
import java.util.Scanner;

public class adminService {
    //them truyen
    public void addBook(Scanner scanner, List<Book> books) {
        System.out.print("Nhập tên truyện: ");
        String title = scanner.nextLine();
        System.out.print("Nhập thể loại: ");
        String genre = scanner.nextLine();
        System.out.print("Nhập giá bán: ");
        double price = Double.parseDouble(scanner.nextLine());
        double rating = 0;
        System.out.print("Nhập số lượng trong kho: ");
        int stock = Integer.parseInt(scanner.nextLine());

        books.add(new Book(title,genre,price,rating,stock));
        System.out.println("Thêm truyện thành công!");
    }
// sua truyen
    public void editBook(Scanner scanner, List<Book> books) {
        System.out.print("Nhập tên truyện cần sửa: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title, books);

        if (book != null) {
            System.out.print("Nhập tên mới: ");
            book.setTitle(scanner.nextLine());
            System.out.print("Nhập thể loại mới: ");
            book.setGenre(scanner.nextLine());
            System.out.print("Nhập giá mới: ");
            book.setPrice(Double.parseDouble(scanner.nextLine()));
            System.out.print("Nhập số lượng mới trong kho: ");
            book.setStock(Integer.parseInt(scanner.nextLine()));
            System.out.println("Sửa truyện thành công!");
        } else {
            System.out.println("Truyện không tồn tại.");
        }
    }
// xoa truyen
    public void deleteBook(Scanner scanner, List<Book> books) {
        System.out.print("Nhập tên truyện cần xóa: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title,books);

        if (book != null) {
            books.remove(book);
            System.out.println("Xóa truyện thành công!");
        } else {
            System.out.println("Truyện không tồn tại.");
        }
    }
// chap nhan yeu cau mua truyen
    public void approveTicket( Scanner scanner, List<Ticket> tickets,List<Book> books) {
        if (tickets.isEmpty()) {
            System.out.println("Không có yêu cầu nào để duyệt.");
            return;
        }

        for (Ticket ticket : tickets) {
            System.out.println(ticket);

            System.out.print("Chấp nhận yêu cầu này? (y/n): ");
            String choice = scanner.nextLine();

            if ("y".equalsIgnoreCase(choice)) {
                Book book = findBookByTitle(ticket.getBookTitle(),books);
                if (book != null && book.getStock() >= ticket.getQuantity()) {
                    book.setStock(book.getStock() - ticket.getQuantity());
                    ticket.setApproved(true);
                    System.out.println("Yêu cầu đã được chấp nhận.");
                } else {
                    System.out.println("Yêu cầu không thể chấp nhận (không đủ số lượng trong kho).");
                }
            } else {
                System.out.println("Yêu cầu không được chấp nhận.");
            }
        }
    }
    // Chap nhan yeu cau thue truyen
    public void approveRentBook(Scanner scanner, List<Book> books, List<TicketRentBook> ticketRentBooks) {
        if (ticketRentBooks.isEmpty()) {
            System.out.println("Không có yêu cầu nào để duyệt.");
            return;
        }
        for (TicketRentBook ticketRentBook : ticketRentBooks) {
            System.out.println(ticketRentBook);
            System.out.print("Chấp nhận yêu cầu này? (y/n): ");
            String choice = scanner.nextLine();
            if ("y".equalsIgnoreCase(choice)) {
                Book book = findBookByTitle(ticketRentBook.getBookTitle(),books);
                if (book != null && book.getStock() >= ticketRentBook.getQuantity()) {
                    book.setStock(book.getStock() - ticketRentBook.getQuantity());
                    ticketRentBook.setApproved(true);
                    System.out.println("Yêu cầu đã được chấp nhận.");
                } else {
                    System.out.println("Yêu cầu không thể chấp nhận (không đủ số lượng trong kho).");
                }
            } else {
                System.out.println("Yêu cầu không được chấp nhận.");
            }

        }
    }

// tim truyen theo tieu de
    public static Book findBookByTitle(String title,List<Book> books) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
