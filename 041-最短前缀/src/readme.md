#最短前缀

##题目描述
        一个字符串的前缀是从该字符串的第一个字符起始的一个子串。例如“carbon”的前缀是有“c”，“ca”，“car”，
    “carb”，“carbo”，和“carbon”。空串不是前缀，但是每个非空串是它自身的子串。
        我们希望能用前缀来缩略地表示单词。例如“carbohydrate”通常用“carb”来表示。在下面的例子中，
    “carbohydrate”能被缩写成“carboh”，但是不能被缩写成“carbo”（或其余更短的前缀），因为已经有一个单词
    用“carbo”开始。
        carbohydrate
        cart
        carbonic
        caribou
        carriage
        car
        一个完全匹配会覆盖一个前缀匹配，例如“car”完全匹配单词“car”。因此“car”是“car”的缩略语是没有二义
    性的，“car”不会被当成“carriage”或者任何在列表中以“car”开始的单词。
        现在给你一组单词，要求找到所有单词唯一标识的最短前缀。

##输入描述:
    输入包含多组数据，每组数据第一行包含一个正整数n（2≤n≤1000）。

    紧接着n行单词，单词只有小写字母组成，长度不超过20个字符。

##输出描述:
    对应每一组数据，按照输入顺序依次输出每个单词的最短前缀。

    每组数据之后输出一个空格作为分隔。

##输入例子:
    3
    ab
    a
    acb
    6
    carbohydrate
    cart
    carbonic
    caribou
    carriage
    car

##输出例子:
    ab
    a
    ac

    carboh
    cart
    carbon
    cari
    carr
    car