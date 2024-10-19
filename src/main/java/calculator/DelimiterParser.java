package calculator;

import java.util.Arrays;


public class DelimiterParser implements Parser{


    private static final String SPECIAL_CHARACTERS = "[.\\\\*+?()\\[\\]{}|^$]";
    private String delimiterRegex;

    public DelimiterParser(String delimiterRegex) {
        this.delimiterRegex = delimiterRegex;
    }

    @Override
    public long[] parseToLongArray(String line) {
        String formula = this.saveDelimiterAndReturnFormula(line);
        return this.parseFormula(formula);
    }

    private long[] parseFormula(String formula) {
        if (formula == null || formula.isEmpty())
            return new long[]{0};
        if ( formula.matches(".*" + delimiterRegex + "$") )
            throw new IllegalArgumentException("마지막 피연산자가 입력되지 않았습니다.");
        if ( formula.matches( delimiterRegex + delimiterRegex) )
            throw new IllegalArgumentException("구분자와 구분자 사이에 피연산자가 존재하지 않습니다.");
        try {
            String[] tokens = formula.split(delimiterRegex);
            return Arrays.stream(tokens)
                    .mapToLong(Long::parseLong)
                    .toArray();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 피연산자가 입력되었습니다.");
        } catch (Exception e) {
            throw new IllegalArgumentException("파싱 중 오류가 발생하였습니다.",e);
        }
    }

    private String saveDelimiterAndReturnFormula(String line) {
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
