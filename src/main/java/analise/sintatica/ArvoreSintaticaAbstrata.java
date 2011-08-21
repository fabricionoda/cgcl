package analise.sintatica;

import junit.framework.Assert;

public class ArvoreSintaticaAbstrata {

	private ArvoreSintaticaAbstrataNo raiz;
	private String printBuffer;

	public ArvoreSintaticaAbstrataNo getRaiz() {
		return raiz;
	}

	public void setRaiz(ArvoreSintaticaAbstrataNo raiz) {
		this.raiz = raiz;
	}

	private void printNosFilhos(ArvoreSintaticaAbstrataNo no) {

		printNo(no);
		for (int i = 0; i < no.quatidadeNosFilhos(); i++) {
			printNosFilhos(no.getListaDeNos().get(i));

		}

	}

	private void printNo(ArvoreSintaticaAbstrataNo no) {
		if (no.possueNosFilhos()) {
			printBuffer = printBuffer + no.getNome();
		} else {
			if (no.getToken() != null) {
				printBuffer = (printBuffer + no.getToken().getTokenType() + "." + no
						.getToken().getValue());
			}
		}

	}

	public String print() {
		this.printBuffer = "";
		this.printNosFilhos(this.raiz);
		return this.printBuffer;

	}

}
