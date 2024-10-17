package calculator;

import java.util.Arrays;


public class Parser {


    private static final String SPECIAL_CHARACTERS = "[.\\\\*+?()\\[\\]{}|^$]";
    private String delimiterRegex;

    public Parser(String delimiterRegex) {
        this.delimiterRegex = delimiterRegex;
    }

    public long[] parseFormula(String formula) {
        String[] tokens;
        try {
            tokens = formula.split(delimiterRegex);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return Arrays.stream(tokens)
                .mapToLong(Long::parseLong)
                .toArray();
    }

    public String saveDelimiterAndReturnFormula(String line) {
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
        return line;
    }


}
