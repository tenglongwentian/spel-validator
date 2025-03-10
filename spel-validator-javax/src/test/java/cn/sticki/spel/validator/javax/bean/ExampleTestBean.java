package cn.sticki.spel.validator.javax.bean;

import cn.sticki.spel.validator.constrain.SpelAssert;
import cn.sticki.spel.validator.constrain.SpelNotNull;
import cn.sticki.spel.validator.javax.SpelValid;
import cn.sticki.spel.validator.test.util.ID;
import cn.sticki.spel.validator.test.util.VerifyFailedField;
import cn.sticki.spel.validator.test.util.VerifyObject;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 作为示例的测试 bean
 *
 * @author 阿杆
 * @version 1.0
 * @since 2024/6/13
 */
public class ExampleTestBean {

    @Data
    @SpelValid
    public static class ParamTestBean implements ID {

        private int id;

        @NotNull
        private Boolean switchAudio;

        /**
         * 当 switchAudio 为 true 时，校验 audioContent，audioContent 不能为null
         */
        @SpelNotNull(condition = "#this.switchAudio == true", message = "语音内容不能为空")
        private String audioContent;

        /**
         * 枚举值校验
         * <p>
         * 通过静态方法调用，校验枚举值是否存在
         */
        @SpelAssert(assertTrue = " T(cn.sticki.spel.validator.javax.enums.ExampleEnum).getByCode(#this.testEnum) != null ", message = "枚举值不合法")
        private Integer testEnum;

    }

    public static List<VerifyObject> testCase() {
        ArrayList<VerifyObject> result = new ArrayList<>();

        ParamTestBean bean = new ParamTestBean();
        bean.setId(1);
        bean.setSwitchAudio(true);
        bean.setAudioContent("hello");
        bean.setTestEnum(1);
        result.add(VerifyObject.of(bean));

        ParamTestBean bean2 = new ParamTestBean();
        bean2.setId(2);
        bean2.setSwitchAudio(null);
        bean2.setAudioContent(null);
        bean2.setTestEnum(0);
        result.add(VerifyObject.of(
                bean2,
                VerifyFailedField.of(ParamTestBean::getSwitchAudio),
                VerifyFailedField.of(ParamTestBean::getTestEnum, "枚举值不合法")
        ));

        ParamTestBean bean3 = new ParamTestBean();
        bean3.setId(3);
        bean3.setSwitchAudio(null);
        bean3.setAudioContent(null);
        bean3.setTestEnum(null);
        result.add(VerifyObject.of(bean3, true));

        return result;
    }

    public static List<VerifyObject> innerTestCase() {
        ArrayList<VerifyObject> result = new ArrayList<>();

        InnerTestBean bean = InnerTestBean.builder().id(1)
                .testClass(InnerTestBean.TestClass.builder().testInt(1).build())
                .build();
        result.add(VerifyObject.of(bean));

        InnerTestBean bean2 = InnerTestBean.builder().id(2)
                .testClass(InnerTestBean.TestClass.builder().testInt(null).build())
                .build();
        result.add(VerifyObject.of(bean2, VerifyFailedField.of("testClass.testInt")));

        InnerTestBean bean3 = InnerTestBean.builder().id(3)
                .testClass(null)
                .build();
        result.add(VerifyObject.of(bean3));

        return result;
    }

    /**
     * 内部嵌套类测试
     */
    @Data
    @Builder
    @SpelValid
    public static class InnerTestBean implements ID {

        private int id;

        @SpelValid()
        private TestClass testClass;

        @Data
        @Builder
        public static class TestClass {

            @SpelNotNull
            private Integer testInt;

        }

    }

}
