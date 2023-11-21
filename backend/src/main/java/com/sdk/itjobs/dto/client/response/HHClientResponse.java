package com.sdk.itjobs.dto.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HHClientResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String title;
    private String minSalary = null;
    private String maxSalary = null;
    private String employer;
    @JsonProperty("alternate_url")
    private String url;

    @JsonProperty("salary")
    private void retrieveMinAndMaxSalaryFromSalary(Map<String, Object> salary) {
        if (salary != null) {
            Object from = salary.get("from");
            minSalary = (from != null) ? from.toString() : null;
            Object to = salary.get("to");
            maxSalary = (to != null) ? to.toString() : null;
        }
    }

    @JsonProperty("employer")
    private void retrieveEmployerFromEmployerData(Map<String, Object> employerData) {
        employer = employerData.get("name").toString();
    }
}
