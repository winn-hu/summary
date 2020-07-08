package effectiveJava.singleton;

public class User {

    private User() {}

    public static User getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    enum Singleton {
        INSTANCE;

        private User user;

        private Singleton() {
            this.user = new User();
        }

        public User getInstance() {
            return this.user;
        }
    }
}
