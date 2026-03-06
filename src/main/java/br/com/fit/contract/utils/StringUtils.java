package br.com.fit.contract.utils;

public class StringUtils {

    private StringUtils() {
    }

    public static String maskCnpj(String numberCnpj) {
        if (numberCnpj == null) return null;
        return numberCnpj.replaceAll("^\\d{3}|\\d{2}$", "***");
    }
}
