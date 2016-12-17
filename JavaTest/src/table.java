
public class table {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=1;i<=9;i++){
			for(int t=1;t<=i;t++){
				System.out.print(i+"*"+t+"="+i*t+"\t");
			}
			System.out.println();
		}
		for(int i=9;i>=1;i--){
			for(int t=1;t<=i;t++){
				System.out.print(i+"*"+t+"="+i*t+"\t");
			}
			System.out.println();
		}

	}

}
