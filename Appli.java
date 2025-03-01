public class Appli {

    public static void main(String[] args) {

        ExpenseService expenseService = new ExpenseService();

        switch (args[0]) {
            case "add" -> expenseService.addExpense(args[2], Float.parseFloat(args[4]));
            case "update" -> System.out.println(args[2] +", " + args[4]);
            case "delete" -> System.out.println(args[2] +", " + args[4]);
            case "list" -> System.out.println(args[2] +", " + args[4]);
            case "summary" -> {
                if (args.length > 1) {
                    System.out.println(args[2] +", " + args[4]);
                } else {
                    System.out.println(args[2] +", " + args[4]);
                }
            }
            default -> System.out.println("""
                    Error : You have select an action not possible.
                    """);
        }
    }
}
