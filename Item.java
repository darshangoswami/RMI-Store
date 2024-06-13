import java.util.Random;

// Item class
public class Item implements java.io.Serializable {
    private int itemId;
    private String title;
    private String details;
    private double price;
    private int stock;

    public Item(String title, String details, double price, int stock) {
        this.itemId = generateItemId();
        this.title = title;
        this.details = details;
        this.price = price;
        this.stock = stock;
    }

    private int generateItemId() {
        Random rand = new Random();
        int min = 1;
        int max = 1000; // Expanded range for uniqueness
        return rand.nextInt((max - min) + 1) + min;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
