package ca.cgutwin.cards.parser.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.StringJoiner;

@JsonDeserialize(builder = Queues.Builder.class)
public class Queues {
  private final Blocks blocks;
  private final Drops drops;

  private Queues(Builder builder) {
    this.blocks = builder.blocksBuilder.build();
    this.drops  = builder.dropsBuilder.build();
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Queues.class.getSimpleName()+"[", "]").add("blocks="+blocks)
                                                                        .add("drops="+drops)
                                                                        .toString();
  }

  public Blocks getBlocks() {
    return blocks;
  }

  public Drops getDrops() {
    return drops;
  }

  public static class Builder {
    private Blocks.Builder blocksBuilder = new Blocks.Builder();
    private Drops.Builder dropsBuilder = new Drops.Builder();

    @JsonProperty("blocks")
    public Builder withBlocks(Blocks.Builder blocksBuilder) {
      this.blocksBuilder = blocksBuilder;
      return this;
    }

    @JsonProperty("drops")
    public Builder withDrops(Drops.Builder dropsBuilder) {
      this.dropsBuilder = dropsBuilder;
      return this;
    }

    public Queues build() {
      return new Queues(this);
    }
  }
}
