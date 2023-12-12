package ca.cgutwin.cards.parser.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.StringJoiner;

@JsonDeserialize(builder = CardName.Builder.class)
public class CardName {
  private final String shortName;
  private final String longName;

  private CardName(Builder builder) {
    this.shortName = builder.shortName;
    this.longName  = builder.longName;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", CardName.class.getSimpleName()+"[", "]").add("shortName='"+shortName+"'")
                                                                          .add("longName='"+longName+"'")
                                                                          .toString();
  }

  public String getShortName() {
    return shortName;
  }

  public String getLongName() {
    return longName;
  }

  public static class Builder {
    private String shortName;
    private String longName;

    @JsonProperty("shortName")
    public Builder withShortName(String shortName) {
      this.shortName = shortName;
      return this;
    }

    @JsonProperty("longName")
    public Builder withLongName(String longName) {
      this.longName = longName;
      return this;
    }

    public CardName build() {
      return new CardName(this);
    }
  }
}
