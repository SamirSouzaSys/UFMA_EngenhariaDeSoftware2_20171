package app;

public class Dinheiro {
	private Double valor;
	private int quantidade;

	public Dinheiro(Integer qtd,Double valor){
		this.quantidade = qtd;
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
}
