/*
Name of Student: Mina, Ezekiel Cole U.
Due Date: 15 October 2025
*/

public final class AnsiStyle {

    // Text Reset
    public static final String RESET = "\u001B[0m";

    // Bright Colors
    public static final String BRIGHT_BLACK = "\u001B[90m";
    public static final String BRIGHT_RED = "\u001B[91m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_CYAN = "\u001B[96m";
    public static final String BRIGHT_WHITE = "\u001B[97m";

    // Bold Style
    public static final String BOLD = "\u001B[1m";

    // Underline Style
    public static final String UNDERLINE = "\u001B[4m";

    private AnsiStyle() {}

    /**
     * Styles a string by wrapping it with the given ANSI code.
     *
     * @param text the text to style
     * @param ansiCode the ANSI code for color or formatting
     * @return styled text that resets after printing
     */
    public static String style(String text, String ansiCode) {
        return ansiCode + text + RESET;
    }

    /**
     * Convenience for just coloring text.
     *
     * @param text text to color
     * @param color a color constant from this class
     * @return colored text or original text if disabled
     */
    public static String color(String text, String color) {
        return style(text, color);
    }

    /**
     * Build a pretty header line with color.
     *
     * @param title the header title
     * @return formatted header string
     */
    public static String header(String title) {
        String line = "--------------------------------------------------------";
        return style(line + "\n" + title + "\n" + line, BOLD + BRIGHT_CYAN);
    }
}
//  https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797
