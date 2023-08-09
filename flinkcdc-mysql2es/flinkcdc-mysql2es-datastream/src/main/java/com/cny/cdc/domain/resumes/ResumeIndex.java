package com.cny.cdc.domain.resumes;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年08月01日 14:21
 */
@Data
@Document(indexName = "resume")
@AllArgsConstructor
public class ResumeIndex {
}
