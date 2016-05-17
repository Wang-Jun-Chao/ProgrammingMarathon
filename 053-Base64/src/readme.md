#Base64

##题目描述
　　Base64是一种基于64个可打印字符来表示二进制数据的表示方法，它Base64规定每76个字符后需要加上一个回车换行。例如：
`TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGlzIHNp`
需要输出成
```
TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGlz
IHNp
```
　　现在给你一串加密后的字符串，请你在每76个字符后面加上一个换行符。

##输入描述:
　　输入有多组数据。

　　每组数据占一行，由字母、数字等非空白符号组成的字符串，长度不超过50000。


##输出描述:
　　对应每一组输入，输出相应的转换后的文本。

　　每一组数据之后输出一个空行作为间隔。

##输入例子:
```
TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGlzIHN
IHNpbmd1bGFyIHBhc3Npb24gZnJvbSBvdGhlciBhbmltYWxzLCB3aGljaCBpcyBhIGx1c3Qgb2YgdGh
```

##输出例子:
```
TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGl
zIHN

IHNpbmd1bGFyIHBhc3Npb24gZnJvbSBvdGhlciBhbmltYWxzLCB3aGljaCBpcyBhIGx1c3Qgb2Y
gdGh
```