package logic;

public class arc_op implements Comparable<arc_op> {

	Integer cod_op, id_preco, df_hr;
	String dt_inicio, dt_fim,nm_subg, nm_subp, cd_a;
	double qtd;
	
	
	Integer getDf_hr() {
		return df_hr;
	}

	void setDf_Hr(Integer df_hr) {
		this.df_hr = df_hr;
	}
	Integer getCod_op() {
		return cod_op;
	}

	void setCod_op(Integer cod_op) {
		this.cod_op = cod_op;
	}

	String getDt_inicio() {
		return dt_inicio;
	}

	void setDt_inicio(String dt_inicio) {
		this.dt_inicio = dt_inicio;
	}

	String getDt_fim() {
		return dt_fim;
	}

	void setDt_fim(String dt_fim) {
		this.dt_fim = dt_fim;
	}

	String getNm_subg(){
		return this.nm_subg;
	}
	void setNm_subg(String nm_subg) {
		this.nm_subg = nm_subg;
	}


	String getCd_a() {
		return cd_a;
	}

	void setCd_a(String cd_a) {
		this.cd_a = cd_a;
	}

	double getQtd() {
		return qtd;
	}

	void setQtd(double d) {
		this.qtd = d;
	}

	int getId_Preco() {
		return id_preco;
	}

	void setId_Preco(int id_preco) {
		this.id_preco = id_preco;
	}
	
	public String toString() {  
		    return " ID Preco " + this.id_preco +" Código: " + this.cod_op+ " Difirença HR: "+ this.df_hr + " Data_inicio: "+ this.dt_inicio + " Data_Final: "+this.dt_fim + " numsubgrupo " + this.nm_subg + " Quantidade " + this.qtd;  
		}
		
		@Override
		public int compareTo(arc_op arcop){
			return this.nm_subg.compareTo(arcop.nm_subg);
		}
	}
	
