package com.sdk.itjobs.dto.client.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperJobClientResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("profession")
    private String title;
    @JsonProperty("payment_from")
    private String minSalary;
    @JsonProperty("payment_to")
    private String maxSalary;
    private String employer;
    @JsonProperty("link")
    private String url;

    @JsonProperty("client")
    private void retrieveEmployerFromClient(Map<String, Object> client) {
        employer = client.get("title").toString();
    }
}
