import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.*;

public class Proiz {
    public static void main(String[] args) {
        BigDecimal otv = ONE;

        BigDecimal n = valueOf(6);
        for (BigDecimal i = ONE; i.compareTo(n) < 0; i = i.add(ONE)) {
            for (BigDecimal j = ONE; j.compareTo(n) < 0; j = j.add(ONE)) {
                otv = otv.multiply(ONE.divide(i.add(j.multiply(j)), MathContext.DECIMAL128));
            }
        }

        System.out.println("Произведение ="  + otv.toPlainString());
    }
}
