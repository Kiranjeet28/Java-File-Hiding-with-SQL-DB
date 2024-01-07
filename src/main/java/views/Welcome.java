package views;
import dao.UserDAO;
import model.User;
import servies.GenerateOTP;
import servies.SendOTPService;
import servies.UserServices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;
public class Welcome {
    public  void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("welcome to the app ");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to signup");
        System.out.println("Press 0 to exit");
        int choice = 0;
        try{
           choice = Integer.parseInt(br.readLine());

        }catch(Exception e ){
            System.out.println("galti gyi welcome ma");
        }
        switch(choice){
            case 1 -> login();
            case 2 -> signUp();
            case 3 -> System.exit(0);
        }
    }
    private void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter  your e mail");
        String email = scanner.nextLine();
try{
    if (UserDAO.isExist(email)){
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sentOTP(email,genOTP);
        System.out.println("Enter the otp");
        String otp = scanner.nextLine();
        if(otp.equals(genOTP)){
            new UserView(email).home();

        }else{
            System.out.println("Wrong OTP");
        }
    }else{
        System.out.println("User not Found");
    }
}catch (SQLException | IOException ex){
    ex.printStackTrace();
}
    }
    private void signUp(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter email ");
        String email = scanner.nextLine();

                String genOTP = GenerateOTP.getOTP();
            SendOTPService.sentOTP(email, genOTP);
            System.out.println("Enter the otp");
            String otp = scanner.nextLine();
            if (otp.equals(genOTP)) {
                User user = new User(email,name);
                int response;

                response = UserServices.saveUser(user);
                switch(response){
                    case 0 -> System.out.println("user registered ");
                    case 1 -> System.out.println("User already existed");

                }
                try {
                    new UserView(email).home();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else {
                System.out.println("Wrong OTP");
            }

    }
}
