package ca.cgutwin.cards;

import ca.cgutwin.cards.parser.json.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.StringJoiner;

@JsonDeserialize(builder = Card.Builder.class)
public class Card {
  private final CardName name;
  private final CardType type;
  private final int price;
  private final Rarity rarity;
  private final int limit;
  private final boolean extraPerks;
  private final Queues queues;
  private final Effects effects;
  private final Blocks negatives;
  private final DeckActions deckActions;

  Card(Builder builder) {
    this.name        = builder.nameBuilder.build();
    this.type        = builder.type;
    this.price       = builder.price;
    this.rarity      = builder.rarity;
    this.limit       = builder.limit;
    this.extraPerks  = builder.extraPerks;
    this.queues      = builder.queuesBuilder.build();
    this.effects     = builder.effectsBuilder.build();
    this.negatives   = builder.negativesBuilder.build();
    this.deckActions = builder.deckActionsBuilder.build();
  }

  public DeckActions getDeckActions() {
    return deckActions;
  }

  public Blocks getNegatives() {
    return negatives;
  }

  public Effects getEffects() {
    return effects;
  }

  public boolean getExtraPerks() {
    return extraPerks;
  }

  public Queues getQueues() {
    return queues;
  }

  public Rarity getRarity() {
    return rarity;
  }

  public int getPrice() {
    return price;
  }

  public CardType getType() {
    return type;
  }

  public CardName getName() {
    return name;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Card.class.getSimpleName() + "{", "}").add("name=" + name)
                                                                        .add("type:" + type)
                                                                        .add("price:" + price)
                                                                        .add("rarity:" + rarity)
                                                                        .add("limit:" + limit)
                                                                        .add("extraPerks:" + extraPerks)
                                                                        .add("queues:{" + queues + "}")
                                                                        .add("effects:{" + effects + "}")
                                                                        .add("negatives:{" + negatives + "}")
                                                                        .add("deckActions:{" + deckActions + "}")
                                                                        .toString();
  }

  public int getLimit() {
    return limit;
  }

  public void render(SpriteBatch sb, int x, int y) {
    Texture texture = new Texture("tileset/tileset.png");
    TextureRegion region = new TextureRegion(texture, 128, 96, 8, 8);
    sb.draw(region, x, y);
  }

  public static class Builder {
    @JsonProperty("name")
    CardName.Builder nameBuilder = new CardName.Builder();
    @JsonProperty("type")
    CardType type;
    @JsonProperty("price")
    int price;
    @JsonProperty("rarity")
    Rarity rarity;
    @JsonProperty("limit")
    int limit;
    @JsonProperty("extra_perks")
    boolean extraPerks;
    @JsonProperty("queues")
    Queues.Builder queuesBuilder = new Queues.Builder();
    @JsonProperty("effects")
    Effects.Builder effectsBuilder = new Effects.Builder();
    @JsonProperty("negatives")
    Blocks.Builder negativesBuilder = new Blocks.Builder();
    @JsonProperty("deck_actions")
    DeckActions.Builder deckActionsBuilder = new DeckActions.Builder();

    @JsonGetter("deck_actions")
    public Builder withDeckActions(DeckActions.Builder deckActionsBuilder) {
      this.deckActionsBuilder = deckActionsBuilder;
      return this;
    }

    public Builder withNegatives(Blocks.Builder negativesBuilder) {
      this.negativesBuilder = negativesBuilder;
      return this;
    }

    public Builder withEffects(Effects.Builder effectsBuilder) {
      this.effectsBuilder = effectsBuilder;
      return this;
    }

    public Builder withQueues(Queues.Builder queuesBuilder) {
      this.queuesBuilder = queuesBuilder;
      return this;
    }

    @JsonGetter("extra_perks")
    public Builder withExtraPerks(boolean extraPerks) {
      this.extraPerks = extraPerks;
      return this;
    }

    public Builder withLimit(int limit) {
      this.limit = limit;
      return this;
    }

    public Builder withRarity(Rarity rarity) {
      this.rarity = rarity;
      return this;
    }

    public Builder withType(CardType type) {
      this.type = type;
      return this;
    }

    public Builder withPrice(int price) {
      this.price = price;
      return this;
    }

    public Builder withName(CardName.Builder nameBuilder) {
      this.nameBuilder = nameBuilder;
      return this;
    }

    public Card build() {
      return new Card(this);
    }
  }
}