package ca.cgutwin.cards.parser.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.StringJoiner;

@JsonDeserialize(builder = Drops.Builder.class)
public class Drops {
  private final int embers;
  private final int treasure;

  private Drops(Builder builder) {
    this.embers   = builder.embers;
    this.treasure = builder.treasure;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Drops.class.getSimpleName()+"{", "}").add("embers:"+embers)
                                                                       .add("treasure:"+treasure)
                                                                       .toString();
  }

  public int getEmbers() {
    return embers;
  }

  public int getTreasure() {
    return treasure;
  }

  public static class Builder {
    private int embers;
    private int treasure;

    @JsonProperty("embers")
    public Builder withEmbers(int embers) {
      this.embers = embers;
      return this;
    }

    @JsonProperty("treasure")
    public Builder withTreasure(int treasure) {
      this.treasure = treasure;
      return this;
    }

    public Drops build() {
      return new Drops(this);
    }
  }
}
