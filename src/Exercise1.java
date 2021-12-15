import java.util.Scanner;

public class Exercise1 {
    public static boolean InterchangeNumberOrNot(int x)
    {
        int number,amount=0,numberAmount=0,oldNumber=-1;
        while(x!=0)
        {
            number = x%10;
            if(oldNumber>-1)
            {
                if((oldNumber%2==0&&number%2!=0)||(oldNumber%2!=0&&number%2==0))
                {
                    numberAmount++;
                }
            }
            x/=10;
            amount++;
            oldNumber = number;
        }

        if (numberAmount==amount-1) return true;
        else return false;
    }

    public static int SumOfInterchangeNumber(int [] array )
    {
        int i,x,c,index=-1;
        int minimalSum = 1000;
        int sum;

        for(i=0;i<array.length;i++)
        {
            if (InterchangeNumberOrNot(array[i]))
            {
                x = array[i];
                sum = 0;

                while(x!=0)
                {
                    c = x%10;
                    x/=10;
                    sum+=c;
                }
                if(sum< minimalSum )
                {
                    minimalSum  = sum;
                    index = i;
                }
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

        //Выделение динамической памяти массиву
        int []array1 = new int[numberOfElements];
        for(i=0;i<array1.length;i++)
        {
            System.out.print("Enter" + i + " element of the array:");
            array1[i] = scn.nextInt();
        }
        System.out.println(" \nArray output:");

        for(i=0;i<array1.length;i++)
        {
            System.out.print(array1[i]+ "  ");
        }


        int index = SumOfInterchangeNumber(array1);
        System.out.println(" \n index = "+index);

        scn.close();



    }

}

