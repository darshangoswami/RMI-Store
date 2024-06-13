import java.util.List;

public class AddUserOperation extends UserOperation {
  private List<Member> members;

  public AddUserOperation(List<Member> members) {
    this.members = members;
  }

  @Override
  protected boolean authorize() {
    return true;
  }

  @Override
  protected boolean performTask(String username, String password, String accountType) {
    MemberFactory factory = accountType.equals("BUYER") ? new CustomerFactory() : new AdminFactory();
    Member newUser = factory.createMember(username, password);
    if (!members.contains(newUser)) {
      members.add(newUser);
      return true;
    }
    return false; // User already exists
  }
}
