package location;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * <p>
 * Created by lzm on 2018/1/4.
 */
public class Grid {

    public List<Location> locations;

    private int lng;

    private int lat;

    public Grid(int lng, int lat) {
        this.lng = lng;
        this.lat = lat;
        locations = new ArrayList<>();
    }

    public int getLng() {
        return lng;
    }


    public int getLat() {
        return lat;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grid grid = (Grid) o;

        if (lng != grid.lng) return false;
        return lat == grid.lat;

    }

    @Override
    public int hashCode() {
        int result = lng;
        result = 31 * result + lat;
        return result;
    }
}
