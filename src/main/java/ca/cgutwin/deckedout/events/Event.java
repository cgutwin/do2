package ca.cgutwin.deckedout.events;

public class Event {
  private final String type;
  private final String data;

  public Event(String type, String data) {
    this.type = type;
    this.data = data;
  }

  // Getters
  public String getType() {
    return type;
  }

  public String getData() {
    return data;
  }
}
