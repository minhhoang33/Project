package MyProject.bookStore.entities;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String genre;
    private double price;
    private double rating; // Danh gia trung binh
    private int stock;
    private List<Double> ratings; // Danh sach danh gia tu khach hang

    public Book(String title, String genre, double price, double rating, int stock) {
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.stock = stock;
        this.rating = 0.0;
        this.ratings = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // them danh gia va cap nhap danh gia trung binh
    public void addRating(double newRating) {
        ratings.add(newRating);
        updateAverageRating();
    }

    // tinh diem danh gia trung binh
    private void updateAverageRating() {
        double sum = 0;
        for (double r : ratings) {
            sum += r;
        }
        this.rating = sum / ratings.size();
    }

    @Override
    public String toString() {
        return "Tên truyện: " + title + " | Thể loại: " + genre + " | Giá bán: " + price +
                " | Đánh giá trung bình: " + rating + " | Số lượng: " + stock;
    }
}

