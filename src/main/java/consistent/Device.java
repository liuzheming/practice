package consistent;

/**
 * Create by lzm on 2020/12/15
 */
public class Device {

    public String uuid;

    public int hash;

    public String name;

    public Device(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Device{" +
                "uuid='" + uuid + '\'' +
                ", hash=" + hash +
                ", name='" + name + '\'' +
                '}';
    }
}
