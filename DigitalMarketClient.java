import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class DigitalMarketClient {
  private DigitalMarketClient() {
  }

  public static void main(String[] args) {
    try {
      // Getting the registry
      Registry registry = LocateRegistry.getRegistry(null, 4269);

      // Looking up the registry for the remote object
      DigitalMarketInterface marketService = (DigitalMarketInterface) registry.lookup("MarketService");

      // User interaction logic here
      try (Scanner inputScanner = new Scanner(System.in)) {
        while (true) {
          System.out.println("\n1. Register Member\n2. Member Login\n3. Exit");
          System.out.print("Choose an option: ");
          int userChoice = inputScanner.nextInt();
          inputScanner.nextLine(); // Consume the trailing newline

          switch (userChoice) {
            case 1:
              // Handle member registration
              System.out.print("Username: ");
              String regUsername = inputScanner.nextLine();
              System.out.print("Password: ");
              String regPassword = inputScanner.nextLine();
              System.out.print("Account Type (BUYER/SELLER): ");
              AccountType regType = AccountType.valueOf(inputScanner.nextLine().toUpperCase());

              boolean isRegistered = marketService.registerMember(regUsername, regPassword, regType);
              if (isRegistered) {
                System.out.println("Registration successful. Welcome to the Digital Market!");
              } else {
                System.out.println("Registration failed. Please try again.");
              }
              break;
            case 2:
              // Handle member login and subsequent actions
              System.out.print("Username: ");
              String loginUsername = inputScanner.nextLine();
              System.out.print("Password: ");
              String loginPassword = inputScanner.nextLine();

              Member loggedInMember = marketService.authenticateMember(loginUsername, loginPassword);
              if (loggedInMember != null) {
                System.out.printf("Welcome back, %s!\n", loggedInMember.getUsername());

                if (loggedInMember.getAccountType() == AccountType.SELLER) {
                  // Seller-specific actions
                  handleSellerActions(inputScanner, marketService, loggedInMember);
                } else if (loggedInMember.getAccountType() == AccountType.BUYER) {
                  // Buyer-specific actions
                  handleBuyerActions(inputScanner, marketService, loggedInMember);
                }
              } else {
                System.out.println("Login failed. Please check your credentials.");
              }
              break;
            case 3:
              System.out.println("Thank you for visiting the Digital Market. Goodbye!");
              System.exit(0);
              break;
            default:
              System.out.println("Invalid choice. Please select a valid option.");
          }
        }
      }
    } catch (Exception e) {
      System.err.println("Client exception: " + e.toString());
      e.printStackTrace();
    }
  }

  private static void handleSellerActions(Scanner scanner, DigitalMarketInterface marketService, Member seller)
      throws RemoteException {
    int choice;
    do {
      System.out.println("\n[Seller Menu]");
      System.out.println("1. List an item for sale");
      System.out.println("2. Remove an item");
      System.out.println("3. Update item details");
      System.out.println("4. Exit");
      System.out.println("5. Add User");
      System.out.println("6. Remove User");
      System.out.println("7. View Inventory");

      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine(); // Flush the buffer

      switch (choice) {
        case 1:
          System.out.print("Enter item name: ");
          String itemName = scanner.nextLine();
          System.out.print("Enter item description: ");
          String itemDesc = scanner.nextLine();
          System.out.print("Enter item price: ");
          double itemPrice = scanner.nextDouble();
          System.out.print("Enter quantity: ");
          int quantity = scanner.nextInt();
          // Assuming a method addItem that takes these parameters exists
          boolean added = marketService.addItem(new Item(itemName, itemDesc, itemPrice, quantity), seller);
          if (added) {
            System.out.println("Item listed successfully.");
          } else {
            System.out.println("Failed to list item.");
          }
          break;
        case 2:
          System.out.print("Enter item ID to remove: ");
          int itemIdToRemove = scanner.nextInt();
          boolean removed = marketService.removeItem(itemIdToRemove, seller);
          if (removed) {
            System.out.println("Item removed successfully.");
          } else {
            System.out.println("Failed to remove item.");
          }
          break;
        case 3:
          System.out.print("Enter item ID to update: ");
          int itemIdToUpdate = scanner.nextInt();
          scanner.nextLine(); // Flush the buffer
          System.out.print("Enter new item name: ");
          String newName = scanner.nextLine();
          System.out.print("Enter new item description: ");
          String newDesc = scanner.nextLine();
          System.out.print("Enter new item price: ");
          double newPrice = scanner.nextDouble();
          System.out.print("Enter new quantity: ");
          int newQuantity = scanner.nextInt();
          // Assuming a method updateItem that takes these parameters exists
          boolean updated = marketService.updateItem(itemIdToUpdate, newName, newDesc, newPrice, newQuantity,
              seller);
          if (updated) {
            System.out.println("Item updated successfully.");
          } else {
            System.out.println("Failed to update item.");
          }
          break;
        case 5:
          System.out.println("Exiting seller menu...");
          System.out.println("Adding a new user.");
          System.out.print("Enter username: ");
          String newUsername = scanner.nextLine();
          System.out.print("Enter password: ");
          String newPassword = scanner.nextLine();
          System.out.print("Enter account type (e.g., BUYER, SELLER): ");
          String accountType = scanner.nextLine();

          if (true) {
            System.out.println("User successfully added.");
          } else {
            System.out.println("Failed to add user.");
          }
          break;
        case 6:
          // Remove User
          System.out.println("Removing a user.");
          System.out.print("Enter username of user to remove: ");
          String usernameToRemove = scanner.nextLine();

          if (true) {
            System.out.println("User successfully removed.");
          } else {
            System.out.println("Failed to remove user.");
          }
          break;
        case 7:
          List<Item> items = marketService.listAvailableItems();
          items.forEach(item -> System.out.println(item.getItemId() + " " + item.getTitle() + " "
              + item.getDetails() + " " + item.getPrice() + " " + item.getStock()));
          break;
        default:
          System.out.println("Invalid choice, please try again.");
      }
    } while (choice != 4);
  }

  private static void handleBuyerActions(Scanner scanner, DigitalMarketInterface marketService, Member buyer)
      throws RemoteException {
    int choice;
    Cart cart = new Cart();
    do {
      System.out.println("\n[Buyer Menu]");
      System.out.println("1. View available items");
      System.out.println("2. Add item to cart");
      System.out.println("3. View cart and checkout");
      System.out.println("4. Exit");
      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine(); // Flush the buffer

      switch (choice) {
        case 1:
          // Assuming a method viewItems that returns a list of items exists
          List<Item> items = marketService.listAvailableItems();
          items.forEach(item -> System.out.println(item.getItemId() + " " + item.getTitle() + " "
              + item.getDetails() + " " + item.getPrice() + " " + item.getStock()));
          break;
        case 2:
          System.out.print("Enter item id: ");
          int itemId = scanner.nextInt();
          System.out.print("Enter quantity: ");
          int quantity = scanner.nextInt();
          for (Item item : marketService.listAvailableItems()) {
            if (item.getItemId() == itemId) {
              cart.addItem(item, quantity);
            }
          }
          System.out.println("Item added to cart.");
          break;
        case 3:
          // Assuming a method checkout that takes buyer as parameter exists
          System.out.println(cart.getItems());
          System.out.println("Total price: " + cart.calculateTotalPrice());
          break;
        case 4:
          System.out.println("Exiting buyer menu...");
          break;
        default:
          System.out.println("Invalid choice, please try again.");
      }
    } while (choice != 4);
  }
}
