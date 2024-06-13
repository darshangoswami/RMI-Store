import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class DigitalMarketServer {
  public static void main(String[] args) {
    try {
      // Initialize and register the marketplace service
      Marketplace marketService = new Marketplace();
      DigitalMarketInterface marketStub = (DigitalMarketInterface) UnicastRemoteObject.exportObject(marketService, 0);
      Registry registry = LocateRegistry.createRegistry(4269);
      registry.rebind("MarketService", marketStub);

      System.out.println("Digital Market server is now active and awaiting connections...");
    } catch (RemoteException e) {
      System.err.println("Server exception: " + e.toString());
      e.printStackTrace();
    }
  }
}
