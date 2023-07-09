package br.com.mailsender.util;

import org.modelmapper.ModelMapper;

public class ClassMapper {

    public static <O, D> D parseObject(ModelMapper modelMapper, O origin, Class<D> destination) {
        return modelMapper.map(origin, destination);
    }

}
