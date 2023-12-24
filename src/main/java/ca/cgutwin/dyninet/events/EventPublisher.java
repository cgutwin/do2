/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.dyninet.events;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventPublisher {
  private final ConcurrentHashMap<EventType, List<Consumer<Event>>> listeners;

  public EventPublisher() {
    this.listeners = new ConcurrentHashMap<>();
  }

  /**
   * Subscribes a listener to a specific event type.
   * This method allows different components of the game to listen for specific events
   * and react accordingly when those events occur.
   *
   * @param eventType The type of event to subscribe to.
   * @param listener  The listener that will handle the event.
   */
  public void subscribe(EventType eventType, Consumer<Event> listener) {
    listeners.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>()).add(listener);
  }

  /**
   * Publishes an event to all subscribed listeners of that event type.
   * Each listener is notified and executes its own logic based on the event.
   *
   * @param event The event to be published.
   */
  public void publish(Event event) {
    List<Consumer<Event>> eventListeners = listeners.getOrDefault(event.type(), new CopyOnWriteArrayList<>());
    for (Consumer<Event> listener: eventListeners) {
      listener.accept(event);
    }
  }
}
