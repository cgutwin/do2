package ca.cgutwin.game.map;

public interface Level {
  void load();

  void update(float delta);

  void render();

  void dispose();

  void show();
}
