package com.univ.validate.basic;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;
import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/12/29 10:06 AM
 * @description
 */
public class ValidateTest {
    
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void basic() {
        // 1. 获取validator对象,即this.MyConstraintSingle2

        // 2. 构造需要校验的对象
        BasicDemo basicDemo = new BasicDemo();
        // basicDemo.setDigit("fel");
        // 3. 校验对象，并获取校验的结果,会获取所有字段校验失败的信息
        Set<ConstraintViolation<BasicDemo>> validateResult = validator.validate(basicDemo);
        for (ConstraintViolation<BasicDemo> result : validateResult) {
            System.out.println(result.getMessage());
        }
    }

    /**
     * 只要有校验失败的“抛异常”
     */
    @Test
    public void basic2() {
        // 1. 获取validator对象,此时只要有一个字段校验失败就返回了
        Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

        // 2. 构造需要校验的对象
        BasicDemo basicDemo = new BasicDemo();

        // 3. 校验对象
        Set<ConstraintViolation<BasicDemo>> validateResult = validator.validate(basicDemo);
        for (ConstraintViolation<BasicDemo> result : validateResult) {
            System.out.println(result.getMessage());
        }
    }


}

