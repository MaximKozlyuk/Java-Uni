package lab5.barBossHouse.io;

import lab2.barBossHouse.Order;

public interface FileSource extends Source<Order> {

    public void setPath(String path);

    public String getPath();

}
