package com.example.springbootdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootdemo.entity.Company;
import com.example.springbootdemo.mapper.CompanyMapper;
import com.example.springbootdemo.service.CompanyService;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年04月14日 17:44
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}
