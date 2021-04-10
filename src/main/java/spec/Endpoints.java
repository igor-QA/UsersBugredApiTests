package spec;

public enum Endpoints {

    /**EndPoint*/
    COMPANY ("/createcompany"),
    USER ("/createuser"),
    REGISTER ("/doregister");

    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

    public String path() {
            return path;
    }
}