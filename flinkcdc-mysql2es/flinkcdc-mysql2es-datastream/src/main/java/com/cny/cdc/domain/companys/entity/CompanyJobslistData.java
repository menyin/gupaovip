package com.cny.cdc.domain.companys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年08月01日 11:38
 */
@Data
@AllArgsConstructor
public class CompanyJobslistData {
        private ArrayList<CompanyJobslistDataJobs> jobs;//[],
        private String type;//"3",
        private String jobCount;//0
}
