public class AdminFactory implements MemberFactory {
  @Override
  public Member createMember(String username, String password) {
    return new Admin(username, password);
  }
}
