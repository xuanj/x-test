public enum Instance{
	Instance;
	public String name;
	public synchronized void getName(String name){
		this.name = name;
	}
}
