package analise.sintatica.producoes;

import analise.exceptions.ProducaoSintaticaException;
import analise.sintatica.ArvoreSintaticaAbstrataNo;

public class RegrasProducaoSimpleExpression extends RegrasProducaoAbstract {
	
	private boolean reconheceProducaoTermAddTerm(ArvoreSintaticaAbstrataNo raiz) throws ProducaoSintaticaException {
		this.salvarIndiceTokenAtual();
		
		ArvoreSintaticaAbstrataNo term = this.validaEGeraProducaoDadoProducao(ProducoesEnum.term);
		raiz.adicionaNoFilho(term);
		this.descartaIndiceSalvo();
		
		boolean isValido;
		do {
			this.salvarIndiceTokenAtual();
			isValido = false;

			try {
				ArvoreSintaticaAbstrataNo addOperator = this.validaEGeraProducaoDadoProducao(ProducoesEnum.addingOperator);
				ArvoreSintaticaAbstrataNo novoTerm;	
				novoTerm = this.validaEGeraProducaoDadoProducao(ProducoesEnum.term);
				raiz.adicionaNoFilho(addOperator);
				raiz.adicionaNoFilho(novoTerm);
				this.descartaIndiceSalvo();
				isValido = true;
			} catch (ProducaoSintaticaException e) {
				//
			}
			
			if (! isValido ) {
				this.recuperarIndiceSalvo();
			}
			
		} while (isValido);
		
		return true;
	}

	@Override
	public ArvoreSintaticaAbstrataNo validaEGeraProducao() throws ProducaoSintaticaException {
		// ( "+" | "-" )  <term> { <addingOperator> <term>} | <term> { <addingOperator> <term>}

		ArvoreSintaticaAbstrataNo raiz = new ArvoreSintaticaAbstrataNo("simpleExpression");
		boolean isValida;

		this.salvarIndiceTokenAtual();

		this.avancaProximoToken();
		if (this.getTokenAtual() == null) {
			this.recuperarIndiceSalvo();
			this.throwProducaoSintaticaException("simpleExpression");
			return null;
		}
		
		if ( (this.getTokenAtual().getValue().equals("+")) || (this.getTokenAtual().getValue().equals("-"))) {
			raiz.adicionaNoFilho(this.getTokenAtual().getValue(), this.getTokenAtual());
			this.descartaIndiceSalvo();
		} else {
			this.recuperarIndiceSalvo();
		}
		
		isValida = this.reconheceProducaoTermAddTerm(raiz);
		
		if (! isValida) {
			this.recuperarIndiceSalvo();
			this.throwProducaoSintaticaException("simpleExpression");
			return null;
		}

		this.descartaIndiceSalvo();	
		return raiz;
	}

}
