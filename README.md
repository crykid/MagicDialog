# MagicDialog

	这是我的第一个开源项目，我不是太清楚需要具体遵守什么细则，如果大家发现有什么问题，还烦请及时告知我；
	
## 一.原理

>说到原理我有些难以启齿，并没有用到什么高深的技术和方法。支持java-8，仅仅是直接继承自系统Dialog，使用xml实现UI，通过建造者模式控制相关的功能，代码简陋怕是贻笑大方我就不挂出来了。

## 二.使用方法及步骤
####	1.在project的build.gradle中添加以下代码：
```
allprojects {
		repositories {
			...
			//这一行
			maven { url 'https://jitpack.io' }
		}
	}
```
####	2.在项目的module的build.gradle中添加以下代码：
```
dependencies {
	//这一行	
	implementation 'com.github.crykid:MagicDialog:v1.0.0'
}
```

#### 3.在代码中需要使用的地方
```
        new MagicDialog
                        .Builder(MainActivity.this)
                        //添加title
                        .title("this is title")
                        //添加图标
                        .icon(R.mipmap.ic_launcher_round)
                        //添加主要提示内容
                        .content("this is content")
                        //添加输入框
                        .input("please enter", "")
                        //添加"确定"等积极性意义的按钮及回调
                        .positiveEvent("confirm", view -> {
								//doSth
                        })
                        //添加"取消"等否定性意义的按钮及回调
                        .negativeEvent("cancel", view -> {
								//doSth
                        })
                        //添加列表并添加adapter
                        .recyclerView(null)
                        //当需要引导用户点击确认时底部的关闭按钮
                        .bottomDismissEvent(v->{ //doSth })
                        //是否允许点击外部或返回取消弹窗
                        .cancelAble(false)
                        .build()
                        .show();

```

结果如下：

![all function](https://raw.githubusercontent.com/crykid/MagicDialog/master/pngs/dialog.jpg)


####	4.在上面3中已经涵盖了当前的所有功能，但是还是需要特别补充一些：
- 关于列表RecyclerView
		
>可以看到在3中，我调用了recyclerView(RecyclerView.adapter)方法，但是在截图中并没有显示出来。在当前库中，并没有实现adapter以及内置的adapter，需要您自己去实现；
		
>在这里recyclerView默认是 列表垂直排列的，如果您想使用使用其它使用方式，可以自己实现adapter的同时实现LayoutManager（同时列表尽量不要太长哦，否则，你会看到很奇怪的样子）：

```
RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
builder.recyclerView(mAdapter,manager);
```

- 关于按钮及回调
 
>在上面的示例中，每个按钮都添加了文案及回调。当点击了按钮仅仅需要弹窗消失而不需要做任何事情的时候，怎么办呢，当然，可以不添加回调，但是，按钮文案是必须的：

```
    new MagicDialog
                    .Builder(this)
                    .title("title")
                    .content("this is content")
                    .positiveEvent("sure")
                    .negativeEvent("cancel")
                    .bottomDismissEvent()
                    .build()
                    .show();
```

####	5.最后一些补充
>目前这些功能仅仅是我近一个月中所需功能的概括，可能并不能满足您的需求，不过我会很快更新一些其它的功能出来。
>例如：不能更改Theme，主动控制dismiss()/cancel(),progress,按钮点击动效，以及没有使用butterknife等等，后期如果需要我会加上的
