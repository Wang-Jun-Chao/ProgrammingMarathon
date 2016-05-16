#网页浏览历史

##题目描述
    标准的网页浏览器都提供一个功能：保留最近浏览过页面的历史记录。通过后退或向前按钮就能在历史记录之间跳转。
    现在，请你模拟这个功能，接收如下三条指令:
    1. BACK：回退功能，即回退到上一个访问的页面；
    2. FORWARD：使用BACK返回上一页之后，可以使用FORWARD回到下一页；
    3. VISIT url：访问指定url的页面，并且所有FORWARD的页面都被清空。

##输入描述:
    输入包含多组数据，每组数据第一行包含一个正整数n（1≤n≤100）。
    紧接着有n行，每一行包含一条指令。其中url是不包含空格、长度不超过100的非空字符串。


##输出描述:
    对应每组数据，为每条指令输出当前页面的URL。
    如果当前指令无效（例如没有上一页时执行BACK指令、或没有下一页时执行FORWARD指令），则输出一行“ignore”。
    每组数据之后输出一个空行作为分隔。

##输入例子:
    13
    VISIT http://www.acm.org/
    VISIT http://acm.ashland.edu/
    VISIT http://acm.baylor.edu/acmicpc/
    BACK
    BACK
    BACK
    FORWARD
    VISIT http://www.ibm.com/
    BACK
    BACK
    FORWARD
    FORWARD
    FORWARD

##输出例子:
    http://www.acm.org/
    http://acm.ashland.edu/
    http://acm.baylor.edu/acmicpc/
    http://acm.ashland.edu/
    http://www.acm.org/
    ignore
    http://acm.ashland.edu/
    http://www.ibm.com/
    http://acm.ashland.edu/
    http://www.acm.org/
    http://acm.ashland.edu/
    http://www.ibm.com/
    ignore