// User class
public class Member implements java.io.Serializable {
  private String username;
  private String password;
  private AccountType role;

  public Member(String username, String password, AccountType role) {
      this.username = username;
      this.password = password;
      this.role = role;
  }

  public String getUsername() {
      return username;
  }

  public void setUsername(String username) {
      this.username = username;
  }

  public String getPassword() {
      return password;
  }

  public void setPassword(String password) {
      this.password = password;
  }

  public AccountType getRole() {
      return role;
  }

  public void setRole(AccountType role) {
      this.role = role;
  }

public AccountType getAccountType() {
    if (this.role == AccountType.SELLER) {
        return AccountType.SELLER;
    }
    else {
        return AccountType.BUYER;
    }
}

  // Getters and setters
}