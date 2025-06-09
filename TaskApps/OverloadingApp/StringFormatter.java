public  class StringFormatter {

    /**
     * Formats a string by capitalizing the first letter.
     * @param input the string to format
     * @return the formatted string with the first letter capitalized
     */
    public static String format(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }


    /**
     * Formats a string by repeating it a specified number of times.
     * @param input the string to format
     * @param repeat the number of times to repeat the string
     * @return the formatted string repeated the specified number of times
     */

    public static String format(String input, int repeat) {

        if (input == null || repeat < 0) {
            throw new IllegalArgumentException("Input cannot be null and repeat must be non-negative");
        }
        StringBuilder formattedString = new StringBuilder();
        for (int i = 0; i < repeat; i++) {
            formattedString.append(input);
        }
        return formattedString.toString();
    }

    /**
     * Formats a string by converting it to uppercase and adding a prefix and suffix.
     * @param input the string to format
     * @param prefix the prefix to add
     * @param suffix the suffix to add
     * @return the formatted string with the prefix, uppercase content, and suffix
     */

    public static String format(String input, String prefix, String suffix) {
        if (input == null || prefix == null || suffix == null) {
            throw new IllegalArgumentException("Input, prefix, and suffix cannot be null");
        }
        return prefix + input + suffix;
    }

    
}