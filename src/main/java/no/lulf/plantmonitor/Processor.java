package no.lulf.plantmonitor;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class Processor {
    private static final Logger LOG = LoggerFactory.getLogger(Processor.class);
    @Incoming("telemetry")
    public void process(final DeviceEvent event) {
        LOG.info("We got event {}", event);
    }
}
