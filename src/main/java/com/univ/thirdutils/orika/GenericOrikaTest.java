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

        // 指定源类型为：List<G1DTO>
        Type<G1DTO> sourceType = new TypeBuilder<G1DTO>(){}.build();
        // 指定目标类型为：List<G1>
        Type<G1> destType = new TypeBuilder<G1>(){}.build();

        List<G1> map = mapperFacade.mapAsList(g1DTOS, sourceType, destType);
        System.out.println(map);
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
