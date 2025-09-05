public class Expense {
    
    // Instance variables to store expense information
    private double amount;           // The monetary amount of the expense
    private ExpenseCategory category; // The category this expense belongs to
    private String date;            // Date when the expense occurred (dd-MM-yyyy format)
    private String description;     // Detailed description of the expense

    /**
     * Constructor to create a new expense entry
     * 
     * @param amount The monetary amount 
     * @param category The expense category 
     * @param date The date in dd-MM-yyyy format 
     * @param description The expense description
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Expense(double amount, ExpenseCategory category, String date, String description) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        
        // Set the validated values
        this.amount = amount;
        this.category = category;
        this.date = date.trim();
        this.description = description.trim();
    }
    
    /**
     * Sets the amount for this expense
     * @param amount The new amount (must be positive)
     * @throws IllegalArgumentException if amount is not positive
     */
    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.amount = amount;
    }
    
    /**
     * Gets the amount of this expense
     * @return The expense amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the category for this expense
     * @param category The new category (cannot be null)
     * @throws IllegalArgumentException if category is null
     */
    public void setCategory(ExpenseCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        this.category = category;
    }

    /**
     * Gets the category of this expense
     * @return The expense category
     */
    public ExpenseCategory getCategory() {
        return category;
    }

    /**
     * Sets the date for this expense
     * @param date The new date in dd-MM-yyyy format (cannot be null or empty)
     * @throws IllegalArgumentException if date is null or empty
     */
    public void setDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be null or empty");
        }
        this.date = date.trim();
    }

    /**
     * Gets the date of this expense
     * @return The expense date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the description for this expense
     * @param description The new description (cannot be null or empty)
     * @throws IllegalArgumentException if description is null or empty
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description.trim();
    }

    /**
     * Gets the description of this expense
     * @return The expense description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns a formatted string representation of this expense
     * @return A formatted string with all expense details
     */
    @Override
    public String toString() {
        return String.format("AMOUNT: $%.2f%nCATEGORY: %s%nDATE: %s%nDESCRIPTION: %s%n", 
            amount, category, date, description);
    }

    /**
     * Compares this expense with another object for equality
     * Two expenses are considered equal if all their fields match exactly
     * 
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Check if it's the same object reference
        if (this == obj) {
            return true;
        }
        
        // Check if the object is null or of different class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Cast to Expense and compare all fields
        Expense other = (Expense) obj;
        return Double.compare(this.amount, other.amount) == 0 &&
               this.category.equals(other.category) &&
               this.date.equals(other.date) &&
               this.description.equals(other.description);
    }
    
    /**
     * Generates a hash code for this expense object
     * @return A hash code value for this expense
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(amount);
        result = 31 * result + category.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}



