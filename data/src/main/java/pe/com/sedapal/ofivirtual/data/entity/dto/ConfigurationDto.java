package pe.com.sedapal.ofivirtual.data.entity.dto;

import io.realm.annotations.PrimaryKey;


/**
 * Created by jsaenz on 21/02/2017.
 */
public class ConfigurationDto {

    public static final String ID = "id";
    public static final String VALUE = "value";
    public static final String NAME = "name";

    @PrimaryKey
    private int id;
    private String name;
    private String value;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
