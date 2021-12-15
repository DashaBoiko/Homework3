import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

public class Exercise6 {

    public static final int NONE = 0;
    public static final int DELIMITER = 1;
    public static final int VARIABLE = 2;
    public static final int NUMBER = 3;


    public static final int SYNTAXERROR = 0;
    public static final int UNBALPARENS = 1;
    public static final int NOEXP = 2;
    public static final int DIVBYZERO = 3;


    public static String EOF = "\0";

    public static String exp; //
    public static int explds; //
    public static String token; //
    public static int tokType; //




    public static void getToken(){
        tokType = NONE;
        token = "";


        if(explds == exp.length()){
            token = EOF;
            return;
        }

        while(explds < exp.length() && Character.isWhitespace(exp.charAt(explds)))
            ++ explds;

        if(explds == exp.length()){
            token = EOF;
            return;
        }
        if(isDelim(exp.charAt(explds))){
            token += exp.charAt(explds);
            explds++;
            tokType = DELIMITER;
        }
        else if(Character.isLetter(exp.charAt(explds))){
            while(!isDelim(exp.charAt(explds))){
                token += exp.charAt(explds);
                explds++;
                if(explds >= exp.length())
                    break;
            }
            tokType = VARIABLE;
        }
        else if (Character.isDigit(exp.charAt(explds))){
            while(!isDelim(exp.charAt(explds))){
                token += exp.charAt(explds);
                explds++;
                if(explds >= exp.length())
                    break;
            }
            tokType = NUMBER;
        }
        else {
            token = EOF;
            return;
        }
    }


    public static int IndexOf(String str, char charAt)
    {
        int i,idx = -1;
        for(i = 0;i<str.length();i++)
        {

            if(str.charAt(i)==charAt)
            {
                idx = i;
                break;
            }
        }

        return idx;
    }


    public static boolean isDelim(char charAt) {
        if(IndexOf(" +-/*%^=()", charAt)!=-1)
            return true;
        return false;
    }


    public static double evaluate(String expstr) {

        double result;

        exp = expstr;
        explds = 0;
        getToken();

        if(token.equals(EOF))
        {
            handleErr(NOEXP);
            return -1;
        }


        result = evalExp2();

        if(!token.equals(EOF))
        {
            handleErr(SYNTAXERROR);
            return -1;
        }


        return result;
    }


    public static double evalExp2() {

        char op;
        double result;
        double partialResult;
        result = evalExp3();
        while((op = token.charAt(0)) == '+' ||
                op == '-'){
            getToken();
            partialResult = evalExp3();
            switch(op){
                case '-':
                    result -= partialResult;
                    break;
                case '+':
                    result += partialResult;
                    break;
            }
        }
        return result;
    }


    public static double evalExp3() {

        char op;
        double result;
        double partialResult;

        result = evalExp4();
        while((op = token.charAt(0)) == '*' ||
                op == '/' || op == '%'){
            getToken();
            partialResult = evalExp4();
            switch(op){
                case '*':
                    result *= partialResult;
                    break;
                case '/':
                    if(partialResult == 0.0)
                    {
                        handleErr(DIVBYZERO);
                        return -1;
                    }
                    result /= partialResult;
                    break;
                case '%':
                    if(partialResult == 0.0)
                    {
                        handleErr(DIVBYZERO);
                        return -1;
                    }
                    result %= partialResult;
                    break;
            }
        }
        return result;
    }


    public static double evalExp4() {

        double result;
        double partialResult;
        double ex;
        int t;
        result = evalExp5();
        if(token.equals("^")){
            getToken();
            partialResult = evalExp4();
            ex = result;
            if(partialResult == 0.0){
                result = 1.0;
            }else
                for(t = (int)partialResult - 1; t > 0; t--)
                    result *= ex;
        }
        return result;
    }


    public static double evalExp5() {
        double result;

        String op;
        op = " ";

        if((tokType == DELIMITER) && token.equals("+") ||
                token.equals("-")){
            op = token;
            getToken();
        }
        result = evalExp6();
        if(op.equals("-"))
            result = -result;
        return result;
    }


    public static double evalExp6() {
        double result;

        if(token.equals("(")){
            getToken();
            result = evalExp2();
            if(!token.equals(")"))
            {
                handleErr(UNBALPARENS);
                return -1;
            }
            getToken();
        }
        else
            result = atom();
        return result;
    }


    public static double atom() {

        double result = 0.0;

        switch(tokType){
            case NUMBER:
                try{
                    result = Double.parseDouble(token);
                }
                catch(NumberFormatException exc){
                    handleErr(SYNTAXERROR);
                    return -1;
                }
                getToken();

                break;
            default:
                handleErr(SYNTAXERROR);
                return -1;
        }
        return result;
    }


    public static void handleErr(int nOEXP2) {

        String[] err = {
                "Syntax error",
                "Unbalanced Parentheses",
                "No Expression Present",
                "Division by zero"
        };
        System.out.println(err[nOEXP2]);
    }

    public static void main(String[] args)
    {

        Scanner scn = new Scanner(System.in);

        //Parser myParser = new Parser();

        while(true)
        {
            System.out.print("Enter an expression for calculation\n-> ");
            String str = scn.nextLine();
            if(str.equals(""))
                break;
            double result = evaluate(str);

            DecimalFormatSymbols s = new DecimalFormatSymbols();
            s.setDecimalSeparator('.');
            DecimalFormat f = new DecimalFormat("#,###.00", s);

            System.out.printf("%s = %s%n", str, f.format(result));
        }

        scn.close();
    }

}