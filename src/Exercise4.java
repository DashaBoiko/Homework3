import java.util.Scanner;

public class Exercise4 {
    public static boolean ArrayFullOrNot(int [] array)
    {
        int i,j,variableToReplace,amount = 0;
        int []array1 = new int [array.length];

        for(i=0;i<array.length;i++)
            array1[i] = array[i];


        for(i=0;i<array1.length-1;i++)
            for(j=i+1;j<array1.length;j++)
            {
                if(array1[i]>array1[j])
                {
                    variableToReplace = array1[i];
                    array1[i] = array1[j];
                    array1[j] = variableToReplace;
                }
            }

        for(i=0;i<array1.length-1;i++)
        {
            if(array1[i+1]-array1[i]==1)
                amount++;
        }

        if(amount==array1.length-1) {
            return true;
        }
        else return false;

    }

    public static boolean ArrayFullOrNotIfGoDown(int[] array)
    {

        int i,amount = 0;


        for(i=0;i<array.length-1;i++)
        {
            if(array[i]-array[i+1]==1)
                amount++;
        }

        if(amount==array.length-1) {
            return true;
        }
        else return false;

    }


    public static void main(String[] args)
    {
        int i, numberOfElements;
        Scanner scn = new Scanner(System.in);
        System.out.print("Write the number of elements:");
        numberOfElements = scn.nextInt();
        int[] array = new int[numberOfElements];
        for (i = 0; i < array.length; i++) {
            System.out.print("Write the  "  + i + "  element ");
            array[i] = scn.nextInt();
        }

        if(ArrayFullOrNot(array))
        {
            System.out.println("Array is full");
        }
        else
        {
            System.out.println("Array is not full");
        }

        if(ArrayFullOrNotIfGoDown(array))
        {
            System.out.println("The array is complete, taking into account decreasing");
        }
        else
        {
            System.out.println("Array is incomplete considering decreasing");
        }


        scn.close();
    }

}

