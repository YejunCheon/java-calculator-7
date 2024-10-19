package calculator;

public class OperationExecutor {

    public OperationExecutor() {
    }
    public long add(long[] operands){
        long sum = 0;
        for (long number : operands) {
            if (number < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + number);
            }
            sum += number;
        }
        return sum;
    }
}
