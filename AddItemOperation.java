import java.util.List;

public class AddItemOperation extends ItemOperation {
  private List<Item> inventory;

  public AddItemOperation(List<Item> inventory) {
    this.inventory = inventory;
  }

  @Override
  protected boolean authorize(Member member) {
    return member instanceof Admin; // Only admins can add items
  }

  @Override
  protected boolean performTask(Item item, Member member) {
    inventory.add(item);
    return true;
  }
}
