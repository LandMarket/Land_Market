package land_market_proto.model;

import java.io.Serializable;

/**
 * Created by Nik_NB on 02.06.2017.
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 112234550L;
    private String address;
    private String note;

    public Address() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
