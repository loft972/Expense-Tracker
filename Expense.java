import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense {

    private int id;
    private String description;
    private float amount;
    private String date;

    public Expense(int id, String description, float amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Expense(int id,  String date, String description, float amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String csvToString(){
        return id  +","+ date + ","+  description + "," +  amount;
    }
}
