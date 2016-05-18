#单词迷阵

##题目描述

　　单词迷阵游戏就是从一个10x10的字母矩阵中找出目标单词，查找方向可以从左往右、从右往左、从上往下或者从下往上。例如下面的迷阵中包含quot等单词。
```
rmhlzxceuq
bxmichelle
mnnejluapv
caellehcim
xdydanagbz
xinairbprr
vctzevbkiz
jgfavqwjan
quotjenhna
iumxddbxnd
```
　　现给出一个迷阵，请你判断某个单词是否存在其中。

##输入描述:
　　输入有多组数据。<br>
　　每组数据包含两部分。<br>
　　第一部分有10行，是一个10x10的字母矩阵。<br>
　　第二部分第一行包含一个整数n (1≤n≤100)，紧接着n行，每行包含一个单词。单词的长度不会超过10。<br>


##输出描述:
　　对应每一个单词，如果它存在于迷阵之中，则输出“Yes”；否则输出“No”。<br>
　　每一组数据之后输出一个空行作为分隔。

输入例子:
```
rmhlzxceuq
bxmichelle
mnnejluapv
caellehcim
xdydanagbz
xinairbprr
vctzevbkiz
jgfavqwjan
quotjenhna
iumxddbxnd
7
dan
danz
brian
michelle
jen
jqi
paul
aaaaaaaaaa
aaaaaaaaaa
aaaaaaaaaa
aaaaaaaaaa
aaaaaaaaaa
aaaaaaaaaa
aaaaaaaaaa
aaaaaaaaaa
aaaaaaaaaa
aaaaaaaaaa
2
aaa
bbb
```

##输出例子:
```
Yes
Yes
Yes
Yes
Yes
Yes
Yes

Yes
No
```