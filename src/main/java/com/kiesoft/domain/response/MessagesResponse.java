package com.kiesoft.domain.response;

import java.util.List;

public interface MessagesResponse extends GenericResponse {

	List<String> getItems();
}
