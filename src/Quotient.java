/*
Name of Student: Mina, Ezekiel Cole U.
Due Date: 15 October 2025
*/

public class Quotient {

    Polynomial quotientP;
    Polynomial remainderP;

    /**
     * Creates a new empty Quotient object with no quotient or remainder yet.
     */
    public Quotient() {
        quotientP = null;
        remainderP = null;
    }

    /**
     * Sets the quotient part of this result.
     *
     * @param q the polynomial that represents the quotient
     */
    public void setQuotientP(Polynomial q) {
        quotientP = q;
    }

    /**
     * Sets the remainder part of this result.
     *
     * @param p the polynomial that represents the remainder
     */
    public void setRemainderP(Polynomial p) {
        remainderP = p;
    }

    /**
     * Returns the quotient part of this result.
     *
     * @return the quotient polynomial
     */
    public Polynomial getQuotientP() {
        return quotientP;
    }

    /**
     * Returns the remainder part of this result.
     *
     * @return the remainder polynomial
     */
    public Polynomial getRemainderP() {
        return remainderP;
    }

    /**
     * Returns a string form of the quotient and remainder.
     *
     * @return a string showing the quotient and remainder
     */
    public String toString() {
        return (" Quotient: " + quotientP.toString() + " \nRemainder: " + remainderP.toString());
    }
}
