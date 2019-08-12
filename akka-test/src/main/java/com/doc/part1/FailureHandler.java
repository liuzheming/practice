package com.doc.part1;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Description:
 *
 * In this case, we can see that after failure the supervised actor is stopped and
 * restarted immediately.
 *
 * @author simon
 * @date 2019/08/09.
 */
public class FailureHandler {


  /**
   * In this case, we can see that after failure the supervised actor is stopped and restarted
   * immediately.
   */
  public static void main(String... args) {
    ActorSystem system = ActorSystem.create("testSystem");
    ActorRef supervisingActor = system.actorOf(SupervisingActor.props(), "supervising-actor");
    supervisingActor.tell("failChild", ActorRef.noSender());

  }

}


class SupervisingActor extends AbstractActor {

  static Props props() {
    return Props.create(SupervisingActor.class, SupervisingActor::new);
  }

  private ActorRef child = getContext().actorOf(SupervisedActor.props(), "supervised-actor");

  @Override
  public Receive createReceive() {
    return receiveBuilder().matchEquals("failChild", e -> {
      child.tell("fail", getSelf());
    }).build();
  }

}


class SupervisedActor extends AbstractActor {

  static Props props() {
    return Props.create(SupervisedActor.class, SupervisedActor::new);
  }

  public Receive createReceive() {
    return receiveBuilder().matchEquals("fail", e -> {
      System.out.println("supervised actor fails now ");
      throw new Exception("I failed!");
    }).build();
  }


  @Override
  public void preStart() {
    System.out.println("Supervised actor started ");
  }

  @Override
  public void postStop() {
    System.out.println("supervised actor stopped");
  }


}