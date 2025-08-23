package com.jdojo.control;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A {@link StringConverter} for {@link LocalDate} objects.
 * <p>
 * This converter handles the conversion between a {@code LocalDate} and its
 * string representation using a specified date pattern. It gracefully handles
 * null values and parsing errors, making it robust for use in UI controls.
 */
public class LocalDateStringConverter extends StringConverter<LocalDate> {
    /**
     * The default date pattern "MM/dd/yyyy" used if no other pattern is specified.
     */
    public static final String DEFAULT_PATTERN = "MM/dd/yyyy";

    private final DateTimeFormatter dtFormatter;

    /**
     * Creates a converter using the {@link #DEFAULT_PATTERN}.
     */
    public LocalDateStringConverter() {
        this(DEFAULT_PATTERN);
    }

    /**
     * Creates a converter using the specified date pattern.
     *
     * @param pattern The non-null date pattern to use for formatting and parsing.
     */
    public LocalDateStringConverter(String pattern) {
        this.dtFormatter = DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * Converts a {@link LocalDate} object to its string representation.
     *
     * @param date The date object to convert.
     * @return The formatted date string, or null if the date is null.
     */
    @Override
    public String toString(LocalDate date) {
        return (date == null) ? null : dtFormatter.format(date);
    }

    /**
     * Converts a string to a {@link LocalDate} object.
     * <p>
     * The string is parsed using the configured date pattern.
     *
     * @param string The string to convert.
     * @return The parsed {@code LocalDate}, or null if the string is null, empty,
     *         or does not represent a valid date according to the pattern.
     */
    @Override
    public LocalDate fromString(String string) {
        if (string == null || string.trim().isEmpty()) {
            return null;
        }

        try {
            return LocalDate.parse(string, dtFormatter);
        } catch (DateTimeParseException e) {
            // The string is not in a valid format. Return null to indicate failure.
            return null;
        }
    }
}
