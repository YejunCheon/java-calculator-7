package calculator;


public class Calculator {

    private Parser parser;
    private OperationExecutor operationExecutor;

    public Calculator(Parser parser, OperationExecutor operationExecutor) {
        this.parser = parser;
        this.operationExecutor = operationExecutor;
    }

    public long add(String s){
        String formula = parser.saveDelimiterAndReturnFormula(s);
        long[] operands = parser.parseFormula(formula);
        return operationExecutor.addAll(operands);
    }
}
