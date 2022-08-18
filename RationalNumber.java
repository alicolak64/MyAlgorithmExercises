import java.util.Objects;

/**
 * @author Ali Çolak
 *         10.03.2021
 */

public class RationalNumber implements Comparable<RationalNumber> {

    private int numerator;
    private int denominator;

    public RationalNumber() {
        numerator = 0;
        denominator = 1;
    }

    public RationalNumber(int numerator, int denominator) {
        int gcd = findGcd(numerator, denominator);
        this.numerator = (denominator > 0 ? 1 : -1) * numerator / gcd;
        this.denominator = (denominator > 0 ? 1 : -1) * denominator / gcd;
    }

    private int findGcd(int numerator, int denominator) {
        int absNumerator = Math.abs(numerator);
        int absDenominator = Math.abs(denominator);
        int gcd = 1;
        for (int i = 1; i <= absDenominator && i <= absNumerator; i++) {
            if (absNumerator % i == 0 && absDenominator % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    public RationalNumber sum(RationalNumber numberToSum) {
        int newNumerator = (this.numerator * numberToSum.denominator) + (this.denominator * numberToSum.numerator);
        int newDenominator = this.denominator * numberToSum.denominator;
        RationalNumber rationalNumber = new RationalNumber(newNumerator, newDenominator);
        return rationalNumber;
    }

    public RationalNumber subtract(RationalNumber numberToSubtract) {
        int newNumerator = (this.numerator * numberToSubtract.denominator)
                - (this.denominator * numberToSubtract.numerator);
        int newDenominator = this.denominator * numberToSubtract.denominator;
        RationalNumber rationalNumber = new RationalNumber(newNumerator, newDenominator);
        return rationalNumber;
    }

    public RationalNumber carp(RationalNumber numberToMultiply) {
        int newNumerator = this.numerator * numberToMultiply.numerator;
        int newDenominator = this.denominator * numberToMultiply.denominator;
        RationalNumber rationalNumber = new RationalNumber(newNumerator, newDenominator);
        return rationalNumber;
    }

    public RationalNumber divide(RationalNumber numberToDivide) {
        int tempNumerator = numberToDivide.denominator;
        int tempDenominator = numberToDivide.numerator;
        int newNumerator = this.numerator * tempNumerator;
        int newDenominator = this.denominator * tempDenominator;
        RationalNumber rationalNumber = new RationalNumber(newNumerator, newDenominator);
        return rationalNumber;
    }

    @Override
    public int compareTo(RationalNumber o) {
        if (this.subtract(o).numerator > 0) {
            return 1;
        } else if (this.subtract(o).numerator == 0) {
            return 0;
        } else
            return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RationalNumber that = (RationalNumber) o;
        return numerator == that.numerator && denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);
        } else if (numerator == 0) {
            return String.valueOf(numerator);
        } else if (denominator == 0) {
            return "The denominator cannot be 0";
        } else {
            return numerator + "/" + denominator;
        }
    }
}