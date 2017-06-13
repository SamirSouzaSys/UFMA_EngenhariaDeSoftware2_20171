package app;

public class Refri {
	private String nome;
	private String descricao;
	private Double preco;
	private int quantidadeAtual;

	public Refri(String nome){
		this.nome = nome;
	}

	public Refri(String nome, String descricao, Double preco, int qtdAtual){
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco < 0 ? 0 : preco;
		this.quantidadeAtual = qtdAtual < 0 ? 0 : qtdAtual;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public int getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(int quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}
	
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder result = new StringBuilder();
		
		String NEW_LINE = System.getProperty("line.separator");
		
		result.append("Nome: "+ nome + NEW_LINE);
		result.append("Descrição: "+  descricao + NEW_LINE);
		result.append("Preço: "+  preco + NEW_LINE);
		result.append("Quantidade: "+  quantidadeAtual + NEW_LINE);
		return result.toString();
	}
}