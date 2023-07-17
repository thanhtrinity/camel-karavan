/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.karavan.service;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.vertx.core.eventbus.EventBus;
import org.apache.camel.karavan.shared.EventType;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import static org.apache.camel.karavan.shared.EventType.START_KARAVAN;

@ApplicationScoped
public class KaravanService {

    private static final Logger LOGGER = Logger.getLogger(KaravanService.class.getName());

    @Inject
    EventBus bus;

    void onStart(@Observes StartupEvent ev) {
        LOGGER.info("Starting Karavan");
        bus.publish(START_KARAVAN, "");
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("Stop Karavan");
        bus.publish(EventType.STOP_INFRASTRUCTURE_LISTENERS, "");
    }

}
