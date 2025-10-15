/*
Name of Student: Mina, Ezekiel Cole U.
Due Date: 15 October 2025
*/

import java.util.*;


public class PolynomialArithmetic {
    Scanner keyboard = new Scanner(System.in);

    /**
     * Starts the program and displays a menu until the user chooses to quit.
     *
     * @throws Exception if an error occurs during input or computation
     */
    public void run() throws Exception {
        byte choice = 0;
        while (choice != 6) {
            showMenu();
            choice = readChoice((byte) 1, (byte) 6);

            switch (choice) {
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
                    System.out.println(AnsiStyle.style("Thank You.", AnsiStyle.BRIGHT_GREEN));
                    break;
            }
        }
    }

    /**
     * Reads a menu choice between a low and high number.
     *
     * @param low  the smallest valid number
     * @param high the largest valid number
     * @return the user's choice as a byte
     * @throws Exception
     */
    private byte readChoice(byte low, byte high) throws Exception {
        byte choice;
        System.out.print(AnsiStyle.style("Enter your choice (" + low + "-" + high + "): ", AnsiStyle.BRIGHT_YELLOW));
        choice = (byte) readInteger(low, high);
        return choice;
    }

    /**
     * Displays the main menu of the program
     */
    public void showMenu() {
        System.out.println(AnsiStyle.style("\n==================== POLYNOMIAL MENU ====================", AnsiStyle.BRIGHT_CYAN));
        System.out.println(AnsiStyle.style("1. Evaluate a polynomial", AnsiStyle.BRIGHT_WHITE));
        System.out.println(AnsiStyle.style("2. Add two polynomials", AnsiStyle.BRIGHT_WHITE));
        System.out.println(AnsiStyle.style("3. Subtract a polynomial from another", AnsiStyle.BRIGHT_WHITE));
        System.out.println(AnsiStyle.style("4. Multiply two polynomials", AnsiStyle.BRIGHT_WHITE));
        System.out.println(AnsiStyle.style("5. Divide a polynomial by another", AnsiStyle.BRIGHT_WHITE));
        System.out.println(AnsiStyle.style("6. Quit", AnsiStyle.BRIGHT_WHITE));
        System.out.println(AnsiStyle.style("=========================================================", AnsiStyle.BRIGHT_CYAN));
    }

    /**
     * Read a polynomial from the user, ask for a value,
     * evaluate the polynomial at that value, and print the result.
     *
     * @throws Exception if reading or evaluating fails
     */
    public void evaluatePolynomial() throws Exception {
        System.out.println(AnsiStyle.style("\nYou want to evaluate a polynomial.", AnsiStyle.BOLD));
        Polynomial p = readPolynomial();

        System.out.println(AnsiStyle.style("\nThe polynomial entered is: ", AnsiStyle.BRIGHT_CYAN) + AnsiStyle.style(p.toString(), AnsiStyle.BRIGHT_WHITE));

        System.out.print(AnsiStyle.style("\nWhat is the value to be assigned to the variable of the polynomial? ", AnsiStyle.BRIGHT_YELLOW));
        double value = readDouble();

        double result = p.evaluate(value);

        System.out.println(AnsiStyle.style("\nThe polynomial evaluates to: ", AnsiStyle.BRIGHT_GREEN) + AnsiStyle.style(String.valueOf(result), AnsiStyle.BRIGHT_WHITE));

        System.out.println(AnsiStyle.style("\nPress enter to continue.....", AnsiStyle.BRIGHT_BLACK));
        keyboard.nextLine();
    }

    /**
     * Reads and validates an integer between low and high.
     *
     * @param low  minimum allowed number
     * @param high maximum allowed number
     * @return a valid integer within range
     */
    private int readInteger(int low, int high) {
        boolean validInput = false;
        int value = 0;

        while (!validInput) {
            try {
                value = Integer.parseInt(keyboard.nextLine());
                if (value < low) {
                    System.out.print("The number must not be lower than " + low + ". ");
                } else if (value > high) {
                    System.out.print("The number must not be greater than " + high + ". ");
                } else {
                    validInput = true;
                }
            } catch (Exception x) {
                System.out.println("Please enter an integer.");
            }
        }
        return value;
    }

    /**
     * Reads and validates a double from user input.
     *
     * @return the double value entered
     */
    private double readDouble() {
        boolean validInput = false;
        double value = 0;

        while (!validInput) {
            try {
                value = Double.parseDouble(keyboard.nextLine());
                validInput = true;
            } catch (Exception x) {
                System.out.println("You have to enter a number.");
            }
        }
        return value;
    }

    /**
     * Builds and returns a polynomial based on user input.
     *
     * @return the entered polynomial
     * @throws Exception
     */
    public Polynomial readPolynomial() throws Exception {
        Polynomial p = new Polynomial();
        int degree = -1;
        boolean validDegree = false;
        char literalCoefficient = 'x';

        System.out.println(AnsiStyle.style("\nThe polynomial should involve one variable/literal only.", AnsiStyle.BRIGHT_BLACK));

        do {
            System.out.print(AnsiStyle.style("What is the literal coefficient of the polynomial? ", AnsiStyle.BRIGHT_YELLOW));
            literalCoefficient = keyboard.nextLine().charAt(0);
        } while (!Character.isAlphabetic(literalCoefficient));

        do {
            System.out.print(AnsiStyle.style("What is the degree of the polynomial? ", AnsiStyle.BRIGHT_YELLOW));
            degree = readInteger(Integer.MIN_VALUE, Integer.MAX_VALUE);
            validDegree = true;
        } while (!validDegree);

        for (int x = degree; x >= 0; x--) {
            Term term = readTerm(literalCoefficient, x);
            p.addTerm(term);
        }

        return p;
    }

    /**
     * Reads a term’s coefficient and creates a Term object.
     *
     * @param literal the literal variable of the polynomial
     * @param degree  the degree of the term
     * @return a new Term object
     * @throws Exception
     */
    public Term readTerm(char literal, int degree) throws Exception {
        System.out.print(AnsiStyle.style("Enter the numerical coefficient of the term with degree " + degree + ": ", AnsiStyle.BRIGHT_YELLOW));
        double nCoeff = readDouble();
        return new Term(nCoeff, literal, degree);
    }

    /**
     * Lets the user add two polynomials.
     *
     * @throws Exception
     */
    public void addPolynomials() throws Exception {
        System.out.println(AnsiStyle.style("\nYou want to add two polynomials.", AnsiStyle.BOLD));
        System.out.println(AnsiStyle.style("Enter the first polynomial.", AnsiStyle.BRIGHT_WHITE));
        Polynomial p1 = readPolynomial();

        System.out.println(AnsiStyle.style("\nEnter the second polynomial.", AnsiStyle.BRIGHT_WHITE));
        Polynomial p2 = readPolynomial();

        System.out.println(AnsiStyle.style("\nFirst polynomial: ", AnsiStyle.BRIGHT_CYAN) + p1);
        System.out.println(AnsiStyle.style("Second polynomial: ", AnsiStyle.BRIGHT_CYAN) + p2);

        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println(AnsiStyle.style("Sum: ", AnsiStyle.BRIGHT_GREEN) + p1.add(p2));
        } else {
            System.out.println(AnsiStyle.style("The two polynomials have different literals.", AnsiStyle.BRIGHT_RED));
        }

        System.out.println(AnsiStyle.style("\nPress enter to continue.....", AnsiStyle.BRIGHT_BLACK));
        keyboard.nextLine();
    }

    /**
     * Lets the user subtract two polynomials.
     *
     * @throws Exception
     */
    public void subtractPolynomials() throws Exception {
        System.out.println(AnsiStyle.style("\nYou want to subtract two polynomials.", AnsiStyle.BOLD));
        System.out.println("Enter the minuend polynomial.");
        Polynomial p1 = readPolynomial();

        System.out.println("Enter the subtrahend polynomial.");
        Polynomial p2 = readPolynomial();

        System.out.println(AnsiStyle.style("\nMinuend polynomial: ", AnsiStyle.BRIGHT_CYAN) + p1);
        System.out.println(AnsiStyle.style("Subtrahend polynomial: ", AnsiStyle.BRIGHT_CYAN) + p2);

        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println(AnsiStyle.style("Difference: ", AnsiStyle.BRIGHT_GREEN) + p1.subtract(p2));
        } else {
            System.out.println(AnsiStyle.style("The two polynomials have different literals.", AnsiStyle.BRIGHT_RED));
        }

        System.out.println(AnsiStyle.style("\nPress enter to continue.....", AnsiStyle.BRIGHT_BLACK));
        keyboard.nextLine();
    }

    /**
     * Lets the user multiply two polynomials.
     *
     * @throws Exception
     */
    public void multiplyPolynomials() throws Exception {
        System.out.println(AnsiStyle.style("\nYou want to multiply two polynomials.", AnsiStyle.BOLD));
        System.out.println("Enter the first polynomial.");
        Polynomial p1 = readPolynomial();

        System.out.println("Enter the second polynomial.");
        Polynomial p2 = readPolynomial();

        System.out.println(AnsiStyle.style("\nFirst polynomial: ", AnsiStyle.BRIGHT_CYAN) + p1);
        System.out.println(AnsiStyle.style("Second polynomial: ", AnsiStyle.BRIGHT_CYAN) + p2);

        if (p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            System.out.println(AnsiStyle.style("Product: ", AnsiStyle.BRIGHT_GREEN) + p1.multiply(p2));
        } else {
            System.out.println(AnsiStyle.style("The two polynomials have different literals.", AnsiStyle.BRIGHT_RED));
        }

        System.out.println(AnsiStyle.style("\nPress enter to continue.....", AnsiStyle.BRIGHT_BLACK));
        keyboard.nextLine();
    }

    /**
     * Lets the user divide two polynomials and shows the quotient and remainder.
     *
     * @throws Exception
     */
    public void dividePolynomials() throws Exception {
        System.out.println(AnsiStyle.style("\nYou want to divide two polynomials.", AnsiStyle.BOLD));
        System.out.println("Enter the dividend polynomial.");
        Polynomial p1 = readPolynomial();

        System.out.println("Enter the divisor polynomial.");
        Polynomial p2 = readPolynomial();

        System.out.println(AnsiStyle.style("\nDividend: ", AnsiStyle.BRIGHT_CYAN) + p1);
        System.out.println(AnsiStyle.style("Divisor: ", AnsiStyle.BRIGHT_CYAN) + p2);

        boolean divisorIsZero = false;
        if (p2.getTerms() == null || p2.getTerms().size() == 0)
            divisorIsZero = true;
        else if (p2.getTerms().size() == 1 && p2.getTerms().get(0).getCoefficient() == 0)
            divisorIsZero = true;

        if (!divisorIsZero && p1.getTerms().get(0).getLiteral() == p2.getTerms().get(0).getLiteral()) {
            Quotient q = p1.divide(p2);
            System.out.println(AnsiStyle.style("Quotient: ", AnsiStyle.BRIGHT_GREEN) + q.getQuotientP());
            System.out.println(AnsiStyle.style("Remainder: ", AnsiStyle.BRIGHT_YELLOW) + q.getRemainderP());
        } else if (divisorIsZero) {
            System.out.println(AnsiStyle.style("Division by zero polynomial is undefined.", AnsiStyle.BRIGHT_RED));
        } else {
            System.out.println(AnsiStyle.style("The two polynomials have different literals.", AnsiStyle.BRIGHT_RED));
        }

        System.out.println(AnsiStyle.style("\nPress enter to continue.....", AnsiStyle.BRIGHT_BLACK));
        keyboard.nextLine();
    }

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        PolynomialArithmetic program;

        try {
            program = new PolynomialArithmetic();
            program.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
