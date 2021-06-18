package no.lulf.plantmonitor;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.vertx.core.json.JsonObject;

@ServerEndpoint("/ws")
@ApplicationScoped
public class EventsResource {

    private final Map<String, Session> sessions = new ConcurrentHashMap<>();
    private static final int CAPACITY = 120_000;
    private final ConcurrentLinkedDeque<Object> buffer = new ConcurrentLinkedDeque<>();

    @OnOpen
    public void onOpen(Session session) {
        for (Object event : buffer) {
            session.getAsyncRemote().sendObject(event);
        }
        sessions.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        sessions.remove(session.getId());
    }

    @Incoming(Channels.TELEMETRY)
    void telemetryEvent(DeviceEvent event) {
        Object nextEvent = new JsonObject()
                .put("type", "telemetry")
                .put("payload", JsonObject.mapFrom(event)).toString();
        if (buffer.size() >= CAPACITY) {
            buffer.pop();
        }
        buffer.add(nextEvent);
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(nextEvent);
        });
    }
}