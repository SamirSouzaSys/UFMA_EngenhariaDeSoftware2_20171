package app;
import java.util.ArrayList;

public class App {

	public static Boolean prepararMaquina(MaquinaRefri maq){
		// Adicionar Dinheiro
		maq.addDinheiro(new Dinheiro(20,0.01));
		maq.addDinheiro(new Dinheiro(10,0.5));
		maq.addDinheiro(new Dinheiro(10,2.0));
		maq.addDinheiro(new Dinheiro(10,5.0));
		maq.addDinheiro(new Dinheiro(10,10.0));
		
		//AdicionarProduto
		maq.addProduto(new Refri("RefriNome1", "", 1.5  ,4));
		maq.addProduto(new Refri("RefriNome2", "", 2.0  ,8));
		maq.addProduto(new Refri("RefriNome3", "", 1.25 ,7));
		maq.addProduto(new Refri("RefriNome4", "", 2.5  ,6));
		maq.addProduto(new Refri("RefriNome5", "", 2.25 ,10));
		
		return true;
	}
	
	public static void main(String[] args) {

		boolean result = false;
		
		MaquinaRefri maq1 = new MaquinaRefri("Máquina 1", "Shopping 1");
		result = prepararMaquina(maq1);
		System.out.println(maq1.relatorioAtual());
		
	}

}
