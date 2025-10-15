/*
Name of Student: Mina, Ezekiel Cole U.
Due Date: 15 October 2025
*/

/**
 * The following class is a template for a term of an algebraic polynomial that involves only one literal.
 * 3x^2 is an example of a term
 * where 3 is the numerical coefficient,
 * x is the literal coefficient and
 * 2 is the degree
 */
public class Term implements Comparable<Term> {
    private double coefficient;     // data member to hold numerical coefficient of a term
    private int degree;             // data member to hold the degree of a term
    private char literal;           // data member to hold the literal coefficient of a term

    /**
     * Constructs a term with coefficient set to 0, degree set to 0 and literal set to x.
     */
    public Term() {
        coefficient = 0;
        degree = 0;
        literal = 'x';
    }


    /**
     * Constructs a term that sets the coefficient, literal and degree
     * to the given the coef, literal and degree
     *
     * @param coef the number part of the term
     * @param literal the variable or letter (like x)
     * @param degree the exponent of the literal
     */
    public Term(double coef, char literal, int degree) {
        this.coefficient = coef;
        this.literal = literal;
        this.degree = degree;
    }
    // sample use
    // Term t = new Term(2.0, 'x', 3)


    /**
     * Sets the value of the numerical coefficient of this term to the specified coef
     *
     * @param coef the new coefficient value
     */
    public void setCoefficient(double coef) {
        this.coefficient= coef;
    }
    // sample use
    // t.setCoefficient(3)


    /**
     * Sets the value of the literal coefficient of this term to the specified character literal
     *
     * @param literal the new literal character (like x or y)
     */
    public void setLiteral(char literal){
        this.literal = literal;
    }
    // t.setLiteral('y')


    /**
     * Sets the value of the degree of this term to a specified degree
     *
     * @param degree the new degree value
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }


    /**
     * Returns the numerical coefficient of this term
     *
     * @return the coefficient value
     */
    public double getCoefficient() {
        return this.coefficient;
    }
    // System.out.println("Coefficient=" + t.getCoefficient())


    /**
     * Returns the literal coefficient of this term
     *
     * @return the literal character (like x)
     */
    public char getLiteral() {
        return this.literal;
    }


    /**
     * Returns the degree of this term
     *
     * @return the degree value
     */
    public int getDegree() {
        return this.degree;
    }


    /**
     * Return 0 if both degrees are equal
     * Return 1 if degree is greater than the other degree
     * Return -1 if degree is lesser than the other degree
     *
     * @param another another term to compare to
     * @return an integer result of the comparison (-1, 0, or 1)
     */
    @Override
    public int compareTo(Term another){
        if (this.getDegree() == another.getDegree()) {
            return 0;
        }

        else if (this.getDegree() > another.getDegree()) {
            return -1;
        }

        else {
            return 1;
        }

        //sample use
        // return this.toString().compareTo(another.toString());
    }

    // Term t1 = new Term()
    // Term t2 = new Term(1,'x', 1)
    // ArrayList<Term> p = new ArrayList();
    // ...
    // p has been assigned several terms
    // Collections.sort(p)


    /**
     * Returns a string representation of the term that follows a format with the example 3x^2
     *
     * @return a string that shows the term
     */
    @Override
    public String toString() {
        // Skip printing if coefficient is zero
        if (coefficient == 0) {
            return "";
        }

        StringBuilder s = new StringBuilder();

        // Handle the sign
        if (coefficient < 0) {
            s.append("-");
        }

        double absCoef = Math.abs(coefficient);

        // Show coefficient only if it's not 1 or if it's a constant term
        if (!(absCoef == 1 && degree != 0)) {
            // Remove .0 if not needed
            if (absCoef == (int) absCoef) {
                s.append((int) absCoef);
            } else {
                s.append(absCoef);
            }
        }

        // Show literal and degree
        if (degree > 0) {
            s.append(literal);
            if (degree > 1) {
                s.append("^").append(degree);
            }
        }

        return s.toString().trim();
    }
}