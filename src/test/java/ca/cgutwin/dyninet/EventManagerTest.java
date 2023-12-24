/*
 * Copyright (c) 2023 by Chris Gutwin. All rights reserved.
 *
 * No part of this software may be reproduced, distributed, or transmitted in any form or by any
 * means including photocopying, recording, or other electronic or mechanical methods, without
 * the prior written permission of the author.
 *
 * For permission requests, write to the author at [chris@cgutwin.ca].
 */

package ca.cgutwin.dyninet;

import ca.cgutwin.dyninet.events.Event;
import ca.cgutwin.dyninet.events.EventManager;
import ca.cgutwin.dyninet.events.EventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

import static ca.cgutwin.dyninet.events.EventType.ENVIRONMENT_CHANGE;
import static ca.cgutwin.dyninet.events.EventType.INTERACTION;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventManagerTest {
  private EventManager eventManager;
  @Mock
  private EventPublisher publisher;
  @Mock
  private Consumer<Event> eventConsumer;

  @BeforeEach
  void setUp() {
    // Initialize the EventManager with the mocked publisher
    eventManager = new EventManager(publisher);
  }

  @Test
  void testEventProcessingOrder() {
    // Create events with different priorities
    Event highPriorityEvent = new Event(INTERACTION, null, null, null, 1);
    Event lowPriorityEvent = new Event(ENVIRONMENT_CHANGE, null, null, null, 5);

    // Queue events
    eventManager.queueEvent(lowPriorityEvent);
    eventManager.queueEvent(highPriorityEvent);

    // Process events
    eventManager.processEvents();

    // Verify that events are processed in the correct order
    // Here we use an InOrder object from Mockito to verify the order of interactions
    InOrder inOrder = inOrder(publisher);
    inOrder.verify(publisher).publish(highPriorityEvent);
    inOrder.verify(publisher).publish(lowPriorityEvent);
  }

  @Test
  void testEventPublishing() {
    EventPublisher realPublisher = new EventPublisher();
    realPublisher.subscribe(INTERACTION, eventConsumer);

    EventManager eventManager = new EventManager(realPublisher);

    Event event = new Event(INTERACTION, null, null, null, 1);
    eventManager.queueEvent(event);

    eventManager.processEvents();

    verify(eventConsumer).accept(event);
  }
}
