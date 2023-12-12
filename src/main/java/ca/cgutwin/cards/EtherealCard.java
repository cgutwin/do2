package ca.cgutwin.cards;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.StringJoiner;

public class EtherealCard extends Card {
  private final boolean isEthereal;

  private EtherealCard(Builder builder) {
    super(builder);
    this.isEthereal = builder.isEthereal;
  }

  @JsonGetter("ethereal")
  public boolean isEthereal() {
    return isEthereal;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", EtherealCard.class.getSimpleName(), "").add(super.toString())
                                                                         .add("isEthereal="+isEthereal)
                                                                         .toString();
  }

  public static class Builder extends Card.Builder {
    private boolean isEthereal = true;

    public Builder withEthereal(boolean isEthereal) {
      this.isEthereal = isEthereal;
      return this;
    }

    @Override
    public EtherealCard build() {
      return new EtherealCard(this);
    }
  }
}
