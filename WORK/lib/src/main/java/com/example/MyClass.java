package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyClass {

    public static void main(String[] args) {
        System.out.println("///////////////////////////////////NO.1//////////////////////////////////");
        //1.遍历出一个文件夹下的所有文件
//        traverseFolder("D:\\wan");
        System.out.println("////////////////////////////////////NO.2/////////////////////////////////");
        //2.输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
//        readStringCount("man你12 岁了");
        System.out.println("///////////////////////////////////NO.3//////////////////////////////////");
        //3.古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，
        // 小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
//        CountRabbit();
        System.out.println("///////////////////////////////////NO.4//////////////////////////////////");
        //4.有1,2,3,4四个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
//        CountUnmber();
        System.out.println("///////////////////////////////////NO.5//////////////////////////////////");
        //5.求1+2!+3!+...+20!的和
//        CountFactorial();
        System.out.println("///////////////////////////////////NO.6//////////////////////////////////");
        //6.5位数中找出所有，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同。
//        CountWan();
        System.out.println("///////////////////////////////////NO.7//////////////////////////////////");
        //7.打印出杨辉三角形（要求打印出10行以上）
//        PrintTriangle();
//        PrintTriangle2();
        System.out.println("///////////////////////////////////NO.8//////////////////////////////////");
        //8.计算字符串中子串出现的次数
//        CountStringChild("a");
        System.out.println("///////////////////////////////////NO.9//////////////////////////////////");
        //9.有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），凡报到3的人退出圈子，
        // 问最后留下的是原来第几号的那位。
        lookupNumChild(30);
        System.out.println("///////////////////////////////////NO.10//////////////////////////////////");
        //10.求100之内的素数  使用除sqrt(n)的方法求出的素数不包括2和3
//        lookupPrimeNumber();

    }

    /**
     * 1.遍历出一个文件夹下的所有文件，并展示出层级关系，
     * 文件夹优先显示，最后以一定格式写入文本中
     * 要求：尽可能简单、逻辑清晰
     * 所选存储格式结构也能看出层级关系，并可读写后直接使用
     * @param path
     */
    public static void traverseFolder(String path) {
        int fileNum = 0, folderNum = 0;
        //返回抽象路径名数组，这些路径名表示此抽象路径名表示的目录中满足指定过滤器的文件和目录。
        File file = new File(path);

        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                //目录  测试此抽象路径名表示的文件是否是一个目录。
                if (file2.isDirectory()) {
                    //获取路径
                    System.out.println("WenJianJia:" + file2.getAbsolutePath());
                    list.add(file2);
                    fileNum++;
                } else {
                    System.out.println("WenJian:" + file2.getAbsolutePath());
                    folderNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                //移除并返回此列表的第一个元素。
                temp_file = list.removeFirst();
                //文件列表 返回一个抽象路径名数组，
                // 这些路径名表示此抽象路径名表示的目录中的文件。
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("WenJianJia:" + file2.getAbsolutePath());
                        list.add(file2);
                        fileNum++;
                    } else {
                        System.out.println("WenJian:" + file2.getAbsolutePath());
                        folderNum++;
                    }
                }
            }
        } else {
            System.out.println("(WenJianfile) does not exist!");
        }
        System.out.println("Folder Co(WenJinaJiaGongYou):" + folderNum + ",File Co(WenJinaGongYou):" + fileNum);
    }
    /**
     * 2.输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
     */
    public static void readStringCount(String str){
        int abcCount=0;//英文字母个数
        int spaceCount=0;//空格键个数
        int numCount=0;//数字个数
        int otherCount=0;//其他字符个数
        /**
         * 它是以前的StringTokenizer和Matcher类之间的某种结合。
         * 由于任何数据都必须通过同一模式的捕获组检索或通过使
         * 用一个索引来检索文本的各个部分。于是可以结合使用正
         * 则表达式和从输入流中检索特定类型数据项的方法。这样，
         * 除了能使用正则表达式之外，Scanner类还可以任意地对字
         * 符串和基本类型(如int和double)的数据进行分析。借助于
         * Scanner，可以针对任何要处理的文本内容编写自定义的语
         * 法分析器。Scanner是SDK1.5新增的一个类,可是使用该类创
         * 建一个对象.Scanner reader=new Scanner(System.in);然
         * 后reader对象调用下列方法(函数),读取用户在命令行输入
         * 的各种数据类型:next.Byte(),nextDouble(),nextFloat,
         * nextInt(),nextLine(),nextLong(),nextShot()上述方法执
         * 行时都会造成堵塞,等待用户在命令行输入数据回车确认.例如,
         * 拥护在键盘输入12.34,hasNextFloat()的值是true,而hasNextInt()
         * 的值是false. NextLine()等待用户输入一个文本行并且回车,该方
         * 法得到一个String类型的数据。
         */
        char[] ch = str.toCharArray();
        for(int i=0;i<ch.length;i++){
            //Character是一个Java类isLetter(char ch)确定指定字符是否为字母。
            if(Character.isLetter(ch[i])){//判断是否字母
                abcCount++;
            }
            else if(Character.isDigit(ch[i])){//判断是否数字
                numCount++;
            }
            else if(Character.isSpaceChar(ch[i])){//判断是否空格键
                spaceCount++;
            }else{//以上都不是则认为是其他字符
                otherCount++;
            }
        }
        System.out.println("Number of letters:abcCount="+abcCount);
        System.out.println("Number of digits:numCount="+numCount);
        System.out.println("Number of spaces:spaceCount="+spaceCount);
        System.out.println("Number of other characters:otherCount="+otherCount);
    }
    /**
     * 3.古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，
     * 小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，
     * 问每个月的兔子总数为多少？
     */
    public static void CountRabbit(){
        //集合是存储兔子的个数的
        List list = new ArrayList();
        //初始化第一只兔子
        list.add(new littleRabbit());
        //外循环是月份
        for (int k = 1; k <=6; k++) {
            //内训环是兔子的个数
            for (int j = 0; j < list.size(); j++) {
                //取出集合中兔子的对象
                littleRabbit rabbit = (littleRabbit) list.get(j);
                //通过对象获取到兔子的年龄
                int age = rabbit.getAge();
                //判断兔子年龄是否达到合法生孩子的法定年龄
                System.out.println("No." + k + "Month ,No." + j + "Rabbit,age="+age);
                if (age >= 3) {
                    list.add(new littleRabbit());
                }
                //外循环一次兔子要加一月的年龄
                age++;
                //赋值赋值给兔子
                rabbit.setAge(age);
            }
            //打印每个月兔子的数量
            System.out.println("No.===" + k + "===MONTH,have===" + list.size() + "====rabbit");
        }
    }
    /**
     * 4.有1,2,3,4四个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
     */
    public static void CountUnmber(){
        int i,j,k,count=0;
        //外循环百位数
        for(i=1;i<5;i++) {
            //中循环十位数
            for (j = 1; j < 5; j++) {
                //内循环个位数
                for (k = 1; k < 5; k++) {
                    //三位数不能相等
                    if (i != j && i != k && j != k) {
                        System.out.println("" + i + j + k);
                        //统计无重复的三位数的个数
                        count++;
//                        if (count == 3) {
//                            System.out.println("count=" + count);
//                            count = 0;
//                        }
                    }
                }
            }
        }
        //打印无重复的三位数的个数
        System.out.println("count=" + count);
    }
    /**
     * 5.求1+2!+3!+...+20!的和
     * 例：2！=1*2；
     */
    public static void CountFactorial(){
        int total=0;
        int num=1;
        //外循环加法
        for (int i = 1; i <=20 ; i++) {
            //内循环阶乘，
            for (int f = 1; f <=i ; f++) {
                num=num*f;
//                System.out.println("num=="+num+"======"+f);
            }
            total=total+num;
            System.out.println("total=="+total+"---num=="+num+"=====i="+i+"=====f="+i);
            num=1;
        }

    }
    /**
     * 6.5位数中找出所有，判断它是不是回文数。即12321是回文数，
     * 个位与万位相同，十位与千位相同。
     */
    public static void CountWan(){
        //遍历所有的五位数
        for (int i = 10000; i < 100000; i++) {
            String s = i+"";
            char[] ch = s.toCharArray();
            //遍历五位数的前后两位是否相等
            for(int f = 0;f < ch.length;f++) {
                char c = ch[f];
                //查找回文数
                if (ch[0] == ch[4] && ch[1] == ch[3]) {
                    System.out.println("this number is Palindrome number:" + i);
                }
            }
        }
    }
    /**
     * 7.打印出杨辉三角形（要求打印出10行以上）
     */
    public static void PrintTriangle(){
        int triangle[][]=new int[10][];// 创建二维数组
        // 遍历二维数组的第一层
        for (int i = 0; i < triangle.length; i++) {
            triangle[i]=new int[i+1];// 初始化第二层数组的大小
            // 遍历第二层数组
            for(int j=0;j<=i;j++){
                // 将两侧的数组元素赋值为1
                if(i==0||j==0||j==i){
                    triangle[i][j]=1;
                }else{// 其他数值通过公式计算
                    triangle[i][j]=triangle[i-1][j]+triangle[i-1][j-1];
                }
                System.out.print(triangle[i][j]+"\t");// 输出数组元素
            }
            System.out.println();//换行
        }
    }
    public static void PrintTriangle2(){
        int[][] a =new int[10][10];
        a[0][0]=a[1][0]=a[1][1]=1;

        for(int k=1;k<10;k++)
            a[k][k]=1;
        for(int f=0;f<10;f++)
            a[f][0]=1;
        for(int i=2;i<10;i++){
            for(int j=1;j<i;j++){
                a[i][j]=a[i-1][j-1]+a[i-1][j];

            }
        }
        for(int i=0;i<10;i++){
            for(int j=0;j<=i;j++)
                System.out.print(a[i][j]+" ");
            System.out.println();
        }
    }
    /**
     * 8.计算字符串中子串出现的次数
     */
    public static void CountStringChild(String zimu){
        String str = "abcdefabhjlecababcab";
        String str1 = zimu;
        int count = 0;
        int start = 0;
        while (str.indexOf(str1, start) >= 0 && start < str.length()) {
            count++;
            start = str.indexOf(str1, start) + str1.length();
        }
        System.out.println(str1 + "++in++" + str + "++show++frequency++By++" + count);
    }
    /**
     * 9.有n个人围成一圈，顺序排号。从第一个人开始报数（从1到3报数），
     * 凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
     */
    public static void lookupNumChild(int porson) {
        boolean[] arr = new boolean[porson];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = true;
        }
        int leftCount = porson;
        int countNum = 0;
        int index = 0;
        while (leftCount > 1) {
            if (arr[index] == true) {
                countNum++;
                if (countNum == 3) {
                    countNum = 0;
                    arr[index] = false;
                    leftCount--;
                }
            }
            index++;
            if (index == porson) {
                index = 0;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == true) {
                System.out.println("NO." + (i + 1) + "++Position's+Porson+Leave+behind.");
            }
        }
    }
    /**
     * 10.求100之内的素数  使用除sqrt(n)的方法求出的素数不包括2和3
     */
    public static void lookupPrimeNumber(){
        boolean b =false;
        System.out.print(2 + " ");
        System.out.print(3 + " ");
        for(int i=3; i<100; i+=2) {
            for(int j=2; j<=Math.sqrt(i); j++) {
                if(i % j == 0) {b = false;
                    break;
                } else{b = true;}
            }
            if(b == true) {System.out.print(i + " ");}
        }
    }
}
//兔子对象
class littleRabbit {
    private int age = 1;
    public void growUp() {
        this.age++;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
