package com.hotmart.models.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiNewsResponse implements Serializable{
	
	private static final long serialVersionUID = 6163761721577020279L;
	
	@JsonProperty
	private String status;

	@JsonProperty
	private Integer totalResults;
	
}
