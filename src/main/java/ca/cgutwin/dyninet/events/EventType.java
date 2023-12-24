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

/**
 * Enumerates the types of events that can occur in the game. This includes interactions between nodes,
 * changes in the environment, and other custom-defined events.
 * Additional event types can be added as needed to cater to specific game mechanics.
 */
public enum EventType {
  INTERACTION, ENVIRONMENT_CHANGE, CUSTOM
  // Add more event types as needed
}
