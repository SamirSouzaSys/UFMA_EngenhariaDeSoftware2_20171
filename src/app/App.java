package app;
import java.util.ArrayList;

public class App {

	public static Boolean prepararMaquina(MaquinaRefri maq){
		// Adicionar Dinheiro
		maq.addDinheiro(new Dinheiro(10,0.5));
		maq.addDinheiro(new Dinheiro(10,5.0));
		maq.addDinheiro(new Dinheiro(10,2.0));
		maq.addDinheiro(new Dinheiro(10,10.0));
		maq.addDinheiro(new Dinheiro(20,0.01));
		
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
		
//		Refri refriTeste = new Refri("RefriNome5");
//		System.out.println(maq1.vender(refriTeste, 1, new Dinheiro(10.0)));
		
		ArrayList<Dinheiro> arrayTroco = new ArrayList<Dinheiro>();
		System.out.println(maq1.calculoTroco(2.5,10.0,false,arrayTroco));
		if(!arrayTroco.isEmpty()){
			for (Dinheiro din : arrayTroco) {
				System.out.println(din.getValor());
			}
		}
		
		ArrayList<Dinheiro> arrayTroco2 = new ArrayList<Dinheiro>();
		System.out.println(maq1.calculoTroco(2.5,500.0,false,arrayTroco2));
		if(!arrayTroco2.isEmpty()){
			for (Dinheiro din : arrayTroco2) {
				System.out.println(din.getValor());
			}
		}
		
	}

}
