package jdk.clone;

public class AdminUser extends User implements Cloneable{

    private int type;

    public AdminUser() {
    }

    public AdminUser(int id, String name, int type) {
        super(id, name);
        this.type = type;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "id=" + getId() +
                "name=" + getName() +
                "type=" + type +
                '}';
    }
}
