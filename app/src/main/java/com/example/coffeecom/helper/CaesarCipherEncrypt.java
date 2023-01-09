package com.example.coffeecom.helper;

public class CaesarCipherEncrypt {

    public String caesarCipherEncrypt(String plainPassword, int shift) {
        String result = "";

        for (int i = 0; i < plainPassword.length(); i++) {
            if (Character.isDigit(plainPassword.charAt(i))) {
                char ch = (char) (((int) plainPassword.charAt(i) + shift - 48) % 10 +48);
                result += ch;
            }
            else if (Character.isUpperCase(plainPassword.charAt(i))) {
                char ch = (char) (((int) plainPassword.charAt(i) +
                        shift - 65) % 26 + 65);
                result += ch;
            } else if(Character.isLowerCase(plainPassword.charAt(i))){
                char ch = (char) (((int) plainPassword.charAt(i) +
                        shift - 97) % 26 + 97);
                result += ch;
            }
        }
        return result;
    }

    public String caesarCipherDecrypt(String encryptedPassword, int shift) {
        String result = "";
        int encryptShift = shift;
        for (int i = 0; i < encryptedPassword.length(); i++) {
            shift = encryptShift;
            if (Character.isDigit(encryptedPassword.charAt(i))) {
                shift = 10 -shift;
                if (shift <= 0) {
                    shift += 10;
                }
                char ch = (char) (((int) encryptedPassword.charAt(i) + shift - 48) % 10 +48);
                result += ch;
            }
            else if (Character.isUpperCase(encryptedPassword.charAt(i))) {
                shift = 26 - shift;
                if (shift<=0) {
                    shift+=26;
                }
                char ch = (char) (((int) encryptedPassword.charAt(i) + shift - 65) % 26 + 65);
                result += ch;
            } else if(Character.isLowerCase(encryptedPassword.charAt(i))){
                shift = 26 - shift;
                if (shift<=0) {
                    shift+=26;
                }
                char ch = (char) (((int) encryptedPassword.charAt(i) + shift - 97) % 26 + 97);
                result += ch;
            }
        }
        return result;
    }
}
