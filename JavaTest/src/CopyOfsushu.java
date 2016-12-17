
public class CopyOfsushu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=2;i<=100;i++){
			for(int t=1;t<=i;t++){
				if(t!=1&&t!=i){
				if(i%t==0){
					break;
				}else{
					if(t==(i-1)){
						System.out.println(i);
						break;
					}else{
						continue;
					}
				}
				}
			}
		}

	}

}
