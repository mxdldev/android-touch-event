# android-touch-event
事件分发好比是领导给下属派活，假如有一个小公司，就三个级别，老板，经理，员工，老板现在有一个任务，他首先会把这是任务派给经理，然后经理接到这个任务，会把这个任务交给下面的员工去完成，如果这个员工觉得这个任务能搞定，那么他就把这个任务完成了，如果搞不定他会告诉他们的经理，经理拿到这个任务之后，如果经理能搞定，经理就自己完成了，如果他也搞不定，他就会去找老板，最终老板自己把这个任务给完成了。这个过程是不是和我们的View事件分发很像，简直是太形象了，我非常佩服谷歌的这些大神们，他们把事件传递机制设计的竟如此精妙。
版权声明：本文来自门心叼龙的博客，属于原创内容，转载请注明出处：https://blog.csdn.net/geduo_83/article/details/90144921

github源码下载地址：https://github.com/geduo83/android-touch-event<br>
[Android事件传递机制的探索与发现之View篇](https://blog.csdn.net/geduo_83/article/details/90145083)<br>
[Android事件传递机制的探索与发现之ViewGroup篇](https://blog.csdn.net/geduo_83/article/details/90145050)<br>
[Android事件传递机制的探索与发现之Activity篇](https://blog.csdn.net/geduo_83/article/details/90145008)<br>
[Android事件传递机制的探索与发现之总结篇](https://blog.csdn.net/geduo_83/article/details/90144921)<br>

在这之前我在网上看到一篇文章也是关于andoid的事件分发机制，我觉得他的总结比喻很到位，他说事件分发好比是领导给下属派活，假如有一个小公司，就三个级别，老板，经理，员工，老板现在有一个任务，他首先会把这是任务派给经理，然后经理接到这个任务，会把这个任务交给下面的员工去完成，如果这个员工觉得这个任务能搞定，那么他就把这个任务完成了，如果搞不定他会告诉他们的经理，经理拿到这个任务之后，如果经理能搞定，经理就自己完成了，如果他也搞不定，他就会去找老板，最终老板自己把这个任务给完成了。这个过程是不是和我们的View事件分发很像，我太佩服我这位网友了，简直是太形象了，但我更佩服谷歌的这些大神们，他们把事件传递机制设计的竟如此精妙。

好了，现在我们言归正传，截止目前关于View事件传递机制的View篇，ViewGroup篇，Activity篇就已经全部讲完了,在前面几篇文章，我们主要通过不断的修改View,ViewGroup,以及Activity他们的dispatchTouchEvent方法,onInterceptTouchevent方法和onTouchevent方法的返回值来测试整个android系统的事件分发流程，为此我画了一张泳道流程图帮助大家来理解它，这张图比网上大部分流程图好理解多了，因为我看网上很多触摸事件流程图都是View和ViewGroup是分开画的，这样一来就把一个完整的事物分割开来，直接就导致很多人当时是记住了，过一阵一就忘记了，要么就是记混淆了，有了这张图流程图，妈妈再也不用担心我们在复杂的事件分发中迷路了。
![Android事件分发流程图](https://upload-images.jianshu.io/upload_images/15342541-f9b363c39567d4bf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


### Activity的事件分发机制
如果事件在传递过程中，对事件分发函数，如果调用父类的同名方法则事件继续往下级View进行分发，如果进行了人工干预，返回true或者false则表示消费掉了，无论dispathTouchEvent返回什么值Activity的onTouchEvent方法永远不会被调用的，除非它下级子View的dispathTouchEvent方法或者onTouchEvent方法返回false，触摸事件才能回传给我们的Activity的onTouchEvent方法，才能引起Activity的onTouchEvent方法的调用

### ViewGroup的事件传递机制
触摸事件的传递顺序是由Activity到ViewGroup，再由ViewGroup递归传递给他的子View

如果事件在传递过程中，对事件分发函数进行了人工干预，返回true表示事件被自行消费，返回false则回传给父View的onTouchEvent进行处理，此时后面的事件都接受不到了，调用同名方法则继续交给他的onInterceptTouchEvent方法进行处理

onInterceptTouchEvent方法对事件进行拦截，如果该方法返回true，则事件不会继续往下传递给子View，而是交给自己的onTouchEvent进行处理，如果返回false或者是调用父类的同名方法则表示事件会继续会传递给子View

如果事件在传递过程中，对事件处理函数进行了人工干预，返回true则表示该事件被消费，返回false和调用同名方法则表示该事件回传给父类的同名方法进行处理

### View的事件传递机制
触摸事件的传递流程是从dispatchTouchEvent开始的，如果不进行人工干预，则事件将会依照View树的嵌套层次从外层向内层传递，到达最内层的View时，就由它的onTouchVent方法处理

如果事件在传递过程中，对事件分发函数进行了人工干预，如果返回true表示自行消费，返回父类的同名方法则该事件传递给自身的onTouchEvent进行处理，返回false表示该事件会回传给父view的onTouchEvent方法进行处理，此时后面的事件都接受不到了，最后由哪个View处理，以后的所有事件都交由它来处理

如果事件在传递过程中，对事件处理函数进行了人工干预，返回true和调用同名方法表示该事件被消费，返回false则表示该事件回传给父View的onTouchEvent进行处理

事件触发是先触发onTouch，再触发onClick，如果onTouch方法返回tue，表示消费掉该事件，不在继续进行事件传递，onClick也不会被调用，如果onTouch方法返回false，则继续会事件传递，onClick会被调用

### 事件传递的三个阶段

*  按照View进行划分
![按照View进行划分](https://upload-images.jianshu.io/upload_images/15342541-10c81f05bb6c6643.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

*   按照事件进行划分
![ 按照事件进行划分](https://upload-images.jianshu.io/upload_images/15342541-1b11646eec78ffcf.jpeg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 结论
无论是View还是ViewGroup ，调用dispatchTouchEvent方法的时候，返回true表示自行消费了，返回false表示事件会回传给父View的onTouchEvent方法进行处理，只有调用父类同名方法的时候略有不同，如果是ViewGroup表示该事件会分发给他的onInterceptTouchEvent方法进行处理，如果是View则表示还事件会分发给自己的onTouchEvent方法进行处理。

无论是View还是ViewGroup ，如果是调用了onTouchEvent方法，方法返回true表示消费掉了，返回false表示该事件会回传给父View的onTouchEvent方法进行处理的，只是调用父类的同名方法的时候处理的逻辑略有不同，如果是View则表示该事件被消费掉了，如果是ViewGourp也表示该事件会回传给父View的onTouchEvent方法进行处理，View偏重消费、ViewGourp偏重分发。

无论是View，还是ViewGourp，无论是调用dispatchTouchEvent方法，还是调用了onTouchEvent，返回true表示消费掉了，返回false表示事件回传给父View了，只是调用父类的同名方法的时候略有不同。
