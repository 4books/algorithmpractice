package template;

public class SomethingDTO {

    private SomethingDTO(){}

    private static class LazyHolder {
        public static final SomethingDTO INSTANCE = new SomethingDTO();
    }

    public static SomethingDTO getInstance() {
        return LazyHolder.INSTANCE;
    }

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SomthingDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
