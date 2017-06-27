package app;
import java.util.ArrayList;

public class App {

	public static boolean debugMode = Boolean.FALSE;
	
	public static Boolean prepararMaquina(MaquinaRefri maq){
		// Adicionar Dinheiro
		maq.addDinheiroTroco(new Dinheiro(10,0.5));
		maq.addDinheiroTroco(new Dinheiro(10,5.0));
		maq.addDinheiroTroco(new Dinheiro(10,2.0));
		maq.addDinheiroTroco(new Dinheiro(10,10.0));
		maq.addDinheiroTroco(new Dinheiro(20,0.01));
		
		//AdicionarProduto
		maq.addProduto(new Refri("RefriNome1", "", 1.5  ,4));
		maq.addProduto(new Refri("RefriNome2", "", 2.0  ,8));
		maq.addProduto(new Refri("RefriNome3", "", 1.25 ,7));
		maq.addProduto(new Refri("RefriNome4", "", 2.5  ,6));
		maq.addProduto(new Refri("RefriNome5", "", 2.25 ,10));
		
		return true;
	}
	
	public static void main(String[] args) {
		boolean emExecucao = true;
		IOdata iod = new IOdata();
		
		while(emExecucao){
			int opt = iod.getModoOperacao();
			switch (opt) {
			case 1:
				
				break;

			default:
				break;
			}
		}
	}

}
