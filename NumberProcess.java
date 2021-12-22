
import java.util.Map;
import java.util.TreeMap;

public class NumberProcess {

    private final static TreeMap<Integer, String> romanMap = new TreeMap<>();

    static {
        romanMap.put(1, "I");
        romanMap.put(4, "IV");
        romanMap.put(5, "V");
        romanMap.put(9, "IX");
        romanMap.put(10, "X");
        romanMap.put(40, "XL");
        romanMap.put(50, "L");
        romanMap.put(90, "XC");
        romanMap.put(100, "C");
    }

    protected Number createNumber(String str) throws Exception {
        int value = 0;
        NumberType type;

        try {
            value = Integer.parseInt(str);
            type = NumberType.ARABIC;
        } catch (NumberFormatException e) {
            value = toArabicNumber(str,0);
            type = NumberType.ROMAN;
        }
        if (value < 1 || value > 10) {
            throw new Exception("Число задано неверно, пожалуйста, выберите числа в диапозоне от 1 до 10");
        }

        return new Number(value, type);
    }

    protected Number createNumber(String symbol, NumberType type) throws Exception {

        Number number = createNumber(symbol);
        if (number.getType() != type) {
            throw new Exception("Числа разных типов, используйте один тип вводных значений");
        }

        return number;
    }

    private int toArabicNumber(String romanStr, int number) throws Exception {
        if (number < 0) throw new Exception("Cимвол введен неверно");

        int result = number;
        number = charToNumber(romanStr.charAt(romanStr.length()-1));

        if(result > number + 1)
            result -= number;
        else result += number;

        if (romanStr.length()-1 == 0) return result;

        romanStr = romanStr.substring(0, romanStr.length()-1);

        return toArabicNumber(romanStr, result);
    }

    private int charToNumber(char symbol) {
         int result = -1;

        for (Map.Entry<Integer, String> entry: romanMap.entrySet()) {
            if(String.valueOf(symbol).equals(entry.getValue()))
                result = entry.getKey();
        }

         return result;
    }

    protected String toRomanNumber(int number) {
        int l = romanMap.floorKey(number);
        if (number == l) {
            return romanMap.get(number);
        }
        return romanMap.get(l) + toRomanNumber(number - l);
    }

}
