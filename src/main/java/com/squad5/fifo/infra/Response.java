package com.squad5.fifo.infra;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Response<T> {
	
	private T data;
	
	private List<String> errors;
		
}
