import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExpenseService {

    private List<Expense> expenseList;
    private static final String fileName = "expenseRecap.csv";

    public ExpenseService() {
        expenseList = new ArrayList<>();
    }

    public void addExpense(String description, float amout) throws FileNotFoundException {
        if(isFileExists()) {
            expenseList = loadCsvFile();
        }

        if(amout < 0 ){
            throw new ArithmeticException("An expense can not be negative !");
        }

        int id = expenseList.isEmpty() ? 1 : expenseList.size() + 1;
        Expense newExpense = new Expense(id, description, amout);
        expenseList.add(newExpense);
        writeCsvFile(expenseList);
        System.out.println("Expense added successfully (ID:" + id +")");
    }

    public void updateExpense(int id, String description, float amount) throws Exception {
        if(isFileExists()) {
            expenseList = loadCsvFile();
            List<Expense> updateList = expenseList.stream()
                    .filter(expId -> expId.getId() == id)
                    .toList();

            if(updateList.isEmpty()){
                throw new Exception("non existent expense ID");
            }

            updateList.forEach(expense -> {
                expense.setDescription(description);
                expense.setAmount(amount);
            });
            writeCsvFile(updateList);
            System.out.println("update Expense: " + id + ","+ description+", " + amount);
        }
    }

    public void deleteExpenseByID(int id) throws Exception {
        if(isFileExists()){
            expenseList = loadCsvFile();
            boolean deletedExepense  = expenseList.removeIf(expense -> expense.getId() == id);
            writeCsvFile(expenseList);
            if (deletedExepense) {
                System.out.println("Expense deleted successfully");
            } else {
                throw new Exception("non existent expense ID");
            }
        }
    }

    public void showExpense() throws FileNotFoundException {
        if(isFileExists()){
            expenseList = loadCsvFile();
            StringBuilder table = new StringBuilder();

            table.append(String.format("%-5s %-10s %-10s %-5s %n", "ID", "Date", "Description", "Amount"));

            for (Expense row : expenseList) {
                table.append(String.format("%-5s %-10s %-11s %-5s %n", row.getId(), row.getDate(), row.getDescription(), row.getAmount()));
            }
            System.out.println(table);
        }
    }

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public void summaryExpense() throws FileNotFoundException {
        if(isFileExists()){
            expenseList = loadCsvFile();
            Double sum = expenseList.stream()
                    .map(Expense::getAmount).mapToDouble(Float::floatValue).sum();
            System.out.println("Total Expense : "+  df.format(sum));
        }
    }

    public void summaryExpenseByMonth(String month) throws FileNotFoundException {
        if(isFileExists()){
            expenseList = loadCsvFile();

            List<Expense>monthExpense = expenseList.stream()
                    .filter(expense -> expense.getDate().substring(5,7).equals(month))
                    .toList();

            if(monthExpense.isEmpty()){
                System.out.println("There not expense for " + getMonthName(month));
            } else {
                Double sum = monthExpense.stream()
                        .map(Expense::getAmount).mapToDouble(Float::floatValue).sum();
                System.out.println("Total Expense for " + getMonthName(month) +" : "+  df.format(sum));
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

    private void writeCsvFile(List<Expense> expenseList){
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

    private String getMonthName(String monthNumber){
        String monthName = "";
        switch(monthNumber){
            case "01" -> monthName = "January";
            case "02" -> monthName = "February";
            case "03" -> monthName = "March";
            case "04" -> monthName = "April";
            case "05" -> monthName = "May";
            case "06" -> monthName = "June";
            case "07" -> monthName = "July";
            case "08" -> monthName = "August";
            case "09" -> monthName = "September";
            case "10" -> monthName = "October";
            case "11" -> monthName = "November";
            default -> monthName = "December";
        }
        return monthName;
    }

    private boolean isFileExists() throws FileNotFoundException {
        if(Files.exists(Path.of(fileName))){
            return true;
        } else {
            throw new FileNotFoundException("There are not Expense save, First add an expense");
        }
    }

}
