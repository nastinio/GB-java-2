import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    private HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();

    public void add(String name, String ...phoneList){
        ArrayList<String> phoneArray = new ArrayList<>();

        for(String currentPhone:phoneList){
            phoneArray.add(currentPhone);
        }
        phoneBook.put(name,phoneArray);

    }

    public ArrayList<String> getPhoneByName(String name){
        return phoneBook.get(name);
    }

    //Служебные методы на печать
    public void displayByName(String name){
        System.out.println("Телефонные номера для "+ name+":");
        for(String phone:getPhoneByName(name)){
            System.out.println(phone);
        }
        System.out.println("----------------------------------");
    }

    public void displayAllPhoneBook(){
        Set<Map.Entry<String, ArrayList<String>>> set = phoneBook.entrySet();
        for (Map.Entry<String, ArrayList<String>> o : set) {
            displayByName(o.getKey());
        }
    }

//    public boolean isNumberValid(String number){
//        Pattern p1 = Pattern.compile("\\+7\\d{10}");
//        Matcher m1 = p1.matcher(number);
//
//        Pattern p2 = Pattern.compile("8\\d{10}");
//        Matcher m2 = p2.matcher(number);
//
//        return m1.find()|m2.find();
//    }


}
