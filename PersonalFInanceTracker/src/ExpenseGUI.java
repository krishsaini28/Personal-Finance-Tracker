import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ExpenseGUI {
    
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219);      // Blue
    private static final Color SECONDARY_COLOR = new Color(46, 204, 113);    // Green
    private static final Color ACCENT_COLOR = new Color(155, 89, 182);       // Purple
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);  // Light Gray
    private static final Color CARD_COLOR = Color.WHITE;                     // White
    private static final Color TEXT_COLOR = new Color(44, 62, 80);           // Dark Gray
    private static final Color ERROR_COLOR = new Color(231, 76, 60);         // Red
    private static final Color SUCCESS_COLOR = new Color(39, 174, 96);       // Green
    
    // Modern fonts
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font BODY_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 12);
    
    private ExpenseManager manager;

    private JFrame mainFrame;
    private JPanel panel1, panel2, panel3, panel4;
    private JTextField amountField;
    private JComboBox<ExpenseCategory> categoryBox;
    private JTextField dateField;
    private JTextArea descriptionArea;
    private JButton addButton, removeButton, viewButton;
    private JTable expenseTable;
    private DefaultTableModel tableModel;
    private PieChartPanel pieChartPanel;
    private Map<ExpenseCategory, Double> expenseData;

    /**
     * Constructor to create and initialize the modern GUI
     * 
     * @param manager The ExpenseManager instance to work with
     */
    public ExpenseGUI(ExpenseManager manager) {
        this.manager = manager;
        
        // Initialize the main frame with modern styling and larger size
        mainFrame = new JFrame("💰 Personal Finance Tracker");
        mainFrame.setSize(1200, 800);  // Set to screen-friendly size
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setBackground(BACKGROUND_COLOR);
        mainFrame.setLocationRelativeTo(null); // Center the window
        mainFrame.setMinimumSize(new Dimension(1000, 600)); // Set minimum size

        // Initialize expenseData map
        expenseData = new HashMap<>();

        // Create a main content panel that will be scrollable
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(null);
        mainContentPanel.setPreferredSize(new Dimension(1160, 1100)); // Increased height to accommodate larger chart panel
        mainContentPanel.setBackground(BACKGROUND_COLOR);

        // Panel 1 - Modern header panel
        panel1 = createHeaderPanel();

        // Panel 2 - Modern input form panel
        panel2 = createInputPanel();

        // Panel 3 - Modern table panel
        panel3 = createTablePanel();

        // Panel 4 - Modern chart panel
        panel4 = createChartPanel();

        // Add all panels to the main content panel
        mainContentPanel.add(panel1);
        mainContentPanel.add(panel2);
        mainContentPanel.add(panel3);
        mainContentPanel.add(panel4);

        // Create scroll pane with the main content panel
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        
        // Style the scroll bars for better appearance
        scrollPane.getVerticalScrollBar().setBackground(BACKGROUND_COLOR);
        scrollPane.getHorizontalScrollBar().setBackground(BACKGROUND_COLOR);
        
        // Add scroll pane to the main frame
        mainFrame.add(scrollPane);

        // Create and add menu bar
        createMenuBar();

        // Make the frame visible
        mainFrame.setVisible(true);
    }

    /**
     * Creates a modern header panel with title and styling
     */
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(20, 20, 1160, 80);  // Expanded width to match new frame size
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setLayout(null);
        
        // Add rounded border effect
        Border roundedBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(PRIMARY_COLOR, 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );
        headerPanel.setBorder(roundedBorder);

        // Title label with modern styling
        JLabel titleLabel = new JLabel("💰 Personal Finance Tracker");
        titleLabel.setBounds(20, 20, 400, 40);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Track your expenses with style");
        subtitleLabel.setBounds(20, 50, 300, 20);
        subtitleLabel.setFont(BODY_FONT);
        subtitleLabel.setForeground(new Color(255, 255, 255, 200));
        headerPanel.add(subtitleLabel);

        // Scroll indicator
        JLabel scrollIndicator = new JLabel("📜 Scroll down to see more content");
        scrollIndicator.setBounds(800, 50, 300, 20);
        scrollIndicator.setFont(BODY_FONT);
        scrollIndicator.setForeground(new Color(255, 255, 255, 180));
        headerPanel.add(scrollIndicator);

        return headerPanel;
    }

    /**
     * Creates a modern input form panel
     */
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 120, 1160, 200);  // Expanded width to match new frame size
        inputPanel.setBackground(CARD_COLOR);
        inputPanel.setLayout(null);
        
        // Add shadow effect border
        Border shadowBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 30), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );
        inputPanel.setBorder(shadowBorder);

        // Section title
        JLabel sectionTitle = new JLabel("➕ Add New Expense");
        sectionTitle.setBounds(20, 10, 200, 25);
        sectionTitle.setFont(HEADER_FONT);
        sectionTitle.setForeground(TEXT_COLOR);
        inputPanel.add(sectionTitle);

        // Create form fields with modern styling
        createFormFields(inputPanel);

        return inputPanel;
    }

    /**
     * Creates form fields with modern styling
     */
    private void createFormFields(JPanel parent) {
        // Amount field - expanded for better visibility
        JLabel amountLabel = new JLabel("Amount ($):");
        amountLabel.setBounds(20, 50, 120, 20);
        amountLabel.setFont(BODY_FONT);
        amountLabel.setForeground(TEXT_COLOR);
        parent.add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 50, 200, 30);  // Wider field
        amountField.setFont(BODY_FONT);
        amountField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        parent.add(amountField);

        // Category field - expanded for better visibility
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(400, 50, 120, 20);
        categoryLabel.setFont(BODY_FONT);
        categoryLabel.setForeground(TEXT_COLOR);
        parent.add(categoryLabel);

        categoryBox = new JComboBox<>(ExpenseCategory.values());
        categoryBox.setBounds(530, 50, 300, 30);  // Much wider dropdown
        categoryBox.setFont(BODY_FONT);
        categoryBox.setBackground(Color.WHITE);
        parent.add(categoryBox);

        // Date field - expanded for better visibility
        JLabel dateLabel = new JLabel("Date (dd-MM-yyyy):");
        dateLabel.setBounds(20, 90, 150, 20);
        dateLabel.setFont(BODY_FONT);
        dateLabel.setForeground(TEXT_COLOR);
        parent.add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(180, 90, 200, 30);  // Wider field
        dateField.setFont(BODY_FONT);
        dateField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        parent.add(dateField);

        // Description field - much larger for better usability
        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(400, 90, 120, 20);
        descLabel.setFont(BODY_FONT);
        descLabel.setForeground(TEXT_COLOR);
        parent.add(descLabel);

        descriptionArea = new JTextArea(3, 30);  // Larger text area
        descriptionArea.setBounds(530, 90, 400, 60);  // Much wider and taller
        descriptionArea.setFont(BODY_FONT);
        descriptionArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        parent.add(descriptionArea);

        // Create modern buttons
        createModernButtons(parent);
    }

    /**
     * Creates modern styled buttons with better spacing and descriptive labels
     */
    private void createModernButtons(JPanel parent) {
        // Add button with descriptive label
        addButton = createModernButton("➕ Add New Expense", SECONDARY_COLOR, 20, 150);
        addButton.addActionListener(e -> addExpense());
        parent.add(addButton);

        // Remove button with descriptive label
        removeButton = createModernButton("🗑️ Remove Selected Item", ERROR_COLOR, 200, 150);
        removeButton.addActionListener(e -> removeExpense());
        parent.add(removeButton);

        // View chart button with descriptive label
        viewButton = createModernButton("📊 View Pie Chart", ACCENT_COLOR, 380, 150);
        viewButton.addActionListener(e -> showPieChart());
        parent.add(viewButton);
        
        // Clear form button with descriptive label
        JButton clearButton = createModernButton("🧹 Clear Input Form", new Color(149, 165, 166), 560, 150);
        clearButton.addActionListener(e -> clearForm());
        parent.add(clearButton);
        
        // Refresh button with descriptive label
        JButton refreshButton = createModernButton("🔄 Refresh Display", new Color(52, 73, 94), 740, 150);
        refreshButton.addActionListener(e -> {
            updateTable();
            updateChart();
            showMessage("✅ Data refreshed successfully!", SUCCESS_COLOR);
        });
        parent.add(refreshButton);
        
        // Clear all expenses button with descriptive label
        JButton clearAllButton = createModernButton("🗑️ Clear All Data", new Color(192, 57, 43), 920, 150);
        clearAllButton.addActionListener(e -> clearAllExpenses());
        parent.add(clearAllButton);
    }

    /**
     * Creates a modern styled button
     */
    private JButton createModernButton(String text, Color color, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 35);
        button.setFont(BUTTON_FONT);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }

    /**
     * Creates a modern table panel
     */
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setBounds(20, 340, 1160, 400);  // Expanded width and height for better table visibility
        tablePanel.setBackground(CARD_COLOR);
        tablePanel.setLayout(new BorderLayout());
        
        // Add shadow effect border
        Border shadowBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 30), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );
        tablePanel.setBorder(shadowBorder);

        // Section title
        JLabel sectionTitle = new JLabel("📋 Expense List");
        sectionTitle.setFont(HEADER_FONT);
        sectionTitle.setForeground(TEXT_COLOR);
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        tablePanel.add(sectionTitle, BorderLayout.NORTH);

        // Create modern table
        createModernTable(tablePanel);

        return tablePanel;
    }

    /**
     * Creates a modern styled table
     */
    private void createModernTable(JPanel parent) {
        String[] columnNames = {"Date", "Amount", "Category", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };

        expenseTable = new JTable(tableModel);
        expenseTable.setFont(BODY_FONT);
        expenseTable.setRowHeight(25);
        expenseTable.setSelectionBackground(PRIMARY_COLOR);
        expenseTable.setSelectionForeground(Color.WHITE);
        expenseTable.setGridColor(new Color(200, 200, 200));
        expenseTable.setShowGrid(true);
        expenseTable.getTableHeader().setFont(HEADER_FONT);
        expenseTable.getTableHeader().setBackground(PRIMARY_COLOR);
        expenseTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(expenseTable);
        scrollPane.setBorder(null);
        parent.add(scrollPane, BorderLayout.CENTER);

        // Load initial data
        updateTable();
    }

    /**
     * Creates a modern chart panel
     */
    private JPanel createChartPanel() {
        JPanel chartPanel = new JPanel();
        chartPanel.setBounds(20, 760, 1160, 300);  // Expanded height to show full pie chart
        chartPanel.setBackground(CARD_COLOR);
        chartPanel.setLayout(new BorderLayout());
        
        // Add shadow effect border
        Border shadowBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0, 0, 0, 30), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );
        chartPanel.setBorder(shadowBorder);

        // Section title
        JLabel sectionTitle = new JLabel("📊 Expense Distribution");
        sectionTitle.setFont(HEADER_FONT);
        sectionTitle.setForeground(TEXT_COLOR);
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        chartPanel.add(sectionTitle, BorderLayout.NORTH);

        // Create pie chart panel
        pieChartPanel = new PieChartPanel();
        chartPanel.add(pieChartPanel, BorderLayout.CENTER);

        return chartPanel;
    }

    /**
     * Creates the menu bar with modern styling
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(PRIMARY_COLOR);
        menuBar.setBorderPainted(false);

        // File Menu
        JMenu fileMenu = new JMenu("📁 File");
        fileMenu.setFont(BODY_FONT);
        fileMenu.setForeground(Color.WHITE);

        JMenuItem saveItem = new JMenuItem("💾 Save Expenses to File");
        saveItem.setFont(BODY_FONT);
        saveItem.addActionListener(e -> {
            manager.saveExpensesToFile("Expenses.csv");
            showMessage("✅ Expenses saved successfully!", SUCCESS_COLOR);
        });
        fileMenu.add(saveItem);

        JMenuItem loadItem = new JMenuItem("📂 Load Expenses from File");
        loadItem.setFont(BODY_FONT);
        loadItem.addActionListener(e -> {
            manager.loadExpensesFromFile("Expenses.csv");
            updateTable();
            showMessage("✅ Expenses loaded successfully!", SUCCESS_COLOR);
        });
        fileMenu.add(loadItem);

        // Add separator
        fileMenu.addSeparator();

        JMenuItem clearAllItem = new JMenuItem("🗑️ Clear All Expenses");
        clearAllItem.setFont(BODY_FONT);
        clearAllItem.addActionListener(e -> clearAllExpenses());
        fileMenu.add(clearAllItem);

        // Sort Menu
        JMenu sortMenu = new JMenu("🔄 Sort");
        sortMenu.setFont(BODY_FONT);
        sortMenu.setForeground(Color.WHITE);

        JMenuItem sortAmountAsc = new JMenuItem("💰 Sort by Amount (Low to High)");
        sortAmountAsc.setFont(BODY_FONT);
        sortAmountAsc.addActionListener(e -> {
            manager.sortExpenses("amount", "asc");
            updateTable();
        });
        sortMenu.add(sortAmountAsc);

        JMenuItem sortAmountDesc = new JMenuItem("💰 Sort by Amount (High to Low)");
        sortAmountDesc.setFont(BODY_FONT);
        sortAmountDesc.addActionListener(e -> {
            manager.sortExpenses("amount", "des");
            updateTable();
        });
        sortMenu.add(sortAmountDesc);

        JMenuItem sortDateAsc = new JMenuItem("📅 Sort by Date (Oldest First)");
        sortDateAsc.setFont(BODY_FONT);
        sortDateAsc.addActionListener(e -> {
            manager.sortExpenses("date", "asc");
            updateTable();
        });
        sortMenu.add(sortDateAsc);

        JMenuItem sortDateDesc = new JMenuItem("📅 Sort by Date (Newest First)");
        sortDateDesc.setFont(BODY_FONT);
        sortDateDesc.addActionListener(e -> {
            manager.sortExpenses("date", "des");
            updateTable();
        });
        sortMenu.add(sortDateDesc);

        menuBar.add(fileMenu);
        menuBar.add(sortMenu);
        mainFrame.setJMenuBar(menuBar);
    }

    /**
     * Shows a modern styled message dialog
     */
    private void showMessage(String message, Color color) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(mainFrame, "Notification");
        dialog.getContentPane().setBackground(color);
        dialog.setVisible(true);
    }

    // ========== EXPENSE MANAGEMENT METHODS ==========
    
    /**
     * Adds a new expense with validation and modern feedback
     */
    private void addExpense() {
        try {
            // Validate and get input values
            double amount = Double.parseDouble(amountField.getText().trim());
            ExpenseCategory category = (ExpenseCategory) categoryBox.getSelectedItem();
            String date = dateField.getText().trim();
            String description = descriptionArea.getText().trim();

            // Validate inputs
            if (amount <= 0) {
                showMessage("❌ Amount must be positive!", ERROR_COLOR);
                return;
            }
            if (date.isEmpty()) {
                showMessage("❌ Date cannot be empty!", ERROR_COLOR);
                return;
            }
            if (description.isEmpty()) {
                showMessage("❌ Description cannot be empty!", ERROR_COLOR);
                return;
            }

            // Add expense to manager
            manager.addExpense(amount, category, date, description);
            
            // Clear form fields
            clearForm();
            
            // Update table and chart
            updateTable();
            updateChart();
            
            showMessage("✅ Expense added successfully!", SUCCESS_COLOR);
            
        } catch (NumberFormatException e) {
            showMessage("❌ Invalid amount format!", ERROR_COLOR);
        } catch (Exception e) {
            showMessage("❌ Error adding expense: " + e.getMessage(), ERROR_COLOR);
        }
    }

    /**
     * Removes the selected expense with improved error handling
     */
    private void removeExpense() {
        int selectedRow = expenseTable.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("❌ Please select an expense to remove!", ERROR_COLOR);
            return;
        }

        try {
            // Get the values from the table
            String date = (String) tableModel.getValueAt(selectedRow, 0);
            
            // Parse amount (remove $ sign if present)
            String amountStr = tableModel.getValueAt(selectedRow, 1).toString().replace("$", "");
            double amount = Double.parseDouble(amountStr);
            
            // Get category directly from the table (it should be the enum name)
            String categoryStr = (String) tableModel.getValueAt(selectedRow, 2);
            ExpenseCategory category = ExpenseCategory.valueOf(categoryStr);
            
            String description = (String) tableModel.getValueAt(selectedRow, 3);
            
            // Remove from manager
            boolean removed = manager.removeExpense(amount, category, date, description);
            
            if (removed) {
                // Remove from table
                tableModel.removeRow(selectedRow);
                updateChart();
                showMessage("✅ Expense removed successfully!", SUCCESS_COLOR);
            } else {
                showMessage("❌ Failed to remove expense!", ERROR_COLOR);
            }
            
        } catch (Exception e) {
            showMessage("❌ Error removing expense: " + e.getMessage(), ERROR_COLOR);
        }
    }

    /**
     * Clears all form fields
     */
    private void clearForm() {
        amountField.setText("");
        dateField.setText("");
        descriptionArea.setText("");
        categoryBox.setSelectedIndex(0);
        showMessage("🧹 Form cleared successfully!", SUCCESS_COLOR);
    }

    /**
     * Clears all expenses with confirmation dialog
     */
    private void clearAllExpenses() {
        int result = JOptionPane.showConfirmDialog(
            mainFrame,
            "⚠️ Are you sure you want to clear ALL expenses?\n\nThis action cannot be undone!",
            "Confirm Clear All Expenses",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (result == JOptionPane.YES_OPTION) {
            manager.clearExpenses();
            updateTable();
            updateChart();
            showMessage("🗑️ All expenses cleared successfully!", SUCCESS_COLOR);
        }
    }

    /**
     * Updates the table with current expense data
     */
    private void updateTable() {
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Add all expenses to the table
        for (Expense expense : manager.getExpenses()) {
            Object[] row = {
                expense.getDate(),
                String.format("$%.2f", expense.getAmount()),
                expense.getCategory().name(),
                expense.getDescription()
            };
            tableModel.addRow(row);
        }
    }

    /**
     * Updates the pie chart with current expense data
     */
    private void updateChart() {
        expenseData.clear();
        
        // Calculate totals by category
        for (Expense expense : manager.getExpenses()) {
            ExpenseCategory category = expense.getCategory();
            expenseData.put(category, expenseData.getOrDefault(category, 0.0) + expense.getAmount());
        }
        
        // Update the pie chart
        pieChartPanel.updateData(expenseData);
        pieChartPanel.repaint();
    }

    /**
     * Shows the pie chart in a separate window with larger size
     */
    private void showPieChart() {
        updateChart();
        
        JFrame chartFrame = new JFrame("📊 Expense Distribution Chart");
        chartFrame.setSize(800, 700);  // Larger window for better visibility
        chartFrame.setLocationRelativeTo(mainFrame);
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.getContentPane().setBackground(BACKGROUND_COLOR);
        
        PieChartPanel chartPanel = new PieChartPanel();
        chartPanel.updateData(expenseData);
        chartFrame.add(chartPanel);
        
        chartFrame.setVisible(true);
    }

    // ========== PIE CHART PANEL ==========
    
    /**
     * Modern PieChartPanel for displaying expense distribution
     */
    private class PieChartPanel extends JPanel {
        private Map<ExpenseCategory, Double> expenseData;

        public PieChartPanel() {
            this.expenseData = new HashMap<>();
            setBackground(CARD_COLOR);
        }

        public void updateData(Map<ExpenseCategory, Double> data) {
            this.expenseData = new HashMap<>(data);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawPieChart((Graphics2D) g);
        }

        private void drawPieChart(Graphics2D g2) {
            double total = expenseData.values().stream().mapToDouble(Double::doubleValue).sum();
            if (total == 0) {
                g2.setFont(HEADER_FONT);
                g2.setColor(TEXT_COLOR);
                g2.drawString("No expense data to display", 50, 80);
                return;
            }

            // Modern color palette
            Color[] colors = {
                new Color(52, 152, 219),   // Blue
                new Color(46, 204, 113),   // Green
                new Color(155, 89, 182),   // Purple
                new Color(241, 196, 15),   // Yellow
                new Color(231, 76, 60),    // Red
                new Color(230, 126, 34),   // Orange
                new Color(26, 188, 156),   // Teal
                new Color(142, 68, 173)    // Dark Purple
            };

            // Pie chart variables
            int x = 50;
            int y = 20;
            int width = 200;
            int height = 200;
            int startAngle = 0;
            int colorIndex = 0;

            // Draw pie chart
            for (Map.Entry<ExpenseCategory, Double> entry : expenseData.entrySet()) {
                double value = entry.getValue();
                int arcAngle = (int) Math.round((value / total) * 360);
                
                g2.setColor(colors[colorIndex % colors.length]);
                g2.fill(new Arc2D.Double(x, y, width, height, startAngle, arcAngle, Arc2D.PIE));
                
                // Draw legend
                int legendY = 20 + (colorIndex * 20);
                g2.fillRect(300, legendY, 15, 15);
                g2.setColor(TEXT_COLOR);
                g2.setFont(BODY_FONT);
                g2.drawString(entry.getKey().name() + ": $" + String.format("%.2f", value), 320, legendY + 12);
                
                startAngle += arcAngle;
                colorIndex++;
            }

            // Draw total
            g2.setFont(HEADER_FONT);
            g2.setColor(TEXT_COLOR);
            g2.drawString("Total: $" + String.format("%.2f", total), 50, 250);
        }
    }
}