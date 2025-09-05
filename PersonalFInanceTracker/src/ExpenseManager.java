import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

   
    private List<Expense> expenses;

   
    public ExpenseManager() {
        this.expenses = new ArrayList<>();
    }

    // ========== EXPENSE MANAGEMENT METHODS ==========
    
    /**
     * Adds a new expense to the manager with individual parameters
     * 
     * @param amount The expense amount 
     * @param category The expense category 
     * @param date The expense date in dd-MM-yyyy format
     * @param description The expense description
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public void addExpense(double amount, ExpenseCategory category, String date, String description) {
        try {
            Expense newExpense = new Expense(amount, category, date, description);
            expenses.add(newExpense);
            System.out.println("✅ New expense added: $" + String.format("%.2f", amount) + 
                             " for " + category + " on " + date);
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Error adding expense: " + e.getMessage());
            throw e; // Re-throw to let caller handle
        }
    }

    /**
     * Adds an existing expense object to the manager
     * 
     * @param expense The expense object to add (cannot be null)
     * @throws IllegalArgumentException if expense is null
     */
    public void addExpense(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense cannot be null");
        }
        expenses.add(expense);
    }

    /**
     * Removes an expense that matches all the specified criteria
     * 
     * @param amount The amount to match
     * @param category The category to match
     * @param date The date to match
     * @param description The description to match
     * @return true if an expense was removed, false if no matching expense found
     */
    public boolean removeExpense(double amount, ExpenseCategory category, String date, String description) {
        for (int i = 0; i < expenses.size(); i++) {
            Expense e = expenses.get(i);
            if (Double.compare(e.getAmount(), amount) == 0 &&
                e.getCategory().equals(category) &&
                e.getDate().equals(date) &&
                e.getDescription().equals(description)) {
                expenses.remove(i);
                System.out.println("✅ Removed expense: " + e);
                return true;
            }
        }
        System.out.println("❌ Expense not found.");
        return false;
    }
    

    // ========== EXPENSE ANALYSIS METHODS ==========
    
    /**
     * Calculates the total expenses within a specified date range
     * 
     * @param startDate The start date in dd-MM-yyyy format
     * @param endDate The end date in dd-MM-yyyy format
     * @return The total amount of expenses in the date range
     * @throws DateTimeParseException if date format is invalid
     */
    public double getTotalExpenses(String startDate, String endDate) {
        double total = 0;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        try {
            LocalDate start = LocalDate.parse(startDate, format);
            LocalDate end = LocalDate.parse(endDate, format);

            if (expenses == null || expenses.isEmpty()) {
                System.out.println("ℹ️ No expenses available.");
                return total;
            }

            for (Expense e : expenses) {
                LocalDate expenseDate = LocalDate.parse(e.getDate(), format);
                if ((expenseDate.isAfter(start) || expenseDate.isEqual(start)) && 
                    (expenseDate.isBefore(end) || expenseDate.isEqual(end))) {
                    total += e.getAmount();
                }
            }
        } catch (DateTimeParseException e) {
            System.err.println("❌ Invalid date format. Please use dd-MM-yyyy format.");
            throw e;
        }
        
        return total;
    }

    /**
     * Filters expenses by a specific category
     * 
     * @param category The category to filter by
     * @return A list of expenses that match the specified category
     */
    public List<Expense> filterByCategory(ExpenseCategory category) {
        List<Expense> filteredExpenses = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getCategory().equals(category)) {
                filteredExpenses.add(e);
            }
        }
        return filteredExpenses;
    }

    // ========== SORTING METHODS ==========
    
    /**
     * Sorts expenses by the specified criteria and order
     * 
     * @param criteria The sorting criteria ("amount" or "date")
     * @param order The sorting order ("asc" for ascending, "des" for descending)
     * @throws IllegalArgumentException if criteria or order is invalid
     */
    public void sortExpenses(String criteria, String order) {
        if (criteria == null || order == null) {
            throw new IllegalArgumentException("Criteria and order cannot be null");
        }
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (criteria.equalsIgnoreCase("amount")) {
            if (order.equalsIgnoreCase("asc")) {
                expenses.sort((e1, e2) -> Double.compare(e1.getAmount(), e2.getAmount()));
            } else if (order.equalsIgnoreCase("des")) {
                expenses.sort((e1, e2) -> Double.compare(e2.getAmount(), e1.getAmount()));
            } else {
                throw new IllegalArgumentException("Invalid order. Use 'asc' or 'des'");
            }
        } else if (criteria.equalsIgnoreCase("date")) {
            if (order.equalsIgnoreCase("asc")) {
                expenses.sort((e1, e2) -> {
                    try {
                        LocalDate d1 = LocalDate.parse(e1.getDate(), format);
                        LocalDate d2 = LocalDate.parse(e2.getDate(), format);
                        return d1.compareTo(d2);
                    } catch (DateTimeParseException e) {
                        System.err.println("❌ Error parsing date: " + e.getMessage());
                        return 0;
                    }
                });
            } else if (order.equalsIgnoreCase("des")) {
                expenses.sort((e1, e2) -> {
                    try {
                        LocalDate d1 = LocalDate.parse(e1.getDate(), format);
                        LocalDate d2 = LocalDate.parse(e2.getDate(), format);
                        return d2.compareTo(d1);
                    } catch (DateTimeParseException e) {
                        System.err.println("❌ Error parsing date: " + e.getMessage());
                        return 0;
                    }
                });
            } else {
                throw new IllegalArgumentException("Invalid order. Use 'asc' or 'des'");
            }
        } else {
            throw new IllegalArgumentException("Invalid criteria. Use 'amount' or 'date'");
        }
    }

    // ========== FILE I/O METHODS ==========
    
    /**
     * Saves all expenses to a CSV file
     * 
     * @param fileName The name of the file to save to
     * @throws IOException if there's an error writing to the file
     */
    public void saveExpensesToFile(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            // Write CSV header
            writer.println("amount,category,date,description");
            
            // Write each expense as a CSV line
            for (Expense e : expenses) {
                writer.println(e.getAmount() + "," + e.getCategory() + "," + 
                             e.getDate() + "," + e.getDescription());
            }
            System.out.println("✅ Expenses successfully saved to " + fileName);
        } catch (IOException e) {
            System.err.println("❌ Error saving expenses to file: " + e.getMessage());
            throw new RuntimeException("Failed to save expenses", e);
        }
    }

    /**
     * Loads expenses from a CSV file
     * 
     * @param fileName The name of the file to load from
     * @throws IOException if there's an error reading the file
     */
    public void loadExpensesFromFile(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("ℹ️ File " + fileName + " does not exist. Starting with empty expense list.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            expenses.clear(); // Clear existing expenses before loading
            
            // Skip header line if it exists
            String firstLine = reader.readLine();
            if (firstLine != null && firstLine.contains("amount,category,date,description")) {
                System.out.println("ℹ️ Skipping CSV header...");
            } else if (firstLine != null) {
                // Process the first line if it's not a header
                processExpenseLine(firstLine);
            }
            
            // Process remaining lines
            while ((line = reader.readLine()) != null) {
                processExpenseLine(line);
            }
            
            System.out.println("✅ Expenses successfully loaded from " + fileName);
        } catch (IOException e) {
            System.err.println("❌ Error loading expenses from file: " + e.getMessage());
            throw new RuntimeException("Failed to load expenses", e);
        }
    }
    
    /**
     * Helper method to process a single line from the CSV file
     * 
     * @param line The CSV line to process
     */
    private void processExpenseLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return; // Skip empty lines
        }
        
        String[] parts = line.split(",", -1);
        if (parts.length == 4) {
            try {
                double amount = Double.parseDouble(parts[0].trim());
                ExpenseCategory category = ExpenseCategory.valueOf(parts[1].toUpperCase().trim());
                String date = parts[2].trim();
                String description = parts[3].trim();
                
                Expense loaded = new Expense(amount, category, date, description);
                expenses.add(loaded);
            } catch (NumberFormatException ex) {
                System.err.println("⚠️ Invalid amount format: " + parts[0] + ". Skipping line...");
            } catch (IllegalArgumentException ex) {
                System.err.println("⚠️ Invalid category: " + parts[1] + ". Skipping line...");
            }
        } else {
            System.err.println("⚠️ Invalid line format (expected 4 fields): " + line);
        }
    }

    // ========== UTILITY METHODS ==========
    
    /**
     * Clears all expenses from the manager
     */
    public void clearExpenses() {
        expenses.clear();
        System.out.println("✅ All expenses cleared.");
    }

    /**
     * Gets a copy of all expenses for external access
     * Returns a defensive copy to prevent external modification
     * 
     * @return A new list containing all expenses
     */
    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    /**
     * Prints all expenses in a formatted manner
     */
    public void printAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("ℹ️ No expenses to display.");
            return;
        }
        
        System.out.println("📋 Total expenses: " + expenses.size());
        System.out.println("=" + "=".repeat(50));
        
        for (int i = 0; i < expenses.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + expenses.get(i));
        }
    }
    
    /**
     * Gets the total number of expenses
     * 
     * @return The number of expenses in the manager
     */
    public int getExpenseCount() {
        return expenses.size();
    }
    
    /**
     * Gets the total amount of all expenses
     * 
     * @return The sum of all expense amounts
     */
    public double getTotalAmount() {
        return expenses.stream()
                      .mapToDouble(Expense::getAmount)
                      .sum();
    }

}
