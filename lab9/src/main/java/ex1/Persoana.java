package ex1;

import java.io.Serializable;

public class Persoana{
	private String numeprenume,adresa,telefon;
	private int zi,luna,an;
	
	public Persoana() {}
	
	
	public Persoana(String np,int z,int l,int a,String ad,String t) {
		this.numeprenume=np;
		this.zi=z;
		this.luna=l;
		this.an=a;
		this.adresa=ad;
		this.telefon=t;
	}
	
	@Override
	public String toString() {
		return numeprenume+" "+zi+"/"+luna+"/"+an+" "+adresa+" "+telefon;
	}
	
	public String getNumeprenume() {return numeprenume;}
	public int getZi() {return zi;}
	public int getLuna() {return luna;}
	public int getAn() {return an;}
	public String getAdresa() {return adresa;}
	public String getTelefon() {return telefon;}
	
}
