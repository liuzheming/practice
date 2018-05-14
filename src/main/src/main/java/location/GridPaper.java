package location;


import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Description:
 * <p>
 * Created by lzm on 2018/1/4.
 */
public class GridPaper {

    private static GridPaper gridPaper;

    private Grid[][] grids;

    //    private static float LONGITUDE_RANGE = 55;
//    private static float LATITUDE_RANGE = 67;
    private int lngMax = 150;
    private int lngMin = 50;
    private int latMax = 60;
    private int latMin = 0;
    private int lngLength;
    private int latLength;
    private double side;

    private GridPaper(List<Location> locations) {
        this(0.1, locations);
    }

    private GridPaper(double side, List<Location> locations) {
        this.side = side;
        lngLength = (int) ((lngMax - lngMin) / side);
        latLength = (int) ((latMax - latMin) / side);
        this.grids = new Grid[lngLength][latLength];
        this.addLocations(locations);
        this.fillNullCeil();
    }

    private void fillNullCeil() {
        for (int x = 0; x < lngLength; x++) {
            for (int y = 0; y < latLength; y++) {
                if (grids[x][y] == null) grids[x][y] = new Grid(x, y);
            }
        }
    }

    public static GridPaper getGridPaper(double side, List<Location> locations) {
        if (gridPaper == null) {
            synchronized (GridPaper.class) {
                if (gridPaper == null) {
                    gridPaper = new GridPaper(side, locations);
                }
            }
        }
        return gridPaper;
    }


    private void addLocations(List<Location> list) {
        list.forEach(this::addLocation);
    }

    private void addLocation(Location item) {

        if (!isValid(item.getLongitude(), item.getLatitude())) {
            System.out.println("坐标信息经纬度异常 : " + item);
        } else {
            double x = (item.getLongitude() - lngMin) / side;
            double y = (item.getLatitude() - latMin) / side;
            try {
                Grid grid = grids[(int) x][(int) y];
                if (grid != null) {
                    grid.locations.add(item);
                } else {
                    Grid newGrid = new Grid((int) x, (int) y);
                    newGrid.locations.add(item);
                    grids[(int) x][(int) y] = newGrid;
                }
            } catch (Exception e) {
                System.out.println(item + " [x=" + x + ", y=" + y + "]");
                e.printStackTrace();
            }
        }
    }

    private boolean isValid(double longitude, double latitude) {
        if (latitude > latMax || latitude < latMin
                || longitude > lngMax || longitude < lngMin) {
            return false;
        }
        return true;
    }

    /**
     * 找到对应网格
     *
     * @param latitude  y
     * @param longitude x
     * @return grid
     */

    private Grid findGrid(double longitude, double latitude) {
        double x = (longitude - lngMin) / side;
        double y = (latitude - latMin) / side;
        return grids[(int) x][(int) y];
    }


    public Location locate(double longitude, double latitude) {
        if (!isValid(longitude, latitude)) {
            System.out.println("定位失败 : [lng=" + longitude + ", lat=" + latitude + "]");
            return null;
        }
        Grid grid = findGrid(longitude, latitude);
        Collection<Grid> area = new HashSet<>();
        area.add(grid);
        findNeighbor(area);
        return findNearest(longitude, latitude, area);
    }

    private void findNeighbor(Collection<Grid> area) {
        int count = 0;
        for (Grid item : area) {
            if (item.locations != null) {
                count += item.locations.size();
            }
        }
        if (count <= 0) {
            findNeighbor(expandRange(area));
        }
    }


    private Location findNearest(double longitude, double latitude, Collection<Grid> area) {
        List<Location> list = new LinkedList<>();
        for (Grid grid : area) {
            list.addAll(grid.locations);
        }
        list.sort((o1, o2) ->
                (int) ((o1.getLatitude() + o1.getLongitude() - latitude - longitude)
                        - (o2.getLatitude() + o2.getLongitude() - latitude - longitude)));
        return list.get(0);
    }


    private Collection<Grid> expandRange(Collection<Grid> area) {
        Collection<Grid> borderGrids = new HashSet<>();
        for (Grid grid : area) {
            // 右侧一列
            if (grid.getLng() + 1 <= lngMax && grid.getLat() + 1 <= latMax)
                borderGrids.add(grids[grid.getLng() + 1][grid.getLat() + 1]);
            if (grid.getLng() + 1 <= lngMax && grid.getLat() - 1 >= latMin)
                borderGrids.add(grids[grid.getLng() + 1][grid.getLat() - 1]);
            if (grid.getLng() + 1 <= lngMax)
                borderGrids.add(grids[grid.getLng() + 1][grid.getLat()]);
            // 中间一列
            if (grid.getLat() + 1 <= latMax)
                borderGrids.add(grids[grid.getLng()][grid.getLat() + 1]);
            if (grid.getLat() - 1 >= latMax)
                borderGrids.add(grids[grid.getLng()][grid.getLat() - 1]);
            // 左侧一列
            if (grid.getLng() - 1 >= lngMin && grid.getLat() + 1 <= latMin)
                borderGrids.add(grids[grid.getLng() - 1][grid.getLat() + 1]);
            if (grid.getLng() - 1 >= lngMin && grid.getLat() - 1 >= latMax)
                borderGrids.add(grids[grid.getLng() - 1][grid.getLat() - 1]);
            if (grid.getLng() - 1 >= lngMin)
                borderGrids.add(grids[grid.getLng() - 1][grid.getLat()]);
        }
        area.addAll(borderGrids);
        return area;
    }


}
