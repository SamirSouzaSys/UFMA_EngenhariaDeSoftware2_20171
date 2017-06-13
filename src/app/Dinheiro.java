package app;

public class Dinheiro implements Comparable<Dinheiro>{
	private Double valor;
	private int quantidade;

	public Dinheiro(Integer qtd,Double valor){
		this.quantidade = qtd;
		this.valor = valor;
	}
	
	public Dinheiro(Double valor){
		this.quantidade = 1;
		this.valor = valor;
	}

	public Double getValor(){
		return this.valor;
	}

	public int getQuantidade(){
		return this.quantidade;
	}
	
	public boolean addQuatidade(int novaQtd){
		if(novaQtd > 0){
			this.quantidade += novaQtd;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean delQuatidade(int novaQtd){
		if(novaQtd > 0 && ( (this.quantidade - novaQtd) >= 0)){
			this.quantidade -= novaQtd;
			return true;
		}else{
			return false;
		}
	}
	
	@Override
    public int compareTo(Dinheiro din) {
		if(this.valor < din.valor){
			return 1;
		}else if(this.valor > din.valor){
			return -1;
		}else{
			return 0;
		}
    }
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder result = new StringBuilder();
		
		String NEW_LINE = System.getProperty("line.separator");
		
		result.append("Valor: "+ valor + NEW_LINE);
		result.append("Quantidade: "+  quantidade + NEW_LINE);
		return result.toString();
	}
}
