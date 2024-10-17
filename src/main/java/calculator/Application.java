package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    private static final String SPECIAL_CHARACTERS = "[.\\\\*+?()\\[\\]{}|^$]";

    public static void main(String[] args) {
        // 사용자 입력 받기
        System.out.println("덧셈할 문자열을 입력해 주세요.\n");
        String line = Console.readLine();

        // 기본 구분자 설정
        String delimiterRegex = ",|:";

        // 커스텀 구분자 검증
        if (line.startsWith("//")) {
            int delimiterEndIndex = line.indexOf("\\n");
            if (delimiterEndIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 뒤에 줄바꿈이 필요합니다.");
            }

            String customDelimiter = line.substring(2, delimiterEndIndex);                                         // //과 \n 사이의 문자열 추출
            customDelimiter = customDelimiter.replaceAll(SPECIAL_CHARACTERS,"\\\\$0");                  // 정규식 이스케이프 문자 전처리
            delimiterRegex = delimiterRegex + "|" + customDelimiter;                                               // 구분자 추가
            line = line.substring(delimiterEndIndex + (line.charAt(delimiterEndIndex) == '\n' ? 1 : 2)); // 계산 요구 문자열로 저장.
        }

        long sum = 0;
        // 계산부
        try {
            // 구분자로 분할 -> 실패 시 IllegalArgumentException 으로 throw 됨.
            String[] tokens = line.split(delimiterRegex);
            // 각 수를 순회하며 sum에 누적.
            for (String token : tokens) {
                if (!token.isEmpty()) {
                    int number = Integer.parseInt(token);
                    // 예외처리 : 음수인 경우 throw
                    if (number < 0) {
                        throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + number);
                    }
                    sum += number;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        System.out.println("결과 : "+sum);
    }
}
