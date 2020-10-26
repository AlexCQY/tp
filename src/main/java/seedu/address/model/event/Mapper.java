package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jfxtras.icalendarfx.components.VEvent;

/**
 * Mapper class that maps Events to VEvents and vice versa.
 */
public class Mapper {

    /**
     * Maps a local Event object to a VEvent for Jfxtras calendar
     * @param event
     * @return
     */
    public static VEvent mapEventToVEvent(Event event) {
        requireNonNull(event);
        VEvent vEvent = new VEvent();
        vEvent.withSummary(event.getEventName())
                .withDateTimeStart(event.getEventStartDateTime())
                .withDateTimeEnd(event.getEventEndDateTime())
                .withDescription(event.getDescription())
                .withRecurrenceRule(event.getRecurrence().getVEventRecurRule())
                .withUniqueIdentifier(event.getUniqueIdentifier());
        return vEvent;
    }

    public static List<VEvent> mapListOfEventsToVEvent(List<Event> events) {
        List<VEvent> vEvents = new ArrayList<>();
        for (Event event: events) {
            vEvents.add(mapEventToVEvent(event));
        }
        return vEvents;
    }

    /**
     * Maps a VEvent from jfxtras calendar to local Event object
     * @param vEvent
     * @return
     */
    public static Event mapVEventToEvent(VEvent vEvent) {
        requireNonNull(vEvent);
        String eventName = vEvent.getSummary().getValue();
        LocalDateTime eventStartDateTime = LocalDateTime.from(vEvent.getDateTimeStart().getValue());
        LocalDateTime eventEndDateTime = LocalDateTime.from(vEvent.getDateTimeEnd().getValue());
        String description = vEvent.getDescription().getValue();
        String uniqueIdentifier = vEvent.getUniqueIdentifier().getValue();

        EventRecurrence recurrence;
        if (vEvent.getRecurrenceRule().getValue() == null) {
            recurrence = EventRecurrence.NONE;
        } else {
            recurrence = EventRecurrence.checkWhichRecurRule(vEvent.getRecurrenceRule()
                    .getValue().toString());
        }
        return new Event(eventName, eventStartDateTime, eventEndDateTime, description,
                uniqueIdentifier, recurrence);
    }

    public static List<Event> mapListOfVEventsToEvent(List<VEvent> vEvents) {
        List<Event> events = new ArrayList<>();
        for (VEvent vEvent: vEvents) {
            events.add(mapVEventToEvent(vEvent));
        }

        return events;
    }



}