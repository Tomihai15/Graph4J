/*
 * Copyright (C) 2022 Cristian Frăsinaru and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graph4j.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Utility methods.
 *
 * @author Cristian Frăsinaru
 */
public class Tools {

    private static final SimpleDateFormat dateTimeFormat
            = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public static String formatTimestamp(Date date) {
        if (date == null) {
            return "";
        }
        return dateTimeFormat.format(date);
    }

    /**
     *
     * @param d a value.
     * @param decimalPlace the decimal position to round the number.
     * @return the rounded value.
     */
    public static double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     *
     * @param matrix a matrix of integers.
     * @return the maximum value in the matrix.
     */
    public static int maxValue(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (max < matrix[i][j]) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }

    /**
     *
     * @param matrix a matrix of doubles.
     * @return the maximum value in the matrix.
     */
    public static double maxValue(double[][] matrix) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (max < matrix[i][j]) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }

    private static int maxLength(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int len = String.valueOf(matrix[i][j]).length();
                if (max < len) {
                    max = len;
                }
            }
        }
        return max;
    }

    private static int maxLength(double[][] matrix, int decimals) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int len;
                if (matrix[i][j] == Double.MAX_VALUE) {
                    len = "MaxValue".length();
                } else if (matrix[i][j] == Double.MIN_VALUE) {
                    len = "MinValue".length();
                } else if (matrix[i][j] == Double.POSITIVE_INFINITY) {
                    len = "Infinity".length();
                } else if (matrix[i][j] == Double.NEGATIVE_INFINITY) {
                    len = "-Infinity".length();
                } else {
                    len = String.format("%." + decimals + "f", matrix[i][j]).length();
                }
                if (max < len) {
                    max = len;
                }
            }
        }
        return max;
    }

    /**
     *
     * @param matrix a matrix of integers.
     */
    public static void printMatrix(int[][] matrix) {
        int cellWidth = maxLength(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(String.format("%" + cellWidth + "s", matrix[i][j]) + " ");
            }
            System.out.println("");
        }
    }

    /**
     *
     * @param matrix a matrix of doubles.
     * @param decimals how many decimals to use for rounding.
     */
    public static void printMatrix(double[][] matrix, int decimals) {
        int cellWidth = maxLength(matrix, decimals);
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < matrix[0].length; j++) {
                String s;
                if (matrix[i][j] == Double.MAX_VALUE) {
                    s = "MaxValue";
                } else if (matrix[i][j] == Double.MIN_VALUE) {
                    s = "MinValue";
                } else if (matrix[i][j] == Double.POSITIVE_INFINITY) {
                    s = "Infinity";
                } else if (matrix[i][j] == Double.NEGATIVE_INFINITY) {
                    s = "-Infinity";
                } else {
                    s = String.format("%." + decimals + "f", matrix[i][j]);
                }
                System.out.print(String.format("%" + cellWidth + "s", s) + "| ");
            }
            System.out.println("");
        }
    }

    /**
     *
     * @param x a value.
     * @return the base 2 logarithm of the value.
     */
    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    /**
     *
     * @param str a string.
     * @param length a length.
     * @return the string padded with spaces to the left such that it has the
     * specified length.
     */
    public static String padLeft(String str, int length) {
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - str.length()) {
            sb.append(' ');
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     *
     * @param str a string.
     * @param length a length.
     * @return the string padded with spaces to the right such that it has the
     * specified length.
     */
    public static String padRight(String str, int length) {
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }

    /**
     * Returns the first non-null value.
     *
     * @param <T> a generic type.
     * @param values an array of values.
     * @return the first value that is not {@code null}.
     */
    public static <T> T coalesce(T... values) {
        for (T value : values) {
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * Shuffles the values in the specified array.
     *
     * @param <T> a generic type.
     * @param array an array of values.
     */
    public static <T> void shuffle(T[] array) {
        Random random = new Random();
        for (int i = 0, n = array.length; i < n; i++) {
            int j = i + random.nextInt(n - i);
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}
