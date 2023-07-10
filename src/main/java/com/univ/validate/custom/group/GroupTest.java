package com.univ.validate.custom.group;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * @author univ date 2023/6/29
 */
public class GroupTest {

    /**
     * 此时验证的就是默认的javax.validation.groups.Default组
     */
    @Test
    public void noGroup() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        UserDTO userDTO = new UserDTO();
        // 不指定验证组，默认就是校验Default组
        Set<ConstraintViolation<UserDTO>> validate = validator.validate(userDTO);
        printErrMsg(validate);
    }
    @Test
    public void save() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Set<ConstraintViolation<UserDTO>> validate = validator.validate(userDTO, Create.class);
        printErrMsg(validate);
    }

    /**
     * 指定Default组与其它组一起校验
     */
    @Test
    public void saveAndDefault() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        UserDTO userDTO = new UserDTO();
        Set<ConstraintViolation<UserDTO>> validate = validator.validate(userDTO, Create.class, Default.class);
        printErrMsg(validate);
    }

    @Test
    public void update() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        UserDTO userDTO = new UserDTO();
        Set<ConstraintViolation<UserDTO>> validate = validator.validate(userDTO, Update.class);
        printErrMsg(validate);
    }

    @Test
    public void saveAndUpdate() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        UserDTO userDTO = new UserDTO();
        Set<ConstraintViolation<UserDTO>> validate = validator.validate(userDTO,
            Create.class, Update.class);
        printErrMsg(validate);
    }

    private void printErrMsg(Set<ConstraintViolation<UserDTO>> set) {
        for (ConstraintViolation<UserDTO> result : set) {
            System.out.println(result.getMessage());
        }
    }

}
