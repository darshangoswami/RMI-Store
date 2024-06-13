import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// Remote interface for the digital market
public interface DigitalMarketInterface extends Remote {
    boolean registerMember(String username, String password, AccountType accountType) throws RemoteException;

    Member authenticateMember(String username, String password) throws RemoteException;

    List<Item> listAvailableItems() throws RemoteException;

    boolean addItem(Item item, Member seller) throws RemoteException;

    Cart retrieveCart(Member buyer) throws RemoteException;

    boolean updateItem(int itemIdToUpdate, String newName, String newDesc, double newPrice, int newQuantity,
            Member seller) throws RemoteException;

    boolean removeItem(int itemIdToRemove, Member seller) throws RemoteException;

    boolean addUser(String newUsername, String newPassword, String accountType) throws RemoteException;

    boolean removeUser(String usernameToRemove) throws RemoteException;
}
