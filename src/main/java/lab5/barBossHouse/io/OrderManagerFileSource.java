package lab5.barBossHouse.io;

public abstract class OrderManagerFileSource implements FileSource{

    private String path;

    @Override
    public void setPath(String path) { this.path = path; }

    @Override
    public String getPath() { return path; }

}
