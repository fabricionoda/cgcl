package analise.sintatica.producoes;

import analise.exceptions.ProducaoSintaticaException;
import coretypes.Token;
import coretypes.gcl.GCLTokenTypes;

public class RegrasProducaoBooleanOperatorTest extends RegrasProducaoTestCase {

	@Override
	public void testCasoIdeal() {
			
		Token token = new Token(GCLTokenTypes.SYMBOL, "&");
		this.pilhaDeToken.add(token);

		ProducoesFactory.setEstado(this.pilhaDeToken);
		
		this.producao = ProducoesFactory.getProducao(ProducoesEnum.booleanOperator);
		assertEquals(this.producao.getClass().getName(), "analise.sintatica.producoes.RegrasProducaoBooleanOperator");		
		try {
			this.raiz = this.producao.validaEGeraProducao();	
		} catch (ProducaoSintaticaException e) {
			fail(e.getMessage());
		}
		
		assertNotNull(this.raiz);
		assertEquals(this.raiz.getNome(), "booleanOperator");
		assertNull(this.raiz.getToken());
		assertEquals(this.raiz.possueNosFilhos(), true);		
		assertEquals(this.raiz.getListaDeNos().size(), 1);
		assertEquals(this.raiz.getListaDeNos().getFirst().getToken().getTokenType(), token.getTokenType());
		assertEquals(this.raiz.getListaDeNos().getFirst().getToken().getValue(), token.getValue());	
	}
	
	public void testCasoSinalOr() {
		
		Token token = new Token(GCLTokenTypes.SYMBOL, "|");
		this.pilhaDeToken.add(token);

		ProducoesFactory.setEstado(this.pilhaDeToken);
		
		this.producao = ProducoesFactory.getProducao(ProducoesEnum.booleanOperator);
		assertEquals(this.producao.getClass().getName(), "analise.sintatica.producoes.RegrasProducaoBooleanOperator");		
		try {
			this.raiz = this.producao.validaEGeraProducao();	
		} catch (ProducaoSintaticaException e) {
			fail(e.getMessage());
		}
		
		assertNotNull(this.raiz);
		assertEquals(this.raiz.getNome(), "booleanOperator");
		assertNull(this.raiz.getToken());
		assertEquals(this.raiz.possueNosFilhos(), true);		
		assertEquals(this.raiz.getListaDeNos().size(), 1);
		assertEquals(this.raiz.getListaDeNos().getFirst().getToken().getTokenType(), token.getTokenType());
		assertEquals(this.raiz.getListaDeNos().getFirst().getToken().getValue(), token.getValue());
	}	

}
