package com.doc.sample.iot;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
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
public class DeviceManager extends AbstractActor {

  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

  public static Props props() {
    return Props.create(DeviceManager.class, DeviceManager::new);
  }


  @Override
  public void preStart() {
    log.info("DeviceManager started");
  }

  public void postStop() {
    log.info("DeviceManager stopped");
  }


  public static final class RequestTrackDevice {

    public final String groupId;

    public final String deviceId;

    public RequestTrackDevice(String groupId, String deviceId) {
      this.groupId = groupId;
      this.deviceId = deviceId;
    }

  }


  public static final class DeviceRegistered {

  }

  private void onTrackDevice(RequestTrackDevice r) {
    ActorRef groupActor = groupIdToActor.get(r.groupId);
    if (groupActor != null) {
      groupActor.forward(r, getContext());
    } else {
      log.info("Create DeviceGroup actor for {}", r.groupId);
      groupActor = getContext().actorOf(DeviceGroup.props(r.groupId));
      getContext().watch(groupActor);
      groupActor.forward(r, getContext());
      groupIdToActor.put(r.groupId, groupActor);
      actorToGroupId.put(groupActor, r.groupId);

    }
  }

  private void onTerminated(Terminated r) {
    ActorRef groupActor = r.getActor();
    String groupId = actorToGroupId.get(groupActor);
    log.info("DeviceGroup actor for {} terminated", groupId);
    actorToGroupId.remove(groupActor);
    groupIdToActor.remove(groupId);
  }


  final Map<String, ActorRef> groupIdToActor = new HashMap<>();

  final Map<ActorRef, String> actorToGroupId = new HashMap<>();

  public DeviceManager() {

  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(RequestTrackDevice.class, this::onTrackDevice)
        .match(Terminated.class, this::onTerminated)
        .build();
  }

}
