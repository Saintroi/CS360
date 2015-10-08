
public class Person {
	public String name;
	public int bday;
	public int ssn;
	public boolean sort;
	
	public Person(String n, int b, int s, boolean o){
		name = n;
		bday = b;
		ssn = s;
		sort = o;}
	
	public String getName(){
		return name;}
	
	public int getDOB(){
		return bday;}
	
	public int getSocial(){
		return ssn;}
	
	public String toString(){
		String s = Integer.toString(ssn);
		while (s.length() < 9){ s = "0" + s;}
		String dob = Integer.toString(bday);
		while (dob.length() < 8){dob = "0" + dob;}
		return (name + " " + s.substring(0,3)+"-"+s.substring(3,5)+"-"+s.substring(5)+" "+dob.substring(0,2)+"/"+dob.substring(2,4)+"/"+dob.substring(4));
	}
	
	public boolean equals(Person other){
		if (sort){
			return (ssn == other.getSocial());		
		}
		else{
			return (bday == other.getDOB());
		}}
	public boolean greaterThan(Person other){
		if(sort){
			return (ssn > other.getSocial());
		}
		else{
			return (bday > other.getDOB());
		}}
	public boolean lessThan(Person other){
		if (sort){
			return (ssn < other.getSocial());	
		}
		else{
			return (bday < other.getDOB());
		}
	}
		
	}
	

