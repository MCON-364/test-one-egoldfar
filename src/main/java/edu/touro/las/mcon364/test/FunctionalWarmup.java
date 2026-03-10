package edu.touro.las.mcon364.test;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalWarmup {

    /**
     * Problem 1
     * Return a Supplier that gives the current month number (1-12).
     */
    public static Supplier<Integer> currentMonthSupplier() {
        return () -> LocalDate.now().getMonthValue();
    }

    /**
     * Problem 2
     * Return a Predicate that is true only when the input string
     * has more than 5 characters.
     */
    public static Predicate<String> longerThanFive() {
        return s -> !(s == null) && s.length() > 5;
    }

    /**
     * Problem 3
     * Return a Predicate that checks whether a number is both:
     * - positive
     * - even
     * <p>
     * Prefer chaining smaller predicates.
     */
    public static Predicate<Integer> positiveAndEven() {

        Predicate<Integer> isEven = i -> i % 2 == 0;
        Predicate<Integer> isPositive = i -> i > 0;
        return isEven.and(isPositive);
    }

    /**
     * Problem 4
     * Return a Function that counts words in a string.
     * <p>
     * Notes:
     * - Trim first.
     * - Blank strings should return 0.
     * - Words are separated by one or more spaces (use can use regex "\\s+")
     *
     */
    public static Function<String, Integer> wordCounter() {
        return s -> {
            s = s.trim();
            if (s.isBlank()) {
                return 0;
            }
            var words = s.split("\\s+");
            return words.length;
        };
    }

    /**
     * Problem 5
     * Process the input labels as follows:
     * - remove blank strings
     * - trim whitespace
     * - convert to uppercase
     * - return the final list in the same relative order
     * <p>
     * Example:
     * ["  math ", "", " java", "  "] -> ["MATH", "JAVA"]
     */
    public static List<String> cleanLabels(List<String> labels) {
        return
                labels.stream()
                        .filter(s -> !s.isBlank())
                        .map(String::trim)
                        .map(String::toUpperCase)
                        .toList();
    }
}
