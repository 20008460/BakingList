package sg.edu.rp.c346.id20008460.bakinglist;

import java.io.Serializable;

public class Users implements Serializable {

    private int id;
    private String username;
    private String password;
    private String role;


    public Users(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users " +
                username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
