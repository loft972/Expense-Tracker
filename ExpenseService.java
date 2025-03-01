import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    private List<Expense> expenseList;

    public ExpenseService() {
        expenseList = new ArrayList<>();
    }

    public void addExpense(String description, float amout){
        int id = expenseList.isEmpty() ? 1 : expenseList.size();
        Expense newExpense = new Expense(id +1 , description, amout);
        expenseList.add(newExpense);
        System.out.println("Expense added successfully (ID: "+ newExpense.getId() +")");
        System.out.println(expenseList);
    }


}
