package streams.onetomulti;

public class Event {

    private String scope;
    private String name;

    public Event(String name, String scope) {
        this.name = name;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public String getScope() {
        return scope;
    }
}
