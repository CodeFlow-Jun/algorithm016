package Week02;
// 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1:
// 输入: s = "anagram", t = "nagaram"
// 输出: true
//
// 示例 2:
// 输入: s = "rat", t = "car"
// 输出: false
//
// 说明:
// 你可以假设字符串只包含小写字母。

import java.util.Arrays;

public class Valid_Anagram_242 {
    // 1. 暴力法，String -> chan[] -> sort() -> equals()
    // 时间复杂度：O(nlogn)，n是s的长度
    // 空间复杂度：O(1)
    /*
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] a1 = s.toCharArray();
        char[] a2 = t.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }
     */
    // 2. 哈希映射，初始化一个26位计数器表
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++; // s增加
            counter[s.charAt(i) - 'a']--; // t减少
        }
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] != 0)
                return false;
        }
        return true;
    }
}
