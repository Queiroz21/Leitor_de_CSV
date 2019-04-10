package logic;

public class Final {
	String sub;
	double result;
	String getSub() {
		return sub;
	}
	void setSub(String sub) {
		this.sub = sub;
	}
	double getResult() {
		return result;
	}
	void setResult(double result) {
		this.result = result;
	}
	public String toString() {  
	    return "Subgrupo: " +this.sub + " Valor: "+ getResult() + "\n";
	}

}
