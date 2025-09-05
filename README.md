# 💰 Personal Finance Tracker

A comprehensive Java-based expense management application with a modern graphical user interface. Track, categorize, and visualize your personal expenses with ease.

## 🎯 Project Purpose

This application is designed to help individuals take control of their personal finances by providing an intuitive way to:
- Track daily expenses across multiple categories
- Visualize spending patterns through interactive charts
- Maintain organized financial records with data persistence
- Analyze spending habits over time

## ✨ Main Features

### 📊 Expense Management
- **Add Expenses**: Record new expenses with amount, category, date, and description
- **Remove Expenses**: Delete individual expenses or clear entire dataset
- **Categorize Expenses**: Organize expenses into predefined categories (Food, Rent, Transport, etc.)
- **Data Validation**: Input validation ensures data integrity

### 🎨 Modern User Interface
- **Intuitive GUI**: Clean, modern interface built with Java Swing
- **Scrollable Content**: Navigate through large datasets with smooth scrolling
- **Responsive Design**: Adapts to different screen sizes and content
- **Visual Feedback**: Clear success/error messages for all user actions

### 📈 Data Visualization
- **Interactive Pie Charts**: Visual representation of expense distribution by category
- **Real-time Updates**: Charts automatically update when data changes
- **Category Breakdown**: See spending patterns across different expense categories

### 💾 Data Persistence
- **CSV File Storage**: Automatic saving and loading of expense data
- **Data Backup**: Export and import functionality for data portability
- **Error Handling**: Robust file I/O with comprehensive error management

### 🔄 Data Management
- **Sorting Options**: Sort expenses by amount (ascending/descending) or date (oldest/newest)
- **Date Range Filtering**: Filter expenses by specific date ranges
- **Clear Functions**: Reset form fields or clear entire dataset with confirmation
- **Refresh Data**: Update display with latest data changes

### 🛡️ Safety Features
- **Confirmation Dialogs**: Prevent accidental data loss with confirmation prompts
- **Input Validation**: Comprehensive validation for all user inputs
- **Error Recovery**: Graceful handling of file I/O and data processing errors

## 🛠️ Technical Stack

- **Java Swing**: Modern GUI framework for desktop application
- **CSV File I/O**: Data persistence and storage
- **Object-Oriented Design**: Clean, maintainable code architecture
- **Custom Graphics**: Interactive pie chart visualization
- **Exception Handling**: Robust error management throughout the application

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java-compatible IDE (optional)

### Installation & Usage

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/PersonalFinanceTracker.git
   cd PersonalFinanceTracker
   ```

2. **Compile the application**
   ```bash
   javac -d bin src/*.java
   ```

3. **Run the application**
   ```bash
   java -cp bin MainMethod
   ```

## 📋 Expense Categories

The application supports the following expense categories:
- 🍕 **Food** - Groceries, restaurants, coffee, snacks
- 🏠 **Rent** - Monthly rent, mortgage payments
- 🚗 **Transport** - Gas, public transit, rideshare, parking
- ⚡ **Utilities** - Electricity, water, internet, phone bills
- 🎬 **Entertainment** - Movies, games, subscriptions, hobbies
- 🏥 **Healthcare** - Medical expenses, prescriptions, insurance
- 🛍️ **Shopping** - Clothing, electronics, household items
- 📚 **Education** - Courses, books, school supplies
- 💼 **Business** - Work-related expenses
- 🎁 **Gifts** - Presents, donations, celebrations
- 🏠 **Home** - Maintenance, repairs, furniture
- 🏃 **Fitness** - Gym memberships, sports equipment
- 🎯 **Other** - Miscellaneous expenses

## 📁 Project Structure

```
PersonalFinanceTracker/
├── src/
│   ├── MainMethod.java          # Application entry point
│   ├── ExpenseManager.java      # Core business logic
│   ├── Expense.java             # Expense data model
│   ├── ExpenseCategory.java     # Category enumeration
│   ├── ExpenseGUI.java          # Graphical user interface
│   └── FileHandler.java         # File I/O operations
├── bin/                         # Compiled class files
├── Expenses.csv                 # Data storage file
└── README.md                    # Project documentation
```

## 🎮 How to Use

1. **Launch the Application**: Run `MainMethod.java` to start the GUI
2. **Add Expenses**: Fill in the form with expense details and click "Add New Expense"
3. **View Data**: Browse expenses in the table below the form
4. **Visualize Spending**: Click "View Pie Chart" to see expense distribution
5. **Manage Data**: Use sorting options, clear functions, or save/load data
6. **Navigate**: Scroll through the interface to access all features

## 🔧 Key Components

### ExpenseManager
- Manages the collection of expense objects
- Handles business logic for adding, removing, and sorting expenses
- Provides data persistence functionality

### ExpenseGUI
- Modern, responsive graphical user interface
- Interactive components with hover effects and visual feedback
- Scrollable content area for large datasets

### Expense
- Data model representing individual expense records
- Includes validation and formatting methods
- Supports comparison and equality operations

## 📊 Features in Detail

### Data Visualization
- **Pie Chart Display**: Shows percentage breakdown of expenses by category
- **Color-coded Categories**: Each category has a distinct color for easy identification
- **Interactive Updates**: Charts refresh automatically when data changes

### File Management
- **Automatic Saving**: Data is saved to CSV format automatically
- **Header Support**: CSV files include proper headers for data organization
- **Error Recovery**: Graceful handling of file read/write errors

### User Experience
- **Intuitive Navigation**: Clear button labels and menu organization
- **Visual Feedback**: Success and error messages for all operations
- **Responsive Design**: Interface adapts to content and screen size

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 🎯 Future Enhancements

- [ ] Export to Excel/PDF formats
- [ ] Budget tracking and alerts
- [ ] Expense trends and analytics
- [ ] Multi-currency support
- [ ] Cloud synchronization
- [ ] Mobile companion app

## 📞 Support

If you encounter any issues or have questions, please open an issue on GitHub.

---
