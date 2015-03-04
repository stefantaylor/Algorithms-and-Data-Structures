package imult;

import java.io.File;

//----------------------------------------------------------------------------
//  Matriculation Number - s1006260
//----------------------------------------------------------------------------

// --------------------------------------------------------------------------- //

// ******************   ___________   ___ _____  ____ ____   ***************** //
// ******************  / ___/      | /  _]     |/    |    \  ***************** //
// ****************** (   \_|      |/  [_|   __|  o  |  _  | ***************** //
// ******************  \__  |_|  |_|    _]  |_ |     |  |  | ***************** //
// ******************  /  \ | |  | |   [_|   _]|  _  |  |  | ***************** //
// ******************  \    | |  | |     |  |  |  |  |  |  | ***************** //
// ******************   \___| |__| |_____|__|  |__|__|__|__| ***************** //

// --------------------------------------------------------------------------- //


public class StudentCode {

    public static BigInt add(BigInt A, BigInt B) {
        Unsigned c = new Unsigned(0);       // unsigned to represent the carry value
        Unsigned d = new Unsigned(0);       // unsigned to represent the digit value
        BigInt result = new BigInt();       // BigInt to store the result
        if (A.length() > B.length()) {      // If A is longer than B, then use A as the upper bound, else B
            for (int i = 0; i < A.length(); i++) {  // loops through the bigint list of digits with the length of A being the upper bound
                d = (Arithmetic.addDigits(A.getDigit(i), B.getDigit(i), c)).digit();  // Gives d the digit value of adding digits i of A & B
                c = (Arithmetic.addDigits(A.getDigit(i), B.getDigit(i), c)).carry();  // Gives c the carry value of adding digits i of A & B
                result.setDigit(i, d);  // sets the i'th digit of the result to d
            }
        } else {
            for (int i = 0; i < B.length(); i++) {  // loops through the bigint list of digits with the length of B being the upper bound
                d = (Arithmetic.addDigits(A.getDigit(i), B.getDigit(i), c)).digit(); // Gives d the digit value of adding digits i of A & B
                c = (Arithmetic.addDigits(A.getDigit(i), B.getDigit(i), c)).carry(); // Gives c the digit value of adding digits i of A & B
                result.setDigit(i, d); // sets the i'th digit of the result to d
            }
        }
        result.setDigit(result.length(), c); // sets the final digit of the result to d
        return result;
    }

    public static BigInt sub(BigInt A, BigInt B) { //works almost identically to the add algorithm, replacing addDigits with subDigits
        Unsigned c = new Unsigned(0);   
        Unsigned d = new Unsigned(0);  
        BigInt result = new BigInt();  
        if (A.length() > B.length()) { 
            for (int i = 0; i < A.length(); i++) {
                d = (Arithmetic.subDigits(A.getDigit(i), B.getDigit(i), c)).digit();
                c = (Arithmetic.subDigits(A.getDigit(i), B.getDigit(i), c)).carry();
                result.setDigit(i, d);
            }
        } else {
            for (int i = 0; i < B.length(); i++) {
                d = (Arithmetic.subDigits(A.getDigit(i), B.getDigit(i), c)).digit();
                c = (Arithmetic.subDigits(A.getDigit(i), B.getDigit(i), c)).carry();
                result.setDigit(i, d);
            }
        }
        result.setDigit(result.length(), c);
        return result;
    }

    public static BigInt koMul(BigInt A, BigInt B) { //precisly follows the Karatsuba-Ofman Algorithm as described in notes
        Unsigned c = new Unsigned(0);   // unsigned to represent a carry value
        Unsigned d = new Unsigned(0);   // unsigned to represent the digit value
        BigInt a0 = new BigInt();   // BigInt to represent Alpha 0
        BigInt a1 = new BigInt();   // BigInt to represent Alpha 1
        BigInt b0 = new BigInt();   // BigInt to represent Beta 0
        BigInt b1 = new BigInt();   // BigInt to represent Beta 1
        BigInt l = new BigInt();   // BigInt to represent l
        BigInt h = new BigInt();   // BigInt to represent h
        BigInt m = new BigInt();   // BigInt to represent m
        BigInt result = new BigInt();  // BigInt to store the result
        int n = Math.max(A.length(), B.length());    //n = the maximum length of the input parameters
        if (n == 1) { // If length is 1 then multiply digits   
            d = (Arithmetic.mulDigits(A.getDigit(0), B.getDigit(0))).digit();
            c = (Arithmetic.mulDigits(A.getDigit(0), B.getDigit(0))).carry();
            result.setDigit(0, d);
            result.setDigit(1, c);
            return result; // return A x B
        } else {        
            a0 = A.split(0, n / 2 - 1);
            a1 = A.split((n / 2), n - 1);   // a = a0 + a1 B ^ (floor n/2)
            b0 = B.split(0, n / 2 - 1);
            b1 = B.split(n / 2, n - 1);     // b = b0 + b1 B ^ (floor n/2)
            l = koMul(a0, b0);              // l = a0b0
            h = koMul(a1, b1);              // h = a1b1
            m = sub(sub(koMul(add(a0, a1), add(b0, b1)), l), h);    // m = (a0+a1)(b0+b1)-l-h
            m.lshift(n / 2);    // m <- mB^(floor n/2)
            h.lshift((n / 2) * 2);  // h <- hB^(2 x floor n/2)
            return add(l, add(m, h)); // return l + m + h
        }
    }

    public static BigInt koMulOpt(BigInt A, BigInt B) {
        Unsigned c = new Unsigned(0);   // unsigned to represent a carry value
        Unsigned d = new Unsigned(0);   // unsigned to represent the digit value
        BigInt a0 = new BigInt();   // BigInt to represent Alpha 0
        BigInt a1 = new BigInt();   // BigInt to represent Alpha 1
        BigInt b0 = new BigInt();   // BigInt to represent Beta 0
        BigInt b1 = new BigInt();   // BigInt to represent Beta 1
        BigInt l = new BigInt();   // BigInt to represent l
        BigInt h = new BigInt();   // BigInt to represent h
        BigInt m = new BigInt();   // BigInt to represent m
        BigInt result = new BigInt();  // BigInt to store the result
        if (Math.min(A.length(), B.length()) > 10) { //if max length of input parameters is greater than 10 perfom koMul
            int n = Math.max(A.length(), B.length());    //n = the maximum length of the Ints
            if (n == 1) {
                d = (Arithmetic.mulDigits(A.getDigit(0), B.getDigit(0))).digit();
                c = (Arithmetic.mulDigits(A.getDigit(0), B.getDigit(0))).carry();
                result.setDigit(0, d);
                result.setDigit(1, c);
                return result;
            } else {
                a0 = A.split(0, n / 2 - 1);
                a1 = A.split((n / 2), A.length() - 1);
                b0 = B.split(0, n / 2 - 1);
                b1 = B.split(n / 2, A.length() - 1);
                l = koMulOpt(a0, b0);
                h = koMulOpt(a1, b1);
                m = sub(sub(koMulOpt(add(a0, a1), add(b0, b1)), l), h);
                m.lshift(n / 2);
                h.lshift((n / 2) * 2);
                return add(l, add(m, h));
            }
        } else { // if max length of input parameters is <= 10 perform schoolmul
            return Arithmetic.schoolMul(A, B);
        }
    }

    public static void main(String argv[]) {
    }
}
