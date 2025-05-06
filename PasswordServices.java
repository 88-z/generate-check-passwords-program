
package passwordservices;
import java.util.*;

public class PasswordServices {

static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
       int choice;
       int length; 
       int score;
      
       do {
          System.out.println("\nTo generate passwords, please enter 1. \nTo check the strength of your password,please enter 2. \nTo generate passwords and check their strengths, please enter 3. \nTo exit the program, please enter 0");
          choice = input.nextInt();
          
          switch (choice)
          {
                case 1:
                    length = lenValidation();
                    String[] passes = generatePasswords(length);
                    printPasswords(passes);
                    break;
                case 2:
                    System.out.println("Enter your password: ");
                    String passInput = input.next();
                    score = checkStrength(passInput);
                    printStrength(score);

                     break; 
                case 3:
                    length = lenValidation();
                    String[] passwords = generatePasswords(length);
                    int[] scores = new int[passwords.length];
                    for (int i = 0; i < passwords.length; i++) {
                        scores[i] = checkStrength(passwords[i]);
                    }
                    printPasswords(passwords, scores);
                    
                     break; 
                case 0:
                                System.out.println("program ended");
                     break;           
          default: System.out.println("Invalid entry");
          }
          
        } while(choice != 0);
    }
    
       
    public static int lenValidation() {
       
        int len;
        System.out.println("Enter the password length");
        
        while (true) {
            if (input.hasNextInt()){ //source from stack overflow https://stackoverflow.com/questions
                len = input.nextInt();
              if (len >1 && len <=100) {
                return len;} 
              else {System.out.println("please enter a length between the range of 2 to 100");
              }}
            else {System.out.println("please enter an integer length");
            input.next();}
        }
 
    }
    

    public static char randomChar(char ch1, char ch2) {
      return (char)(ch1 + Math.random() * (ch2 - ch1 +1));
    }
      
    public static String[] generatePasswords(int length){
     
      String all = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
      String symbols = "!@#$%^&*?~+=*"; 
      
      String[] passwords = new String [3];
      
      for (int j = 0; j < 3; j++) {
          String password = "";
            if (length > 4) {
               char small = randomChar('a', 'z');
               char capital = randomChar('A', 'Z');
               char digit = randomChar('0', '9');
               char symbol = symbols.charAt((int) (Math.random() * 13));


              password += "" + small + capital + digit + symbol;

              for (int i = 0; i <length-4; i++) {
              password += all.charAt((int)(Math.random() * all.length()));
              }
            }  
            else {
              for (int i=0; i<length; i++) {
              password += all.charAt((int)(Math.random() * all.length()));
              }  
            }
           passwords[j] = password;
      }
     return passwords;
    }
    
    
    public static void printPasswords(String []passes){
        System.out.println("Here are a few options:");
        for (int i=0; i < passes.length; i++)
        System.out.println(passes[i]);
    }
    
    
    
    public static int checkStrength(String pw ){
        
        int score = 0 ;
        boolean UpperPw =false, LowerPw =false, DigitPw =false, SymbolPw=false;
        
        for (int i =0; i < pw.length(); i++){
            char ch = pw.charAt(i);
            
            if(Character.isUpperCase(ch)){
                UpperPw = true ;
            }else if(Character.isLowerCase(ch)){
                LowerPw = true ;
            }else if(Character.isDigit(ch)){
                DigitPw = true ;
            }else 
                SymbolPw = true ;
            
        }
        if (UpperPw) score++ ;
        if (LowerPw) score++ ;
        if (DigitPw) score++ ;
        if (SymbolPw) score++ ;
        if (pw.length() >= 8) score++ ;
        
        return score ; 
        
    }
    
    
    public static void printStrength(int score){
        if (score==5){
            System.out.println("This is a very good password! ");
        } else if (score==4){
            System.out.println("This is a good password, but you can still do better");
        } else if (score==3){
            System.out.println("This is a medium password , try making it better ");
        } else {
            System.out.println("This is a weak password , you should find a new one !");
        }
    }
    

    public static void printPasswords(String[] passwords, int[] scores) {
       System.out.println("Here are your generated passwords with their strengths:");
       for (int i = 0; i < passwords.length; i++) {
           System.out.println(passwords[i]);

           printStrength(scores[i]);
       }
   }
        
    
    
}
