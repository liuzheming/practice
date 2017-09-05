package thread.consumer;

/**
 * Description: 产品
 * <p>
 * Created by lzm on 2017/9/1.
 */
public class Product {

    int id;

    public Product(int id) {
        super();
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }
}
