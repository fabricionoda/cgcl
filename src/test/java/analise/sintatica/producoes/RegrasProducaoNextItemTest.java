package analise.sintatica.producoes;

import utils.GCLTokenTypes;
import coretypes.Token;

public class RegrasProducaoNextItemTest extends RegrasProducaoTestCase {

	@Override
	public void testCasoIdeal() {
		Token token = new Token(GCLTokenTypes.NUMBER, "123");
		this.pilhaDeToken.add(token);

		ProducoesFactory.setEstado(this.pilhaDeToken);
		
		this.producao = ProducoesFactory.getProducao(ProducoesEnum.nextitem);
		assertEquals(this.producao.getClass().getName(), "analise.sintatica.producoes.RegrasProducaoNextItem");		
		this.raiz = this.producao.validaEGeraProducao();
		
		assertNotNull(this.raiz);
		assertEquals(this.raiz.getNome(), "nextItem");
		assertNull(this.raiz.getToken());
		assertEquals(this.raiz.possueNosFilhos(), true);		
		assertEquals(this.raiz.getListaDeNos().size(), 1);
		assertEquals(this.raiz.getListaDeNos().getFirst().getToken().getTokenType(), token.getTokenType());
		assertEquals(this.raiz.getListaDeNos().getFirst().getToken().getValue(), token.getValue());

	}
	
	public void testCasoComIdentificador() {
		Token token = new Token(GCLTokenTypes.IDENTIFIER, "ola_eu_sou_um_id");
		this.pilhaDeToken.add(token);

		ProducoesFactory.setEstado(this.pilhaDeToken);
		
		this.producao = ProducoesFactory.getProducao(ProducoesEnum.nextitem);
		assertEquals(this.producao.getClass().getName(), "analise.sintatica.producoes.RegrasProducaoNextItem");		
		this.raiz = this.producao.validaEGeraProducao();
		
		assertNotNull(this.raiz);
		assertEquals(this.raiz.getNome(), "nextItem");
		assertNull(this.raiz.getToken());
		assertEquals(this.raiz.possueNosFilhos(), true);		
		assertEquals(this.raiz.getListaDeNos().size(), 1);
		assertEquals(this.raiz.getListaDeNos().getFirst().getToken().getTokenType(), token.getTokenType());
		assertEquals(this.raiz.getListaDeNos().getFirst().getToken().getValue(), token.getValue());

	}
	
	public void testCasoComNumeroFloat() {
		// TODO: verificar se pode ser numero float aqui! 
		Token token = new Token(GCLTokenTypes.NUMBER, "1,2");
		this.pilhaDeToken.add(token);

		ProducoesFactory.setEstado(this.pilhaDeToken);
		
		this.producao = ProducoesFactory.getProducao(ProducoesEnum.nextitem);
		assertEquals(this.producao.getClass().getName(), "analise.sintatica.producoes.RegrasProducaoNextItem");		
		this.raiz = this.producao.validaEGeraProducao();
		
		assertNotNull(this.raiz);
		assertEquals(this.raiz.getNome(), "nextItem");
		assertNull(this.raiz.getToken());
		assertEquals(this.raiz.possueNosFilhos(), true);		
		assertEquals(this.raiz.getListaDeNos().size(), 1);
		assertEquals(this.raiz.getListaDeNos().getFirst().getToken().getTokenType(), token.getTokenType());
		assertEquals(this.raiz.getListaDeNos().getFirst().getToken().getValue(), token.getValue());

	}		

}