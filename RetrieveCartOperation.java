import java.util.Map;

public class RetrieveCartOperation extends CartOperation {
  private Map<Member, Cart> memberCarts;

  public RetrieveCartOperation(Map<Member, Cart> memberCarts) {
    this.memberCarts = memberCarts;
  }

  @Override
  protected boolean authorize(Member member) {
    return true;
  }

  @Override
  protected Cart performTask(Member member) {
    return memberCarts.get(member);
  }
}
