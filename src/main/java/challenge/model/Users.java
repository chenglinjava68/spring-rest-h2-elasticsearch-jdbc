package challenge.model;

import java.util.Set;


public class Users {

    private int id;
    private String handle;
    private String password;
    private String name;
    private Set<Role> roles;

    public Users() {
    }

    public Users(Users users) {
        this.roles = users.getRoles();
        this.name = users.getName();
        this.handle =users.getHandle();
        this.id = users.getId();
        this.password = users.getPassword();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandle() {
        return handle;
    }

    public void setLastName(String handle) {
        this.handle = handle;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
