import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordVerification {
    public static void main(String[] args) {
        PasswordVerification pv = new PasswordVerification();

        System.out.println("Введите пароль для проверки");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        System.out.println(pv.checkPasswordSimpleWay(input));

        //pv.testCheckPassword();
       /* String input = "ohhh)hda6Ldh";
        System.out.println(input+": "+pv.checkPassword(input));*/

    }

    public boolean checkPassword(String input) {
        Pattern p1 = Pattern.compile("^(?=\\S{8})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*(\\W|_)).*$");
        Matcher m1 = p1.matcher(input);

        return m1.find();
    }

    public boolean checkPasswordSimpleWay(String input) {
        Pattern p1 = Pattern.compile("^\\S{8,}$");
        Matcher m1 = p1.matcher(input);

        Pattern p2 = Pattern.compile("[a-z]");
        Matcher m2 = p2.matcher(input);

        Pattern p3 = Pattern.compile("[A-Z]");
        Matcher m3 = p3.matcher(input);

        Pattern p4 = Pattern.compile("[0-9]");
        Matcher m4 = p4.matcher(input);

        Pattern p5 = Pattern.compile("\\W|_");
        Matcher m5 = p5.matcher(input);


        return m1.find() && m2.find()&& m3.find()&& m4.find()&& m5.find();
    }

    public void testCheckPassword(){
        ArrayList<String> array = new ArrayList<>();
        array.add("qwertyui");
        array.add("ASWDFFFG");
        array.add("123456789");
        array.add("<>@$%^$%^@");
        array.add(" jsh 2Asdjj,");
        array.add("QwerTy69@4");

        System.out.println("Simple way:\n");
        for(String currentPassword:array){
            System.out.println(currentPassword+": "+ checkPasswordSimpleWay(currentPassword));
        }

        System.out.println("===================");

        System.out.println("Smart way:\n");
        for(String currentPassword:array){
            System.out.println(currentPassword+": "+ checkPassword(currentPassword));
        }
    }
}
