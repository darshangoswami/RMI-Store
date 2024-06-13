import java.rmi.RemoteException;

public abstract class ItemOperation {
  protected abstract boolean authorize(Member member);

  protected abstract boolean performTask(Item item, Member member) throws RemoteException;

  // Template method
  public final boolean execute(Item item, Member member) throws RemoteException {
    if (authorize(member)) {
      return performTask(item, member);
    }
    return false;
  }
}