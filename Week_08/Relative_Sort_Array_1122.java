package Week08;

public class Relative_Sort_Array_1122 {
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        //第一步：将arr1中数据分配到各桶中
        int[] bucket=new int[1001];
        int[] res=new int[arr1.length];
        for (int data:arr1)
            bucket[data]++;
        //第二步：按照arr2的顺序收集桶中数据
        int j=0;
        for (int data:arr2){
            while (bucket[data]-->0)
                res[j++]=data;
        }
        //第三步：按升序顺序收集桶中其他数据
        for (int i=0;i<1001;i++){
            while (bucket[i]-->0)
                res[j++]=i;
        }
        return res;
    }
}
