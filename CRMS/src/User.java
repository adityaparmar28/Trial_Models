import java.util.Scanner;

class User
{
    Scanner sc=new Scanner(System.in);

    void userMenu()
    {
        boolean isUML=false;

        while (!isUML)
        {
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("|                            USER PROFILE                              |");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println();
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Change Password");
            System.out.println("4. User Activity Log");
            System.out.println("5. Home Page");
            System.out.println("+----------------------------------------------------------------------+");
            System.out.print("Enter Your Choice: ");
            int user_ch = sc.nextInt();

            switch (user_ch)
            {
                case 1:
                {
                    // View Profile
                    viewProfile();
                    break;
                }

                case 2:
                {
                    // Update Profile
                    updateProfile();
                    break;
                }

                case 3:
                {
                    // Change Password
                    changePassword();
                    break;
                }

                case 4:
                {
                    // User Activity Log
                    userActivityLog();
                    break;
                }

                case 5:
                {
                    // Home Page
                    isUML=true;
                    return;
                }

                default:
                {
                    System.err.println("[X] [ERROR] Invalid number format. Please enter a valid choice....");
                }
            }
        }
    }

    void viewProfile()
    {
        //Show last logged in user data throgh query....
        //Aani mate user login na log alag thi create kari emathi last login user details fetch karishu....
    }

    void updateProfile()
    {
        //Update user profile throgh query....
        //Update comfirmation mate yes karaishu then user credentials cross check kari update karishu....
    }

    void changePassword()
    {
        //Change user password throgh query....
        //OPT ya phir Captch fetch karaishu....
    }

    void userActivityLog()
    {
        //Show user activity log throgh query....
        //as text file ma store karishu and fetch karishu....
    }
}
