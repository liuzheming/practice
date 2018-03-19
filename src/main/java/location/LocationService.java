package location;


import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 * <p>
 * Created by lzm on 2018/1/4.
 */
public class LocationService {

    @Resource
    private LocationMapper locationMapper;

    private static GridPaper gridPaper;

    private LocationService() {

    }

    /**
     * 读数据库,初始化网格
     */
    private void initPaper() {
        List<Location> locations = locationMapper.getAll();
        gridPaper = GridPaper.getGridPaper(0.1, locations);
    }

    /**
     * 获取坐标位置的对应信息,数据库初始化语句见area_info.sql
     *
     * @param lng 经度
     * @param lat 维度
     * @return 返回位置信息的描述
     */
    public static Location locate(double lng, double lat) {
        return gridPaper.locate(lng, lat);
    }


    public static void main(String[] args) {

//        String[] files = new String[]{"applicationContext.xml"};
//
//        ApplicationContext app = new ClassPathXmlApplicationContext(files);
//
//        LocationService service = (LocationService) app.getBean("locationService");
//        service.locationMapper = (LocationMapper) app.getBean("locationMapper");
//        List<Location> list = service.locationMapper.getAll();
        LocationService service = new LocationService();
        service.initPaper();

        long start = System.currentTimeMillis();
        System.out.println("位置查询: " + service.gridPaper.locate(116.377, 40.085)); // 霍营
//        System.out.println("位置查询: " + service.gridPaper.locate(104.06, 30.67)); // 成都
//        System.out.println("位置查询: " + service.gridPaper.locate(138.96, 40.49)); // 侯马
//        System.out.println(service.gridPaper.locate(111.35828019999997, 35.6370189)); // 侯马
        System.out.println("耗时: " + (System.currentTimeMillis() - start));

    }
}
