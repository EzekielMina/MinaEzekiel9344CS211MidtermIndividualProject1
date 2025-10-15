/*
Name of Student: Mina, Ezekiel Cole U.
Due Date: 15 October 2025
*/

import java.util.*;

public class PolynomialArithmetic {
    Scanner keyboard = new Scanner(System.in);

    /**
     * Start the program
     *
     * @throws Exception
     */
    public void run() throws Exception {
        byte choice = 0;
        while ( choice != 6 ) {
            showMenu();
            choice = readChoice((byte) 1, (byte) 6);

            switch (choice){
                case 1:
                    evaluatePolynomial();
                    break;
                case 2:
                    addPolynomials();
                    break;
                case 3:
                    subtractPolynomials();
                    break;
                case 4:
                    multiplyPolynomials();
                    break;
                case 5:
                    dividePolynomials();
                    break;
                case 6:
                    System.out.println("Thank you for using this program.");
            } // end of switch
        } // end of while
    } // end of run


    /**
     * Ask the user to pick a number between low and high, then return it.
     *
     * @param low the smallest allowed choice
     * @param high the largest allowed choice
     * @return the chosen number as a byte
     * @throws Exception
     */
    private byte readChoice(byte low, byte high) throws Exception{
        byte choice = 0;
        System.out.print("Enter your choice<"+ low + "... " + high + ">: ");
        choice = (byte) readInteger(low, high);
        return choice;
    }

    /**
     * Displays sthe Menu of the Program
     */
    public void showMenu() {
        System.out.println("-----------------------MENU--------------------------");
        System.out.println("1. Evaluate a polynomial");
        System.out.println("2. Add two polynomials");
        System.out.println("3. Subtract a polynomial from another polynomial");
        System.out.println("4. Multiply two polynomials");
        System.out.println("5. Divide a polynomial by another polynomial");
        System.out.println("6. Quit");
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Read a polynomial from the user, ask for a value,
     * evaluate the polynomial at that value, and print the result.
     *
     * @throws Exception if reading or evaluating fails
     */
    public void evaluatePolynomial() throws Exception{
        System.out.println("You want to evaluate a polynomial.");
        Polynomial p = readPolynomial();
        System.out.println("The polynomial entered is " + p.toString());

        System.out.print("What is the value to be assigned to variable of the polynomial? ");
        double value= readDouble();

        System.out.println("The polynomial evaluates to : "+ p.evaluate(value));
        System.out.println("Press enter to continue.....");

        keyboard.nextLine();
    }

    /**
     * Read and return an integer from the user between low and high.
     * Keeps asking until the user gives a valid number.
     *
     * @param low the minimum number allowed
     * @param high the maximum number allowed
     * @return the integer entered by the user
     */
    private int readInteger(int low, int high){
        boolean validInput = false;
        int value=0;

        while (!validInput){
            try{
                value = Integer.parseInt(keyboard.nextLine());
                if ( value < low){
                    System.out.print("The number must not be lower than "+ low + ". ");
                }
                else if ( value > high){
                    System.out.print("The number must not be greater than "+ high +". ");
                } else {
                    validInput = true;
                }
            } catch (Exception x){
                System.out.println("You have to enter an integer from " + low + " to " + high + ".");
            }
        }
        return value;
    }

    /**
     * Read and return a number with decimal from the user.
     * Keeps asking until the user gives a valid number.
     *
     * @return the number entered by the user
     */
    private double readDouble(){
        boolean validInput = false;
        double value=0;

        while (!validInput){
            try{
                value = Double.parseDouble(keyboard.nextLine());
                validInput = true;
            } catch (Exception x){
                System.out.println("You have to enter a number.");
            }
        }
        return value;
    }

    /**
     * Ask the user for the variable letter, degree, and coefficients,
     * then build and return a Polynomial object.
     *
     * @return a polynomial built from the user's answers
     * @throws Exception
     */
    public Polynomial readPolynomial() throws Exception{
        Polynomial p = new Polynomial();
        int degree=-1;
        boolean validDegree = false;
        char literalCoefficient = 'x';

        System.out.println("The polynomial should involve one variable/literal only.");

        do {
            System.out.print("What is the literal coefficient of the polynomial in one variable? ");
            literalCoefficient = keyboard.nextLine().charAt(0);
        } while (!Character.isAlphabetic(literalCoefficient));

        do {
            System.out.print("What is the degree of the polynomial? ");
            degree = readInteger(Integer.MIN_VALUE,Integer.MAX_VALUE);
            validDegree = true;
        } while (!validDegree);

        for (int x=degree; x>=0; x=x-1){
            Term term = readTerm(literalCoefficient, x);
            p.addTerm(term);
        }

        return p;
    }

    /**
     * Ask the user for the coefficient of a single term and return it as a Term object.
     *
     * @param literal the letter used for the variable
     * @param degree the degree of the term
     * @return a Term object with the number the user entered
     * @throws Exception
     */
    public Term readTerm(char literal, int degree)throws Exception{
        double nCoeff = 0;
        System.out.print("Enter the numerical coefficient of the term with degree " + degree +": ");
        nCoeff = readDouble();
        Term term = new Term(nCoeff, literal, degree);
        return term;
    }

    /**
     * Let the user enter two polynomials and print their sum.
     *
     * @throws Exception
     */
    public void addPolynomials() throws Exception {
        System.out.println("You want to add two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();

        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();

        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());

        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Sum of the polynomials : " + p1.add(p2));
        } else {
            System.out.println("The two polynomials cannot be added because they have different literals.");
        }

        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Let the user enter two polynomials and print their difference (first - second).
     *
     * @throws Exception
     */
    public void subtractPolynomials() throws Exception {
        System.out.println("You want to subtract two polynomials.");
        System.out.println("Enter the minuend polynomial (the polynomial to be subtracted from).");
        Polynomial p1 = readPolynomial();

        System.out.println("Enter the subtrahend polynomial (the polynomial to subtract).");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();

        System.out.println("Minuend polynomial : " + p1.toString());
        System.out.println("Subtrahend polynomial : " + p2.toString());

        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Difference of the polynomials : " + p1.subtract(p2));
        } else {
            System.out.println("The two polynomials cannot be subtracted because they have different literals.");
        }

        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Let the user enter two polynomials and print their product.
     *
     * @throws Exception
     */
    public void multiplyPolynomials() throws Exception {
        System.out.println("You want to multiply two polynomials.");
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();

        System.out.println("Enter the second polynomial.");
        System.out.println("Note that the second variable should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();

        System.out.println("First polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());

        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println("Product of the polynomials : " + p1.multiply(p2));
        } else {
            System.out.println("The two polynomials cannot be multiplied because they have different literals.");
        }

        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Let the user enter a dividend and a divisor, then print the quotient and remainder.
     * If the divisor is zero or the variables differ, show an error message.
     *
     * @throws Exception
     */
    public void dividePolynomials() throws Exception {
        System.out.println("You want to divide two polynomials.");
        System.out.println("Enter the dividend polynomial (the polynomial to be divided).");
        Polynomial p1 = readPolynomial();

        System.out.println("Enter the divisor polynomial (the polynomial to divide by).");
        System.out.println("Note that the divisor variable should have the same variable/literal as the dividend.");
        Polynomial p2 = readPolynomial();

        System.out.println("Dividend polynomial : " + p1.toString());
        System.out.println("Divisor polynomial : " + p2.toString());

        // Check divisor is not zero polynomial
        boolean divisorIsZero = false;
        if (p2.getTerms() == null || p2.getTerms().size() == 0) divisorIsZero = true;
        else if (p2.getTerms().size() == 1 && p2.getTerms().get(0).getCoefficient() == 0) divisorIsZero = true;

        if (!divisorIsZero && p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            Quotient q = p1.divide(p2);
            System.out.println("Quotient: " + q.getQuotientP().toString());
            System.out.println("Remainder: " + q.getRemainderP().toString());
        } else if (divisorIsZero) {
            System.out.println("Division by zero polynomial is undefined. Please enter a non-zero divisor.");
        } else {
            System.out.println("The two polynomials cannot be divided because they have different literals.");
        }

        System.out.println("Press enter to continue.....");
        keyboard.nextLine();
    }

    /**
     * Start the program.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        PolynomialArithmetic program;

        try {
            program = new PolynomialArithmetic();
            program.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // end of main
} // end of PolynomialArithmetic class
