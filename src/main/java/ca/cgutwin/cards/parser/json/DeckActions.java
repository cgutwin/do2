package ca.cgutwin.cards.parser.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.StringJoiner;

@JsonDeserialize(builder = DeckActions.Builder.class)
public class DeckActions {
  private final int recycle;
  private final int stumble;

  private DeckActions(Builder builder) {
    this.recycle = builder.recycle;
    this.stumble = builder.stumble;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DeckActions.class.getSimpleName()+"[", "]").add("recycle="+recycle)
                                                                             .add("stumble="+stumble)
                                                                             .toString();
  }

  public int getRecycle() {
    return recycle;
  }

  public int getStumble() {
    return stumble;
  }

  public static class Builder {
    private int recycle;
    private int stumble;

    @JsonProperty("recycle")
    public Builder withRecycle(int recycle) {
      this.recycle = recycle;
      return this;
    }

    @JsonProperty("stumble")
    public Builder withStumble(int stumble) {
      this.stumble = stumble;
      return this;
    }

    public DeckActions build() {
      return new DeckActions(this);
    }
  }
}
