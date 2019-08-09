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
public class HierarchyExperiments {


  public static void main(String... args) {
    ActorSystem system = ActorSystem.create("testSystem");

    /*
     * create an actor directly under /user,we can call this top level actor,
     * even through, in practice it is only the top of the user defined hierarchy.
     * *** You typically have only one(or very few) top level actors in your ActorSystem.
     */
    ActorRef firstActor = system.actorOf(PrintMyActorRefActors.props(), "firstActor");
    System.out.println(firstActor.toString());
    firstActor.tell("printIt", ActorRef.noSender());
  }


}


class PrintMyActorRefActors extends AbstractActor {


  static Props props() {
    return Props.create(PrintMyActorRefActors.class, PrintMyActorRefActors::new);
  }


  @Override
  public Receive createReceive() {
    return this.receiveBuilder().matchEquals("printIt", e -> {
      ActorRef secondActor = getContext().actorOf(Props.empty(), "second-actor");
      System.out.println(secondActor);
    }).build();
  }


}
