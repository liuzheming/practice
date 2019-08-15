package com.doc.sample.iot;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.Map;
import scala.concurrent.duration.FiniteDuration;

/**
 * Description:
 *
 * Created by lzm on 2019/8/15.
 */
public class DeviceGroupQuery extends AbstractActor {


  public static final class CollectionTimeout {

  }


  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);


  final Map<ActorRef, String> actorToDeviceId;

  final long requestId;

  final ActorRef requester;

  Cancellable queryTimeoutTimer;


  public DeviceGroupQuery(Map<ActorRef, String> actorToDeviceId, long requestId, ActorRef requester,
      FiniteDuration timeout) {
    this.actorToDeviceId = actorToDeviceId;
    this.requestId = requestId;
    this.requester = requester;
    this.queryTimeoutTimer
        = getContext().getSystem().scheduler()
        .scheduleOnce(timeout, getSelf(),
            new CollectionTimeout(),
            getContext().getDispatcher(),
            getSelf());

  }

  public static Props props(Map<ActorRef, String> actorToDeviceId, long requestId,
      ActorRef requester, FiniteDuration timeout) {
    return Props.create(DeviceGroupQuery.class, () -> new DeviceGroupQuery(
        actorToDeviceId, requestId, requester, timeout));
  }


  @Override
  public Receive createReceive() {
    return null;
  }
}
