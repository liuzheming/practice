package com.doc.part2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * Description:
 * <p>
 *
 * @author simon
 * @date 2019/08/09.
 */
public class IotMain {


  public static void main(String... args) throws Exception {

    ActorSystem system = ActorSystem.create("iot-system");
    try {
      ActorRef supervisor = system.actorOf(IotSupervisor.props(), "iot-supervisor");
      System.out.println("Press ENTER to exit the system");
      System.in.read();
    } finally {
      system.terminate();
    }

  }


}
