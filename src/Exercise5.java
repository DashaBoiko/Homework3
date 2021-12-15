import java.util.Scanner;

public class Exercise5 {
    public static String ReplaceFrequency(String str)
    {

        int []array = new int[256];
        int i;
        char c1=' ',c2 = ' ';
        int maximum1=0, maximum2=0;

        for(i=0;i<str.length();i++)
            array[str.charAt(i)]++;


        for(i=0;i<256;i++)
        {
            if(array[i]>0 && i!=' ')
                if(array[i]>maximum1)
                {
                    maximum1 =array[i];
                    c1 = (char)i;
                }
        }

        for(i=0;i<256;i++)
        {
            if(array[i]>0 && i!=' ')
                if(array[i]!=maximum1 && array[i]>maximum2)
                {
                    maximum2 =array[i];
                    c2 = (char)i;
                }
        }

        if(maximum2==0)return str;


        String strOut="";
        for(i=0;i<str.length();i++)
        {
            if(str.charAt(i)==c1)
                strOut+=c2;
            else if(str.charAt(i)==c2)
                strOut+=c1;
            else
                strOut+=str.charAt(i);

        }

        return strOut;
    }

    public static void main(String[] args)
    {

        //take this text and test it

        String str;
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the string");
        str = scn.nextLine();
        System.out.println("Output string: "+ReplaceFrequency(str));

        scn.close();


    }

}
