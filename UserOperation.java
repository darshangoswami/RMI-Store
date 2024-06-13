import java.rmi.RemoteException;

public abstract class UserOperation {
  protected abstract boolean authorize();

  protected abstract boolean performTask(String username, String password, String accountType) throws RemoteException;

  // Template method
  public final boolean execute(String username, String password, String accountType) throws RemoteException {
    if (authorize()) {
      return performTask(username, password, accountType);
    }
    return false;
  }
}
