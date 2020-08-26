package ru.javawebinar.basejava;

public class MainString {
     public static  void main(String[] args) {
         String[] strArray = new String[] {"1", "2", "3", "4", "5"};
         StringBuilder sb = new StringBuilder();
         int i=0;
         for(String str : strArray) {
             sb.append(str);
             if (i < strArray.length-1){
                 sb.append(", ");
             }
             i++;
         }
         System.out.println(sb.toString());

         String str1 = "abc";
         String str2 = "c";
         String str3 = ("ab" + str2).intern();
         System.out.println(str1 == str3);
     }
}
