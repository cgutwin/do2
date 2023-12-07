package ca.cgutwin.maps;

public interface Level {
  void load();

  void update(float delta);

  void render();

  void dispose();

  void show();
}
