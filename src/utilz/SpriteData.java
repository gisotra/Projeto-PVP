package utilz;

public class SpriteData {
    private String name;
    private String path;
    //nao coloquei width nem height pq eu quero manualmente mexer nas classes

    // Construtor
    public SpriteData(String name, String path) {
        this.name = name;
        this.path = path;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
