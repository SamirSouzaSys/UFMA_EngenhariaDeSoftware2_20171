package app;

import java.util.ArrayList;
import java.util.Collections;

public class MaquinaRefri {
  private ArrayList<Refri> estoque;
  private ArrayList<Dinheiro> caixaTroco;
  private ArrayList<Dinheiro> caixaVenda;
  private String id;
  /**
   * @param id
   */
  
  public MaquinaRefri(String id) {
    this.id = id;
    this.estoque = new ArrayList<Refri>();
    this.caixaTroco = new ArrayList<Dinheiro>();
    this.caixaVenda = new ArrayList<Dinheiro>();
  }

  /**
   * Adicionar algum dinheiro na bandeja de Troco
   *
   * @param dimdim
   * @return
   */
  public Boolean addDinheiroTroco(Dinheiro dimdim) {
    if (dimdim.getValor() > 0.0) {
      if (this.caixaTroco.isEmpty()) {
        for (Dinheiro obj : this.caixaTroco) {
          if (obj.getValor() == dimdim.getValor()) {
            obj.addQuatidade(dimdim.getQuantidade());
            return true;
          }
        }
      }

      this.caixaTroco.add(dimdim);
      return true;
    }
    return false;
  }

  /**
   * @param dimdim
   * @return
   */
  public Boolean addDinheiroVenda(Dinheiro dimdim) {
    if (dimdim.getValor() > 0.0) {
      if (this.caixaVenda.isEmpty()) {
        for (Dinheiro obj : this.caixaVenda) {
          if (obj.getValor() == dimdim.getValor()) {
            obj.addQuatidade(dimdim.getQuantidade());
            return true;
          }
        }
      }
      this.caixaVenda.add(dimdim);
      return true;
    }
    return false;
  }

  /**
   * retorna quantidade total de troco disponível na máquina
   * - De TODOS VALORES ou VALOR ESPECÍFICO
   * @return
     */
  public Double getTrocoDisponivel(Double valorMoeda) {
    Double total = 0.0;

    // Verifica o TOTAL geral de todos os valores disponível na máquina
    if (valorMoeda == 0) {
      for (Dinheiro din : this.caixaTroco) {
        total += din.getQuantidade() * din.getValor();
      }
      return total;
      //Verifica o total de SOMENTE um valor específico
    } else {
      for (Dinheiro din : this.caixaTroco) {
        if (din.getValor() == valorMoeda) {
          total += din.getQuantidade() * din.getValor();
          return total;
        }
      }
      return -1.0;
    }
  }

  /**
   * adiciona um refri ao estoque da máquina
   *
   * @param refric
   * @return
   */
  public String addProduto(Refri refri) {
    String strResult = "";

    if (this.estoque.isEmpty()) {
      strResult = "Estoque CARREGADO";
    } else {
      strResult = "Estoque RECARREGADO";
    }

    this.estoque.add(refri);
    return strResult;
  }

  /**
   * Relatório Atual do estado da máquina - Monetário e Produtos
   * @return
   */
  public String relatorioAtual() {
    String relatorioTexto = "Dados da Máquina\n\n";
    for (Dinheiro din : caixaTroco) {
      relatorioTexto += "Valor: "     +     din.getValor()
        + " -> Quantidade "     +     din.getQuantidade()      +      "\n";
    }
    relatorioTexto += "\n";
    relatorioTexto += "Valor total na máquina de Troco: "     +     this.getTrocoDisponivel(0.0);
    relatorioTexto += "\n\n";

    for (Refri ref : estoque) {
      relatorioTexto += "Refri: "     +     ref.getNome()
        + " -> Quantidade "     +     ref.getQuantidadeAtual()
        + " -> Preço "     +     ref.getPreco()     +     "\n";
    }
    relatorioTexto += "\n";

    return relatorioTexto;
  }

  /**
   *
   * @param refriBuscado
   * @return Refri
   */
  private Refri getProduto(Refri refriBuscado) {
    for (Refri refriNaMaquina : this.estoque) {
      if (refriNaMaquina.getNome() == refriBuscado.getNome()) {
        return refriNaMaquina;
      }
    }
    return null;
  }
  /**
   * 
   * @return
   */
  
  public ArrayList<Refri> getAllProdutosList() {
    ArrayList<Refri> refrisEstoque = new ArrayList<Refri>();

    for (Refri refriNaMaquina : this.estoque) {
      refrisEstoque.add(refriNaMaquina);
    }
    return refrisEstoque;
  }
  /**
   * 
   * @return
   */
  
  public String getAllProdutos() {
    String stringProdutos = null;

    for (Refri refriNaMaquina : this.estoque) {
      stringProdutos += refriNaMaquina.toString();
    }
    return stringProdutos;
  }

  /**
   * @param refriBuscado
   * @param quantidadeARetirar
   * @return
   */
  private Boolean retirarProduto(Refri refriBuscado, int quantidadeARetirar) {
    Refri refri = this.getProduto(refriBuscado);
    int posicao = this.estoque.indexOf(refri);
    int qtdAtual = refri.getQuantidadeAtual();

    if (qtdAtual >= quantidadeARetirar) {
      this.estoque.get(posicao).setQuantidadeAtual(qtdAtual - quantidadeARetirar);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Vende um refri se disponível na máquina
   *
   * @param qtdRefri
   * @param refriEscolhido
   * @param valorInserido
   * @return
   */
  public Boolean vender(Refri refriEscolhido, int qtdRefri, Dinheiro valorInserido) {
    Refri refriParaVenda = this.getProduto(refriEscolhido);

    if (refriParaVenda != null) {
      Double precoUnidade = refriParaVenda.getPreco();
      Double precoTotal   = precoUnidade * qtdRefri;
      ArrayList<Dinheiro> troco = new ArrayList<Dinheiro>();

      if ((refriParaVenda.getQuantidadeAtual() >= qtdRefri)
          && this.calculoTroco(precoTotal, valorInserido.getValor(), true, troco)) {
        // Adicionar Dinheiro de compra na bandeja de venda
        this.addDinheiroVenda(valorInserido);
        // Retirar Dinheiro de compra da bandeja de troco
        this.calculoTroco(precoTotal, valorInserido.getValor(), false, troco);
        // Retirar Produto do Estoque
        this.retirarProduto(refriEscolhido, qtdRefri);

        return true;
      }
    }
    return false;
  }

  /**
   * realiza o cálculo do troco verificando se é possível a máquina devolver
   * esse troco
   * @param valorCompra
   * @param valorInserido
   * @param somenteCalculando
   * @return
   */
  public boolean calculoTroco(Double valorCompra, Double valorInserido,
      boolean simulacao, ArrayList<Dinheiro> resultArrayTroco) {
    // 2.5, 10
    if (valorCompra > valorInserido) {
      return false;
    }

    // dinheiros que comporão o troco
    // Array de moedas/cédulas que comporá o troco
    ArrayList<Dinheiro> dinheirosTroco = new ArrayList<Dinheiro>();
    // ArrayList<Dinheiro> copiaLocalTroco = this.caixaTroco;
    ArrayList<Dinheiro> copiaLocalTroco = new ArrayList<Dinheiro>();

    copiaLocalTroco = this.caixaTroco;
    
    // Sort - decrescente - No dinheiro
    Collections.sort(copiaLocalTroco);

    Double trocoValor = valorInserido - valorCompra;

    // Calcula lista de moedas que comporão o troco
    // Cédulas/Moedas disponíveis na máquina
    for (Dinheiro din : copiaLocalTroco) {
      while (((trocoValor - din.getValor()) >= 0) && (din.getQuantidade() >= 1)) {
        dinheirosTroco.add(new Dinheiro(1, din.getValor()));
        din.delQuatidade(1);
        trocoValor -= din.getValor();
      }
    }

    // Troco Possível
    if (trocoValor == 0) {
      if (!simulacao) {
        resultArrayTroco.addAll(dinheirosTroco);
        // Retira valores do repositório de troco global
        if (!dinheirosTroco.isEmpty()) {
          Collections.sort(this.caixaTroco);

          // Bandeja de Troco da Máquina
          for (Dinheiro dinGlobal : this.caixaTroco) {
            // Lista de moedas que servirão para o troco
            for (Dinheiro trocoLocal : dinheirosTroco) {
              if ((dinGlobal.getValor() == trocoLocal.getValor())
                    && (trocoLocal.getQuantidade() > 0)) {
                dinGlobal.delQuatidade(1);
                trocoLocal.delQuatidade(1);
              }
            }
          }
        } else {
          return false;
        }
      }
      return true;
    } else {
      return false;
    }
  }
}