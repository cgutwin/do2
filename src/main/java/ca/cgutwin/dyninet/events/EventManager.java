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

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Manages the queueing and processing of events in the game. The EventManager uses a priority queue to handle
 * events in an order based on their priority, ensuring that more critical events are processed first.
 * <p>
 * It also integrates with an EventPublisher to notify subscribers after an event is processed, allowing
 * different parts of the game to react to these events.
 */
public class EventManager {
  private final PriorityBlockingQueue<Event> eventQueue;
  private final EventPublisher publisher;

  /**
   * Constructs an EventManager with an associated EventPublisher.
   *
   * @param publisher The EventPublisher used for notifying subscribers post event processing.
   */
  public EventManager(EventPublisher publisher) {
    // The initial capacity of 11 can be adjusted based on expected load.
    this.eventQueue = new PriorityBlockingQueue<>(11, Comparator.comparingInt(Event::priority));
    this.publisher  = publisher;
  }

  /**
   * Queues an event to be processed.
   *
   * @param event The event to be queued.
   */
  public void queueEvent(Event event) {
    eventQueue.add(event);
  }

  /**
   * Processes the events in the queue. Each event is handled based on its priority, and after processing,
   * it is published to the subscribers via the EventPublisher.
   */
  public void processEvents() {
    while (!eventQueue.isEmpty()) {
      Event event = eventQueue.poll();
      handleEvent(event);
    }
  }

  private void handleEvent(Event event) {
    // Process the event based on its type and priority
    // Notify subscribers after processing
    publisher.publish(event);
  }
}