import java.util.Scanner;

public class Exercise8 {

    public static String SubStr(String str, int position1, int position2)
    {
        String strOut="";
        for(int i= position1;i< position2;i++)
            strOut+=str.charAt(i);

        return strOut;
    }

    public static boolean functionCompareTo(String string1, String string2)
    {
        if(string1.length()!=string2.length())return false;

        for(int i=0;i<string1.length();i++)
            if(string1.charAt(i)!=string2.charAt(i))
                return false;

        return true;

    }

    public static String[] returnSubstringOfArray()
    {
        String[]arrayString = new String[1000];
        Scanner scn = new Scanner(System.in);
        String string="";
        int i = 0;
        while(true)
        {
            System.out.print("Enter the string: ");
            string = scn.nextLine();
            if(string.length()<4)break;
            arrayString[i] = string;
            i++;
        }


        String[]masStr = new String[i];

        for(i=0;i<masStr.length;i++)
            masStr[i] = arrayString[i];


        return masStr;
    }


    public static String[] checkUserArray(String str)
    {
        //Java
        int i,j,k=0;
        int numberOfElements = str.length()*(str.length()-1)/2;
        String []arraySubstring = new String[numberOfElements];

        for(i=2;i<=str.length();i++)
            for(j=0;j<=str.length()-i;j++)
            {
                arraySubstring[k] = SubStr(str,j,j+i);
                k++;
            }

        return arraySubstring;
    }


    public static String mostPopularSubstring(String[] arrayOfString)
    {
        int i, j;
        int amount = 0,k = 0,indexOfPopularSubstring = -1,maximalAmount=-1;



        for(i=0;i<arrayOfString.length;i++)
            amount+= arrayOfString[i].length()*(arrayOfString[i].length()-1)/2;


        String []arrayOfCommonString = new String[amount];


        for(i=0;i<arrayOfString.length;i++)
        {
            String []substringArray = checkUserArray(arrayOfString[i]);
            for(j=0;j<substringArray.length;j++)
            {
                arrayOfCommonString[k] = substringArray[j];
                k++;
            }
        }


        for(i=0;i<arrayOfCommonString.length-1;i++)
        {
            k = 0;
            for(j=i+1;j<arrayOfCommonString.length;j++)
            {
                if(functionCompareTo(arrayOfCommonString[i],arrayOfCommonString[j]))
                    k++;
            }

            if(k>maximalAmount)
            {
                maximalAmount = k;
                indexOfPopularSubstring = i;
            }

        }


        return arrayOfCommonString[indexOfPopularSubstring];
    }

    public static void main(String[] args)
    {

        String[] stringArray = returnSubstringOfArray();

        if(stringArray.length>0)
        {
            String str = mostPopularSubstring(stringArray);
            System.out.println(str);
        }

    }

}

