
public class MainMethod {
    
    /**
     * Main method - Application entry point
     * 
     * @param args Command line arguments (not used)
     * @throws Exception If any error occurs during execution
     */
    public static void main(String[] args) throws Exception {

    
        ExpenseManager manager = new ExpenseManager();

        // Load existing expenses from file (if available)
        // This will populate the manager with previously saved expenses
        manager.loadExpensesFromFile("Expenses.csv");

        // Sample expenses to demonstrate functionality
        // Note: These are only added if the file is empty or for demonstration

        /*** 
         * System.out.println("Adding sample expenses for demonstration...");
        manager.addExpense(new Expense(50.00, ExpenseCategory.FOOD, "12-03-2025", "Dinner at a restaurant"));
        manager.addExpense(new Expense(15.75, ExpenseCategory.TRANSPORT, "10-03-2025", "Uber ride to office"));
        manager.addExpense(new Expense(1200.00, ExpenseCategory.RENT, "01-03-2025", "Monthly apartment rent"));
        manager.addExpense(new Expense(45.99, ExpenseCategory.ENTERTAINMENT, "05-03-2025", "Movie night at the theater"));
        manager.addExpense(new Expense(30.00, ExpenseCategory.SHOPPING, "08-03-2025", "Bought a new t-shirt"));
        manager.addExpense(new Expense(100.00, ExpenseCategory.UTILITIES, "04-03-2025", "Electricity bill payment"));
        manager.addExpense(new Expense(250.00, ExpenseCategory.HEALTHCARE, "07-03-2025", "Doctor consultation & medication"));
        manager.addExpense(new Expense(89.99, ExpenseCategory.EDUCATION, "09-03-2025", "Online course subscription"));
        manager.addExpense(new Expense(12.50, ExpenseCategory.FOOD, "11-03-2025", "Lunch at a fast-food restaurant"));
        manager.addExpense(new Expense(5.50, ExpenseCategory.TRANSPORT, "03-03-2025", "Bus fare for work commute"));
        */
        

        // Display all expenses in the system with formatting
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📊 EXPENSE SUMMARY REPORT");
        System.out.println("=".repeat(60));
        System.out.println("📋 Total Expenses: " + manager.getExpenseCount());
        System.out.println("💰 Total Amount: $" + String.format("%.2f", manager.getTotalAmount()));
        System.out.println("=".repeat(60));
        
        manager.printAllExpenses();

        // Demonstrate expense filtering by date range with visual appeal
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📅 EXPENSE ANALYSIS BY DATE RANGE");
        System.out.println("=".repeat(60));
        
        double total1 = manager.getTotalExpenses("01-03-2025", "15-03-2025");
        double total2 = manager.getTotalExpenses("05-03-2025", "12-03-2025");
        
        System.out.println("📊 Period: 01-03-2025 to 15-03-2025");
        System.out.println("💰 Total: $" + String.format("%.2f", total1));
        System.out.println("📈 Average per day: $" + String.format("%.2f", total1 / 15));
        
        System.out.println("\n📊 Period: 05-03-2025 to 12-03-2025");
        System.out.println("💰 Total: $" + String.format("%.2f", total2));
        System.out.println("📈 Average per day: $" + String.format("%.2f", total2 / 8));

        // Demonstrate expense sorting functionality
        manager.sortExpenses("date", "asc");
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📅 EXPENSES SORTED BY DATE (ASCENDING)");
        System.out.println("=".repeat(60));
        manager.printAllExpenses();

        // Save all expenses to file for persistence
        manager.saveExpensesToFile("Expenses.csv");
        System.out.println("\n" + "=".repeat(60));
        System.out.println("💾 DATA PERSISTENCE");
        System.out.println("=".repeat(60));
        System.out.println("✅ Expenses successfully saved to file: Expenses.csv");
        System.out.println("📁 File location: " + System.getProperty("user.dir") + "/Expenses.csv");

        // Launch the graphical user interface
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🚀 LAUNCHING GRAPHICAL INTERFACE");
        System.out.println("=".repeat(60));
        System.out.println("🎨 Modern GUI with enhanced visual design");
        System.out.println("📊 Interactive charts and tables");
        System.out.println("🎯 User-friendly expense management");
        System.out.println("=".repeat(60));
        
        ExpenseGUI egui = new ExpenseGUI(manager);
    }
}
