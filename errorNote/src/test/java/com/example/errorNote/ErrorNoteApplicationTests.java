package com.example.errorNote;


import com.example.errorNote.Modele.EtatProbleme;
import com.example.errorNote.Repository.RepoEtat;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ErrorNoteApplicationTests {

	@Autowired
	RepoEtat repoEtat;
	/*@Test
	void contextLoads() {
	}*/
	@Test
	@Order(1)
	public void create() {
		EtatProbleme etatProbleme =new  EtatProbleme(7L,"Ballo");
		repoEtat.save(etatProbleme);
	}

	/*@Test
	public void creat(){
		ControllerEtatTest e = new ControllerEtatTest();
		e.create();
	}*/



}
