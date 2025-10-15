/*
Name of Student: Mina, Ezekiel Cole U.
Due Date: 15 October 2025
*/

import java.util.LinkedList; // access the LinkedList class from package util

public class Polynomial {
    private LinkedList<Term> terms; // list of terms that make up the polynomial

    /**
     * Creates an empty polynomial with no terms yet.
     *
     * @throws Exception if the list cannot be created
     */
    public Polynomial() throws Exception {
        terms = new LinkedList<Term>();
    }

    /**
     * Adds a new term into this polynomial.
     * The terms are kept in decreasing order of degrees.
     * If a term with the same degree already exists, their coefficients are added.
     * If the new coefficient becomes zero, that term is removed.
     *
     * @param newTerm the term to add
     * @throws Exception if adding to the list fails
     */
    public void addTerm(Term newTerm) throws Exception {
        int ctr;
        boolean found = false;
        Term currTerm = null;

        for (ctr = 0; ctr < terms.size(); ctr++) {
            currTerm = terms.get(ctr);

            if (currTerm.getDegree() <= newTerm.getDegree()) {
                found = true;
                break;
            }
        }

        if (!found) {
            terms.add(newTerm);
        } else {
            if (currTerm.getDegree() < newTerm.getDegree()) {
                terms.add(ctr, newTerm);
            } else {
                currTerm.setCoefficient(currTerm.getCoefficient() + newTerm.getCoefficient());

                if (currTerm.getCoefficient() == 0) {
                    terms.remove(ctr);
                }
            }
        }
    }

    /**
     * Turns this polynomial into a string.
     * For example: 3x^2 - 5x + 3
     *
     * @return the polynomial written as a string
     */
    public String toString() {
        if (terms == null || terms.isEmpty()) {
            return "0";
        }

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < terms.size(); i++) {
            Term currTerm = terms.get(i);
            double coef = currTerm.getCoefficient();
            int deg = currTerm.getDegree();

            if (coef == 0) continue;

            // Determine the sign
            if (s.length() > 0) {
                s.append(coef > 0 ? " + " : " - ");
            } else {
                if (coef < 0) s.append("-");
            }

            // Get the absolute value for display
            double absCoef = Math.abs(coef);

            // Display coefficient
            if (!(absCoef == 1 && deg != 0)) {
                s.append(absCoef);
            }

            // Display variable and degree
            if (deg > 0) {
                s.append(currTerm.getLiteral());
                if (deg > 1) {
                    s.append("^").append(deg);
                }
            }
        }

        // If all terms were zero
        if (s.length() == 0) {
            return "0";
        }

        return s.toString();
    }


    /**
     * Solves or finds the value of this polynomial for a given number.
     *
     * @param value the number that replaces the variable (like x = 2)
     * @return the final computed value of the polynomial
     * @throws Exception if the calculation fails
     */
    public double evaluate(double value) throws Exception {
        double sum = 0;

        for (int ctr = 0; ctr < terms.size(); ctr++) {
            Term currTerm = terms.get(ctr);
            sum += currTerm.getCoefficient() * Math.pow(value, currTerm.getDegree());
        }

        return sum;
    }

    /**
     * Replaces the list of terms in this polynomial with another list.
     *
     * @param t the new list of terms to set
     */
    public void setTerms(LinkedList<Term> t) {
        terms = t;
    }

    /**
     * Gives the list of terms that make up this polynomial.
     *
     * @return the list of terms in this polynomial
     */
    public LinkedList<Term> getTerms() {
        return terms;
    }

    /**
     * Adds another polynomial to this one and returns the result as a new polynomial.
     *
     * @param otherPolynomial the polynomial to add
     * @return a new polynomial that is the sum of this and the other
     * @throws Exception
     */
    public Polynomial add(Polynomial otherPolynomial) throws Exception {
        Polynomial result = new Polynomial();
        LinkedList<Term> resultTerms = new LinkedList<Term>();

        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm = this.getTerms().get(ctr);
            resultTerms.add(new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree()));
        }

        result.setTerms(resultTerms);

        for (int ctr2 = 0; ctr2 < otherPolynomial.getTerms().size(); ctr2++) {
            Term currentTerm = otherPolynomial.getTerms().get(ctr2);
            Term sTerm = new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree());
            result.addTerm(sTerm);
        }

        if (result.getTerms().size() == 0) {
            result.addTerm(new Term(0, 'x', 0));
        }
        return result;
    }

    /**
     * Subtracts another polynomial from this one and returns the result as a new polynomial.
     *
     * @param otherPolynomial the polynomial to subtract
     * @return a new polynomial that is the difference of this and the other
     * @throws Exception
     */
    public Polynomial subtract(Polynomial otherPolynomial) throws Exception {
        Polynomial result = new Polynomial();
        LinkedList<Term> resultTerms = new LinkedList<Term>();

        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm = this.getTerms().get(ctr);
            resultTerms.add(new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree()));
        }

        result.setTerms(resultTerms);

        for (int ctr2 = 0; ctr2 < otherPolynomial.getTerms().size(); ctr2++) {
            Term currentTerm = otherPolynomial.getTerms().get(ctr2);
            Term sTerm = new Term(-currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree());
            result.addTerm(sTerm);
        }

        if (result.getTerms().size() == 0) {
            result.addTerm(new Term(0, 'x', 0));
        }

        return result;
    }

    /**
     * Multiplies this polynomial by another polynomial and returns the result.
     *
     * @param otherPolynomial the polynomial to multiply by
     * @return a new polynomial that is the product of both
     * @throws Exception
     */
    public Polynomial multiply(Polynomial otherPolynomial) throws Exception {
        Polynomial result = new Polynomial();

        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm1 = this.getTerms().get(ctr);

            for (int ctr2 = 0; ctr2 < otherPolynomial.getTerms().size(); ctr2++) {
                Term currentTerm2 = otherPolynomial.getTerms().get(ctr2);

                double pCoef = currentTerm2.getCoefficient() * currentTerm1.getCoefficient();
                int pDegree = currentTerm2.getDegree() + currentTerm1.getDegree();

                result.addTerm(new Term(pCoef, currentTerm1.getLiteral(), pDegree));
            }
        }

        if (result.getTerms().size() == 0)
            result.addTerm(new Term(0, 'x', 0));

        return result;
    }

    /**
     * Divides this polynomial by another polynomial.
     * Returns both the quotient and remainder in a Quotient object.
     *
     * @param divisor the polynomial to divide by
     * @return a Quotient object containing the quotient and remainder
     * @throws Exception
     */
    public Quotient divide(Polynomial divisor) throws Exception {
        Quotient result = new Quotient();
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();

        LinkedList<Term> dividend = new LinkedList<Term>();
        Term qTerm;
        Polynomial subtrahend = new Polynomial();

        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm = this.getTerms().get(ctr);
            dividend.add(new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree()));
        }

        remainder.setTerms(dividend);

        while ((remainder != null) && (remainder.getTerms().size() > 0) && ((remainder.getTerms().get(0)).getDegree() >= (divisor.getTerms().get(0)).getDegree())) {

            Term numTerm = remainder.getTerms().get(0);
            Term divTerm = divisor.getTerms().get(0);

            qTerm = new Term(numTerm.getCoefficient() / divTerm.getCoefficient(), numTerm.getLiteral(), numTerm.getDegree() - divTerm.getDegree());

            quotient.addTerm(qTerm);

            LinkedList<Term> pQTerm = new LinkedList<Term>();
            pQTerm.add(qTerm);

            Polynomial multiplier = new Polynomial();
            multiplier.setTerms(pQTerm);

            subtrahend = multiplier.multiply(divisor);
            remainder = remainder.subtract(subtrahend);
        }

        if (quotient.getTerms().size() == 0) {
            quotient.addTerm(new Term(0, 'x', 0));
        }
        result.setQuotientP(quotient);

        if (remainder.getTerms().size() == 0) {
            remainder.addTerm(new Term(0, 'x', 0));
        }

        result.setRemainderP(remainder);

        return result;
    }
}
