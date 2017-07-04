package app;

public class Refri {
  private String nome;
  private Double preco;
  private int quantidadeAtual;

  public Refri(String nome) {
    this.nome = nome;
  }

  /**
   * 
   * @param nome
   * @param preco
   * @param qtdAtual
   */
  
  public Refri(String nome, Double preco, int qtdAtual) {
    this.nome = nome;
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

  @Override
    public String toString() {
    StringBuilder result = new StringBuilder();
    String newLine = System.getProperty("line.separator");

    result.append("Nome: " + nome + newLine);
    result.append("Preço: " + preco + newLine);
    result.append("Quantidade: " + quantidadeAtual + newLine);
    return result.toString();
  }
}