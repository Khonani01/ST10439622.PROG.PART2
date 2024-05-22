/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.st10439622.java;

import javax.swing.JOptionPane;

public class ST10439622Java {

    private static String username;
    private static String password;
    private static String firstName;
    private static String lastName;
    static int taskCount = 0; // To keep track of task numbers

    public static void main(String[] args) {
        RegisterUser();
        if (LoginUser()) {
            showMainMenu();
        }
    }

    public static void RegisterUser() {
        // Input username
        do {
            username = JOptionPane.showInputDialog(null, "Enter Username:");
            if (!isValidUsername(username)) {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted, "
                        + "Please ensure that your username contains an "
                        + "underscore and is no more than "
                        + "5 characters in length");
            }
        } while (!isValidUsername(username));
        JOptionPane.showMessageDialog(null, "Username successfully captured");

        // Input password
        do {
            password = JOptionPane.showInputDialog(null, "Enter Password:");
            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted, Please ensure that "
                        + "the password contains at least 8 characters, "
                        + "a capital letter, a number, and a special character.");
            }
        } while (!isValidPassword(password));
        JOptionPane.showMessageDialog(null, "Password successfully captured");

        // Input first name and last name
        firstName = JOptionPane.showInputDialog(null, "Enter First Name:");
        lastName = JOptionPane.showInputDialog(null, "Enter Last Name:");

        JOptionPane.showMessageDialog(null, "Registration complete Mr/Mrs " + firstName + " " + lastName);
    }

    public static boolean LoginUser() {
        String inputUsername = JOptionPane.showInputDialog(null, "Enter Username:");
        String inputPassword = JOptionPane.showInputDialog(null, "Enter Password:");

        boolean isAuthenticated = authenticate(inputUsername, inputPassword);

        if (isAuthenticated) {
            JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName + ", it is great to see you again.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username or Password incorrect, Please try again.");
            return false;
        }
    }

    public static boolean authenticate(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(password);
    }

    public static boolean isValidUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean isValidPassword(String password) {
        String upperCase = "(.*[A-Z].*)";
        String lowerCase = "(.*[a-z].*)";
        String digits = "(.*[0-9].*)";
        String specialChars = "(.*[!@#$%^&*].*)";

        return password.length() >= 8 &&
                password.matches(upperCase) &&
                password.matches(lowerCase) &&
                password.matches(digits) &&
                password.matches(specialChars);
    }

    public static void showMainMenu() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
        boolean running = true;

        while (running) {
            String[] options = {"Add Task", "Show Report", "Quit"};
            int choice = JOptionPane.showOptionDialog(null, "Choose an option", "Main Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    addTasks();
                    break;
                case 1:
                    showReport();
                    break;
                case 2:
                    running = false;
                    break;
                default:
                    running = false;
            }
        }
    }

    
    public static void addTasks() {
        int numberOfTasks = Integer.parseInt(JOptionPane.showInputDialog(null, "How many tasks do you want to enter?"));

        for (int i = 0; i < numberOfTasks; i++) {
            taskCount++;
            String taskName = JOptionPane.showInputDialog(null, "Enter Task Name:");
            String checkTaskDescription;

            do {
               checkTaskDescription = JOptionPane.showInputDialog(null, "Enter Task Description (less than 50 characters):");
                if (checkTaskDescription.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                }
            } while (checkTaskDescription.length() > 50);

            String developerDetails = JOptionPane.showInputDialog(null, "Enter Developer Details (First and Last Name):");
            int returnTotalHours = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Task Duration (in hours):"));

            String[] statuses = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Select Task Status", "Task Status",
                    JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);

            String createTaskID = generateTaskID(taskName, developerDetails);
            String printTaskDetails = String.format("Task Status: %s\nDeveloper Details: %s\nTask Number: %d\nTask Name: %s\nTask Description: %s\nTask ID: %s\nDuration: %d hours",
                    taskStatus, developerDetails, taskCount, taskName, checkTaskDescription, createTaskID, returnTotalHours);
            JOptionPane.showMessageDialog(null, "Task successfully captured\n" + printTaskDetails);
        }
    }

    public static void showReport() {
        JOptionPane.showMessageDialog(null, "Coming soon");
    }

    public static String generateTaskID(String taskName, String developerDetails) {
        String createTaskID = taskName.substring(0, 2).toUpperCase() + ":" +
                taskCount + ":" +
                developerDetails.substring(developerDetails.length() - 3).toUpperCase();
        return createTaskID;
    }

    static boolean checkTaskDescription(String this_is_a_short_description) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
