import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Marketplace implements DigitalMarketInterface {
  private Map<Member, Cart> memberCarts;
  private List<Member> members;
  private List<Item> inventory;

  public Marketplace() {
    memberCarts = new HashMap<>();
    members = new ArrayList<>();
    inventory = new ArrayList<>();
  }

  @Override
  public boolean registerMember(String username, String password, AccountType accountType) throws RemoteException {
    MemberFactory factory;
    if (accountType == AccountType.BUYER) {
      factory = new CustomerFactory();
    } else { // Default to SELLER
      factory = new AdminFactory();
    }

    Member newMember = factory.createMember(username, password);
    members.add(newMember);
    if (accountType == AccountType.BUYER) {
      memberCarts.put(newMember, new Cart());
    }

    return true;
  }

  @Override
  public Member authenticateMember(String username, String password) throws RemoteException {
    for (Member member : members) {
      if (member.getUsername().equals(username) && member.getPassword().equals(password)) {
        return member;
      }
    }
    return null;
  }

  @Override
  public List<Item> listAvailableItems() throws RemoteException {
    return new ArrayList<>(inventory);
  }

  @Override
  public boolean addItem(Item item, Member seller) throws RemoteException {
    AddItemOperation addItemOperation = new AddItemOperation(inventory);
    return addItemOperation.execute(item, seller);
  }

  @Override
  public boolean removeItem(int itemIdToRemove, Member seller) throws RemoteException {
    RemoveItemOperation removeItemOperation = new RemoveItemOperation(inventory);
    Item itemToRemove = inventory.stream()
        .filter(item -> item.getItemId() == itemIdToRemove)
        .findFirst()
        .orElse(null);
    if (itemToRemove == null)
      return false;
    return removeItemOperation.execute(itemToRemove, seller);
  }

  @Override
  public boolean updateItem(int itemIdToUpdate, String newName, String newDesc, double newPrice, int newQuantity,
      Member seller) throws RemoteException {
    UpdateItemOperation updateItemOperation = new UpdateItemOperation(inventory);
    // First, find the item by its ID
    Item itemToUpdate = inventory.stream()
        .filter(item -> item.getItemId() == itemIdToUpdate)
        .findFirst()
        .orElse(null);
    if (itemToUpdate == null)
      return false;

    // Set new values to the item before executing the operation
    itemToUpdate.setTitle(newName);
    itemToUpdate.setDetails(newDesc);
    itemToUpdate.setPrice(newPrice);
    itemToUpdate.setStock(newQuantity);

    return updateItemOperation.execute(itemToUpdate, seller);
  }

  @Override
  public boolean addUser(String username, String password, String accountType) throws RemoteException {
    AddUserOperation addUserOperation = new AddUserOperation(members);
    return addUserOperation.execute(username, password, accountType);
  }

  @Override
  public boolean removeUser(String username) throws RemoteException {
    RemoveUserOperation removeUserOperation = new RemoveUserOperation(members);
    return removeUserOperation.execute(username, null, null);
  }

  @Override
  public Cart retrieveCart(Member buyer) throws RemoteException {
    RetrieveCartOperation retrieveCartOperation = new RetrieveCartOperation(memberCarts);
    return retrieveCartOperation.execute(buyer);
  }

}
