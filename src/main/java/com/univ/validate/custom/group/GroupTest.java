package com.univ.validate.custom.group;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.Test;

/**
 * @author univ date 2023/6/29
 */
public class GroupTest {

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
