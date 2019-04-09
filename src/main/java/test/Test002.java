package test;

import java.util.Arrays;

public class Test002 {
	//-----------------快速排序（优化后：三数取中）-----------------
	public static int partition(int []array,int lo,int hi){
        //三数取中
        int mid=lo+(hi-lo)/2;
        System.out.println(mid);
        if(array[mid]>array[hi]){
            swap(array[mid],array[hi]);
        }
        if(array[lo]>array[hi]){
            swap(array[lo],array[hi]);
        }
        if(array[mid]>array[lo]){
            swap(array[mid],array[lo]);
        }
        int key=array[lo];
        System.out.println(key);

        while(lo<hi){
            while(array[hi]>=key&&hi>lo){
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }
    
    public static void swap(int a,int b){
        int temp=a;
        a=b;
        b=temp;
    }
    
    
    public static void sort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return ;
        }
        int index=partition(array,lo,hi);
        sort(array,lo,index-1);
        sort(array,index+1,hi);
    }
    
    
    
    //-------------冒泡排序 （优化后的，设置标志位）-----------------
    public static void BubbleSort(int[] arr) {
        boolean flag = true;
        while(flag){
            int temp;//定义一个临时变量
            for(int i=0;i<arr.length-1;i++){//冒泡趟数，n-1趟
                for(int j=0;j<arr.length-i-1;j++){
                    if(arr[j+1]<arr[j]){
                        temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                        flag = true;
                    }
                }
                if(!flag){
                    break;//若果没有发生交换，则退出循环
                }
            }
        }
    }    
    
    
    public static void main(String[] args) {
        int array[] = new int[]{1,6,2,5,10,8,9};
        //BubbleSort.BubbleSort(array);
        sort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }
    
}
