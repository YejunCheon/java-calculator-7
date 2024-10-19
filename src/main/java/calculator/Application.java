package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        // 사용자 입력 받기
        System.out.println("덧셈할 문자열을 입력해 주세요.\n");
        String line = Console.readLine();

        // 계산
        Parser parser = new DelimiterParser(",|:");
        OperationExecutor operationExecutor = new PositiveNumberOperationExecutor();
        Calculator addCalc = new Calculator(parser, operationExecutor);

        long ans = addCalc.add(line);

        // 결과 출력
        System.out.println("결과 : " + ans);
    }
}
