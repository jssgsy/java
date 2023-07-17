package com.univ.thirdutils.orika;

import lombok.Data;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示orika泛型类转换使用
 *
 * @author univ
 * date 2023/7/17
 */
public class GenericOrikaTest {

    /**
     * 要解决的问题：
     *  将一个List(其中元素类型已知)转换成List<T>
     * 场景：
     * 方法：借助TypeBuilder
     * 底层本质：ParameterizedType的应用
     */
    @Test
    public void t1() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        G1DTO g1DTO = new G1DTO();
        g1DTO.setName("g1_dto_name");
        g1DTO.setAddress("g1_dto_address");
        ArrayList<G1DTO> g1DTOS = new ArrayList<>();
        g1DTOS.add(g1DTO);

        // 指定源类型为：G1DTO, 注意，不是List<G1DTO>，因为这里调用的就是mapAsList方法
        // 输入必须是具体的类型而不能是泛型，这是自然 o - o
        Type<G1DTO> sourceType = new TypeBuilder<G1DTO>(){}.build();

        // 指定目标类型为：G1, 注意，不是List<G1>，因为这里调用的就是mapAsList方法
        Type<G1> destType = new TypeBuilder<G1>(){}.build();

        List<G1> map = mapperFacade.mapAsList(g1DTOS, sourceType, destType);
        System.out.println(map);

        // 经验证，这里没法使用map方法来达到相同的目标。
        // 看来要明确指定是转换成对象还是转换成集合。
        // 如下代码是不ok的
        // 指定目标类型为：List<G1DTO>
        /*Type<List<G1DTO>> sourceType2 = new TypeBuilder<List<G1DTO>>(){}.build();
        Type<List<G1>> destType2 = new TypeBuilder<List<G1>>(){}.build();
        List<G1> map2 = mapperFacade.map(g1DTOS, sourceType2, destType2);
        System.out.println(map2);   // 输出：[]*/

    }

}

@Data
class G1DTO {
    private String name;
    private String address;
}

@Data
class G1 {
    private String name;
    private String address;
}
