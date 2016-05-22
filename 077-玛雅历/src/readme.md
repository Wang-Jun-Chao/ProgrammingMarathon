#玛雅历

##题目描述
　　上周末，M.A 教授对古老的玛雅有了一个重大发现。从一个古老的节绳（玛雅人用于记事的工具）中，教授发现玛雅人使用了一个一年有 365 的叫做 Haab 的日历。这个 Haab 日历拥有 19 个月，在开始的 18 个月，一个月有 20 天，月份的名字分别是 pop, no, zip, zotz, tzec, xul, yoxkin, mol, chen, yax, zac, ceh, mac, kankin, muan, pax, koyab, cumhu. 这些月份中的日期用 0 到 19 表示。Haab 历的最后一个月叫做 uayet，它只有 5 天，用 0 到 4 表示。玛雅人认为这个日期最少的月份是不吉利的，在这个月法庭不开庭，人们不从事交易，甚至没有人打扫屋中的走廊。<br>
　　因为宗教的原因， 玛雅人还使用了另一个日历， 在这个日历中年被称为 Tzolkin(holly年)，一年被分成 13 个不同的时期，每个时期有 20 天，每一天用一个数字和一个单词相组合的形式来表示。使用的数字是 1~13，使用的单词共有 20 个，它们分别是：imix, ik, akbal, kan, chicchan, cimi, manik, lamat, muluk, ok, chuen, eb, ben, ix, mem, cib, caban, eznab, canac, ahau。注意：年中的每一天都有着明确的描述，比如，在一年的开始，日期如下描述： 1 imix, 2 ik, 3 akbal, 4 kan, 5 chicchan, 6 cimi, 7 manik, 8 lamat, 9 muluk, 10 ok, 11 chuen, 12 eb, 13 ben, 1 ix, 2 mem, 3 cib, 4 caban, 5 eznab, 6 canac, 7 ahau, 8 imix, 9 ik, 10 akbal………也就是说数字和单词各自独立循环使用。<br>
　　Hab 历和 Tzolkin 历中的年都用数字 0，、1、2…… 示表，数字 0 表示世界的开始。所所以第一天被表示成：Haab: 0 pop 0（Day Month Year格式），Tzolkin: 1 imix 0。<br>
　　请帮助 M.A.教授写一个程序可以把 Haab 历转化成 Tzolkin 历。

##输入描述:
　　输入包含多组数据，每组数据包含一个 Haab 历日期，格式为：日期 月份 年份。年份不超过5000。

##输出描述:
　　对应每一组输出，输出相应的Tzolkin 历日期。格式为：天数字 天名称 年份。

##输入例子:
```
10 zac 0
0 pop 0
10 zac 1995
```

##输出例子:
```
3 chuen 0
1 imix 0
9 cimi 2801
```