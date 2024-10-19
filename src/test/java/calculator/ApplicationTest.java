package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 커스텀_미사용() {
        assertSimpleTest(() -> {
            run("1,2");
            assertThat(output()).contains("결과 : 3");
        });
    }
    @Test
    void 한자리() {
        assertSimpleTest(() -> {
            run("3");
            assertThat(output()).contains("결과 : 3");
        });
    }
    @Test
    void 이스케이프_문자_커스텀구분자() {
        assertSimpleTest(() -> {
            run("//$\\n1$5$5");
            assertThat(output()).contains("결과 : 11");
        });
    }

    @Test
    void 혼합된_구분자_테스트() {
        assertSimpleTest(() -> {
            run("//#\\n1#5,5,3");
            assertThat(output()).contains("결과 : 14");
        });
    }

    @Test
    void 피연산자_미입력_종료_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("2,3,"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 구분자_중복_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("2,,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }


    @Test
    void 음수_예외() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 영_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("0,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자가_아닌_피연산자_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("0,c,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
