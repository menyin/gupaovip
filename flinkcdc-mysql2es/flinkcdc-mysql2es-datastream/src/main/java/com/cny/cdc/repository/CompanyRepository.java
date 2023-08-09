package com.cny.cdc.repository;

import com.cny.cdc.domain.companys.CompanyIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CompanyRepository extends ElasticsearchRepository<CompanyIndex,String> {
}
