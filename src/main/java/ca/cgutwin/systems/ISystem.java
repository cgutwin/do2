package ca.cgutwin.systems;

public interface ISystem {

  void init();

  void update(float dt);

  void render(float dt);

  void dispose();
}
