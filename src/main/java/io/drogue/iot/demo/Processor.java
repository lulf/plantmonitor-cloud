package io.drogue.iot.demo;

import javax.enterprise.context.ApplicationScoped;

import io.netty.handler.logging.LogLevel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.drogue.iot.demo.data.DeviceEvent;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class Processor {
    private static final Logger LOG = LoggerFactory.getLogger(Processor.class);
    @Incoming("telemetry")
    public void process(final DeviceEvent event) {
        LOG.info("We got event", event);
    }
}
