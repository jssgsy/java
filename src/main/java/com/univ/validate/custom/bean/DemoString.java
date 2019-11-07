package com.univ.validate.custom.bean;

import com.univ.validate.custom.constrain.LengthContraint;

import lombok.Data;

/**
 * @author univ
 * @date 2019/11/7 9:55 AM
 * @description
 */
@Data
public class DemoString {

    @LengthContraint(min = 3, message = "最小长度不能小于3")
    private String name;

    @LengthContraint(min = 5, message = "最小长度不能小于5")
    private String address;
}
