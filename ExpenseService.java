import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExpenseService {

    private List<Expense> expenseList;
    private static final String fileName = "expenseRecap.csv";

    public ExpenseService() {
        expenseList = new ArrayList<>();
    }

    public void addExpense(String description, float amout){
        if(Files.exists(Path.of(fileName))) {
            expenseList = loadCsvFile();
        }
        int id = expenseList.isEmpty() ? 1 : expenseList.size() + 1;
        Expense newExpense = new Expense(id, description, amout);
        expenseList.add(newExpense);
        writeCsvFile();
        System.out.println("Expense added successfully (ID:" + id +")");
    }

    public void updateExpense(int id, String description, float amount){
        if(Files.exists(Path.of(fileName))) {
            expenseList = loadCsvFile();
            expenseList.stream()
                    .filter(expId -> expId.getId() == id)
                    .forEach(expense -> {
                        expense.setDescription(description);
                        expense.setAmount(amount);
                    });
            writeCsvFile();
            System.out.println("update Expense: " + id + ","+ description+", " + amount);
        }
    }

    public void deleteExpenseByID(int id){
        if(Files.exists(Path.of(fileName))){
            expenseList = loadCsvFile();
            boolean deletedExepense  = expenseList.removeIf(expense -> expense.getId() == id);
            writeCsvFile();
            if (deletedExepense) {
                System.out.println("Expense deleted successfully");
            } else {
                System.out.println("Expense doesn't exist");
            }
        }
    }


    private List<Expense> loadCsvFile(){
        List<Expense> readExpenseCsv = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = br.readLine()) != null){
                String[] values = (line.split(","));
                readExpenseCsv.add(new Expense(Integer.parseInt(values[0]), values[1], values[2], Float.parseFloat(values[3])));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return readExpenseCsv;
    }

    private void writeCsvFile(){
        try{
            FileWriter fw = new FileWriter(fileName);
            for(Expense expense : expenseList){
                fw.write(expense.csvToString()+"\n");
            }
            fw.close();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

}
