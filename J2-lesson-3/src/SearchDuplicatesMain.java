import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;

public class SearchDuplicatesMain {
    public static void main(String[] args) {
        SearchDuplicatesMain sd = new SearchDuplicatesMain();

        String[] list = new String[20];
        for(int i=0;i<list.length;i++){
            list[i] = sd.randomAlphaNumeric(4+(int) (Math.random() * 10));
        }
        //Искуственно создадим повторы
        list[5]= list[0];
        list[7]= list[0];
        list[15]= list[9];
        list[13]= list[9];
        list[8]= list[19];
        //Распечатаем
        System.out.println("Исходный массив, размера: "+list.length);
        for (String s:list){
            System.out.println(s);
        }

        HashMap<String,Integer> uniqueWordsList = new HashMap<>();
        for (int i = 0; i < list.length; i++) {
            Integer res = uniqueWordsList.get(list[i]);
            uniqueWordsList.put(list[i], res == null ? 1 : res + 1);
        }
        System.out.println("Массив без дубликатов, размера: "+uniqueWordsList.size());
        System.out.println(uniqueWordsList);

    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }


}
