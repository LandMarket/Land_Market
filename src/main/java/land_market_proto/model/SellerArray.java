package land_market_proto.model;

import java.util.ArrayList;

/**
 * Created by Nik_NB on 02.06.2017.
 */
public class SellerArray {
    private ArrayList<Object> objects = new ArrayList<>();

    public SellerArray() {
    }

    public SellerArray(ArrayList<Object> objects) {
        this.objects = objects;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Object> objects) {
        this.objects = objects;
    }
}
