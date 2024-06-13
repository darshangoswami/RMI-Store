public class CustomerFactory implements MemberFactory {
  @Override
  public Member createMember(String username, String password) {
    return new Customer(username, password);
  }
}
