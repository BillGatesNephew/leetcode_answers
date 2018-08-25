class Solution {
    /**
     * Solution Explanation
     * ============================
     * First recognize that the input is bounded to be a number between 0 and (2^31) - 1. This reflects that the largest 
     * component of the number given will be "billion". Now, consider the number N = a,bcd,efg,hij, where a through j is a digit
     * between 0 and 9. There are two key components to converting this number. First, a number between 0 and 999 must be able 
     * to be parsed from 3 digits. Second, the 3 digits whose value was parsed must have the magnitude of their placement after 
     * it. For example, consider the number 123,100,020. Starting from the leftmost group of three, the digits 123 are converted
     * to "one hundred twenty three", and then the magnitude "million" is placed after them to get the string "one hundred 
     * twenty three million". In this manner, the number can be broken down by dividing the number into separate groups. In 
     * order to parse three digits, the common names for the first 20 numbers from 0 to 19 must be hard coded along with the
     * tens names (twenty, thirty, etc). After that all that needs to be hardcoded are the magnitude positions. The basic 
     * algorithm is as follows:
     * 
     *      parseUnderThousand(n):
     *          1. Initialize an array firstTwenty to have the first twenty number common names from 0 to 19, and 
     *             an array tensNumbers to have the common names for the tens places.
     *          2. If n equals 0, then return the string "".
     *          3. If n is less than 20, then return firstTwenty[n] + " ".
     *          4. If n is less than 100, then return tensNumbers[n / 10] + " " + parseUnderThousand(num % 10).
     *          5. Return firstTwenty[n / 100] + " Hundred " + parseUnderThousand(n % 100).
     * 
     *      numberToWords(n):
     *          1. Initialize an array M with values: "", "Thousand", "Million", "Billion".
     *          2. Initialize an empty string S.
     *          3. Initialize i to be 0.
     *          4. While n is greater than 0:
     *              i) If n modulo 1000 does not equal 0, then set S to be parseUnderThousand(n % 1000) + M[i] + " " + S.
     *              ii) Set n to be n divided by 1000.
     *              iii) Increment i by one.
     *          5. Return S with the white space trimmed.
     * 
     * Special Cases: There is a single special case to consider:
     *      1. num is equal to 0 => Return "Zero". 
     * 
     * ============================
     *
     *
     * Solution Complexities
     * ============================
     * Time Complexity:  O()
     * Space Complexity: O()
     * ============================
     */

    private static final String[] UNDER_TWENTY = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] TENS_NAMES = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] MAGNITUDES = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        // Special Case: num == 0.
        if(num == 0) return "Zero";
        /**
         * Iterate over an increasing magnitude range, and determine the number's 
         * grammar for each magnitude.
         */
        int magnitude = 0;
        String result = "";
        while (num > 0) {
            if(num % 1000 != 0) {
                result = parseUnderThousand(num % 1000) + MAGNITUDES[magnitude] + " " + result;
            }
            num /= 1000;
            magnitude++;
        }
        return result.trim();
    }

    public String parseUnderThousand(int num) {
        // Ignore zero.
        if(num == 0) return "";
        // Directly access a number under twenty
        if(num < 20) return UNDER_TWENTY[num] + " ";
        // Recursively act on a number that is two digits.
        if(num < 100) return TENS_NAMES[num / 10] + " " + parseUnderThousand(num % 10);
        // Recursively act on a number that is three digits.
        return UNDER_TWENTY[num / 100] + " Hundred " + parseUnderThousand(num % 100); 
    }


}
