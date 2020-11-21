class Overload{
    public static void main(String[] a){
	System.out.println(1);
    }
}
class test{
	public int one(){
		return 1;
	}

	public int one(){
		return 3;
	}

	public int one(int x){
		return x;
	}
}