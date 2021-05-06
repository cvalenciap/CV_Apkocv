package pe.com.sedapal.ofivirtual.data.entity.dto;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SessionUserDto extends RealmObject {
    @PrimaryKey
    private long id;
    private String email;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
