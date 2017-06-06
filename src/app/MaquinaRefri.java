package app;
import java.util.ArrayList;

public class MaquinaRefri {

	private ArrayList<Refri> estoque;
	private ArrayList<Dinheiro> caixaTroco;
	private ArrayList<Dinheiro> caixaVenda;
	private String localizacao;
	private String id;

	public MaquinaRefri(String id, String localizacao){
		this.id = id;
		this.localizacao = localizacao;
		this.estoque = new ArrayList<Refri>();
		this.caixaTroco = new ArrayList<Dinheiro>();
		this.caixaVenda = new ArrayList<Dinheiro>();
	}
	
	/**
	 * Adicionar algum dinheiro na bandeja de Troco
	 * @param dimdim
	 * @return
	 */
	public String addDinheiro(Dinheiro dimdim)
	{
		if(this.caixaTroco != null){
			for(Dinheiro obj : this.caixaTroco){
				if(obj.getValor() == dimdim.getValor()){
					obj.addQuatidade(dimdim.getQuantidade());
					return "Valor acrescentado a uma quantidade anterior existente";
				}
			}
		}
		
		this.caixaTroco.add(dimdim);
		return "Valor adicionado. não havia esse valor disponível na máquina";
	}

	/**
	 * retorna quantidade total de troco disponível na máquina
	 * - De TODOS VALORES ou VALOR ESPECÍFICO
	 * @return
	 */
	public Double getTrocoDisponível(Double valor){
		Double total = 0.0;
		
		//Verifica o total de SOMENTE um valor específico
		if(valor == 0)
		{
			for(Dinheiro din : this.caixaTroco){
				total += din.getQuantidade() * din.getValor();
			}
			return total;
		}
		// Verifica o TOTAL geral de todos os valores disponível na máquina
		else { 
			for(Dinheiro din : this.caixaTroco){
				if(din.getValor() == valor){
					total += din.getQuantidade() * din.getValor();
					return total;
				}
			}
			return -1.0;
		}
	}

	/**
	 * adiciona um refri ao estoque da máquina
	 * @param refri
	 * @return
	 */
	public String addProduto(Refri refri){
		if(this.estoque.isEmpty()){
			this.estoque.add(refri);
			return "Estoque CARREGADO";
		}else{
			this.estoque.add(refri);			
			return "Estoque RECARREGADO";
		}
	}

	public String relatorioAtual(){
		String relatorioTexto = "Dados da Máquina\n\n";
		for (Dinheiro din : caixaTroco) {
			relatorioTexto += "Valor: "+din.getValor()+
							  " -> Quantidade "+din.getQuantidade()+"\n";
		}
		relatorioTexto += "\n";
		
		relatorioTexto += "Valor total na máquina: "+this.getTrocoDisponível(0.0);
	
		relatorioTexto += "\n\n";
		
		for(Refri ref : estoque){
			relatorioTexto += "Refri: "+ref.getNome()+
							  " -> Quantidade "+ref.getQuantidadeAtual()+
							  " -> Preço "+ref.getPreco()+"\n";
		}
		
		return relatorioTexto;
	}
	
	/**
	 * Vende um refri se disponível na máquina
	 * @param qtdRefri
	 * @param refriEscolhido
	 * @param valorInserido
	 * @return
	 */
	public Boolean vender(int qtdRefri, Refri refriEscolhido,
			Dinheiro valorInserido){
		return false;
	}

}
