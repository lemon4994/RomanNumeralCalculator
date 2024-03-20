//This was coded by: Nicholas Chiarello
package chiarello.nicholas.romannumeralcalculator;

public class Roman {
    /**
     * Returns a number value for a specific Roman Numeral.
     * @param c Roman Numeral
     * @return Number with equal value to the numeral
     */
    short whatNumber(char c) {
        if (c == 'I') {
            return 1;
        } else if (c == 'V') {
            return 5;
        } else if (c == 'X') {
            return 10;
        } else if (c == 'L') {
            return 50;
        } else if (c == 'C') {
            return 100;
        } else if (c == 'D') {
            return 500;
        } else if (c == 'M') {
            return 1000;
        }
        return 0;
    }
    /**
     * Repeats a char in a line.
     *  Ex. if i = 3 and c = t: return "ttt"
     * @param i Times to repeat
     * @param c char to repeat
     * @return c times i
     */
    String prints(int i, char c) {
        String letters = "";
        while (i > 0) {
            letters += c;
            i--;
        }
        return letters;
    }
    /**
     * Converts a Roman Numeral into a number
     * @param roman A Roman Numeral
     * @return A positive number if successful; -1 if numeral is incorrect format; -2 if numeral included invalid characters; -3 if numeral can't exist (above 5000)
     */
    short convertToInt(String roman) {
        roman = roman.toUpperCase();
        short sum = 0;
        for (int i = 0, j = 1; i < roman.length(); i++, j++) { //i is for checking the current letter, j is the letter ahead (for subtraction rule)
            if ("IVXLCDM".indexOf(roman.charAt(i)) == -1) { //checks if it has invalid characters
                return -2;
            }
            if ((j) <= roman.length()-1) { //this is for avoiding out of bounds exceptions
                char a = roman.charAt(i);
                char b = roman.charAt(j);
                if (whatNumber(a) < whatNumber(b)) { //checks if subtraction rule is in effect here
                    i++; j++;
                    sum += (whatNumber(b)-whatNumber(a));
                    continue;
                }
            }
            sum += whatNumber(roman.charAt(i));
            if (sum >= 5000) {
                return -3;
            }
        }
        if (roman.equals(convertToString(sum))) { // "Is the input the correct format?"
            return sum;
        }
        return -1;
    }

    /**
     * Turns a number into the appropriately formatted Roman Numeral
     * @param num Number to convert
     * @return A Roman Numeral if successful; "-1" if the number is an incorrect size
     */
    String convertToString(short num) {
        if (num <= 0 || num >= 5000) { //Numerals are between 0 and 5000
            return "-1";
        }
        String roman = "";
        int length = 0, counter = 1;
        while(num/counter != 0) { //finds number of digits in the number
            counter*=10;
            length++;
        }

        int[] digits = new int[length]; //Step 1: make an array for every digit

        for (int i = length-1, j = 1; i >= 0; i--, j*=10) { // Step 2: move every digit to its own index (132 = [1,3,2])
            int a = (num/j)%10;
            digits[i] = a;
        }
        for (int i = 0; i < digits.length; i++) { // Step 3: make every digit its own number (132 = [100,30,2])
            int tens = 1;
            for (int j = 1; j < length-i; j++) {
                tens*=10;
            } // now each individual digit can be checked for its letter
            if (digits[i]*(tens-i) >=1000) {roman += prints(digits[i], 'M');}
            else if (digits[i]*(tens) ==900) {roman += "CM";}
            else if (digits[i]*(tens) >= 500) {roman += "D" + prints((digits[i])-5, 'C');}
            else if (digits[i]*(tens) == 400) {roman += "CD";}
            else if (digits[i]*(tens) >= 100) {roman += prints(digits[i], 'C');}
            else if (digits[i]*(tens) == 90) {roman += "XC";}
            else if (digits[i]*(tens) >= 50) {roman += "L" + prints((digits[i])-5, 'X');}
            else if (digits[i]*(tens) == 40) {roman += "XL";}
            else if (digits[i]*(tens) >= 10) {roman += prints(digits[i], 'X');}
            else if (digits[i]*(tens) == 9) {roman += "IX";}
            else if (digits[i]*(tens) >= 5) {roman += "V" + prints((digits[i])-5, 'I');}
            else if (digits[i]*(tens) == 4) {roman += "IV";}
            else if (digits[i]*(tens) >= 1) {roman += prints(digits[i], 'I');}
        }
        return roman;
        //	return null;

    }
}
