package ca.cgutwin.cards;

public class PermanentCard extends Card {
  private final boolean isPermanent;

  private PermanentCard(PermanentCard.Builder builder) {
    super(builder);
    this.isPermanent = builder.isPermanent;
  }

  public static class Builder extends Card.Builder {
    private boolean isPermanent = true;

    public PermanentCard.Builder withPermanent(boolean isPermanent) {
      this.isPermanent = isPermanent;
      return this;
    }

    @Override
    public PermanentCard build() {
      return new PermanentCard(this);
    }
  }
}
