// Admin class
public class Admin extends Member {
  public Admin(String username, String password) {
    // Assuming AccountType.ADMIN represents the admin role in the refactored enum
    super(username, password, AccountType.SELLER);
  }

}
