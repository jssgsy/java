package com.univ.validate.custom;

import java.util.Arrays;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.univ.validate.custom.bean.DemoClassOrInterface;
import com.univ.validate.custom.bean.DemoCompound;
import com.univ.validate.custom.bean.DemoGroup;
import com.univ.validate.custom.bean.DemoList;
import com.univ.validate.custom.bean.DemoMutiple;
import com.univ.validate.custom.bean.DemoNested;
import com.univ.validate.custom.bean.DemoSingle;
import com.univ.validate.custom.bean.DemoString;
import com.univ.validate.custom.group.GroupA;
import com.univ.validate.custom.group.GroupB;

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

    @Test
    public void compoundValidator() {
        DemoCompound demoCompound = new DemoCompound();
        demoCompound.setName("123456");
        Set<ConstraintViolation<DemoCompound>> validate = validator.validate(demoCompound);
        for (ConstraintViolation<DemoCompound> result : validate) {
            System.out.println(result.getMessage());
        }
    }

    @Test
    public void classOrInterfaceValidator() {
        DemoClassOrInterface demoClassOrInterface = new DemoClassOrInterface();
        demoClassOrInterface.setAge(10);
        Set<ConstraintViolation<DemoClassOrInterface>> validate = validator.validate(demoClassOrInterface);
        for (ConstraintViolation<DemoClassOrInterface> result : validate) {
            System.out.println(result.getMessage());
        }
    }

    // 嵌套验证
    @Test
    public void nested() {
        DemoNested demoNested = new DemoNested();
        demoNested.setAge(10);
        DemoNested.DemoInner demoInner = new DemoNested.DemoInner();
        demoInner.setHeight(100);
        demoNested.setDemoInner(demoInner);
        Set<ConstraintViolation<DemoNested>> validate = validator.validate(demoNested);
        for (ConstraintViolation<DemoNested> result : validate) {
            System.out.println(result.getMessage());
        }
    }

    // 组验证
    @Test
    public void group() {
        DemoGroup demoGroup = new DemoGroup();
        demoGroup.setNameDefault("1");
        // 验证默认组
        Set<ConstraintViolation<DemoGroup>> validate = validator.validate(demoGroup);
        for (ConstraintViolation<DemoGroup> result : validate) {
            System.out.println(result.getMessage());
        }
        System.out.println("-----------------");

        // 验证GroupA组
        demoGroup.setNameA("1");
        Set<ConstraintViolation<DemoGroup>> groupA = validator.validate(demoGroup, GroupA.class);
        for (ConstraintViolation<DemoGroup> result : groupA) {
            System.out.println(result.getMessage());
        }
        System.out.println("-----------------");

        // 验证GroupB组
        demoGroup.setNameB("2");
        Set<ConstraintViolation<DemoGroup>> groupB = validator.validate(demoGroup, GroupB.class);
        for (ConstraintViolation<DemoGroup> result : groupB) {
            System.out.println(result.getMessage());
        }
    }
}
