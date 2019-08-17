package com.doc.sample.iot;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * <p>
 *
 * @author simon
 * @date 2019/08/12.
 */
public class DeviceGroup extends AbstractActor {


  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);


  final String groupId;

  public DeviceGroup(String groupId) {
    this.groupId = groupId;
  }

  public static Props props(String groupId) {
    return Props.create(DeviceGroup.class, () -> new DeviceGroup(groupId));
  }


  final Map<String, ActorRef> deviceIdToActor = new HashMap<>();
  final Map<ActorRef, String> actorToDeviceId = new HashMap<>();


  @Override
  public void preStart() {
    log.info("device group started");
  }

  @Override
  public void postStop() {
    log.info("device group stopped");
  }


  public static final class RequestAllTemperatures {

    final long requestId;

    public RequestAllTemperatures(long requestId) {
      this.requestId = requestId;
    }

  }


  public static final class RespondAllTemperatures {

    public final long requestId;

    public final Map<String, TemperatureReading> temperatures;

    public RespondAllTemperatures(long requestId, Map<String, TemperatureReading> temperatures) {
      this.requestId = requestId;
      this.temperatures = temperatures;
    }

  }


  public interface TemperatureReading {

  }

  public static final class Temperature implements TemperatureReading {

    public final double value;

    public Temperature(double value) {
      this.value = value;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Temperature that = (Temperature) o;
      return Double.compare(that.value, value) == 0;
    }

    public String toString() {
      return "Temperature{" + "value=" + value + '}';
    }
  }


  public enum TemperatureNotAvailable implements TemperatureReading {
    INSTANCE
  }

  public enum DeviceNotAvailable implements TemperatureReading {
    INSTANCE
  }

  public enum DeviceTimedOut implements TemperatureReading {
    INSTANCE
  }

  public static final class RequestDeviceList {

    final long requestId;

    public RequestDeviceList(long requestId) {
      this.requestId = requestId;
    }
  }

  public static final class ReplyDeviceList {

    public final long requestId;

    public final Set<String> ids;

    public ReplyDeviceList(long requestId, Set<String> ids) {
      this.requestId = requestId;
      this.ids = ids;
    }

  }

  private void onTrackDevice(DeviceManager.RequestTrackDevice trackMsg) {

    if (this.groupId.equals(trackMsg.groupId)) {
      ActorRef deviceActor = this.deviceIdToActor.get(trackMsg.deviceId);
      if (deviceActor != null) {
        deviceActor.forward(trackMsg, getContext());
      } else {
        log.info("create device actor for {}", trackMsg.deviceId);
        deviceActor = getContext().actorOf(Device.props(trackMsg.groupId, trackMsg.deviceId),
            "track-" + trackMsg.deviceId);
        deviceIdToActor.put(trackMsg.deviceId, deviceActor);
        actorToDeviceId.put(deviceActor, trackMsg.deviceId);
        deviceActor.forward(trackMsg, getContext());
        getContext().watch(deviceActor);
      }
    } else {
      log.warning("Ignoring trackDevice request for {}. this actor is responsible for {}",
          groupId, trackMsg.deviceId);
    }
  }


  private void onTerminated(Terminated t) {
    ActorRef deviceActor = t.getActor();
    String deviceId = actorToDeviceId.get(deviceActor);
    log.info("device actor for {} has been terminated", deviceId);
    actorToDeviceId.remove(deviceActor);
    deviceIdToActor.remove(deviceId);
  }

  private void onDeviceList(RequestDeviceList req) {
    getSender().tell(new ReplyDeviceList(req.requestId, deviceIdToActor.keySet()), getSelf());
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(DeviceManager.RequestTrackDevice.class, this::onTrackDevice)
        .match(Terminated.class, this::onTerminated)
        .match(RequestDeviceList.class, this::onDeviceList)
        .build();
  }
}
