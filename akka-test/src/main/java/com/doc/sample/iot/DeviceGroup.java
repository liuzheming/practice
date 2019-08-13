package com.doc.sample.iot;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.HashMap;
import java.util.Map;

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


  @Override
  public void preStart() {
    log.info("device group started");
  }

  @Override
  public void postStop() {
    log.info("device group stopped");
  }


  public void onTrackDevice(DeviceManager.RequestTrackDevice trackMsg) {

    if (this.groupId.equals(trackMsg.groupId)) {
      ActorRef deviceRef = this.deviceIdToActor.get(trackMsg.deviceId);
      if (deviceRef != null) {
        deviceRef.forward(trackMsg, getContext());
      } else {
        log.info("create device actor for {}", trackMsg.deviceId);
        deviceRef = getContext().actorOf(Device.props(trackMsg.groupId, trackMsg.deviceId),
            "track-" + trackMsg.deviceId);
        deviceIdToActor.put(trackMsg.deviceId, deviceRef);
        deviceRef.forward(trackMsg, getContext());

      }
    } else {
      log.warning("Ignoring trackDevice request for {}. this actor is responsible for {}",
          groupId, trackMsg.deviceId);
    }

  }


  @Override
  public Receive createReceive() {
    return receiveBuilder().match(DeviceManager.RequestTrackDevice.class, this::onTrackDevice).
        build();
  }
}
