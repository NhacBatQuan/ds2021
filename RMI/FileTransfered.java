<<<<<<< HEAD
package RMI_FileTransfer;

import java.io.Serializable;
import java.time.LocalDate;

public class FileTransfered implements Serializable {
    private String name;
    private byte[] content;
    private LocalDate date;
    public FileTransfered(String name,byte[] content,LocalDate date){
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public byte[] getContent() {
        return content;
    }

}
=======
package RMI_FileTransfer;

import java.io.Serializable;
import java.time.LocalDate;

public class FileTransfered implements Serializable {
    private String name;
    private byte[] content;
    private LocalDate date;
    public FileTransfered(String name,byte[] content,LocalDate date){
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public byte[] getContent() {
        return content;
    }

}
>>>>>>> 9a00cd17eb7300f82acda1279b45d996903d1af4
