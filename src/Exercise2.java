import java.util.Random;
import java.util.Scanner;

public class Exercise2 {

    public static int SumOfNumbers(int x)
    {
        int sum = 0;
        while(x!=0)
        {
            sum+=(x%10);
            x/=10;
        }

        return sum;
    }
    public static int TheHighestProximityOfArray(int[]arr1,int []arr2)
    {
        int i,j,index=-1;
        int maximumAmount = 0;
        int amount;

        for(i=0;i<arr1.length;i++)
        {
            amount = 0;
            for(j=0;j<arr2.length;j++)
            {
                if(SumOfNumbers(arr1[i])==SumOfNumbers(arr1[j]))
                    amount++;
            }

            if(amount>maximumAmount)
            {
               maximumAmount = amount;
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args)
    {
        int i,numberOfElements;
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array:");
        numberOfElements = scn.nextInt();


        int []array1 = new int[numberOfElements];
        int []array2 = new int[numberOfElements];


        Random rnd = new Random();
        for(i=0;i<array1.length;i++)
        {
            array1[i] = rnd.nextInt(90)+10;
            array2[i] = rnd.nextInt(90)+10;
        }

        System.out.println("\nArray output 1 :");

        //Вывод массива
        for(i=0;i<array1.length;i++)
        {
            System.out.print(array1[i]+ "  ");
        }

        System.out.println("\nArray output 2 :");

        for(i=0;i<array2.length;i++)
        {
            System.out.print(array2[i]+ "  ");
        }

        int index = TheHighestProximityOfArray(array1,array2);
        System.out.println("\nindex = "+ index);

        scn.close();

    }

}

