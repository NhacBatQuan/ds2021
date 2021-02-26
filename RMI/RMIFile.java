package RMI;

import java.time.LocalDate;

public class RMIFile {
    private String name;
    private byte[] content;
    private LocalDate date;
    public RMIFile(String name,byte[] content,LocalDate date){
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
