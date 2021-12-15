import java.util.Scanner;

public class Exercise3 {
    public static int [] FactorsOfNumber(int x)
    {
        int []array = new int[70];

        int y = x;
        int n = 2;
        int i, k = 0;

        while(y>1 && n<x)
        {

            while(y%n==0)
            {
                array[k] = n;
                k++;
                y/=n;
            }
            n++;
        }

        int []multipliersArray = new int[k];
        for(i=0;i<multipliersArray.length;i++)
            multipliersArray[i] = array[i];


        return multipliersArray;
    }

    public static void main(String[] args)
    {


        int i,integer;
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter an integer : >");
        integer = scn.nextInt();
        int[] array = FactorsOfNumber(integer);

        if(array.length==0)
        {
            System.out.println("The number is prime");
        }
        else
        {
            System.out.println("Multiplier output");
            for(i=0;i<array.length;i++)
            {
                System.out.print(array[i]+ "  ");
            }

        }

        scn.close();

    }

}


