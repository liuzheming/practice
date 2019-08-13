package sample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.TestKit;
import com.doc.sample.iot.Device;
import com.doc.sample.iot.Device.RecordTemperature;
import com.doc.sample.iot.DeviceGroup;
import com.doc.sample.iot.DeviceManager;
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


  @Test
  public void testReplyRegistrationRequests() {

    TestKit probe = new TestKit(system);
    ActorRef device = system.actorOf(Device.props("UFO", "U1"));
    device.tell(new DeviceManager.RequestTrackDevice("UFO", "U1"), probe.testActor());
    probe.expectMsgClass(DeviceManager.DeviceRegistered.class);
    Assert.assertEquals(device, probe.lastSender());

  }


  @Test
  public void testWrongRegistrationRequests() {

    TestKit probe = new TestKit(system);
    ActorRef deviceActor = system.actorOf(Device.props("UFO", "U1"));
    deviceActor.tell(new DeviceManager.RequestTrackDevice("UFO", "U2"), probe.testActor());
    probe.expectNoMessage();
    deviceActor.tell(new DeviceManager.RequestTrackDevice("UFO2", "U1"), probe.testActor());
    probe.expectNoMessage();

  }


  @Test
  public void testRegisterDeviceActor() {

    TestKit probe = new TestKit(system);
    ActorRef groupActor = system.actorOf(DeviceGroup.props("GROUP"), "GROUP-UFO");
    groupActor.tell(new DeviceManager.RequestTrackDevice("GROUP", "U1"), probe.testActor());
    probe.expectMsgClass(DeviceManager.DeviceRegistered.class);
    ActorRef device1 = probe.lastSender();

    groupActor.tell(new DeviceManager.RequestTrackDevice("GROUP", "U2"), probe.testActor());
    probe.expectMsgClass(DeviceManager.DeviceRegistered.class);
    ActorRef device2 = probe.lastSender();

    Assert.assertNotEquals(device1, device2);
  }


  @Test
  public void testIgnoringRequest4WrongGroupId() {

    TestKit probe = new TestKit(system);
    ActorRef groupActor = system.actorOf(DeviceGroup.props("GROUP"), "GROUP-UFO");
    groupActor.tell(new DeviceManager.RequestTrackDevice("GROUP1", "DEVICE"), probe.testActor());
    probe.expectNoMessage();

  }


  @Test
  public void testReturnSameActorForSameDeviceId() {

    TestKit probe = new TestKit(system);
    ActorRef groupActor = system.actorOf(DeviceGroup.props("GROUP"), "GROUP-UFO");
    groupActor.tell(new DeviceManager.RequestTrackDevice("GROUP", "U1"), probe.testActor());
    probe.expectMsgClass(DeviceManager.DeviceRegistered.class);
    ActorRef device1 = probe.lastSender();
    groupActor.tell(new DeviceManager.RequestTrackDevice("GROUP", "U1"), probe.testActor());
    probe.expectMsgClass(DeviceManager.DeviceRegistered.class);
    ActorRef device2 = probe.lastSender();
    Assert.assertEquals(device1, device2);
  }

}
