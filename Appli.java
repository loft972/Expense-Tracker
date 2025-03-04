import java.io.FileNotFoundException;

public class Appli {

    public static void main(String[] args) throws Exception {

        ExpenseService expenseService = new ExpenseService();

        switch (args[0]) {
            case "add" -> expenseService.addExpense(args[1], Float.parseFloat(args[2]));
            case "update" -> expenseService.updateExpense(Integer.parseInt(args[1]), args[2], Float.parseFloat(args[3]));
            case "delete" ->expenseService.deleteExpenseByID(Integer.parseInt(args[1]));
            case "list" -> expenseService.showExpense();
            case "summary" -> {
                if (args.length > 1) {

                    if(args[1].length() == 1){
                        expenseService.summaryExpenseByMonth("0"+args[1]);
                    } else{
                        expenseService.summaryExpenseByMonth(args[1]);
                    }

                } else {
                    expenseService.summaryExpense();
                }
            }
            default -> System.out.println("""
                    Error : You have select an action not possible.
                    """);
        }
    }
}
