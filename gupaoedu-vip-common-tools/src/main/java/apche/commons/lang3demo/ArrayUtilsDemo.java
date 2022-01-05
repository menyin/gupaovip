package apche.commons.lang3demo;

import org.apache.commons.lang3.ArrayUtils;

public class ArrayUtilsDemo {
    public static void main(String[] args) {

        //增加元素
        /*int[] ints = {1, 2, 3};
        int[] add = ArrayUtils.add(ints, 5);
        for (int i : add) {
            System.out.println(i);
        }
        System.out.println(add==ints);//得到的对象其实是个新的数组*/

        //删除元素
        int[] ints = {1, 2, 3};
        int[] remove = ArrayUtils.remove(ints, 2);
        for (int i : remove) {
            System.out.println(i);
        }
        System.out.println(remove==ints);//得到的对象其实是个新的数组

        //其它操作详见博客园收藏....



    }
}
