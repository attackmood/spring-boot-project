package com.cantaur.api.sample.service;

import com.cantaur.api.sample.mapper.SampleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService{
    final SampleMapper sampleMapper;

    @Override
    public String getSample(){
        return sampleMapper.getSample();
    }
}
