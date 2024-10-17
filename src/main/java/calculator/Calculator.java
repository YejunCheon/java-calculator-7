package calculator;


public class Calculator {

    private Parser parser;

    public Calculator(Parser parser) {
        this.parser = parser;
    }

    public long add(String s){
        long sum = 0;
        String formula = parser.saveDelimiterAndReturnFormula(s);
        long[] tokens = parser.parseFormula(formula);

        for (long number : tokens) {
            if (number < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + number);
            }
            sum += number;
        }
        return sum;
    }
}
