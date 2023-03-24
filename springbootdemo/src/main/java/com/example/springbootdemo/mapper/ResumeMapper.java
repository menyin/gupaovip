package com.example.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo.entity.Resume;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月17日 16:01
 */
@Mapper
public interface ResumeMapper extends BaseMapper<Resume> {
}
