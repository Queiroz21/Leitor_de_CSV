package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Operacoes {
	
	String DM = "C:\\DadosMercado.csv";
	String OP = "C:\\Operacoes.csv";
	BufferedReader br = null;
	String linha = "";
	ArrayList<arc_dm> dados = new ArrayList<>();
	ArrayList<arc_op> dados2 = new ArrayList<>();
	ArrayList<Final> fim = new ArrayList<>();{
		fim.add(null);
	}
	Final fl;
	arc_dm arc;
	arc_op op;
	java.util.Date dt1;
	java.util.Date dt2;
	
	String separadorCSV = ";";
	int i =0;
	
	public void leia() throws IOException, NumberFormatException, ParseException{
			leiaDM();
			leiaOP();
			ajuste();
		}
	
	public void leiaDM() throws IOException, NumberFormatException, ParseException{
		br = new BufferedReader(new FileReader(DM));
		//verificando se o arquivo ainda possui a próxima linha
		br.readLine();
		while ((linha = br.readLine()) != null) {
			//salvando a linha em espaços diferentes dentro do array
			String[] d = linha.split(separadorCSV);{
				arc = new arc_dm();
				arc.setId_preco(Integer.parseInt(d[0]));
				arc.setNu_prazo(Integer.parseInt(d[1]));
				arc.setVl_preco(Double.parseDouble(d[2].replace(",", ".")));
			};
			dados.add(arc);
//			System.out.println((i++) + "- Dados Mercado: Código= " + d[0] + " , N_prazo=" + d[1] + ", Valor: " + d[2] +"]");
		}
		Collections.sort(dados);
//		for(arc_dm a: dados){
//			System.out.println(a.toString());
//		}
		
		
	}
	
	public void leiaOP() throws IOException, ParseException{
		br = new BufferedReader(new FileReader(OP));
		//verificando se o arquivo ainda possui a próxima linha
		br.readLine();
		while ((linha = br.readLine()) != null) {
			//salvando a linha em espaços diferentes dentro do array
			String[] d = linha.split(separadorCSV);{
				op = new arc_op();
				op.setCod_op(Integer.parseInt(d[0]));
				op.setDt_inicio(d[1]);
				op.setDt_fim(d[2]);
				op.setDf_Hr(calculohrs(op));
				op.setNm_subg(d[9]);
				op.setQtd(Double.parseDouble(d[12].replace(",", ".")));
				op.setId_Preco(Integer.parseInt(d[13]));			
			};
			dados2.add(op);
//			System.out.println((i++) + "- Dados Mercado: Código= " + d[0] + " , N_prazo=" + d[1] + ", Valor: " + d[2] +"]");
		}
		Collections.sort(dados2);
//		for(arc_op a: dados2){
//			System.out.println(a.toString());
//		}
	}

	public int calculohrs(arc_op aop) throws ParseException{
		Integer dt = null;	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		sdf.setLenient(false);
		dt1 = sdf.parse (aop.getDt_inicio());
		dt2 = sdf.parse (aop.getDt_fim());
		return dt = (int) ((dt2.getTime() - dt1.getTime() + 3600000)/ 86400000L);
	}

	public void ajuste() throws ParseException{
		
		ArrayList<String> aux = new ArrayList<>();		{
			String aux2="";
			for(int i =0; i<dados2.size();i++){
				//carregando dados de todos subgrupos
				if(!aux.contains(dados2.get(i).getNm_subg())){
					aux2 = dados2.get(i).getNm_subg();
					aux.add(aux2);;
				}
			}
			//System.out.println(aux.toString());
		}
		int cont = 1;
		fl= new Final();
		for(arc_op b: dados2){
			for(arc_dm a: dados){
				if(a.getId_preco() == b.getId_Preco() && a.getNu_prazo() == b.getDf_hr()){ 
					fl.setResult(fl.getResult()+ (b.getQtd()*a.getVl_preco()));		
					break;
					}
				}
			if(aux.size() != cont-1){
				if(aux.get(cont-1) != b.getNm_subg()){
					fl.setSub(aux.get(cont-1));
					fim.add(fl);
					cont++;
					fl= new Final();
				}
			}else{
				fim.remove(0);
				//System.out.println(fim.toString());
				break;
			}
		}
		geraCsv();
		
	}
	public void geraCsv(){
		try{
			BufferedWriter writer = new BufferedWriter (new FileWriter ( "C:\\Final.csv")); 
			writer.append("Subgrupo");
	        writer.append(',');
	        writer.append("Valor");
	        writer.append('\n');
			for(Final f: fim){
				writer.append(f.getSub());
		        writer.append(',');
		        writer.append(String.valueOf(f.getResult()));
		        writer.append('\n');
			}
			writer.flush();
	        writer.close();
			System.out.println("Arquivo finalizado!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}