import java.util.List;

public class UpdateItemOperation extends ItemOperation {
  private List<Item> inventory;

  public UpdateItemOperation(List<Item> inventory) {
    this.inventory = inventory;
  }

  @Override
  protected boolean authorize(Member seller) {
    return seller instanceof Admin; // Only admins can update items
  }

  @Override
  protected boolean performTask(Item item, Member seller) {
    if (item != null) {
      item.setTitle(item.getTitle());
      item.setDetails(item.getDetails());
      item.setPrice(item.getPrice());
      item.setStock(item.getStock());
      return true;
    }
    return false; // Item not found
  }
}
