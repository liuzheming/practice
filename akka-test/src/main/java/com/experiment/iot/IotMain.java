package com.experiment.iot;

import java.io.IOException;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import java.util.Optional;

public class IotMain {

  public static void main(String[] args) throws IOException {
    ActorSystem system = ActorSystem.create("iot-system");

    try {
      // Create top level supervisor
      ActorRef supervisor = system.actorOf(IotSupervisor.props(), "iot-supervisor");

      System.out.println("Press ENTER to exit the system");
      System.in.read();
    } finally {
      system.terminate();
    }
  }


  public static final class ReadTemperature {

  }

  public static final class RespondTemperature {

    final Optional<Double> value;

    public RespondTemperature(Optional<Double> value) {
      this.value = value;
    }
  }
}