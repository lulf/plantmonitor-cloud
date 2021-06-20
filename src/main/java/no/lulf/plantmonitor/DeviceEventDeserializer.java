package no.lulf.plantmonitor;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class DeviceEventDeserializer extends ObjectMapperDeserializer<DeviceEvent> {
    public DeviceEventDeserializer() {
        super(DeviceEvent.class);
    }
}
