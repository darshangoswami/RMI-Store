import java.rmi.RemoteException;

public abstract class CartOperation {
  // In some cases, authorization might be needed to access certain cart
  // operations
  protected abstract boolean authorize(Member member);

  // The method to actually perform the cart operation
  protected abstract Cart performTask(Member member) throws RemoteException;

  // Template method that defines the sequence of steps to perform a cart
  // operation
  public final Cart execute(Member member) throws RemoteException {
    if (authorize(member)) {
      return performTask(member);
    }
    return null; // Could also throw an exception if authorization fails
  }
}
