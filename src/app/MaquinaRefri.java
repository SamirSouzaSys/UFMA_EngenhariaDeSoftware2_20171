package app;
import java.util.ArrayList;
import java.util.Collections;

public class MaquinaRefri implements Log{

	private ArrayList<Refri> estoque;
	private ArrayList<Dinheiro> caixaTroco;
	private ArrayList<Dinheiro> caixaVenda;
	private ArrayList<String> log;
	private String localizacao;
	private String id;

	public MaquinaRefri(String id, String localizacao){
		this.id = id;
		this.localizacao = localizacao;
		this.estoque = new ArrayList<Refri>();
		this.caixaTroco = new ArrayList<Dinheiro>();
		this.caixaVenda = new ArrayList<Dinheiro>();
		this.log = new ArrayList<String>();
		this.log.add(this.getOperationToLog("Máquina Ligada"));
	}

	public String getOperationToLog(String operation){
		return operation + " - " + java.time.LocalDateTime.now();
	}

	/**
	 * Adicionar algum dinheiro na bandeja de Troco
	 * @param dimdim
	 * @return
	 */
	public Boolean addDinheiroTroco(Dinheiro dimdim)
	{
		if(dimdim.getValor() > 0.0)
		{
			if(this.caixaTroco.isEmpty()){
				for(Dinheiro obj : this.caixaTroco){
					if(obj.getValor() == dimdim.getValor()){
						obj.addQuatidade(dimdim.getQuantidade());
						this.log.add(this.getOperationToLog("Add Dinheiro Troco_Já existente"));
						return true;
					}
				}
			}

			this.caixaTroco.add(dimdim);
			this.log.add(this.getOperationToLog("Add Dinheiro Troco_Novo"));
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param dimdim
	 * @return
	 */
	public Boolean addDinheiroVenda(Dinheiro dimdim)
	{
		if(dimdim.getValor() > 0.0)
		{
			if(this.caixaVenda.isEmpty()){
				for(Dinheiro obj : this.caixaVenda){
					if(obj.getValor() == dimdim.getValor()){
						obj.addQuatidade(dimdim.getQuantidade());
						this.log.add(this.getOperationToLog("Add Dinheiro Venda_Já existente"));
						return true;
					}
				}
			}

			this.caixaVenda.add(dimdim);
			this.log.add(this.getOperationToLog("Add Dinheiro Venda_Novo"));
			return true;
		}
		return false;
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
			this.log.add(this.getOperationToLog("Requisitou Troco Total"));
			return total;
		}
		// Verifica o TOTAL geral de todos os valores disponível na máquina
		else { 
			for(Dinheiro din : this.caixaTroco){
				if(din.getValor() == valor){
					total += din.getQuantidade() * din.getValor();
					this.log.add(this.getOperationToLog("Requisitou Troco Único"));
					return total;
				}
			}
			this.log.add(this.getOperationToLog("Requisitou Troco Único - Valor desconhecido"));
			return -1.0;
		}
	}

	/**
	 * adiciona um refri ao estoque da máquina
	 * @param refric
	 * @return
	 */
	public String addProduto(Refri refri){
		if(this.estoque.isEmpty()){
			this.estoque.add(refri);
			this.log.add(this.getOperationToLog("Estoque CARREGADO"));
			return "Estoque CARREGADO";
		}else{
			this.estoque.add(refri);
			this.log.add(this.getOperationToLog("Estoque RECARREGADO"));			
			return "Estoque RECARREGADO";
		}
	}

	/**
	 * Relatório Atual do estado da máquina - Monetário e Produtos
	 * @return
	 */
	public String relatorioAtual(){
		String relatorioTexto = "Dados da Máquina\n\n";
		for (Dinheiro din : caixaTroco) {
			relatorioTexto += "Valor: "+din.getValor()+
					" -> Quantidade "+din.getQuantidade()+"\n";
		}
		relatorioTexto += "\n";

		relatorioTexto += "Valor total na máquina de Troco: "+this.getTrocoDisponível(0.0);

		relatorioTexto += "\n\n";

		for(Refri ref : estoque){
			relatorioTexto += "Refri: "+ref.getNome()+
					" -> Quantidade "+ref.getQuantidadeAtual()+
					" -> Preço "+ref.getPreco()+"\n";
		}

		relatorioTexto += "\n";

		for(String str : this.log){
			relatorioTexto += "Log: "+str+"\n";
		}
		return relatorioTexto;
	}
	
	/**
	 * 
	 * @param refriBuscado
	 * @return
	 */
	private Refri getProduto(Refri refriBuscado){
		if(this.estoque.isEmpty())
			return null;
		
		for(Refri refriNaMaquina : this.estoque){
			if(refriNaMaquina.getNome() == refriBuscado.getNome()){
				return refriNaMaquina;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param refriBuscado
	 * @param quantidadeARetirar
	 * @return
	 */
	private Boolean retirarProduto(Refri refriBuscado, int quantidadeARetirar){
		Refri refri = this.getProduto(refriBuscado);
		
		if(refri == null)
			return false;
		
		int posicao = this.estoque.indexOf(refri);
		
		int qtdAtual = refri.getQuantidadeAtual();
		
		if(qtdAtual >= quantidadeARetirar){
			Refri refriEstoque = this.estoque.get(posicao);
			refriEstoque.setQuantidadeAtual(qtdAtual - quantidadeARetirar);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Vende um refri se disponível na máquina
	 * @param qtdRefri
	 * @param refriEscolhido
	 * @param valorInserido
	 * @return
	 */
	public Boolean vender(Refri refriEscolhido, int qtdRefri, Dinheiro valorInserido){
		Refri refriParaVenda = this.getProduto(refriEscolhido);
		
		if(refriParaVenda != null){
			Double precoUnidade = refriParaVenda.getPreco();
			Double precoTotal = precoUnidade * qtdRefri;
			ArrayList<Dinheiro> troco = new ArrayList<Dinheiro>();
			
			if( (refriParaVenda.getQuantidadeAtual() >= qtdRefri)
					&& this.calculoTroco(precoTotal, valorInserido.getValor(), true, troco) )
			{
				// Adicionar Dinheiro de compra na bandeja de venda
				this.addDinheiroVenda(valorInserido);
				// Retirar Dinheiro de compra da bandeja de troco
				this.calculoTroco(precoTotal, valorInserido.getValor(), false, troco);
				
				// Retirar Produto do Estoque
				this.retirarProduto(refriEscolhido,qtdRefri);
				// Add ao Log
			}
//					&& refriParaVenda.getPreco() )
		}
		return false;
	}

	/**
	 * realiza o cálculo do troco verificando se é possível a máquina devolver esse troco
	 * @param valorCompra
	 * @param valorInserido
	 * @param somenteCalculando
	 * @return
	 */
	public boolean calculoTroco(Double valorCompra, Double valorInserido, boolean simulacao, ArrayList<Dinheiro> resultArrayTroco)
	{	
		// 2.5, 10
		if(valorCompra > valorInserido)
			return false;

		//dinheiros que comporão o troco // Array de moedas/cédulas que comporá o troco
		ArrayList<Dinheiro> dinheirosTroco  = new ArrayList<Dinheiro>();
//		ArrayList<Dinheiro> copiaLocalTroco = this.caixaTroco;
		ArrayList<Dinheiro> copiaLocalTroco =  new ArrayList<Dinheiro>();
		
		for (Dinheiro dinheiro : this.caixaTroco) {
			copiaLocalTroco.add(new Dinheiro(dinheiro.getQuantidade(), dinheiro.getValor()));
		}
		
		//Sort - decrescente - No dinheiro
		Collections.sort(copiaLocalTroco);

		Double trocoValor = valorInserido - valorCompra;
//		Double trocoAtual = trocoValor;

		// Calcula lista de moedas que comporão o troco
		// Cédulas/Moedas disponíveis na máquina
		for(Dinheiro din : copiaLocalTroco)
		{
			while( ( (trocoValor - din.getValor()) >= 0 ) &&
					(din.getQuantidade() >= 1) ){
				dinheirosTroco.add(new Dinheiro(1, din.getValor()));
				din.delQuatidade(1);
				
				trocoValor -= din.getValor();
			}
			 //FIM FOR
		}

		//Troco Possível
		if(trocoValor == 0) {
			if(!simulacao){
				resultArrayTroco.addAll(dinheirosTroco);
				// Retira valores do repositório de troco global
				if(!dinheirosTroco.isEmpty()){
					Collections.sort(this.caixaTroco);

					// Bandeja de Troco da Máquina
					for(Dinheiro dinGlobal : this.caixaTroco){
						// Lista de moedas que servirão para o troco
						for(Dinheiro trocoLocal: dinheirosTroco){
							if( (dinGlobal.getValor() == trocoLocal.getValor()) &&
									(trocoLocal.getQuantidade() > 0)
									){
								dinGlobal.delQuatidade(1);
								trocoLocal.delQuatidade(1);
							}
						}
					}	
				}else{
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
}