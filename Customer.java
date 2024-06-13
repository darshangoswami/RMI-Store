// Customer class
public class Customer extends Member {
  public Customer(String username, String password) {
    super(username, password, AccountType.BUYER);
  }

}
