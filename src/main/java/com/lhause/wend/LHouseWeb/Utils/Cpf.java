package com.lhause.wend.LHouseWeb.Utils;

/**
 *
 * @author Wendley S
 */
public class Cpf {

    /**
     * Valida um cpf
     *
     * @param  cpf
     *         numeros do cpf: 12345..
     *
     * @return  se o cpf e verdadeiro ou não
     */
    public static Boolean cpfValido(String cpf) {
        int cpfLenght = 11;
        if (cpf.length() != cpfLenght) {
            return false;
        }

        StringBuilder bulder = new StringBuilder(cpfLenght);
        int[] cpfIntArr = new int[cpfLenght];
        int[] multiplicationArr = new int[10];

        for (int i = 0; i < 9; i++) {
            multiplicationArr[i] = i + 1;
        }

        for (int i = 0; i < cpfLenght; i++) {
            cpfIntArr[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int[] multiplyResult = multiplyTwoArrays(cpfIntArr, multiplicationArr, 9);
        int sum = sumIntArray(multiplyResult);
        cpfIntArr[9] = cpfDigit(sum);

        for (int i = 0; i < 10; i++) {
            multiplicationArr[i] = i + 0;
        }

        multiplyResult = multiplyTwoArrays(cpfIntArr, multiplicationArr, 10);
        sum = sumIntArray(multiplyResult);
        cpfIntArr[10] = cpfDigit(sum);

        for (int i = 0; i < cpfIntArr.length; i++) {
            bulder.append(cpfIntArr[i]).toString();
        }

        return cpf.equals(bulder.toString());
    }

    private static int cpfDigit(int multplicationSumResult) {
        int r = multplicationSumResult % 11;
        return (r > 9) ? 0 : r;
    }

    private static int[] multiplyTwoArrays(int[] arr1, int[] arr2, int buffer) {
        int[] r = new int[buffer];

        for (int i = 0; i < buffer; i++) {
            r[i] = arr1[i] * arr2[i];
        }

        return r;
    }

    private static int sumIntArray(int[] arr) {
        int r = 0;
        for (int i = 0; i < arr.length; i++) {
            r += arr[i];
        }
        return r;
    }
}
