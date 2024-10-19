package calculator;


public class Calculator {

    private Parser delimiterParser;
    private OperationExecutor positiveNumberOperationExecutor;

    public Calculator(Parser delimiterParser, OperationExecutor positiveNumberOperationExecutor) {
        this.delimiterParser = delimiterParser;
        this.positiveNumberOperationExecutor = positiveNumberOperationExecutor;
    }

    public long add(String s){
        long[] operands = delimiterParser.parseToLongArray(s);
        return positiveNumberOperationExecutor.addAll(operands);
    }
}
