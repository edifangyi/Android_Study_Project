模仿 QQ 5.0 侧拉菜单
=====
-------------------------------------------------------------
### 一般的侧滑的实现  

ViewGroup Menu + Context  

onTouchEvent  
MOVE:  

	ViewGroup的lefMargin  

UP:根据显示菜单的宽度，决定将其隐藏或者显示  

	1.Scroller
	2.LeftMargin + Thread


继承： HorizontalScrollView

自定义ViewGroup  

	1.onMeasure  
        决定内部view（子View）的宽和高，以及自己的宽和高  
	2.onLayout  
		决定子View放置的位置
	3.onTouchEvent
	    判断手指动作，决定效果

### 自定义属性  
允许用户自定义设置菜单离屏幕右边的边距  

1. 书写xml文件 valuse/attr.xml
2. 在布局文件中进行使用，特别注意xmlns
3. 在构造方法中（3个参数的构造方法）中火的我们设置的值

-------------------------------------------------------------

### 抽屉式侧滑

菜单是在下面不动，没有被拖出来的效果。在内容区域底下

	mMenuWidth    
	100px mMenuWidth-100  
	200px mMenuWidth-200  

属性动画(Android 3.0 以上):TraslationX

	getScrollX :mMenuWidth ~ 0

设置调用动画时机  
ACTION MOVE

-------------------------------------------------------------
### QQ5.0

1. 内容区域 1.0 ~0.7 缩放效果  

2. 菜单的变化量需要修改

3. 菜单的显示时有缩放以及透明度变化  

    	缩放效果： 0.7 ~ 1.0  
			1.0 - scale*0.3  
		透明度：0.6 ~ 1.0  
			0.6 + 0.4*scale
-------------------------------------------------------------
# 总结
-------------------------------------------------------------
## 自定义ViewGroup
1. 构造方法的选择，获得一些需要用到的值
2. onMeasure 计算子 View 的宽和高，以及设置自己的宽和高
3. onLayout 决定子 View 的布局的位置
4. onTouchEvent 动画可选

## 构造方法
1. context------new CustomViewGroip(context)

		this(context, null);
2. context 、attr 布局文件中声明（没有自定义的属性）
		
		this(context, attrs, 0);
3. context 、attr 、defStyle （有自定义的属性）

## 自定义属性
1. attr.xml
2. 布局文件中 xmlns=
3. 在3个参数的构造方法中，获得我们自定义属性的值

## 属性动画

Android 3.0 之前添加的

	nineoldanimation.jar

Android 3.0之后不用添加，API都一样

=====================
## 普通菜单切换
![Aaron Swartz](https://raw.githubusercontent.com/edifangyi/Android_Study_Project/master/QQ%205.0/%E7%B4%A0%E6%9D%90%E5%8F%8A%E7%AC%AC%E4%B8%89%E6%96%B9jar/%E6%99%AE%E9%80%9A%E8%8F%9C%E5%8D%95%E5%88%87%E6%8D%A2.png)

## 抽屉式缩放透明度菜单
![Aaron Swartz](https://raw.githubusercontent.com/edifangyi/Android_Study_Project/master/QQ%205.0/%E7%B4%A0%E6%9D%90%E5%8F%8A%E7%AC%AC%E4%B8%89%E6%96%B9jar/%E6%8A%BD%E5%B1%89%E5%BC%8F%E7%BC%A9%E6%94%BE%E9%80%8F%E6%98%8E%E5%BA%A6%E8%8F%9C%E5%8D%95.png)

