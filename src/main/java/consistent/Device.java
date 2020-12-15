package consistent;

/**
 * Create by lzm on 2020/12/15
 */
public class Device {

    public String uuid;

    public Device(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Device{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}
