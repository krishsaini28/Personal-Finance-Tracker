
public enum ExpenseCategory {
    FOOD,                    
    TRANSPORT,              
    RENT,                  
    UTILITIES,              
    HEALTHCARE,             
    ENTERTAINMENT,          
    SHOPPING,              
    EDUCATION,              
    TRAVEL,                 
    SAVINGS,                
    DEBT,                   
    GIFTS,               
    SUBSCRIPTIONS,          
    CHARITY,               
    HOME_MAINTENANCE,       
    CAR_EXPENSES,           
    OTHER;                 
    
    /**
     * Gets an array of all category names as strings
     * This is useful for populating dropdown menus in the GUI
     * 
     * @return An array of strings containing all category names
     */
    public static String[] getCategoryList() {
        ExpenseCategory[] categories = ExpenseCategory.values();
        String[] categoryNames = new String[categories.length];
        for (int i = 0; i < categories.length; i++) {
            categoryNames[i] = categories[i].name();
        }
        return categoryNames;
    }
    
    /**
     * Gets a user-friendly description of the category
     * 
     * @return A formatted string with category name and description
     */
    @Override
    public String toString() {
        return this.name(); // Return just the category name for CSV compatibility
    }
    
    /**
     * Gets a brief description of what this category includes
     * 
     * @return A string describing the category
     */
    public String getDescription() {
        switch (this) {
            case FOOD: return "Groceries, Restaurants, Coffee, Snacks";
            case TRANSPORT: return "Gas, Bus, Train, Uber, Taxi, Parking";
            case RENT: return "Monthly rent, Mortgage payments";
            case UTILITIES: return "Electricity, Water, Internet, Phone bills";
            case HEALTHCARE: return "Doctor visits, Medicines, Insurance";
            case ENTERTAINMENT: return "Movies, Netflix, Music, Games, Events";
            case SHOPPING: return "Clothes, Electronics, Accessories";
            case EDUCATION: return "Tuition, Books, Online Courses";
            case TRAVEL: return "Flights, Hotels, Tourist Attractions";
            case SAVINGS: return "Investments, Emergency Fund, Retirement";
            case DEBT: return "Loan Payments, Credit Card Bills";
            case GIFTS: return "Birthdays, Weddings, Holiday Presents";
            case SUBSCRIPTIONS: return "Streaming Services, Gym Membership, Software";
            case CHARITY: return "Donations, Fundraisers, Community Support";
            case HOME_MAINTENANCE: return "Repairs, Furniture, Home Improvements";
            case CAR_EXPENSES: return "Insurance, Maintenance, Fuel, Registration";
            case OTHER: return "Miscellaneous expenses";
            default: return "Unknown category";
        }
    }
}
