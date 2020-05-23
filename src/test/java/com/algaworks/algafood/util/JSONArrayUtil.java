package com.algaworks.algafood.util;

import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONException;

import lombok.Getter;

@Getter
public class JSONArrayUtil {
	
	private JSONArray jsonArray;

	private String jsonArrayString;
	
	private String[] jsonObjects;
	
	public JSONArrayUtil(String jsonArrayString) {
		try {
			this.jsonArray = new JSONArray(jsonArrayString);
			this.jsonArrayString = jsonArrayString;
			
			initializeJsonObjects();
		} catch (JSONException e) {
			throw new RuntimeException("Erro na string json", e);
		}
	}

	public void initializeJsonObjects() {
		jsonObjects = new String[jsonArray.length()];
		IntStream.range(0, this.jsonArray.length()).forEach( i -> {
			try {
				String jsonObjectString = jsonArray.get(i).toString();
				jsonObjects[i] = jsonObjectString;
			} catch (JSONException e) {
				throw new RuntimeException("Erro ao inicializar array de objetos json", e);
			}
		});
	}
	
	
}
