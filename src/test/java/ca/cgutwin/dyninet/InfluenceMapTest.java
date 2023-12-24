package ca.cgutwin.dyninet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class InfluenceMapTest {
  private InfluenceMap map;
  private Node sourceNode;
  private Node targetNode;
  private Influence influence;

  @BeforeEach
  void setUp() {
    map        = new InfluenceMap();
    sourceNode = new Node("source");
    targetNode = new Node("target");
    influence  = new Influence(); // Assume Influence is a concrete class
  }

  @Test
  void testAddInfluence() {
    map.addInfluence(sourceNode, targetNode, influence);
    assertFalse(map.getInfluences(sourceNode).isEmpty());
  }
}