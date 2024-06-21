import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception 
    {
        //--------------------Declaring Variable--------------------
        Scanner scanner = new Scanner(System.in);
        int loopCount = 0;
        int mainLoopCount = 0;
        boolean Error;

        char semester;
        float week = 0;
        float lecture_hour = 0;
        float tutorial_hour = 0;
        float practical_hour = 0;
        char userConfirm = 'N';

        int inputType = 0;
        
        float total_hour;

        float total_absence_hour = 0;
        float absence_lec;
        float absence_tutor;
        float absence_prac;

        char userEnd;

        while(mainLoopCount < 10)
        {

            //--------------------Clearing Console--------------------
            ClearConsole.ClearScreen();

            //--------------------Collecting information--------------------

            //----------------------------------------------First Loop--------------------------------------------------------------
            //Collecting course information
            
            while(loopCount < 5 && userConfirm != 'Y' && userConfirm != 'y')
            {
                Error = false; //Resetting error

                //--------------------User input variable--------------------
                System.out.println("--------------------Please enter course Information--------------------\n");

                System.out.print("Long sem or Short Sem (L/S)\t\t\t: ");
                semester = Character.toUpperCase(scanner.next().charAt(0));

                if(semester != 'L' && semester != 'S')
                {
                    Error = true;
                }
                else if(semester == 'L')
                {
                    week = 13;
                }
                else if(semester == 'S')
                {
                    week = 6;
                }

                System.out.print("Please enter how many hour lecture per week\t: ");
                try{
                    lecture_hour = scanner.nextFloat();}
                catch(InputMismatchException e)
                {
                    lecture_hour = 0;
                    scanner.nextLine();
                    Error = true;
                }

                System.out.print("Please enter how many hour tutorial per week\t: ");
                try{
                    tutorial_hour = scanner.nextFloat();}
                catch(InputMismatchException e)
                {
                    tutorial_hour = 0;
                    scanner.nextLine();
                    Error = true;
                }

                System.out.print("Please enter how many hour practical per week\t: ");
                try{
                    practical_hour = scanner.nextFloat();}
                catch(InputMismatchException e)
                {
                    practical_hour = 0;
                    scanner.nextLine();
                    Error = true;
                }

                //--------------------Showing information information--------------------
                ClearConsole.ClearScreen();
                System.out.println("--------------------Course Information--------------------\n");
                System.out.println("Bar list week\t\t: " + week);
                System.out.println("Lecture hour per week\t: " + lecture_hour);
                System.out.println("Tutorial hour per week\t: " + tutorial_hour);
                System.out.println("Practical hour per week\t: " + practical_hour);

                //--------------------User Confirmation-----------------------
                if(Error)
                {
                    System.out.println("\nInvalid input captured. Please enter valid input. \n");
                }
                else
                {
                    System.out.println("\n--------------------User Confirmation--------------------\n");
                    System.out.print("Confirming course informarion? (y/n)\t: ");
                    userConfirm = scanner.next().charAt(0);
                }

                //--------------------Update Loop count--------------------
                loopCount++;
            }

            //
            if(loopCount >= 5)
            {
                System.out.println("System overtime. loopCOunt = " + loopCount);
                System.exit(1); //Stop the system due to overime
            }

            loopCount = 0; //Reset loopCount for next loop
            ClearConsole.ClearScreen(); //Clean screen

            //Calculate for total hour
            total_hour = week * (lecture_hour + tutorial_hour + practical_hour);

            //----------------------------------------------Second Loop--------------------------------------------------------------
            //Select absece input type
            while(loopCount < 5)
            {
                Error = false;
                System.out.println("--------------------Please enter more Information--------------------\n");
                System.out.println("Please choose input type :\n\n1. Total absence hour\n2. Absence class\n");
                System.out.print("Please select input type (1/2)\t: ");
                
                try{
                    inputType = scanner.nextInt();}
                catch(InputMismatchException e){
                    System.out.println("Invalid input captured.");
                    inputType = 0; //Reset inputType back to default value to prevent program from halting
                    scanner.nextLine();
                    Error = true;
                }

                if((inputType == 1 || inputType == 2) && !Error) 
                {
                    break; //Exit loop and proceed to next section
                }
                
                loopCount++;
            }

            if(loopCount >= 5)
            {
                System.out.println("System overtime. loopCOunt = " + loopCount);
                System.exit(1); //Stop the system due to overime
            }

            //----------------------------------------------Third Loop--------------------------------------------------------------
            //Enter user absence status
            while(loopCount < 5)
            {
                ClearConsole.ClearScreen();
                Error = false; //Resetting error

                System.out.println("--------------------Please enter absence detail--------------------\n");

                if(inputType == 1)
                {
                    System.out.print("Please enter total hour absence\t: ");

                    try{
                        total_absence_hour = scanner.nextFloat();}
                    catch(InputMismatchException e)
                    {
                        total_absence_hour = 0;
                        scanner.nextLine();
                        Error = true;
                    }

                    if(!Error && total_absence_hour < total_hour)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid input captured. Please enter valid input");
                    }
                }
                else if (inputType == 2) 
                {
                    System.out.print("Please enter how many lecture absenced\t\t: ");
                    try{
                        absence_lec = scanner.nextFloat();}
                    catch(InputMismatchException e)
                    {
                        absence_lec = 0;
                        scanner.nextLine();
                        Error = true;
                    }

                    System.out.print("Please enter how many tutorial absenced\t\t: ");
                    try{
                        absence_tutor = scanner.nextFloat();}
                    catch(InputMismatchException e)
                    {
                        absence_tutor = 0;
                        scanner.nextLine();
                        Error = true;
                    }

                    System.out.print("Please enter how many practical absenced\t: ");
                    try{
                        absence_prac = scanner.nextFloat();}
                    catch(InputMismatchException e)
                    {
                        absence_prac = 0;
                        scanner.nextLine();
                        Error = true;
                    }

                    if(!Error)
                    {
                        total_absence_hour = (absence_lec*lecture_hour) + (absence_tutor*tutorial_hour) + (absence_prac*practical_hour);
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid input captured. Please enter valid input");
                    }
                }
            }
            
            //------------------------------------------Calculating for percentage---------------------------------
            ClearConsole.ClearScreen();

            System.out.println("--------------------Result--------------------\n");
            AttendanceCalculator.calculateAttendance(total_hour, total_absence_hour);

            System.out.println("Current attendance percentage\t: " + String.format("%.2f", AttendanceCalculator.getCurrentPercentage()) + "%");
            System.out.println("Total hour can be skip\t\t: " + String.format("%.2f", AttendanceCalculator.getTotalHourAvailable()));
            System.out.println("Rounded off hour\t\t: " + (int)AttendanceCalculator.getTotalHourAvailable() + ".00");
            System.out.println("Hour used\t\t\t: " + String.format("%.2f", total_absence_hour));
            
            //System.out.println("\nAvailable hour reminding\t: " + String.format("%.2f", AttendanceCalculator.getHourAvailable()));
            System.out.println("\nAvailable hour reminding\t: " + (int)AttendanceCalculator.getHourAvailable() + ".00");

            System.out.println("\n----------------------------------------------\n");

            //--------------------------------------------End of Program-------------------------------------------

            //Promt for user to choose whether repeat or exit the program
            
            loopCount = 0; // Resetting loopCount

            while(loopCount < 10)
            {
                Error = false; // Resetting error

                System.out.print("Do you want to restart program or exit? (R/E)\t: ");
                userEnd = Character.toUpperCase(scanner.next().charAt(0));

                if(userEnd != 'R' && userEnd != 'E')
                {
                    Error = true;
                    System.out.println("Invalid input captured, please enter valid input\n");
                }
                else if(userEnd == 'R')
                {
                    userConfirm = '!';
                    break;
                }
                else if(userEnd == 'E')
                {
                    scanner.close(); //Close scanner before exit program
                    System.exit(1);
                }

                loopCount++; //Upgrade loopCount after each cycle
            }
        }
    } //Closing main loop
    
    //Defining new class for attendance calculator
    public class AttendanceCalculator 
    {
        private static double totalHourAvailable; // Total hours that can be skipped
        private static double hourAvailable; // Current hours available to skip class
        private static double currentPercentage; // Current attendance percentage
    
        public static void calculateAttendance(float total, float absence) 
        {
            totalHourAvailable = total * 0.2; // 20% of total hours can be skipped
            hourAvailable = totalHourAvailable - absence; // Calculate available hours to skip class
            currentPercentage = (total-absence)/total * 100;
        }
    
        public static double getTotalHourAvailable() 
        {
            return totalHourAvailable;
        }

        public static double getHourAvailable()
        {
            return hourAvailable;
        }

        public static double getCurrentPercentage()
        {
            return currentPercentage;
        }
    }    
}