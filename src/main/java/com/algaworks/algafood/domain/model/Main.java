package com.algaworks.algafood.domain.model;

public class Main {

	public static void main(String[] args) {
		Cozinha c1 = new Cozinha();
		Cozinha c2 = new Cozinha();
		
		c1.setId(1L);
		c1.setNome("a");
		
		c1.setId(1L);
		c1.setNome("b");
		
		System.out.println(c1.equals(c2));
	}
}
