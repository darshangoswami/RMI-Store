import java.util.List;

public class RemoveItemOperation extends ItemOperation {
  private List<Item> inventory;

  public RemoveItemOperation(List<Item> inventory) {
    this.inventory = inventory;
  }

  @Override
  protected boolean authorize(Member member) {
    return member instanceof Admin; // Only admins can remove items
  }

  @Override
  protected boolean performTask(Item item, Member member) {
    return inventory.removeIf(i -> i.getItemId() == item.getItemId());
  }
}