package com.hys.dichotomie;

public class model_tools {
	
	public static void insert(Long[] data,int index,Long data_atom){
			data[index]=data_atom;
	}
	public static void delete(Long[] data,int index){
			for(int i=index;i<data.length;i++){
				if(data[i+1]!=null){
				data[i]=data[i++];
				}
			}
	}
	//二分法查找
	//data是有序数组
	//param是待查找位置的数据
	public static int dichotomie_find(Long[] data,Long param){
		int start,end,current;
		start=0;
		end=data.length-1;
		while(true){
			current=(start+end)/2;
			if(data[current]>param){
				start=current;
			}else if(data[current]<param){
				end=current;
			}else if(data[current]==param){
				return current;
			}else if(start>end){
				return 001;
			}
			
		}
	}
}
