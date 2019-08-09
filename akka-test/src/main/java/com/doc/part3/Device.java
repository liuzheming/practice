package com.doc.part3;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.Optional;

/**
 * Description:
 *
 * Created by lzm on 2019/8/9.
 */
public class Device extends AbstractActor {

  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

  final String groupId;

  final String deviceId;

  public Device(String groupId, String deviceId) {
    this.groupId = groupId;
    this.deviceId = deviceId;
  }


  public static Props props(String groupId, String deviceId) {
    return Props.create(Device.class, () -> new Device(groupId, deviceId));
  }

  public static final class ReadTemperature {

    final Long requestId;

    public ReadTemperature(Long requestId) {
      this.requestId = requestId;
    }

  }

  public static final class RespondTemperature {

    public final Optional<Double> value;

    public final long requestId;

    public RespondTemperature(Long requestId, Optional<Double> value) {
      this.requestId = requestId;
      this.value = value;
    }
  }

  public static final class RecordTemperature {

    final Double value;

    final Long requestId;

    public RecordTemperature(Double value, Long requestId) {
      this.value = value;
      this.requestId = requestId;
    }
  }

  public static final class TemperatureRecorded {

    public final long requestId;

    public TemperatureRecorded(Long requestId) {
      this.requestId = requestId;
    }
  }


  Optional<Double> lastTemperatureReading = Optional.empty();


  @Override
  public void preStart() {
    log.info("Device actor {}-{} started", groupId, deviceId);
  }

  @Override
  public void postStop() {
    log.info("Device actor {}-{} stopped", groupId, deviceId);
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(ReadTemperature.class, e -> {
          getSender()
              .tell(new RespondTemperature(e.requestId, lastTemperatureReading), getSelf());
        })
        .match(RecordTemperature.class, e -> {
          log.info("Recorded temperature reading {} with {} ", e.value, e.requestId);
          this.lastTemperatureReading = Optional.of(e.value);
          getSender().tell(new TemperatureRecorded(e.requestId), getSelf());
        })
        .build();
  }


}
