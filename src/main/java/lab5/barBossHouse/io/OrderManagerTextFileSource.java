package lab5.barBossHouse.io;

import lab5.barBossHouse.InternetOrder;
import lab5.barBossHouse.Order;
import lab5.barBossHouse.TableOrder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Scanner;

public class OrderManagerTextFileSource extends OrderManagerFileSource {

    /*
    для записи и чтения данных из файла использует символьные потоки.
    Для записи рекомендуется использовать PrintWriter, а для чтения (и разбора)
    рекомендуется использовать либо StreamTokenizer, либо java.util.Scanner.
     */

    public OrderManagerTextFileSource () {
        File f = new File("./storage/lastOrderId.txt");
        try (Scanner s = new Scanner(f)) {
            super.lastOrderId = s.nextLong();
        } catch (IOException exp) {
            exp.printStackTrace();
        } catch (Exception exp) {
            System.out.println("Some shit occurred");
            exp.printStackTrace();
        }
    }

    @Override
    public void load(Order order) {

    }

    @Override
    public void store(Order order) {

    }

    @Override
    public void delete(Order order) {
        Integer orderHash = order.getCreationTime().hashCode();

//        Collection files = FileUtils.listFiles(
//                new File(super.pathToOrdersFold), new RegexFileFilter("^(.*?)"), DirectoryFileFilter.INSTANCE
//        );
//        System.out.println(files);

        try {
            Path dir = FileSystems.getDefault().getPath(super.pathToOrdersFold);
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
            for (Path path : stream) {
                try {
                    if (Integer.parseInt(path.getFileName().toString().split("_")[1]) == orderHash) {
                        File f = new File(super.pathToOrdersFold + path.getFileName());
                        if (f.delete()) {
                            return;// ибо по идее копий быть не должно и смысла дальше итерироваться нет
                        }
                    }
                } catch (NumberFormatException exp) { }
            }
        } catch (IOException exp) {
            exp.printStackTrace();
        }

    }

    @Override
    public void create(Order order) {
        StringBuilder fileName = new StringBuilder("./storage/orders/T");
        if (order instanceof TableOrder) {
            fileName.append("T");
        } else {
            fileName.append("I");
        }
        fileName.append(super.lastOrderId);
        super.incLastOrderId();
        fileName.append("_");
        fileName.append(order.getCreationTime().hashCode());
        try (PrintWriter pw = new PrintWriter(fileName.toString())) {
            if (order instanceof TableOrder) {
                pw.println("TableOrder");
            } else {
                pw.println("InternetOrder");
            }
            pw.println(order.getCreationTime());
            pw.println(order.getCustomer());
            pw.println(order.getCustomer().getAddress());
            pw.println(order.size());
            for (Object i : order) {
                pw.println(i);
            }
            pw.println(order.getOrderPrice());
        } catch (FileNotFoundException exp) {
            exp.printStackTrace();
        }
    }

}


















