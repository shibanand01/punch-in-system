package com.punch;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Main {
    private Scanner scan = new Scanner(System.in);
    private static int input;
    private PunchService service;

    public void control(){
        service = new PunchService();
        while (true) {
            menu();
            System.out.print("Enter Your Choice: ");
            input = scan.nextInt();
            scan.nextLine();
            switch (input) {
                case 1:
                    service.punchIn();
                    break;
                case 2:
                    service.punchOut();
                    break;
                case 3:
                    System.out.println(service.getSessionDuration());
                    break;
                case 4:
                    service.printAllSessions();
                    break;
                case 5:{
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String dateString = scan.nextLine();
                    try{
                        LocalDate date = LocalDate.parse(dateString);
                        System.out.println(service.getTotalDurationForDate(date));
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid Date Format!");
                    }
                    break;
                }
                case 6:{
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String dateString = scan.nextLine();
                    try{
                        LocalDate date = LocalDate.parse(dateString);
                        System.out.println(service.getTotalDurationForWeek(date));
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid Date Format!");
                    }
                    break;
                }
                case 7:{
                    System.out.print("Enter the Month (YYYY-MM): ");
                    String monthString = scan.nextLine();
                    try{
                        YearMonth month = YearMonth.parse(monthString);
                        System.out.println(service.getTotalDurationForMonth(month));
                    }
                    catch(Exception e)
                    {
                        System.out.println("Invalid Date Format!");
                    }
                    break;
                }
                case 8:
                    if(service.isPunchedIn() == true){
                        service.punchOut();
                    }
                    System.out.println("System exiting. Goodbye!");
                    return;
                default:
                    System.out.println("In-Valid Input!!");
                    break;
            }
        }
    }
    //prints menu unless true
    public void menu()
    {
        System.out.println();
        if (service.isPunchedIn() == false)
            System.out.println("1. Punch In\n2. Punch Out\n3. Get Current Duration\n4. View Sessions\n5. Daily Report\n6. Weekly Report\n7. Monthly Report\n8. Exit");
        else
            System.out.println("2. Punch Out\n3. Get Current Duration\n4. View Sessions\n5. Daily Report\n6. Weekly Report\n7. Monthly Report\n8. Exit");
        System.out.println();
    }
    public static void main(String[] args) {
        Main m1 = new Main();
        m1.control();
    }
}
