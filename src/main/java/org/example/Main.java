package org.example;

import java.util.*;

public class Main{
    //  global variables for storing the total score and number of students
    static int totalScore = 0;
    static int totalNumberOfStudents = 0;

    //  special variables for storing both the name and grade of the students that have the highest score
    static HashMap<String, Integer> studentsInfo = new HashMap<>();
    static HashMap<String, Integer> studentWithHighestGrade = new HashMap<>();

    //  variables for counting grade equivalent occurrences
    static int gradeCountA = 0;
    static int gradeCountB = 0;
    static int gradeCountC = 0;
    static int gradeCountD = 0;
    static int gradeCountF = 0;

    //  main method
    public static void main(String[] args) {
        //  getting total number of students
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        totalNumberOfStudents = Integer.parseInt(input.nextLine());

        //  re-initializing the total score to zero
        totalScore = 0;

        //  iterating through the inputted total number of students
        for (int i = 1; i <= totalNumberOfStudents; i++) {
            showStudentPrompt(i, input);
        }

        //  displaying the output string summary
        showSummary();
    }

    //  method for displaying summary
    public static void showSummary() {
        System.out.println();
        System.out.println("----- Class Summary -----");
        //  getting the average score by dividing the computed total score and number of students then casting it to float
        System.out.println("Average Score: " + String.format("%.2f", (float) totalScore / totalNumberOfStudents));
        System.out.println("Grade Counts: A:" + gradeCountA
                + " B:" + gradeCountB
                + " C:" + gradeCountC
                + " D:" + gradeCountD
                + " F:" + gradeCountF
        );

        //  iterating though the stored hashmap then storing the key and value of the student with the highest
        //  score to the another hashmap
        studentsInfo.forEach((name, score) -> {
            studentWithHighestGrade.put(name, score);

            if (score > studentWithHighestGrade.values().iterator().next()) {
                studentWithHighestGrade.clear();
                studentWithHighestGrade.put(name, score);
            }
        });
        //  Using the Entry object in order to access the key and value in a hashmap
        Map.Entry<String, Integer> highestStudent = studentWithHighestGrade.entrySet().iterator().next();

        //  Displaying the student with the highest grade
        System.out.println("Top Student(s): " + highestStudent.getKey() + " (" + highestStudent.getValue() + ")");
    }

    //  method for displaying user prompts for student name and student score
    public static void showStudentPrompt(int studentCount, Scanner input) {
        System.out.println();
        System.out.print("Enter name of student " + studentCount + ": ");
        String studentName = input.nextLine();

        //  making sure that no score will be less than 0 or more than 100
        int studentScore;
        do {
            System.out.print("Enter score for " + studentName + ": ");
            studentScore = input.nextInt();
        } while (studentScore < 0 || studentScore > 100);
        //  doing the escape line sequence since Scanner.nextInt() doesn't have its own escape sequence
        input.nextLine();

        System.out.println(studentName + " got grade: " + initScoreEquivalent(studentScore, studentName));
    }

    //  method for calculating grade equivalent
    public static char initScoreEquivalent(int studentScore, String studentName) {
        //  incrementing the amount for each of the students' score
        totalScore += studentScore;
        //  adding the students' information to the hashmap
        studentsInfo.put(studentName, studentScore);

        //  matching grade equivalent then incrementing the respective variable
        if (studentScore >= 90 && studentScore <= 100) {
            gradeCountA++;
            return 'A';
        } else if (studentScore >= 80 && studentScore <= 89) {
            gradeCountB++;
            return 'B';
        } else if (studentScore >= 70 && studentScore <= 79) {
            gradeCountC++;
            return 'C';
        } else if (studentScore >= 60 && studentScore <= 69) {
            gradeCountD++;
            return 'D';
        } else {
            gradeCountF++;
            return 'F';
        }
    }
}
