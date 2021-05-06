package pe.com.sedapal.ofivirtual.data.entity.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ImagenesOnboardingDto extends RealmObject {

    public static final String ID = "id";
    public static final String CODIGO = "codigo";
    public static final String NAME = "name";

    @PrimaryKey
    private int id;
    private String fondoOnboarding;
    private String iconoOnboarding;
    private String descripcionOnboarding;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFondoOnboarding() {
        return fondoOnboarding;
    }

    public void setFondoOnboarding(String fondoOnboarding) {
        this.fondoOnboarding = fondoOnboarding;
    }

    public String getIconoOnboarding() {
        return iconoOnboarding;
    }

    public void setIconoOnboarding(String iconoOnboarding) {
        this.iconoOnboarding = iconoOnboarding;
    }

    public String getDescripcionOnboarding() {
        return descripcionOnboarding;
    }

    public void setDescripcionOnboarding(String descripcionOnboarding) {
        this.descripcionOnboarding = descripcionOnboarding;
    }
}
