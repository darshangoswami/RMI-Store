import java.util.HashMap;
import java.util.Map;

// Cart class
public class Cart implements java.io.Serializable {
    private Map<Item, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        items.put(item, quantity);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    // public Map<Item, Integer> getItems() {
    //     return items;
    // }

    public String getItems() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            sb.append(item.getTitle()).append(" - Quantity: ").append(quantity).append("\n");
        }
        return sb.toString();
    }

    public void clear() {
        items.clear();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }
}
