package ca.cgutwin.cards.parser.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.StringJoiner;

@JsonDeserialize(builder = Effects.Builder.class)
public class Effects {
  private final boolean speed;
  private final boolean jump;
  private final boolean regen;

  private Effects(Builder builder) {
    this.speed = builder.speed;
    this.jump  = builder.jump;
    this.regen = builder.regen;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Effects.class.getSimpleName()+"{", "}").add("speed:"+speed)
                                                                         .add("jump:"+jump)
                                                                         .add("regen:"+regen)
                                                                         .toString();
  }

  public boolean getSpeed() {
    return speed;
  }

  public boolean getRegen() {
    return regen;
  }

  public boolean getJump() {
    return jump;
  }

  public static class Builder {
    private boolean speed;
    private boolean jump;
    private boolean regen;

    @JsonProperty("speed")
    public Builder withSpeed(boolean speed) {
      this.speed = speed;
      return this;
    }

    @JsonProperty("jump")
    public Builder withJump(boolean jump) {
      this.jump = jump;
      return this;
    }

    @JsonProperty("regen")
    public Builder withRegen(boolean regen) {
      this.regen = regen;
      return this;
    }

    public Effects build() {
      return new Effects(this);
    }
  }
}
