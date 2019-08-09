package com.doc.part1;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Description:
 * <p>
 *
 * @author simon
 * @date 2019/08/09.
 */
public class Lifecycle {

  public static void main(String... args) throws Exception {
    ActorSystem system = ActorSystem.create("testSystem");
    ActorRef firstActor = system.actorOf(StartStopActor1.props(), "first");
    firstActor.tell("stop", ActorRef.noSender());
    System.out.println("please enter to exit");
    System.in.read();
    system.terminate();
  }
}


class StartStopActor1 extends AbstractActor {

  static Props props() {
    return Props.create(StartStopActor1.class, StartStopActor1::new);
  }


  @Override
  public void preStart() {
    System.out.println("first start");
    getContext().actorOf(StartStopActor2.props(), "secondActor");
  }

  @Override
  public void postStop() {
    System.out.println("first stop");
  }


  @Override
  public Receive createReceive() {
    return receiveBuilder().matchEquals("stop", e -> {
      /*
       *
       */
      getContext().stop(this.getSelf());
    }).build();
  }


}

class StartStopActor2 extends AbstractActor {

  static Props props() {
    return Props.create(StartStopActor2.class, StartStopActor2::new);
  }

  @Override
  public void preStart() {
    System.out.println("second started");
  }

  @Override
  public void postStop() {
    System.out.println("second stopped");
  }

  @Override
  public Receive createReceive() {
    // actor will not be initialed when this method return null
    return receiveBuilder().build();
  }


}