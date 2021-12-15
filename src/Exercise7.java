import java.util.Scanner;

public class Exercise7 {
    public class exercise107 {

        public static String subString (String str, int pos1, int pos2) {
            String strOut = "";
            for (int i = pos1; i < pos2; i++)
                strOut += str.charAt(i);

            return strOut;
        }


        public static int functionIndexOf(String str, String substr) {
            int i, j, k;
            int len1 = str.length(), len2 = substr.length();

            for (i = 0; i < len1 - len2 + 1; i++) {
                k = 0;
                for (j = 0; j < len2; j++) {
                    if (str.charAt(i + j) == substr.charAt(j))
                        k++;
                }

                if (k == len2) return i;
            }

            return -1;
        }

        public static void main(String[] args) {
            Scanner scn = new Scanner(System.in);

            System.out.println("Enter the quadratic equation:");

            String str = scn.nextLine();
            String strNext;

            double a, b, c;
            char subscribe = '+';

            int position1, position2;


            position1 =  functionIndexOf(str, "x^2");
            if (position1 == -1) {

                System.out.println("Error in the equation - coefficient a");
                scn.close();
                return;
            }

            if (position1 == 0) a = 1;
            else if (position1 == 1 && str.charAt(0) == '+') a = 1;
            else if (position1 == 1 && str.charAt(0) == '-') a = -1;
            else a = Double.parseDouble(subString(str, 0, position1));


            strNext = subString(str, position1 + 3, str.length());

            position2 =  functionIndexOf(strNext, "x");


            position1 =  functionIndexOf(strNext, "+");

            if (position1 == -1 ||position1 > position2) {
                position1 =  functionIndexOf(strNext, "-");
                subscribe= '-';
            }

            if (position1 == -1) {

                System.out.println("Equation error - coefficient b");
                scn.close();
                return;
            }

            if (position2 == -1 || position2 < position1) {

                System.out.println("Equation error - coefficient b");
                scn.close();
                return;
            }

            if (strNext.charAt(position2 - 1) == ' ' || strNext.charAt(position2 - 1) == subscribe) {
                if (subscribe == '+') b = 1;
                else b = -1;
            } else {
                b = Double.parseDouble(subString(strNext, position1 + 1, position2));
                if (subscribe == '-') b = -b;
            }


            strNext = subString(strNext, position2 + 1, strNext.length());
            subscribe = '+';
            position1 =  functionIndexOf(strNext, "+");

            if (position1 == -1) {
                position1 =  functionIndexOf(strNext, "-");
                subscribe = '-';
            }

            if (position1 == -1) {

                System.out.println("Error in the equation - coefficient c");
                scn.close();
                return;
            }

            c = Double.parseDouble(subString(strNext, position1 + 1, strNext.length()));
            if (subscribe == '-') c = -c;

            System.out.println("a = " + a + "\nb = " + b + "\nc = " + c);


            double d = b * b - 4 * a * c;
            if (d > 0) {
                double x1 = (-b - Math.sqrt(d)) / (2 * a);
                double x2 = (-b + Math.sqrt(d)) / (2 * a);
                System.out.println("The equation has two roots : x1= " + x1 + ", x2=" + x2);
            } else if (d == 0) {
                double x;
                x = -b / (2 * a);
                System.out.println("The equation has one root: x = " + x);
            } else {
                System.out.println("The equation has no roots");
            }

            scn.close();

        }

    }
}