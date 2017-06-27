package app;

import java.util.ArrayList;
import java.util.Scanner;

public class IOdata {
	private void printTitle(boolean clearBefore){
		if(clearBefore){
			for(int i = 1; i<10 ;i++){
				System.out.println("");
			}
		}
		
		System.out.println("\n\tMáquina de Refrigerante");
	}
	
	public void printMainMenu(){
		this.printTitle(true);
		System.out.println("Digite a opção desejada:\n"
				+ "\t1-Entrar em MODO MANUTENÇÃO\n"
				+ "\t2-Entrar em MODO OPERAÇÃO"
				);
	}
	
	public void printManutencaoMenu(){
		this.printTitle();
		System.out.println("Digite a opção desejada:\n"
				+ "\t1-Adicionar Dinheiro(para o troco)\n"
				+ "\t2-Adicionar Produto"
				);
	}
	
	public int getModoOperacao(){
		this.printMainMenu();
		
		ArrayList opts = this.getCommands();
		int opt = Integer.parseInt( (String) opts.get(0) );
		return opt;
	}
	
	public int getManutencaoOperacao(){
		this.printManutencaoMenu();
		
		ArrayList opts = this.getCommands();
		int opt = Integer.parseInt( (String) opts.get(0) );
		return opt;
	}
	
	private ArrayList getCommands() {
        boolean invalidInput = true;
        ArrayList<String> listaOpts = new ArrayList();

        Scanner in;
        while (invalidInput) {
            in = new Scanner(System.in).useDelimiter("\\s *|\\n");

            String hString = in.nextLine();
            String[] hArray = hString.split(" |,|\\n|\\s");

            for (String elm : hArray) {
                if (!elm.equals("")) {
                    listaOpts.add(elm);
                }
            }

            if (App.debugMode) {
            	System.out.print(in.next());
            }

            if (listaOpts.size() > 0) {
                invalidInput = false;
            } else {
                System.out.println("Digite novamente. Dados não reconhecidos");
            }
        }
        return listaOpts;
    }
}
