import java.util.List;

public class RemoveUserOperation extends UserOperation {
  private List<Member> members;

  public RemoveUserOperation(List<Member> members) {
    this.members = members;
  }

  @Override
  protected boolean authorize() {
    return true;
  }

  @Override
  protected boolean performTask(String username, String password, String accountType) {
    return members.removeIf(member -> member.getUsername().equals(username));
  }
}
