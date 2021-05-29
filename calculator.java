import java.util.*;
public class calculator
{//start of class
    int i,j,k,c;
    String s; // for storing the arithmatic operation
    final String op="()^/*+-%";
    final String trigo[]= {"sin", "cos", "tan", "sec", "cosec", "cot","log()"}; 
    final String no="0123456789";
    /** accept() --> check() (if valid) --> adjust() --> brackets() --> bedmas() --> calculate(), after removing and finishing all brackets and operations within it, bedmas() is called      */
                                                    
    public void accept() 
    {
        Scanner sc = new Scanner(System.in);  boolean valid=false;  String choice;
        char ch1;
        System.out.println("Do not press 'spacebar' or any special characters that is not considered as operators");
        System.out.println("Do not enter any alphabet except for 'f' to exit and 'c' to clear:"); System.out.println();
        do // loop continue until the user exits...
        { 
            System.out.println("Enter your arithmatic operation or press f to EXIT:");
            s=sc.next();
            if(s.equalsIgnoreCase("f")==true) // condition for exiting the application
            { System.out.println("I am returning to main(), fuck off!"); return;} //returns to main 
               
            else // if an arithmatic operation is entered 
            {   
                System.out.println("Going to Check()");
                s=check(); // to check the validity of arithmatic statement...
                if(s.equalsIgnoreCase("f")==true)
                    return; //returns to main()
                if(s.equalsIgnoreCase("invalid")==true)
                {
                    //System.out.println("Syntax Error");  System.out.println("Following errors your might have made:"+"\n1. any unnecessary letters"+"\n2. You might have entered a space"+"\n3. A special symbol"+"\n4. Successive occurence of any two operators"+"\n5. MATH ERROR"+"\n6. Unnecessary bracekts");
                    System.out.println("Invalid Input");
                }
                do
                {
                    System.out.println("Do you want to continue? --> Y/N");
                    ch1=sc.next().charAt(0);
                    if("YyNn".indexOf(ch1)>-1)
                    {   valid=true;}
                    else
                    {
                        valid=false;
                        System.out.println("Invalid Input");
                    }
                }while(valid==false);
                if(ch1=='y' || ch1=='Y') 
                    continue;
            }
            System.out.println("I am here");
        }while("nN".indexOf(ch1)==-1); // if ch1=='N' then user exits the application
        return;  //returns to main()
    }
    
    public String check() //checking the validity of arithmatic statement --> returns "invalid" or "f" or "c"
    {
        System.out.println("in check()");
        /** In the following code, there are several CASES listed
         *  for differenent VALIDIFICATION of given
            arithmatic statement...                      */
        
        char ch;String m=s;
        /** UPDATE FOR CASE 1 (ii)  An arithmatic statement shall have as its opening character as : an operand or bracket or + or -  
         *                                                            and its closing character as : an operand or bracket or %           
         */
        
        /**  CASE 1  (i)
             Nested if conditions:
                1. to check the consecutive occurence of operators
                   Example: ( 16546**5-5416*4..6-4//6/4/* )                   
                2. to check if '0' comes after '/'               */
        System.out.println("CASE 1");
        for(i=0;i<s.length();i++)  // for loop to check if any unnecessary letter(s) is used...
        {   
            ch=s.charAt(i);
            if(i<(s.length()-1)) // beacuse we are using i+2 and i+1 and to avoid *out of bounds error*
            {
                String t1=s.substring(i,i+2);
                if(s.charAt(i)=='+' || s.charAt(i)=='-' || s.charAt(i)=='*' || s.charAt(i)=='/')
                {   
                    if(s.charAt(i+1)=='*' || s.charAt(i+1)=='/' || s.charAt(i+1)==')')
                    {   System.out.println("Invalid: 1"); return "invalid";}
                }
                if(t1.equalsIgnoreCase("/0")==true)
                { System.out.println("Cannot be divided by Zero"); return "invalid";}
            }
            if(i<(s.length()-2)) // beacuse we are using i+3 and to avoid *Strig index out of bounds error*
            {  
                String t2=s.substring(i,i+3);
                if(t2.equalsIgnoreCase("/+0")==true || t2.equalsIgnoreCase("/-0")==true)
                {
                    System.out.println("Cannot be divided by Zero");
                    return "invalid";
                }
            }
            
            if(s.charAt(i)=='%' && (Character.isDigit(s.charAt(i-1))==false || (i<s.length()-1 && "/*+-()".indexOf(s.charAt(i+1))==-1)))
            {
                System.out.println("Invalid: 1"); return "invalid";
            }
            
            if(s.charAt(i)=='.' && s.charAt(i+1)=='.') // checks for consecutive occurence of '.' decimal points
            {
                System.out.println("Invalid: 1");   return "invalid";
            }
            
            if(ch=='f' || ch=='c') // if user (wants to exit) or (clear statement and enter a new one)
            { 
                s=""; s=s+ch; return s;
            }
            
            if(Character.isLetter(ch)==true || ch==' ') // if user enters invalid characters
            {
                s="invalid"; return s;
            }
        }
        System.out.println("CASE 1 (i) VALID");
        /** CASE 1 (ii) 
            Checking if any arithmatic statement ends/starts with an operator which is invalid...
            Example: 5*,  /6*4* , and so on...               */
        
        if("/*%".indexOf(s.charAt(0))>-1 || "/*+-".indexOf(s.charAt(s.length()-1))>-1)
        {
            System.out.println("CASE 1 (ii) not VALID");
            return "invalid";
        }
        System.out.println("CASE 1 (ii) VALID");
        
        /** CASE 2:
         
            for loop to check if the arithmatic statements
            have valid operators and operands. The counter k counts the no. of valid operators and operands.
            If every character in s is valid then k==s.length()                                    */
        System.out.println("CASE 2"); k=0;
        for(i=0;i<s.length();i++) // loop to search for valid operands and operators in String s
        {
            // if-else-if to check if s has only operators and operands
            
            if(Character.isDigit(s.charAt(i))==false && op.indexOf(s.charAt(i))>-1) // condition to check valid operators only
                k++;
                                
            if(no.indexOf(s.charAt(i))>-1 || s.charAt(i)=='.') // condition to check valid operands only
                k++; 
        }
        
        if(k==s.length()) /**if every character in s is either operator or operand, then...                */
        {
            System.out.println("CASE 2 VALID");
            int z,y;
            boolean valid=true; // valid will be == true if all arithmatic statement has valid '()' 
            /** CASE 3:
                
                a while loop to check two things about valid parenthesis: 
                1. '(' comes before ')'
                2. No. of '(' == No. of ')'    
                This two conditions defines valid '()'              */
            System.out.println("CASE 3");
            while(s.indexOf('(')>-1 || s.indexOf(')')>-1) 
            {
                z=s.indexOf(')');
                if((s.indexOf('(')>-1 && z==-1) || (s.indexOf('(')==-1 && z>-1))
                {
                     /** condition for checking if no. of '('== no.of ')'                */
                     System.out.println("Invalid 2 for unequal no. of ( and )"); 
                     valid=false;// valid=false for indicating that no. of ( and ) are not equal...
                     break; 
                }
                if(z==0 || (s.substring(0,z)).indexOf('(')==-1)
                {
                    /**  if there is ')' without an opening/starting '('          */
                    System.out.println("Invalid 1 for ) coming before ( ");
                    valid=false; 
                    break;
                }
                y=(s.substring(0,z)).lastIndexOf('(');
                System.out.println("Chosen brackets: "+s.substring(y,z+1));
                if(y==0) // if arithmatic statement begins with '('
                    s=s.substring(1,z)+s.substring(z+1); //taking off '(' and ')'
                else if(y>0)    
                    s=s.substring(0,y)+s.substring(y+1,z)+s.substring(z+1); //taking off '(' and ')'
                System.out.println("after removing off brackets: "+s);
            }
                       
            if(valid==true) // if all conditions for valid parenthesis are satisfied and/or other parameters are satisfied too
            {   
                System.out.println("CASE 3 VALID"); s=m;
                //System.out.println("all () are valid");
                System.out.println(s);
                if(s.indexOf('(')!=-1 || s.indexOf('%')!=-1)
                    adjust(); //return "";
                s=bedmas(s); // a function for evaluating the operation
                if(s.compareTo("")!=0)   //  "" indicates Successful Evaluation*
                {    System.out.println("Final Answer:  "+s);
                     return "";
                }
                else // indicates Math Error
                {  
                    System.out.println("MATH ERROR"); 
                    return "invalid";
                }
                
            }
            else
            { System.out.println("Case 3 invalid"); return "invalid"; }// returning to accept() for invalid parenthesis...
        }
        else{System.out.println("Case 2 invalid");}
        System.out.println("CASE 3 IGNORED");
        return "invalid"; // returning to accept() for invalid parenthesis...
    }
    
    public void adjust() /**-->  a function to put ' * ' between a '(' or ')' and an operator. 
                                                        or evaluate % into 0.01
                                 Example: ( 6484 * 4654 + (4-68*4) * 456 - 546 + 12 )     */
    {
        /**  NEEDS TO BE OPTIMIZED   */
        if(s.indexOf('(')!=-1)
        {
            String m=s;int l=s.length();i=0;
            while(i<l-1)
            {
                if(Character.isDigit(s.charAt(i))==true && s.charAt(i+1)=='(') // if ( comes after a digit
                {    
                    s=s.substring(0,i+1)+"*"+s.substring(++i);i++;l=s.length();
                    continue; 
                }
                else if(s.charAt(i)==')' && (Character.isDigit(s.charAt(i+1))==true || s.charAt(i+1)=='(')) // 'if figit comes after )' or '( comes after )''
                {
                    s=s.substring(0,i+1)+"*"+s.substring(++i);
                    i++;
                    l=s.length();  continue;
                }
                i++;
            }
        }
        /** For percent % calculation */
        while(s.indexOf('%')!=-1)
        {
            int percent_index = s.indexOf('%');
            int digit_index = percent_index-1;
            String operand="";
            // to extract the number before %
            while(digit_index>=0 && (Character.isDigit(s.charAt(digit_index))==true || s.charAt(digit_index)=='.')) 
            {
                operand = s.charAt(digit_index) + operand; // forming up the operator from backwards
                digit_index = digit_index - 1;      
            }
            String R = Double.toString((Double.parseDouble(operand))*0.01);
            //int c=0;
            /*for(i=0;i<R.length();i++)
            {
                if(R.charAt(i)=='0' && R.charAt(i+1)=='0' && R.charAt(i+2)=='0')
                {
                    c=i;
                    break;
                }
            }
            if(c>3)
                R=R.substring(0,i);*/
            System.out.println("Evaluation of "+operand+"% : "+R);
            System.out.println();
            if(digit_index!=-1 && percent_index!=(s.length()-1))
                s=s.substring(0,digit_index+1)+R+s.substring(percent_index+1); //Readjusting the string after percent evaluation
            else if(digit_index==-1)
                s=R+s.substring(percent_index+1); //Readjusting the string after percent evaluation
            else if(percent_index==(s.length()-1))
                s=s.substring(0,digit_index+1)+R; //Readjusting the string after percent evaluation        
            System.out.println(s);
        }
        System.out.println("String after adjustment:\n "+s);
        if(s.indexOf('(')!=-1)
            brackets();
        return;
    }
    
    public void brackets() /** --> calculate operation within ()  */
    {
        int z,y;
        k=0; String result="";
        System.out.println("in bracekt()");
        while(s.indexOf(')')>-1) // loop to calculate every operation within ()
        {
            z=s.indexOf(')');
            y=(s.substring(0,z)).lastIndexOf('(');
            System.out.println("Chosen brackets: "+s.substring(y,z+1));
            result=bedmas(s.substring(y+1,z));
            System.out.println("Result of chosen inner brackets: "+result);
            if(result.equalsIgnoreCase("")==true)
            { k=1;break;}
            if(y==0) // if arithmatic statement begins with '('
                s=result+s.substring(z+1); //taking off '(' and ')'
            else if(y>0)    
                s=s.substring(0,y)+result+s.substring(z+1); //taking off '(' and ')'
            System.out.println("after removing off brackets: "+s);
        }
        if(k==0)
        { 
            System.out.println("Final statement after removing (): "+s);
            s=bedmas(s);
            if(s.compareTo("")!=0) //  "" indicates *MATH ERROR*
               System.out.println("Final Answer:\n"+s);
            else
               System.out.println("MATH ERROR"); 
            return;
        }
        else
        {
            System.out.println("MATH ERROR");
            return;
        }
    }
    
    public String bedmas(String t) /** -->  returns result of an operation      */
    {
        System.out.println("in edams()");
        String str="^/*+-";    
        
        for(i=0;i<str.length();i++)
        {
            if(t.indexOf(str.charAt(i))>-1)
            {
                t=calculate(t,str.charAt(i)); // --> goes to calculate()
                if(t.compareTo("")==0)   //r=="" indicates *MATH ERROR*
                    return "";
                System.out.println(t);
            }
        }
        return t; // returns to check()/bracket() after result of operation is found
    }
    
    public String calculate(String t, char operator) /** --> calculates an operation and returns it result           */
    {
        System.out.println("in calculate()");
        String R=""; // R stands for result of a single arithmatic operation
        int v=0; int i=0; String operand1, operand2;
        System.out.println("arithmatic statement: "+t);
        while(t.indexOf(operator)>-1)
        {
           if(t.compareTo(R)==0) // when the final result is achieved
           {
               System.out.println("Final result achieved");
               return t; //returns to main()
           }
           operand1=""; operand2="";   int j;
           int p=t.indexOf(operator);
           if(p==0 && operator=='-')
           {
                // if the first operand of any operation has a '-' sign then it's not an operator...
                p=(t.substring(1)).indexOf(operator)+1;  /* ignoring '-' sign of first operand using t.substring(1) and choosing next '-' operator.  
                                                            position p is increased by 1 to get the exact locationo of '-' operator        
                                                            since 0th index was shifted by 1        */
                System.out.println(t);
           }
           j=p-1; // stores the position before operator
           int h=0;
           while(j>=0 && (Character.isDigit(t.charAt(j))==true || t.charAt(j)=='.')) // extracting the operand1 that comes before operator
           {
               operand1=t.charAt(j)+operand1; j--;  
           }
           h=j; 
           if(h>=0 && (t.charAt(h)=='-' || t.charAt(h)=='+'))   /** if operator/sign before operand1 is '-'. It will be added to operand1. */
           {   operand1=t.charAt(h)+operand1; }
           
           j=p+1; // stores the position after operator
           
           if(j<t.length() && (t.charAt(p+1)=='-' || t.charAt(p+1)=='+')) /** if operand2 has '+'/'-' beofre it, it will be added to operand2 */
           {    
               operand2=operand2+t.charAt(p+1); j++;
           }
           
           while(j<t.length() && (Character.isDigit(t.charAt(j))==true || t.charAt(j)=='.')) // extracting the operand2 that comes after operator
           {
               operand2=operand2+t.charAt(j);   j++; h=j;
           }
           /*if(j==t.length())
           {operand2="";}*/
           System.out.println(operand1+operator+operand2);
           if(operand1.compareTo("")!=0 && operand2.compareTo("")!=0) // checking if the two operands are empty are not.
           {
               /**
               following if-else conditions for different operators             */
               if(operator=='^')
               {
                    if(Double.parseDouble(operand1)>0.0)
                        R=Double.toString(Math.pow((Double.parseDouble(operand1)),(Double.parseDouble(operand2))));
                    else
                    {
                        System.out.println("Invalid");
                        return "";
                    }
               }
              
               else if(operator=='/')
               {
                   if(operand2.compareTo("0")==0 || operand2.compareTo("0.0")==0) // checking if the denominator is 0 or not
                   {
                       System.out.println("Cannot be divided by ZERO"); 
                       return "";
                   } 
                   else
                   { 
                       R=Double.toString((Double.parseDouble(operand1))/(Double.parseDouble(operand2)));
                   }                   
               }
               else if(operator=='*')
               {
                   R=Double.toString((Double.parseDouble(operand1))*(Double.parseDouble(operand2)));   
               }
               else if(operator=='+')
               {   
                   System.out.println("Addition");       
                   R=Double.toString((Double.parseDouble(operand1))+(Double.parseDouble(operand2)));
               }
               else if(operator=='-') 
               {   
                   System.out.println("Subtraction");
                   R=Double.toString((Double.parseDouble(operand1))-(Double.parseDouble(operand2)));
               }
                System.out.println("Result: "+R);
               
                /**  Following if-else statements for  
                     inserting the result into the operation
                     Ex: 9 - 11 + 2 * 11 ; 
                     R = 2 * 11; --> 22
                     new String = 9 - 11 + 22    */ 
                  
                if((p-operand1.length())>0 && (operand2.length()+p+1)<t.length()) // addition of R inside the arithmatic statement
                {   System.out.println("1");
                    if(Double.parseDouble(R)>=0.0)
                        t=t.substring(0,p-operand1.length())+'+'+R+t.substring(operand2.length()+p+1);
                    
                    else 
                        t=t.substring(0,p-operand1.length())+R+t.substring(operand2.length()+p+1);
                }
                 /** following else-if statements for two operands operations */
                     
                else if((p-operand1.length())<=0) // front end addition of R to arithmatic statement 't'
                {   System.out.println("2");
                    t=R+t.substring((p+operand2.length()+1));}
                
                else if((operand2.length()+p+1)==t.length() && p-operand1.length()>0)// back end addition of R to arithmatic statement 't'
                {   System.out.println("3");
                    if(Double.parseDouble(R)>=0.0)
                        t=t.substring(0,p-operand1.length())+'+'+R;
                    
                    else
                        {   System.out.println((p)+","+(operand1.length())+"@");
                            t=t.substring(0,p-operand1.length())+R; }
                }
                                   
            }
            else if(operand1.compareTo("")==0)
            {
               System.out.println("Operand 1 absent");
               if(t.lastIndexOf(operator)==0 && operator=='-' )
               {
                  return t;
               }
               t=t.substring(1); // if first operand of an operation start with '+' 
            }
            else
                return t;
            System.out.println("New String: "+t);
        }// end of outermost while loop
        return t;   
    }
    
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome To Calculator");
        int ch;
        calculator ob = new calculator();
        /*System.out.println("  n!  ");*/
        System.out.println("c for clear");
        ob.accept();
        System.out.println("Thank You");
    }
}// end of class
