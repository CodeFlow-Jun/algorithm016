# 学习笔记

---------------------

## ***一、Arrays.sort()***
* **本质：双路快排**
* **代码模版：**
```java
import java.util.Scanner;
import java.util.Arrays;
 
public class sort_array {
    public static void main(String[] args) {
        //1.数组排序Arrays.sort();
        int[] a = {1,3,5,7,9,8,6,4,2};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        //[1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
```
1.对于很小的数组（长度小于27），会使用插入排序。 <br>
2.选择两个点P1,P2作为轴心，比如我们可以使用第一个元素和最后一个元素。  <br>
3.P1必须比P2要小，否则将这两个元素交换，现在将整个数组分为四部分：  <br>
（1）第一部分：比P1小的元素。  <br>
（2）第二部分：比P1大但是比P2小的元素。  <br>
（3）第三部分：比P2大的元素。  <br>
（4）第四部分：尚未比较的部分。  <br>
在开始比较前，除了轴点，其余元素几乎都在第四部分，直到比较完之后第四部分没有元素。  <br>
4.从第四部分选出一个元素a[K]，与两个轴心比较，然后放到第一二三部分中的一个。  <br>
5.移动L，K，G指向。  <br>
6.重复 4 5 步，直到第四部分没有元素。  <br>
7.将P1与第一部分的最后一个元素交换。将P2与第三部分的第一个元素交换。  <br>
8.递归的将第一二三部分排序。 <br> 

## ***二、冒泡排序***
* **代码模板：**
```java
  int a[] = {23, 48, 12, 56, 45};
	int temp;
	for(int i=0;i<a.length;i++) {
      for(int j=i+1;j<a.length;j++) {
          if(a[i]>a[j]) {
            temp=a[i];
            a[i]=a[j];
            a[j]=temp;
          }
      }
  }
```
## ***三、选择排序***
* **代码模板：**
```java
  int arr3[]= {23,12,48,56,45};
    for(int i=0;i<arr3.length;i++) {
		int tem=i;
                //将数组中从i开始的最小的元素所在位置的索引赋值给tem
		for(int j=i;j<arr3.length;j++) {
			if(arr3[j]<arr3[tem]) {
				tem=j;
			}
		}
		//上面获取了数组中从i开始的最小值的位置索引为tem，利用该索引将第i位上的元素与其进行交换
		int temp1=arr3[i];
		arr3[i]=arr3[tem];
		arr3[tem]=temp1;
}
```

## ***四、反转排序***
* **代码模板：**
```java
  int[] a={23,12,48,56,45};
	for(int i=0;i<a.length/2;i++) {
      int temp=a[i];
      a[i]=a[a.length-i-1];
      a[a.length-i-1]=temp;
	}
```

## ***五、插入排序***
* **代码模板：**
```java
  int []arr5={23,12,48,56,45};
	for (int i = 1; i < arr5.length; i++) {
		for (int j = i; j > 0; j--) {
			if (arr5[j - 1] > arr5[j]) {//大的放后面
				int tmp = arr5[j - 1];
				arr5[j - 1] = arr5[j];
				arr5[j] = tmp;
			}
		}
  }
```
