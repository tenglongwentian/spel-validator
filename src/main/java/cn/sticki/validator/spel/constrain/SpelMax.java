package cn.sticki.validator.spel.constrain;

import cn.sticki.validator.spel.SpelConstraint;
import cn.sticki.validator.spel.SpelValid;
import cn.sticki.validator.spel.constraintvalidator.SpelMaxValidator;
import org.intellij.lang.annotations.Language;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 被标记的元素值必须小于或等于指定的最小值。{@code null} 元素被认为是有效的。
 * <p>
 * 支持所有 {@link Number} 类型及它们的基本数据类型。
 *
 * @author 阿杆
 * @version 1.0
 * @since 2024/9/29
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Repeatable(SpelMax.List.class)
@SpelConstraint(validatedBy = SpelMaxValidator.class)
public @interface SpelMax {

    /**
     * 校验失败时的错误消息。
     */
    String message() default "必须小于或等于 {value}";

    /**
     * 约束开启条件，必须为合法的SpEL表达式，计算结果必须为boolean类型。
     * <p>
     * 当 表达式为空 或 计算结果为true 时，会对带注解的元素进行校验。
     * <p>
     * 默认情况下，开启校验。
     */
    @Language("SpEL")
    String condition() default "";

    /**
     * 分组条件，必须为合法的SpEL表达式。
     * <p>
     * 当分组信息不为空时，只有当 {@link SpelValid#spelGroups()} 中的分组信息与此处的分组信息有交集时，才会对带注解的元素进行校验。
     * <p>
     * 其计算结果可以是任何类型，但只有两个计算结果完全相等时，才被认为是相等的。
     */
    @Language("SpEL")
    String[] group() default {};

    /**
     * 指定元素最大值。必须为合法的SpEL表达式，
     * <p>
     * 表达式的计算结果必须为 {@link Number} 类型。
     */
    @Language("SpEL")
    String value() default "0";

    @Documented
    @Target(FIELD)
    @Retention(RUNTIME)
    @interface List {

        SpelMax[] value();

    }

}
