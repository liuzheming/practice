package com.doc.sample.iot;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Description:
 * <p>
 *
 * @author simon
 * @date 2019/08/09.
 */
public class IotSupervisor extends AbstractActor {

  private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);


  public static Props props() {
    return Props.create(IotSupervisor.class, IotSupervisor::new);
  }

  @Override
  public void preStart() {
    System.out.println("IoT application start");
  }

  @Override
  public void postStop() {
    System.out.println("IoT application stop");
  }

  public Receive createReceive() {
    return receiveBuilder().matchEquals("", e -> {

    }).build();
  }


}
