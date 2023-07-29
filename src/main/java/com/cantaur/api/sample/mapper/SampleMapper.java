package com.cantaur.api.sample.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleMapper {
    String getSample();
}
