package cn.sticki.validator.spel;

import cn.sticki.validator.spel.bean.*;
import cn.sticki.validator.spel.util.ValidateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 约束类测试
 *
 * @author 阿杆
 * @version 1.0
 * @since 2024/6/15
 */
public class ConstrainTest {

    /**
     * 这是一个测试示例
     */
    @Test
    void testExample() {
        boolean verified = ValidateUtil.checkConstraintResult(ExampleTestBean.testCase());
        Assertions.assertTrue(verified);
    }

    @Test
    void testSpelAssert() {
        boolean verified = ValidateUtil.checkConstraintResult(SpelAssertTestBean.testCase());
        Assertions.assertTrue(verified);
    }

    @Test
    void testSpelNotBlank() {
        boolean verified = ValidateUtil.checkConstraintResult(SpelNotBlankTestBean.testCase());
        Assertions.assertTrue(verified);
    }

    @Test
    void testSpelNotEmpty() {
        boolean verifiedParam = ValidateUtil.checkConstraintResult(SpelNotEmptyTestBean.paramTestCase());
        Assertions.assertTrue(verifiedParam, "spelNotEmpty param test failed");

        boolean verifiedType = ValidateUtil.checkConstraintResult(SpelNotEmptyTestBean.typeTestCase());
        Assertions.assertTrue(verifiedType, "spelNotEmpty type test failed");
    }

    @Test
    void testSpelNotNull() {
        boolean paramTest = ValidateUtil.checkConstraintResult(SpelNotNullTestBean.paramTestCase());
        Assertions.assertTrue(paramTest, "spelNotNull param test failed");

        boolean typeTest = ValidateUtil.checkConstraintResult(SpelNotNullTestBean.typeTestCase());
        Assertions.assertTrue(typeTest, "spelNotNull type test failed");

        boolean repeatableTest = ValidateUtil.checkConstraintResult(SpelNotNullTestBean.repeatableTestCase());
        Assertions.assertTrue(repeatableTest, "spelNotNull repeatable test failed");
    }

    @Test
    void testSpelNull() {

    }

    @Test
    void testSpelSize() {

    }
}
