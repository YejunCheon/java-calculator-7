package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        // 사용자 입력 받기
        System.out.println("덧셈할 문자열을 입력해 주세요.\n");
        String line = Console.readLine();

        // 계산
        Parser basicParser = new Parser(",|:");
        Calculator addCalc = new Calculator(basicParser);

        long ans = addCalc.add(line);

        // 결과 출력
        System.out.println("결과 : " + ans);
    }
}
