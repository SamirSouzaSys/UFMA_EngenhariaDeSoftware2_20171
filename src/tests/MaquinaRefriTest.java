package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.Dinheiro;
import app.MaquinaRefri;
import app.Refri;

public class MaquinaRefriTest {
	public static MaquinaRefri maq1;

	@Before
	public void beforeClass(){

		maq1 = new MaquinaRefri("Máquina 1", "Shopping 1");
	}
	
	@After
	public void afterClass(){

		maq1 = null;
	}	
	public void prepararMaquinaTroco() {
//		MaquinaRefri maq1 = new MaquinaRefri("Máquina 1", "Shopping 1");

		// Adicionar Dinheiro
		maq1.addDinheiroTroco(new Dinheiro(10,0.5));
		maq1.addDinheiroTroco(new Dinheiro(10,5.0));
		maq1.addDinheiroTroco(new Dinheiro(10,2.0));
		maq1.addDinheiroTroco(new Dinheiro(10,10.0));
		maq1.addDinheiroTroco(new Dinheiro(20,0.01));

		//(10*0.5)+(10*5)+(10*2)+(10*10)+(20*0.01) = 175.2
	}
	
	public void prepararMaquinaProduto() {
//		MaquinaRefri maq1 = new MaquinaRefri("Máquina 1", "Shopping 1");

		//AdicionarProduto
		maq1.addProduto(new Refri("RefriNome1", "", 1.5  ,4));
		maq1.addProduto(new Refri("RefriNome2", "", 2.0  ,8));
		maq1.addProduto(new Refri("RefriNome3", "", 1.25 ,7));
		maq1.addProduto(new Refri("RefriNome4", "", 2.5  ,6));
		maq1.addProduto(new Refri("RefriNome5", "", 2.25 ,10));
	}
	
	// Tests
	@Test
	public void checarMaquinaTrocoSucesso(){
		this.prepararMaquinaTroco();
		Double result = maq1.getTrocoDisponível(0.0);
		assertTrue (175.2 == result);
	}
	
	@Test
	public void checarMaquinaProdutosSucesso() {
		this.prepararMaquinaProduto();
		
		ArrayList<Refri> refrisTest = new ArrayList<Refri>();

		refrisTest.add(new Refri("RefriNome1", "", 1.5  ,4));
		refrisTest.add(new Refri("RefriNome2", "", 2.0  ,8));
		refrisTest.add(new Refri("RefriNome3", "", 1.25 ,7));
		refrisTest.add(new Refri("RefriNome4", "", 2.5  ,6));
		refrisTest.add(new Refri("RefriNome5", "", 2.25 ,10));

		String stringProdutos = "Lista de Produtos\n";
		for (Refri refri : refrisTest) 
			stringProdutos += refri.toString();

		assertEquals(stringProdutos, maq1.getAllProdutos());

	}

	@Test
	public void calculoTrocoSucesso() {
		this.prepararMaquinaTroco();
		this.prepararMaquinaProduto();
		
		ArrayList<Dinheiro> arrayTroco = new ArrayList<Dinheiro>();
		
		boolean resultTroco = maq1.calculoTroco(2.5,10.0,false,arrayTroco);
		
		assertTrue(resultTroco);
	}
	
	@Test
	public void calculoTrocoFalha() {
		this.prepararMaquinaTroco();
		this.prepararMaquinaProduto();
		
		ArrayList<Dinheiro> arrayTroco = new ArrayList<Dinheiro>();
		
		boolean resultTroco = maq1.calculoTroco(2.5,2.0,false,arrayTroco);
		
		assertFalse(resultTroco);
	}
	
	@Test
	public void listaDinheiroTrocoSucesso() {
		this.prepararMaquinaTroco();
		this.prepararMaquinaProduto();
		
		ArrayList<Dinheiro> arrayTroco = new ArrayList<Dinheiro>();
		
		boolean resultTroco = maq1.calculoTroco(2.5,10.0,false,arrayTroco);
		
		String stringTrocoMetodo = "Lista de Troco\n";
		for (Dinheiro dimdim : arrayTroco) 
			stringTrocoMetodo += dimdim.toString();

		ArrayList<Dinheiro> dinheirosTroco = new ArrayList<Dinheiro>();

		dinheirosTroco.add(new Dinheiro(0,5.0));
		dinheirosTroco.add(new Dinheiro(0,2.0));
		dinheirosTroco.add(new Dinheiro(0,0.5));
		
		String stringTrocoDinheiros = "Lista de Troco\n";
		for (Dinheiro dimdim : dinheirosTroco) 
			stringTrocoDinheiros += dimdim.toString();
		
		assertEquals(stringTrocoDinheiros, stringTrocoMetodo);
	}

	@Test
	public void venderSucesso(){
		this.prepararMaquinaTroco();
		this.prepararMaquinaProduto();
		
		Refri refriTeste = new Refri("RefriNome5");
		
		Boolean resultVenda = maq1.vender( refriTeste, 2, new Dinheiro(1,5.0) );
		
		assertTrue(resultVenda);
	}
	
	@Test
	public void venderFalha(){
		this.prepararMaquinaTroco();
		this.prepararMaquinaProduto();
		
		Refri refriTeste = new Refri("RefriNome5");
		
		Boolean resultVenda = maq1.vender(refriTeste, 2, new Dinheiro(2.0));
		
		assertFalse(resultVenda);
	}
}
