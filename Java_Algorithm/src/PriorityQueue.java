
public class PriorityQueue {

	private int maxSize;
	private long[] queueArray;
	private int nItems;
	
	public PriorityQueue(int s){
		this.maxSize=s;
		queueArray=new long[maxSize];
		nItems=0;
	}
	public void insert(long item){
		int j;
		if(nItems==0){
			queueArray[nItems++]=item;
		}else {
			for(j=nItems-1;j>=0;j--){
				if(item>queueArray[j])
					queueArray[j+1]=queueArray[j];
				else break;
			}
			queueArray[j+1]=item;
			nItems++;
		}
		
	}
}
