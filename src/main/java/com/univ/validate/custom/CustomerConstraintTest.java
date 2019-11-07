package com.univ.validate.custom;

import java.util.Arrays;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

/**
 * @author univ
 * @date 2019/11/1 7:37 PM
 * @description
 */
public class CustomerConstraintTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 自定义校验器
     */
    @Test
    public void customValidatorSingle() {
        DemoSingle demoSingle = new DemoSingle();
        demoSingle.setId(100);
        demoSingle.setName("xx");
        Set<ConstraintViolation<DemoSingle>> validate = validator.validate(demoSingle);
        for (ConstraintViolation<DemoSingle> result : validate) {
            System.out.println(result.getMessage());
        }
    }

    /**
     * 自定义校验器
     */
    @Test
    public void customValidatorMutiple() {
        DemoMutiple demoMutiple = new DemoMutiple();
        demoMutiple.setName("xx");
        demoMutiple.setAge(5);
        Set<ConstraintViolation<DemoMutiple>> validate = validator.validate(demoMutiple);
        for (ConstraintViolation<DemoMutiple> result : validate) {
            System.out.println(result.getMessage());
        }
    }

    @Test
    public void customValidatorList() {
        DemoList demoList = new DemoList();
        demoList.setName("xxxxx");
        demoList.setAge(5);
        DemoSingle demoSingle = new DemoSingle();
        demoSingle.setId(100);
        demoSingle.setName("");
        demoList.setList(Arrays.asList(demoSingle));

        Set<ConstraintViolation<DemoList>> validate = validator.validate(demoList);
        for (ConstraintViolation<DemoList> result : validate) {
            System.out.println(result.getMessage());
        }
    }

    @Test
    public void lengthValidator() {
        DemoString demoString = new DemoString();
        Set<ConstraintViolation<DemoString>> validate = validator.validate(demoString);
        for (ConstraintViolation<DemoString> result : validate) {
            System.out.println(result.getMessage());
        }
    }

}
