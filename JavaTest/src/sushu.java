
public class sushu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int total=0;
		for(int i=2;i<=100;i++){
			for(int t=2;t<=i;t++){
				if(i%t==0&&i!=t){
					break;
				}else{
					if(t==i){
						total+=i;
						//System.out.println(i);
						break;
					}else{
						continue;
					}
				}
			}
		}
		System.out.println(total);
	}

}
