package analise.sintatica.producoes;

import analise.sintatica.ArvoreSintaticaAbstrataNo;
import coretypes.Token;
import coretypes.gcl.GCLTokenTypes;

public class RegrasProducaoExpressionTest extends RegrasProducaoTestCase {

	@Override
	public void testCasoIdeal() {
		
		Token tokenDois = new Token(GCLTokenTypes.NUMBER, "2");
		Token tokenMaiorIgual = new Token(GCLTokenTypes.SYMBOL, ">=");
		Token tokenUm = new Token(GCLTokenTypes.NUMBER, "1");
		this.pilhaDeToken.add(tokenDois);
		this.pilhaDeToken.add(tokenMaiorIgual);
		this.pilhaDeToken.add(tokenUm);
		
		ProducoesFactory.setEstado(this.pilhaDeToken);
		
		this.producao = ProducoesFactory.getProducao(ProducoesEnum.expression);
		assertEquals(this.producao.getClass().getName(), "analise.sintatica.producoes.RegrasProducaoExpression");		
		this.raiz = this.producao.validaEGeraProducao();
		
		assertNotNull(this.raiz);
		assertEquals(this.raiz.getNome(), "expression");
		assertNull(this.raiz.getToken());
		assertEquals(this.raiz.possueNosFilhos(), true);
		assertEquals(this.raiz.getListaDeNos().size(), 1);

		ArvoreSintaticaAbstrataNo relationalExpression;
		relationalExpression = this.raiz.getListaDeNos().getFirst();
		
		assertNotNull(relationalExpression);
		assertEquals(relationalExpression.getNome(), "relationalExpression");
		assertNull(relationalExpression.getToken());
		assertEquals(relationalExpression.possueNosFilhos(), true);
		assertEquals(relationalExpression.getListaDeNos().size(), 3);		
		
		int i = 0;
		assertEquals(relationalExpression.getListaDeNos().get(i).getNome(), "simpleExpression");	
		i++;
		assertEquals(relationalExpression.getListaDeNos().get(i).getNome(), "relationalOperator");	
		i++;
		assertEquals(relationalExpression.getListaDeNos().get(i).getNome(), "simpleExpression");	
	}
	
	public void testRelationalOperatorFaltandoUmaSimpleExpression() {
		
		Token tokenDois = new Token(GCLTokenTypes.NUMBER, "2");
		Token tokenMaiorIgual = new Token(GCLTokenTypes.SYMBOL, ">=");
		this.pilhaDeToken.add(tokenDois);
		this.pilhaDeToken.add(tokenMaiorIgual);
		
		ProducoesFactory.setEstado(this.pilhaDeToken);
		
		this.producao = ProducoesFactory.getProducao(ProducoesEnum.expression);
		assertEquals(this.producao.getClass().getName(), "analise.sintatica.producoes.RegrasProducaoExpression");		
		this.raiz = this.producao.validaEGeraProducao();
		
		assertNull(this.raiz);

	}	

}