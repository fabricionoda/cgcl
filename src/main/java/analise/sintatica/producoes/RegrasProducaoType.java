package analise.sintatica.producoes;

import analise.exceptions.ProducaoSintaticaException;
import analise.sintatica.ArvoreSintaticaAbstrataNo;

public class RegrasProducaoType extends RegrasProducaoAbstract {

	@Override
	public ArvoreSintaticaAbstrataNo validaEGeraProducao() throws ProducaoSintaticaException {
		//<typeSymbol> [ <arraytype> | <rangetype> ] | <tupletype>
		
		ArvoreSintaticaAbstrataNo raiz = new ArvoreSintaticaAbstrataNo("type");
		
		this.salvarIndiceTokenAtual();
		try {
			ArvoreSintaticaAbstrataNo tupleType = this.validaEGeraProducaoDadoProducao(ProducoesEnum.tupletype);
			raiz.adicionaNoFilho(tupleType);
			this.descartaIndiceSalvo();
			return raiz;		
		} catch (ProducaoSintaticaException e) {
			this.recuperarIndiceSalvo();
		}
		
		// ---------------------
		
		this.salvarIndiceTokenAtual();		
		try {
			ArvoreSintaticaAbstrataNo typeSymbol = this.validaEGeraProducaoDadoProducao(ProducoesEnum.typeSymbol);
			raiz.adicionaNoFilho(typeSymbol);
			this.descartaIndiceSalvo();
		} catch (ProducaoSintaticaException e) {
			this.recuperarIndiceSalvo();
			this.throwProducaoSintaticaException("type");
		}

		
		try {
			this.salvarIndiceTokenAtual();
			ArvoreSintaticaAbstrataNo arrayType = this.validaEGeraProducaoDadoProducao(ProducoesEnum.arraytype);
			raiz.adicionaNoFilho(arrayType);
			this.descartaIndiceSalvo();
			return raiz;
		} catch (ProducaoSintaticaException e) {
			this.recuperarIndiceSalvo();
		}
		
		try {
			this.salvarIndiceTokenAtual();
			ArvoreSintaticaAbstrataNo rangeType = this.validaEGeraProducaoDadoProducao(ProducoesEnum.rangetype);
			raiz.adicionaNoFilho(rangeType);
			this.descartaIndiceSalvo();
			return raiz;				
		} catch ( ProducaoSintaticaException e) {
			this.recuperarIndiceSalvo();
		}
		
		return raiz;
	}

}
