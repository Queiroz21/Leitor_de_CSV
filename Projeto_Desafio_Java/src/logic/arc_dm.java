package logic;

public class arc_dm implements Comparable<arc_dm>{

	Integer id_preco, nu_prazo;
	double vl_preco;
	
	int getId_preco() {
		return id_preco;
	}
	void setId_preco(int id_preco) {
		this.id_preco = id_preco;
	}
	int getNu_prazo() {
		return nu_prazo;
	}
	void setNu_prazo(int nu_prazo) {
		this.nu_prazo = nu_prazo;
	}
	double getVl_preco() {
		return vl_preco;
	}
	void setVl_preco(double vl_preco) {
		this.vl_preco = vl_preco;
	}
	
	public String toString() {  
	    return "ID: " + this.id_preco + " Prazo:(Dias): "+ this.nu_prazo + " Valor: "+this.vl_preco;  
	}
	@Override
	public int compareTo(arc_dm arcdm){
		return this.id_preco.compareTo(arcdm.id_preco);
	}

}
