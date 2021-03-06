package analise.semantica;

import java.util.LinkedList;

import analise.exceptions.AnaliseSemanticaException;
import analise.semantica.validacoes.*;
import analise.sintatica.ArvoreSintaticaAbstrataNo;
import coretypes.StringList;

public class AnaliseSemantica {
	
	private ArvoreSintaticaAbstrataNo no;
	private StringList ListaDeErros;
	private LinkedList<AnaliseSemanticaAcaoAbstrata> acoes;
	
	public AnaliseSemantica(ArvoreSintaticaAbstrataNo no) {
		
		this.ListaDeErros = new StringList();

		this.acoes = new LinkedList<AnaliseSemanticaAcaoAbstrata>();
		this.acoes.add(new AnaliseSemanticaControlaEscopo());
		this.acoes.add(new AnaliseSemanticaAddProcedures());
		this.acoes.add(new AnaliseSemanticaAddConstants());
		this.acoes.add(new AnaliseSemanticaAddVariaveis());
		this.acoes.add(new AnaliseSemanticaVerificaVariaveis());
		this.acoes.add(new AnaliseSemanticaVerificaProcedures());

		this.no = no;
	}

	public AnaliseSemantica() {
		this(null);
	}
	
	public StringList getListaDeErros() {
		return this.ListaDeErros;
	}
		
	private void analisarArvoreDadoNo(ArvoreSintaticaAbstrataNo no) {

		for (AnaliseSemanticaAcaoAbstrata acao : this.acoes) {
			try {
				acao.executa(no);
			} catch (AnaliseSemanticaException e) {
				this.ListaDeErros.add(e.getMessage());
			}
		}

		for (ArvoreSintaticaAbstrataNo noFilho : no.getListaDeNos()) {
			this.analisarArvoreDadoNo(noFilho);
		}
	}
		
	public boolean analisarArvoreApartirDaRaiz(ArvoreSintaticaAbstrataNo raiz) {
		this.ListaDeErros.clear();
		this.no = raiz;
		this.analisarArvoreDadoNo(this.no);
		
		return this.ListaDeErros.isEmpty();
	}
	
	public boolean analisar() {
		if (this.no != null)
			return this.analisarArvoreApartirDaRaiz(this.no);
		
		return false;
	}
	
	public ArvoreSintaticaAbstrataNo getArvoreSintaticaAnotada() {
		return this.no;
	}

}
