
import java.util.ArrayList;
import java.util.List;


public class Main {

 public  static void main(String [ ] args)
    {
    // List<String> nameList = new ArrayList<> ();
    // nameList.add("John");
    // String [ ] names = {"Ann", "Bob", "Carol"};
    //     // Add to arrayList
    //     nameList.addAll(Arrays.asList(names));
    //     // Display name list
    //     // for (int k = 0; k < nameList.size(); k++)
    //     // System.out.println(nameList.get(k));    
    //     System.out.println("Name List: " + nameList);     

    List<String> list = new ArrayList<>();
    list.add("Apple");
    list.add("Banana");
    list.add("Apple");
    list.remove(2); 
    list.add("Banana");
    System.out.println(list); // [Apple, Banana, Apple]
    }    
}
