package lab5.barBossHouse.io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class OrderManagerFileSource implements FileSource{

    protected long lastOrderId;
    protected String pathToOrdersFold = "./storage/orders/";
    private String pathToLastId = "./storage/lastOrderId.txt";

    private String path;

    @Override
    public void setPath(String path) { this.path = path; }

    @Override
    public String getPath() { return path; }

    protected void incLastOrderId () {
        try (PrintWriter pw = new PrintWriter(pathToLastId)) {
            pw.write(String.valueOf(++lastOrderId));
        } catch (FileNotFoundException exp) {
            exp.printStackTrace();
        }
    }

}
