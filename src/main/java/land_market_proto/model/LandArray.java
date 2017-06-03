package land_market_proto.model;

import java.util.ArrayList;

/**
 * Created by Виктор on 03.06.2017.
 */
public class LandArray {
    private ArrayList<Land> lands = new ArrayList<>();

    public LandArray() {

    }

    public LandArray(ArrayList<Land> lands) {

        this.lands = lands;
    }

    public ArrayList<Land> getLands() {
        return lands;
    }

    public void setLands(ArrayList<Land> lands) {
        this.lands = lands;
    }
}
