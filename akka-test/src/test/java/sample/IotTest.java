package sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.TestKit;
import com.doc.part3.Device;
import com.doc.part3.Device.RecordTemperature;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

/**
 * Description:
 *
 * Created by lzm on 2019/8/9.
 */
public class IotTest {


  private ActorSystem system = ActorSystem.create("test-system");


  @Test
  public void testReplyWithEmptyReadingIfNoTemperatureIsKnow() {
    TestKit probe = new TestKit(system);
    ActorRef deviceActor = system.actorOf(Device.props("Group", "Device"));

    deviceActor.tell(new RecordTemperature(10.64, 12306L), probe.testActor());
    Device.TemperatureRecorded response = probe.expectMsgClass(Device.TemperatureRecorded.class);
    Assert.assertEquals(12306L, response.requestId);

    deviceActor.tell(new Device.ReadTemperature(12307L), probe.testActor());
    Device.RespondTemperature temperature = probe.expectMsgClass(Device.RespondTemperature.class);
//    Device.RespondTemperature response = probe.expectMsgClass(Device.RespondTemperature.class);
    Assert.assertEquals(Optional.of(10.64), temperature.value);
    Assert.assertEquals(12307L, temperature.requestId);

  }

}
