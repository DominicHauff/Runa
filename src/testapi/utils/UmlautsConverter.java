package testapi.utils;

public class UmlautsConverter {
    public static String removeUmlauts(final String umlautsString) {
        if (umlautsString == null) return null;
        StringBuilder sb = new StringBuilder();
        for (char c : umlautsString.toCharArray()) {
            switch (c) {
                case 'Ü' -> sb.append("U");
                case 'ü' -> sb.append("u");
                case 'Ö' -> sb.append("O");
                case 'ö' -> sb.append("o");
                case 'Ä' -> sb.append("A");
                case 'ä' -> sb.append("a");
                default -> sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String convertToUmlauts(final String noUmlautsString) {
        StringBuilder sb = new StringBuilder();
        char[] chars = noUmlautsString.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            String s = chars[i] + Character.toString(chars[i + 1]);
            switch (s) {
                case "UE" -> chars[i] = 'Ü';
                case "ue" -> chars[i] = 'ü';
                case "OE" -> chars[i] = 'Ö';
                case "oe" -> chars[i] = 'ö';
                case "AE" -> chars[i] = 'Ä';
                case "ae" -> chars[i] = 'ä';
                default -> {
                }
            }
        }
        int shift = 0;
        for (int i = 0; i + shift < chars.length; i++) {
            sb.append(chars[i + shift]);
            if (Character.toString(chars[i]).matches("[ÜüÖöÄä]")) shift++;
        }
        return sb.toString();
    }
}
