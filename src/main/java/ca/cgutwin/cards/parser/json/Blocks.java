package ca.cgutwin.cards.parser.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.StringJoiner;

@JsonDeserialize(builder = Blocks.Builder.class)
public class Blocks {
  private final int clank;
  private final int hazard;

  private Blocks(Builder builder) {
    this.clank  = builder.clank;
    this.hazard = builder.hazard;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Blocks.class.getSimpleName()+"[", "]").add("clank="+clank)
                                                                        .add("hazard="+hazard)
                                                                        .toString();
  }

  public int getClank() {
    return clank;
  }

  public int getHazard() {
    return hazard;
  }

  public static class Builder {
    private int clank;
    private int hazard;

    @JsonProperty("clank")
    public Builder withClank(int clank) {
      this.clank = clank;
      return this;
    }

    @JsonProperty("hazard")
    public Builder withHazard(int hazard) {
      this.hazard = hazard;
      return this;
    }

    public Blocks build() {
      return new Blocks(this);
    }
  }
}
