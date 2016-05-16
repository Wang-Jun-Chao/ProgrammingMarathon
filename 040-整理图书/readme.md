#整理图书

##题目描述
    图书馆最近新进了一批书，管理员们需要把这些书按照所属类别以及书名分别放置在不同的书架上。
    但由于书太多了，这可忙坏了管理员们，你能帮他们整理一下这些新书吗？

##输入描述:
    输入包含多组数据。
    每组数据第一行包含一个正整数n (1≤n≤1000)。

    紧接着n行，每行包含一个书名和这本书的所属类别。书名和类别名都只有字母构成，并且长度均不超过32个字符。


##输出描述:
    对应每一组数据，按照要求先按照类别排序（大小写无关），再按照书名排序（大小写无关），最后输出排好序的书名，
    每一个书名占一行。

    每一组数据之后输出一个空行做分隔符。

##输入例子:
    3
    JavaScriptInAction Programming
    OnLisp Lisp
    LetOverLambda Lisp
    3
    abc ZZZ
    XXX AAA
    DEF AAA

##输出例子:
    LetOverLambda
    OnLisp
    JavaScriptInAction

    DEF
    XXX
    abc