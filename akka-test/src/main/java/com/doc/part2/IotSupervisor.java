package com.doc.part2;

import akka.actor.AbstractActor;
import akka.actor.Props;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 * <p>
 *
 * @author simon
 * @date 2019/08/09.
 */
@Slf4j
public class IotSupervisor extends AbstractActor {

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
