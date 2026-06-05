package PractisesExercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Javastreams {

   // @Test
    public void startsWithA(){

        ArrayList<String> a =new ArrayList<String>();
        a.add("Alex");
        a.add("Ron");
        a.add("Alekay");

        int count=0;
        for(int i=0; i<a.size(); i++){

            String actualName= a.get(i);
            if(actualName.startsWith("A")){
                count++;
            }
        }
        System.out.println(count);

        /*
        The above code uses loop to find out the names that starts with character A. its time complexity is also high
        since it utilizes loops. We can use streams which executes parallely and also provides faster execution complexity.
        Using Streams
        1. Convert a  collection to stream
        2.Use intermediate operation to the stream (filter() etc)
        3.Use conditions (lambda expressions ) . lambda expressions use -> . where the left side expression express single
        element (like element(i) in for loop and right side expression express condition
        4.Use terminal operations to get the result.
         */


        }

      //  @Test
    public void streamsFilter() {

        ArrayList<String> a1 =new ArrayList<String>();
        a1.add("Alex");
        a1.add("Ron");
            ArrayList<String> a =new ArrayList<String>();
            a.add("Alex");
            a.add("Ron");
            a.add("Alekay");
        a1.add("Alekay");

        long count= a1.stream().filter(s -> s.startsWith("A")).count();
        System.out.println(count);

        //prints all names from the list
        a1.stream().filter(s ->s.length()>=3).forEach(s->System.out.println(s));

        //print limited names from the list
            a1.stream().filter(s ->s.length()>=3).limit(1).forEach(s-> System.out.println(s));
    }

   // @Test
    public void streamsMap(){

        //another way to declare stream without converting list to streams
        //Stream.of("Karthika", "sushil", "sugirtha", "saraswathy").filter(s -> s.startsWith("s")).map(s-> s.toUpperCase()).forEach(s-> System.out.println(s));
        List<String> a2 = Arrays.asList("Karthika", "sushil", "sugirtha", "saraswathy");
        /* converts the names which starts with s to uppercase */
        a2.stream().filter(s -> s.startsWith("s")).map(s-> s.toUpperCase()).forEach(s-> System.out.println(s));
        //sorts the name that starts with s
        a2.stream().filter(s -> s.startsWith("s")).sorted().forEach(s-> System.out.println(s));

        List<String> a3 = Arrays.asList("Arun", "Ajith", "Sri", "Aadhya");
        Stream<String> famliyNames= Stream.concat(a2.stream(),a3.stream());
        //famliyNames.sorted().forEach  (s->System.out.println(s));
       famliyNames.sorted(Comparator.reverseOrder()).forEach(s->System.out.println(s));

       boolean flag= famliyNames.anyMatch(s->s.equalsIgnoreCase("Arun"));
       System.out.println(flag);


    }

   // @Test
    public void collectStreams(){
       List<Integer> values= Arrays.asList(7,3,9,2,4,0,4);
      // values.stream().filter(s-> s.equals(4)).forEach(s->System.out.println(s));

        //find unique numbers from an array
        //sort the array
        //print the element from 3rd index
        List<Integer> values1= Arrays.asList(7,3,9,2,4,0,4);
        values1.stream().distinct().forEach(s->System.out.println(s));
        List<Integer> ls= values1.stream().distinct().sorted().collect(Collectors.toList());
        System.out.println("third index in the array is " +ls.get(2));




    }

    @Test
    public void streamsInSelenium(){


        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.findElement(By.xpath("//tr/th[1]")).click();
        //Instead of using loops we wrote streams for getting text from web table columns, sort it, compare to original list
        List <WebElement> elements= driver.findElements(By.xpath("//tr/td[1]"));
        List<String> originalList= elements.stream().map(s->s.getText()).collect(Collectors.toList());
        List<String> sortedList= originalList.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedList, originalList);
        List<String> price;
        List<String> discount;

        //find the price pf rice from the webtable if not found in first resuults click next in the pagination and find the price of rice.
        //used do while because we need to search for rice price first and then move to next page if not found
        do {
    //find the price of Rice

            List <WebElement> rows= driver.findElements(By.xpath("//tr/td[1]"));
     price = rows.stream().filter(s -> s.getText().contains("Rice")).map(s -> priceOfVeggies(s)).collect(Collectors.toList());
    price.forEach(s -> System.out.println("Price value of rice is" +s));
      discount=rows.stream().filter(s->s.getText().contains("Rice")).map(s->getDiscountPrice(s)).collect(Collectors.toList());
discount.forEach(s->System.out.println("Discount value for rice is"+s));
    if (price.size() < 1) {
        driver.findElement(By.cssSelector("a[aria-label='Next']")).click();
    }
}while(price.size() < 1);


    }
    private static String priceOfVeggies(WebElement s){
      String priceValue  =s.findElement(By.xpath("//tr/td[1]/following-sibling::td[1]")).getText();
      return priceValue;
    }

    private static String getDiscountPrice(WebElement s){
       String discountValue= s.findElement(By.xpath("//tr/td[2]/following-sibling::td")).getText();
        return discountValue;
    }

}



