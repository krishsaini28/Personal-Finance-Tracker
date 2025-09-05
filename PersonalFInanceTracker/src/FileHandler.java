import java.io.*;
import java.util.List;

public class FileHandler {
    private String fileName; 
    private ExpenseManager manager; 

    
    public FileHandler(String fileName, ExpenseManager manager) {
        this.fileName = fileName;
        this.manager = manager;
    }

    // Save Expenses to CSV
    public void saveExpensesToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
    
            writer.println("amount,category,date,description");

            // Get list of expenses from ExpenseManager
            List<Expense> expenses = manager.getExpenses();

            // Write each expense as a CSV line
            for (Expense e : expenses) {
                writer.println(e.getAmount() + "," +
                        e.getCategory() + "," +
                        e.getDate() + "," +
                        e.getDescription());
            }
            System.out.println("Expenses successfully saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving expenses to file: " + e.getMessage());
        }
    }

    // Load Expenses from CSV
    public void loadExpensesFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("No file found. Starting with an empty expense list.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String header = reader.readLine();
            if (header != null && header.contains("amount,category,date,description")) {
                System.out.println("Skipping header line...");
            }

            String line;
            manager.clearExpenses(); // Clear current list before loading

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); // Split CSV line into 4 parts
                if (parts.length == 4) {
                    try {
                        double amount = Double.parseDouble(parts[0]);
                        ExpenseCategory category;

                        try {
                            category = ExpenseCategory.valueOf(parts[1].toUpperCase().trim());
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Invalid category: " + parts[1] + ". Skipping...");
                            continue; // Skip invalid categories
                        }

                        String date = parts[2];
                        String description = parts[3];

                        Expense loadedExpense = new Expense(amount, category, date, description);
                        manager.addExpense(loadedExpense); // Add to ExpenseManager
                    } catch (NumberFormatException ex) {
                        System.out.println("Skipping invalid line: " + line);
                    }
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
            System.out.println("Expenses successfully loaded from " + fileName);
        } catch (IOException e) {
            System.out.println("Error loading expenses from file: " + e.getMessage());
        }
    }
}
