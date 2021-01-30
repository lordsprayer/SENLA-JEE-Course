/*Написать программу:1)выводящую на экран случайно сгенерированное трёхзначное
 натуральное число и его наибольшую цифру*/

import java.util.Random;
public class Main {

    public static void main(String[] args) {
        int min = 100;
        int max = 999;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        int first = i/100;
        int third = i%10;
        int second = (i%100 - third)/10;
        int maxvalue;
        if (first >= second)
        {
            if (first >= third)
                maxvalue = first;
            else
                maxvalue = third;
        }
        else
            if (second >= third)
                maxvalue = second;
            else
                maxvalue = third;
        //System.out.printf("a=%d, b=%d, c=%d ",first, second, third);
        System.out.println("Random three-digit number = " + i + ", max value = " + maxvalue);
    }
}
