package calculator;

import java.util.Arrays;

public class OperationExecutor {

    public OperationExecutor() {
    }
    public long addAll(long[] operands){
        validateIsPositiveNumber(operands);
        return Arrays.stream(operands).sum();
    }
    private void validateIsPositiveNumber(long[] operands){
        Arrays.stream(operands)
            .forEach( n ->{
                if (n <= 0) {
                    throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + n);
                }
            });
    }
}
