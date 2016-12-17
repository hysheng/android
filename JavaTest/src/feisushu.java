
public class feisushu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=1;i<=100;i++){
			for(int t=1;t<=i;t++){
				if(t!=1&&t!=i){
				if(i%t==0){
					System.out.println(i);
					break;
				}
				}
			}
		}

	}

}
