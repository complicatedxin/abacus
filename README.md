## 示例
### 1. 数值计算
基础计算工具类`Abacus`中，接口入参支持继承自`Number`类型的数字
```java
Number b = (byte) 1;
Number d = 8.08D;
Assert.assertEquals(new BigDecimal("9.08"), Abacus.add(b, d));
Assert.assertEquals(new BigDecimal("-7.08"), Abacus.subtract(b, d));
Assert.assertEquals(new BigDecimal("8.08"), Abacus.multiply(b, d));
Assert.assertEquals(new BigDecimal("0.12"), Abacus.divide(b, d));
```
### 2. 表达式计算
```java
Assert.assertEquals(new BigDecimal("0.04"),
        Calculator.calc("4 % 1 ", null));
Assert.assertEquals(new BigDecimal("0.00"),
        Calculator.calc("0 / 1", null));
```
为了融入实际计算，`%`的计算逻辑改为：x的百分之一

### 3. 表达式参数赋值
表达式中参数名前需加`#`声明，将计算时必要的参数纳入到`Map<String, Number>`集合传入
```java
Map<String, Number> variables = new HashMap<String, Number>() {{
    put("a", 5);
    put("b", 12);
    put("p", 1_0000);
    put("ir", 0.7);
}};

Assert.assertEquals(new BigDecimal("13.00"),
        Calculator.calc("(#a^2 + #b^2)^0.5", variables));

Assert.assertEquals(new BigDecimal("10070.00"),
        Calculator.calc("#p + #p % #ir", variables));
```

### 4. 计算精度
`.precision(runningPrecision, finalPrecision, rounding)`设置分为计算中精度、结果精度、凑整规则三个参数
+ 计算中精度：所有计算方式均会在计算中返回给定精度的结果，默认6位
+ 结果精度：表达式结果输出时保留的位数，默认2位
+ 凑整规则：保留精度位时的取值规则，默认逢五进一
```java
Assert.assertEquals(new Calculator().precision(3, 4).calc("1 / 200 * 100"),
        new BigDecimal("0.5000")); // 0.005(0.005) * 100

Assert.assertEquals(new Calculator().precision(2, 4).calc("1 / 200 * 100"),
        new BigDecimal("1.0000")); // 0.005(0.01) * 100
```

## 未发布特性
### 1. 过程记录
见`feature/process`分支

通过`.recordProcess(processName)`方法开启过程记录
```java
Calculator calculator = new Calculator();
BigDecimal res = calculator
        .recordProcess("test")
        .variable("a", 5)
        .variable("b", 3)
        .calc("( #a / #b - (1 + 2) % 90 ) * 5");
System.out.println(calculator.getProcessStepQueue().generateProcessReport());
System.out.println(res);
```
输出：
```text
CalcProcess[test]: ( #a / #b - (1 + 2) % 90 ) * 5
- 5 / 3 = 1.666667
- 1 + 2 = 3
- 3 % 90 = 2.700000
- 1.666667 - 2.700000 = -1.033333
- -1.033333 * 5 = -5.166665

-5.17
```

