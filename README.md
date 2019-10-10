# **自定义**View动画篇

## 一、视图动画

### 1.1 补间动画

有两种实现方式：

> XML实现 
>
> 代码实现

#### 1.1.1 XML实现

● alpha: 渐变透明度的动画效果。

● scale : 渐变尺寸伸缩动画效果。

● translate: 画面变换位置移动动画效果。

● rotate: 画面转移旋转动画效果。

● set: 定义动画集。

在res文件夹下添加anim文件夹用于存放动画，或者写在drawable文件夹下。

##### 1.1.1.1 Animation继承属性

```xml
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="3000"
    android:fillAfter="true"
    android:fillBefore="true"
    android:repeatMode="restart"
    android:repeatCount="infinite"
    android:interpolator="@android:anim/linear_interpolator"
    android:fillEnabled="true">
```

- duration: 动画持续时间。(传入值为ms)
- fillAfter: 动画结束时保持动画结束时的状态。
- fillBefore/fillEnabled: 动画结束时将动画还原到初始化状态。
- repeatCount: 动画重复次数。(infinite表示无限播放)
- repeatMode: 动画重复形式。(reverse表示倒放，restart表示重新播放原动画)
- interpolator: 插值器。

##### 1.1.1.2 scale标签

相关参数：

```xml
<scale
        android:fromXScale="1.0"
        android:toXScale="3.0"
        android:fromYScale="1.0"
        android:toYScale="3.0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:repeatCount="infinite"
        android:repeatMode="restart"/>
```



- fromXScale/fromYScale: 动画开始时在X(Y)轴的伸缩倍数。(必须传入float类型数值)

- toXScale/toYScale: 动画结束时在X(Y)轴的伸缩倍数。(必须传入float类型数值)

- pivotX/pivotY: 用于指定动画起始点坐标。

  三种传值：        

  (1) 数值(50)：起始点即为坐标值。  

  (2) 百分数(50%): 起始点即为原点坐标基础上加上自己宽度的百分比。  

  (3) 百分数p(50%p): 起始点即为原点坐标基础上加上父布局宽度的百分比。

##### 1.1.1.3 alpha标签

相关参数：

```xml
<alpha
    android:fromAlpha="0.4"
    android:toAlpha="0.0"
    android:repeatCount="infinite" />
```

- fromAlpha: 动画开始时的透明度。
- toAlpha: 动画结束时的透明度。

##### 1.1.1.4 rotate标签

相关参数：

```xml
<rotate
    android:pivotX="50%"
    android:pivotY="50%"
    android:fromDegrees="0"
    android:toDegrees="180" />
```

- fromDegress: 动画开始时旋转的角度位置。
- toDegress: 动画结束时旋转的角度位置。
- pivotX/pivotY: 用于指定动画起始点坐标。

##### 1.1.1.5 translate标签

相关参数：

```xml
<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="3000"
    android:fillAfter="true"
    android:fromXDelta="0"
    android:toXDelta="0"
    android:fromYDelta="0"
    android:toYDelta="45%p">

</translate>
```

- fromXDelta/fromYDelta: 起始点X(Y)坐标。
- toXDelta/toYDelta: 结束点X(Y)坐标。

##### 1.1.1.6 set标签

相关参数：

```xml
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="3000"
    android:fillAfter="true"
    android:fillBefore="true"
    android:repeatMode="restart"
    android:repeatCount="infinite"
    android:interpolator="@android:anim/linear_interpolator"
    android:fillEnabled="true">

    <scale
        android:fromXScale="1.0"
        android:toXScale="3.0"
        android:fromYScale="1.0"
        android:toYScale="3.0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:repeatCount="infinite"
        android:repeatMode="restart"/>

    <alpha
        android:fromAlpha="0.4"
        android:toAlpha="0.0"
        android:repeatCount="infinite" />

    <rotate
        android:pivotX="50%"
        android:pivotY="50%"
        android:fromDegrees="0"
        android:toDegrees="180" />


</set>
```

**set本身并无属性，都从Animation处继承而来，在set标签下的属性对动画集中所有动画都起效果。**

##### 1.1.1.7 代码部分

```java
  private Button start;

  Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);
  animation.setInterpolator(new CycleInterpolator(1));
  start.startAnimation(animation);
```



#### 1.1.2 代码实现

标签与对应的类：（所有类都继承于Animation类）

|   标签    |         类         |
| :-------: | :----------------: |
|   scale   |   ScaleAnimation   |
|   alpha   |   AlphaAnimation   |
|  rotate   |  RotateAnimation   |
| translate | TranslateAnimation |
|    set    |    AnimationSet    |

Animation类里的方法与属性对应：

|       标签属性       |             方法              |
| :------------------: | :---------------------------: |
|   android:duration   |       setDuration(long)       |
|  android:fillAfter   |     setFillAfter(boolean)     |
|  android:fillBefore  |    setFillBefore(boolean)     |
|  android:repeatMode  |      setRepeatMode(int)       |
| android:repeatCount  |      setRepeatCount(int)      |
| android:interpolator | setInterpolator(Interpolator) |
| android:fillEnabled  |    setFillEnabled(boolean)    |

##### 1.1.2.1 普通标签例子

```java
 private ImageView imageView;

 RotateAnimation animation=new      RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
 animation.setRepeatCount(Animation.INFINITE);
 animation.setDuration(2000);
 animation.setInterpolator(new DecelerateInterpolator());
 imageView.startAnimation(animation);
```

##### 1.1.2.2 set标签例子

```java
private ImageView imageView;

AnimationSet set=new AnimationSet(true); //true表示在set中定义一个插值器，所有动画都共用这个插值器。
set.addAnimation(Animation);
set.setDuration(2000);
set.setFillAfter(true);

imageView.startAnimation(set);
```

##### 1.1.2.3 Animation

提供方法：

|                             方法                             |           作用           |
| :----------------------------------------------------------: | :----------------------: |
|                        void cancel()                         |         取消动画         |
|                         void reset()                         | 重置控件到动画开始前状态 |
| void setAnimationListener(Animation.AnimationListener listener) |       设置动画监听       |

#### 1.1.3 插值器

动画的变化速率是由插值器（Interpolator类）决定的，默认为匀速。

|             插值器类             |                      资源ID                      |          效果          |
| :------------------------------: | :----------------------------------------------: | :--------------------: |
| AccelerateDecelerateInterpolator | @android:anim/accelerate_decelerate_interpolator |      先加速后减速      |
|      AccelerateInterpolator      |      @android:anim/accelerate_interpolator       |          加速          |
|      AnticipateInterpolator      |      @android:anim/anticipate_interpolator       |        初始偏移        |
| AnticipateOvershootInterpolator  | @android:anim/anticipate_overshoot_interpolator  |     初始，结束偏移     |
|        BounceInterpolator        |        @android:anim/bounce_interpolator         |          弹跳          |
|        CycleInterpolator         |         @android:anim/cycle_interpolator         | 循环（速率为正弦函数） |
|      DecelerateInterpolator      |      @android:anim/decelerate_interpolator       |          减速          |
|        LinearInterpolator        |        @android:anim/linear_interpolator         |          匀速          |
|      OvershootInterpolator       |       @android:anim/overshoot_interpolator       |        结束偏移        |

### 1.2 逐帧动画

一帧一帧的播放动画。

#### 1.2.1 XML实现

**定义逐帧动画：**

```xml
<?xml version="1.0" encoding="utf-8"?>
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="true">

    <item
        android:drawable="@drawable/wangyiyun"
        android:duration="1000" />
    
    <item
        android:duration="1000"
        android:drawable="@drawable/scan_circle" />
</animation-list>
```

- android:oneshot: true执行一次，false一直循环。
- item为一帧的动画。

**设置ImageView：**

```xml
<ImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/iv"
    android:background="@drawable/animation_list" />
```

**开始动画：**

```java
ImageView image=(ImageView)findViewById(R.id.iv);
AnimationDrawable anim=(AnimationDrawable)image.getDrawable();
anim.start();
```

#### 1.2.2 代码实现

AnimationDrawable类：

![](https://github.com/xuesui/View/blob/master/app/src/main/res/drawable-v24/IMG_20190925_195834.jpg)



## 二、属性动画

### 2.1 ValueAnimator

> **为什么要引入属性动画？**
>
> 为了弥补视图动画的不足而设计的，它能针对控件的属性做出动画，例如颜色的渐变动画，这是视图动画无法实现的。
>
> 补间动画对控件进行操作时并没有改变其内部属性值，导致控件位置移动之后点击事件的响应还是在原来的位置，属性动画可以弥补这个缺点。



**常用函数**

- 构造函数：  

  ofInt(int...value)  

  ofFloat(float...value)  

  ofObject(TypeEvaluator,Object...values)  

- 回调监听：  

  addUpdateListener(ValueAnimator.AnimatorUpdateListener) 用于监听属性值的实时变化。  

  addListener(Animator.AnimatorListener)用于监听动画的进程，如开始，结束，取消，重复。  

  removeUpdateListener(listener)  

  removeAllUpdateListener()  

  removeListener(listener)  

  removeAllListener()  

- setDuration(long)  

- getAnimatedValue(): 获取ValueAnimator在运动时当前运动点的值。  

- start() cancel()  

- setRepeatMode(int)  setRepeatCount(int)  



**一个栗子**

```java
final ValueAnimator animator=ValueAnimator.ofInt(0xffffff00,0xff0000ff);
animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int color= (int) valueAnimator.getAnimatedValue();
        textView.setTextColor(color);
    }
});
animator.setDuration(2000);
animator.setEvaluator(new ArgbEvaluator());
textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        animator.start();
    }
});
```

[^注意]: 重复次数为INFINITE的动画，当Activity结束的时候，必须调用cancel()取消动画，否则动画将无限循环，导致View无法释放，因此Activity也无法释放，引起内存泄漏。  



### 2.2 自定义插值器与Evaluator

- **决定动画进度速度的类**  

系统提供插值器之前已经详细介绍过，我们从LineanerInterpolator源码来分析插值器的实现方式。  

```java
public class LinearInterpolator extends BaseInterpolator {
    public LinearInterpolator() {
        throw new RuntimeException("Stub!");
    }

    public LinearInterpolator(Context context, AttributeSet attrs) {
        throw new RuntimeException("Stub!");
    }

    public float getInterpolation(float input) {
        throw new RuntimeException("Stub!");
    }
}
```

```java
public abstract class BaseInterpolator implements Interpolator {
    public BaseInterpolator() {
        throw new RuntimeException("Stub!");
    }
}
```

```java
public interface Interpolator extends TimeInterpolator {
}
```

```java
public interface TimeInterpolator {
    float getInterpolation(float var1);
}
```

从源码可以看出，相当于插值器实现了TimeInterpolator接口，实现了接口里的getInterpolation方法，这个方法传入的值为时间进度（在0~1之间），返回值为就为当前动画进度（通过传入值根据一定计算方法得出），具体返回方法在getInterpolator中实现。  

- **Evaluator**

  > ValueAnimator动画流程  
  >
  > ​        ①                                   ②                                  ③                                     ④  
  >
  > ofInt(0,400)          ->          插值器         ->           Evaluator            ->        监听器返回  
  >
  > (定义动画数值区间)     (返回当前数值进度)     (根据进度计算当前值)         (在监听器返回)

  因此知道，Evaluator的作用便是根据插值器getInterpolation方法返回的动画进度值，来计算动画中的具体对应数值，从而返回给监听器。  

- **IntEvaluator、floatEvaluator**

  使用ofInt、ofFloat时默认使用上面两种evaluator，下面看看代码的实现。  

  ```java
  public class IntEvaluator implements TypeEvaluator<Integer> {
      public IntEvaluator() {
          throw new RuntimeException("Stub!");
      }
  
      public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
          throw new RuntimeException("Stub!");
      }
  }
  ```

  在evaluate函数中返回具体数值，参数中fraction为插值器getInterpolation返回的时间进度值。

- **argEvaluator**

  专门用于控件颜色的变化的evaluator。

  ```java
  final ValueAnimator animator=ValueAnimator.ofInt(0xffffff00,0xff0000ff);
  animator.addUpdateListener(valueAnimator -> {
      int color= (int) valueAnimator.getAnimatedValue();
      textView.setBackgroundColor(color);
  });
  animator.setDuration(2000);
  animator.setInterpolator(new LinearInterpolator());
  animator.setEvaluator(new ArgbEvaluator());
  textView.setOnClickListener(view -> animator.start());
  ```

- **自定义Evaluator以及ofObject的使用**

  ```java
  public static ValueAnimator ofObject(TypeEvaluator evaluator, Object... values) {
      throw new RuntimeException("Stub!");
  }
  ```

  第一个参数为evaluator，通常为自定义，后面为传入的对象。  

  自定义evaluator需继承TypeEvaluator<>，且泛型类型必须和后面传入对象类型一致，且需要在evaluate方法中处理转换关系，否则会报错。  

  例如想要显示A~Z字母的轮播，需要自定义Character类型evaluator，并且利用ASCII码进行转换。

- **总结**

  (1)可以通过自定义插值器和evaluator来管理控制动画进度。  

  (2)如果想自定义int，float以外的类型，需要使用ofObject，会很麻烦，所以下面介绍ObjectAnimator来解决这个问题。

### 2.3 ObjectAnimator

ObjectAnimator是ValueAnimator的派生类，所有ValueAnimator的函数ObjectAnimator都可以使用，更方便的对控件进行管理。  

使用例子：

```java
ObjectAnimator animator1=ObjectAnimator.ofFloat(textView,"alpha",1,0,1);
animator1.setDuration(2000);
animator1.start();
```

- 第一个参数指定作用的控件。
- 第二个参数指定操作的属性。
- 第三个参数为值的变化。

[^如何实现第二个参数决定操作属性？]: 通过属性拼装set函数并反射调用

例如，上面的例子在TextView中寻找setAlpha函数，并且调用。  

setAlpha是View中的函数，如果自定义View自己创建新的函数时需要注意：

- 如果最后一个参数仅传入一个，需要写get函数，函数返回的值就是动画的初始值。
- get，set函数的命名必须是 <u>驼峰命名法</u>。

> ObjectAnimator动画流程  
>
> ​        ①                                                                 ②                                  ③                                     ④  
>
> ofInt(tv,"translationX",0,400)          ->          插值器         ->           Evaluator            ->        监听器返回  
>
> (定义动画对象及区间)     (返回当前数值进度)     (根据进度计算当前值)             (根据属性拼装set函数反射调用)

### 2.4 AnimatorSet

#### 2.4.1 两种动画集构造方式

- **playSequentially()与playTogether()函数**

  playSequentially(Animator ... items) ：依次执行动画。

  playTogether(Animator ... items)：同时执行动画。

- **AnimatorSet.Builder**

  ```java
  public class Builder {
      Builder(Animator anim) {
          throw new RuntimeException("Stub!");
      }
  
      //和前面的动画一起执行
      public AnimatorSet.Builder with(Animator anim) {
          throw new RuntimeException("Stub!");
      }
  	//先执行这个动画，再执行前面的动画
      public AnimatorSet.Builder before(Animator anim) {
          throw new RuntimeException("Stub!");
      }
  	//执行前面的动画之后再执行这个动画
      public AnimatorSet.Builder after(Animator anim) {
          throw new RuntimeException("Stub!");
      }
  	//延时执行
      public AnimatorSet.Builder after(long delay) {
          throw new RuntimeException("Stub!");
      }
  }
  ```

  使用方法：

  ```java
  AnimatorSet animatorSet=new AnimatorSet();
  animatorSet.play(animator).with(animator1);
  ```

#### 2.4.2 监听器

和ValueAnimator一模一样，只不过监听的是AnimatorSet的状态，与其中动画无关。所以repeat回调永远不会调用。

#### 2.4.3 常用函数

常用的几种set函数不再多概述，多了一种setTarget()函数，用于设置目标控件。

- 除了setStartDelay函数以外的所有set函数都会覆盖动画的相同函数。
- setStartDelay函数会先等待AnimatorSet的延时结束，再开始动画执行，但是需要注意的是，**真正的动画开始延时=AnimatorSet.StartDelay+第一个动画.StartDelay**。

#### 2.4.4 示例：路径动画

[路径动画](https://github.com/xuesui/View/blob/master/app/src/main/java/com/example/view/activity/PathActivity.java)

### 2.5 属性动画XML实现

这里暂时省略

## 三、属性动画进阶

### 3.1 PropertyValuesHolder与Keyframe

#### 3.1.1 PropertyValuesHolder

- 概述：这个类的含义就是保存了动画过程中所需要操作的属性和对应的值。

- 类似于ofFloat方法便是把属性和值封装成一个PropertyValuesHolder对象，进而进行下一步操作。

- 构造方法：

  ```java
  public static PropertyValuesHolder ofInt(String propertyName, int... values) 
  
  public static PropertyValuesHolder ofFloat(String propertyName, float... values)
  
   public static PropertyValuesHolder ofObject(String propertyName, TypeEvaluator evaluator, Object... values)
   
    public static PropertyValuesHolder ofKeyframe(String propertyName, Keyframe... values)
  ```

  上面列出了最常用的几个构造方法，还有很多构造方法可以查询源码。

- 使用方法：和ObjectAnimator类似，都是采用拼接反射的方法寻找set函数从而进行动画操作，最后再调用ObjectAnimator的静态方法ofPropertyValuesHolder设置目标控件以及 PropertyValuesHolder。

  ```java
  public static ObjectAnimator ofPropertyValuesHolder(Object target, PropertyValuesHolder... values)
  ```

#### 3.1.2 Keyframe

**在PropertyValuesHolder的构造函数中可以看到，ofKeyframe需要传入Keyframe实例，那么这个类又有什么用处呢？**

- 概述：顾名思义，frame为帧数的意思，所以Keyframe类就是用传入的两个帧数，来自动补全中间帧数的工具类，十分好用。

- 构造函数：

  ```java
  public static Keyframe ofInt(float fraction, int value)
  
  public static Keyframe ofFloat(float fraction, float value)
  
  public static Keyframe ofObject(float fraction，Object value)
  ```

- 开始帧数和结束帧数都以最近的和最远的一个帧数决定，**注意至少要包含两个帧数！**

#### 3.1.3 综合例子

- propertyValuesHolder例子

  [字母动画](https://github.com/xuesui/View/blob/master/app/src/main/java/com/example/view/activity/PropertyActivity.java)

- keyframe例子

  [闹钟动画](https://github.com/xuesui/View/blob/master/app/src/main/java/com/example/view/activity/PropertyActivity.java)

### 3.2 ViewPropertyAnimator

- 在Android3.1中新增补充了这一机制，目的是使动画的代码更容易读写，并且不使用反射机制，而是计算每一帧属性和对应值，调用invalidate()进行重绘，可以轻微提升性能。

- 构造函数

  ```java
  public ViewPropertyAnimator animate() 
  ```

  调用View类的animate函数，获取ViewPropertyAnimator实例

- 常用函数：每个函数的返回值都为ViewPropertyAnimator，因此可以方便的链式调用。

  | 函数                                           | 含义                                        |
  | ---------------------------------------------- | ------------------------------------------- |
  | alpha(float value)                             | 透明度                                      |
  | scaleY(float value)                            | Y轴方向缩放大小                             |
  | scaleX(float value)                            | X轴方向缩放大小                             |
  | translationY(float value)                      | Y轴方向移动值                               |
  | translationX(float value)                      | X轴方向移动值                               |
  | rotation(float value)                          | 绕Z轴旋转度数                               |
  | rotationX(float value)                         | 绕X轴旋转度数                               |
  | rotationY(float value)                         | 绕Y轴旋转度数                               |
  | x(float value)                                 | 相对于父容器左上角坐标在X轴方向上的位置     |
  | y(float value)                                 | 相对于父容器左上角坐标在Y轴方向上的位置     |
  | alphaBy(float value)                           | 透明度增量                                  |
  | rotationBy(float value)                        | 绕Z轴旋转增量                               |
  | rotationXBy(float value)                       | 绕X轴旋转增量                               |
  | rotationYBy(float value)                       | 绕Y轴旋转增量                               |
  | translationXBy(float value)                    | X轴方向移动值增量                           |
  | translationYBy(float value)                    | Y轴方向移动值增量                           |
  | scaleXBy(float value)                          | X轴方向缩放大小增量                         |
  | scaleYBy(float value)                          | Y轴方向缩放大小增量                         |
  | xBy(float value)                               | 相对于父容器左上角坐标在X轴方向上的位置增量 |
  | yBy(float value)                               | 相对于父容器左上角坐标在Y轴方向上的位置增量 |
  | setInterpolator(TimeInterpolator interpolator) | 插值器                                      |
  | setStartdelay(long startDelay)                 | 开始延时                                    |
  | setDuration(long duration)                     | 动画时长                                    |

- 设置监听器：setListener()，和objectAnimator相同，个人认为用处多为组合动画设置不同时间开始。

### 3.3 为ViewGroup组件添加动画

#### 3.3.1 android:animateLayoutChanges属性

- xml：

  ```xml
  <LinearLayout
      android:id="@+id/ll_viewgroup"
      android:layout_width="match_parent"
      android:layout_height="0dp"
      android:orientation="vertical"
      app:layout_constraintBottom_toBottomOf="parent"
      app:layout_constraintEnd_toEndOf="parent"
      app:layout_constraintStart_toStartOf="parent"
      android:animateLayoutChanges="true"
      app:layout_constraintTop_toTopOf="@+id/guideline5" />
  ```

- 代码：

  ```java
  private void add() {
      id++;
      Button button = new Button(this);
      String string = "button" + id;
      button.setText(string);
      LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      button.setLayoutParams(params);
      linearLayout.addView(button);
  }
  
  private void delete() {
      if (id > 0) {
          linearLayout.removeViewAt(0);
      }
      id--;
  }
  ```

**缺点是只能使用默认的动画效果，所以我们多用下面介绍的来进行viewGroup动画的自定义。**

#### 3.3.2 LayoutTransition

使用步骤：  

- 创建实例

  ```java
  LayoutTransition transition=new LayoutTransition();
  ```

- 创建动画并进行设置

  ```java
  ObjectAnimator animator=ObjectAnimator.ofFloat(null,"alpha",0f,1f);
  transition.setAnimator(LayoutTransition.DISAPPEARING,animator);
  ```

- 将LayoutTransition设置到ViewGroup中

  ```java
  linearLayout.setLayoutTransition(transition);
  ```



setAnimator的参数：

- APPEARING: 元素在容器中出现时所定义的动画。
- DISAPPEARING: 元素在容器中消失时所定义的动画。
- CHANGE_APPEARING: 当添加新元素时其他元素应用的动画。
- CHANGE_DISAPPEARING: 当元素消失时其他元素应用的动画。



**注意：**

- 使用CHANGE_APPEARING和CHANGE_DISAPPEARING时，只能设置ProvertyValuesHolder所构造的动画才有效果。
- 构造ProvertyValuesHolder动画时，“left”，“top”属性必须写。
- 构造ProvertyValuesHolder动画时，ofInt（），ofFloat（）中的参数值，第一个和最后一个必须相同，否则会被弃用。
- 构造ProvertyValuesHolder动画时，如果ofInt（），ofFloat（）中的参数值全部相同，将不会有动画效果。

[添加删除控件动画](https://github.com/xuesui/View/blob/master/app/src/main/java/com/example/view/activity/ViewGroupActivity.java)
### 3.4 NineOldAndroids开源库动画

对官方的库进行了封装，除了没有LayoutTransition以外，其他几乎完全一样，ViewpropertyAnimator用法有些许不同。

```java
//官方API（3.1以上）
view.animate().setDuration(1000).x(100).y(100).start();
//NineOldAndroids
ViewPropertyAnimator.animate(view).setDuration(1000).x(100).y(100).start();
```

但是新增了一个类ViewHelper，提供了一系列静态set，get函数，以便于操作各种属性，方便了使用，还解决了官方API的兼容问题。

## 四、动画进阶

### 4.1 利用PathMeasure实现路径动画

- 初始化 ：

  ```java
  PathMeasure pathMeasure=new PathMeasure（）;
  pathMeasure.setPath(Path path,boolean forceClosed);
  
  //或者
  PathMeasure pathMeasure=new PathMeasure（Path path,boolean forceClosed）;
  ```

- 简单函数：

  |         函数          |       作用       |
  | :-------------------: | :--------------: |
  |   float getLength()   |   获取路径长度   |
  |  boolean isClosed()   | 获取路径是否闭合 |
  | boolean nextContour() | 跳转到下一条曲线 |

  一条路径中可能存在不同曲线，而getlength等测量函数都是获取当前曲线的长度，不是Path的全部长度，得到曲线的顺序与路径中曲线添加顺序相同。

- getSegment()函数（用于截取路径中的某个片段）：

  (1)基本用法：

  ```java
  public boolean getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)
  ```

  - float startD：开始截取位置距离Path起始点的长度。
  - float stopD：结束截取位置距离Path起始点的长度。
  - Path dst：截取的Path将会被**添加**到dst中。
  - boolean startWithMoveTo：起始点是否使用moveto。

  (2)注意：

  - 如果startD==stopD或者startD和stopD不在【0，getLength】范围中，函数返回false，不会改变dst的内容。
  - 要使用这个函数，必须关闭硬件加速，在自定义View的构造函数中调用setLayerType（LATER_TYPE_SOFTWARE，null）禁用硬件加速。

  (3)总结：

  - 路径截取是从路径左上角开始的。
  - 路径截取方向和路径生成方向相同。

- getPosTan()函数（用于获取路径上某一长度的位置以及该位置的正切值）：

  ```java
  public boolean getPosTan(float distance, float[] pos, float[] tan)
  ```

  - float distance：距离Path起始点的长度。
  - float[] pos：该点的坐标值，pos【0】表示x坐标，pos【1】表示y坐标。
  - float[] tan：该点的正切值，会返回半径为1的圆上对应角度的点的坐标，多利用Math.atan2(double y,double x)函数求出**弧度**。

- getMatrix()函数(用于获取路径上某一长度的位置以及该位置的正切值的矩阵)

  ```java
  public boolean getMatrix(float distance, Matrix matrix, int flags)
  ```

  - float distance：距离Path起始点的长度。
  - Matrix matrix：根据flags封住好的matrix会根据flags的设置而存入不同的内容。
  - int flags：用于指定哪些内容会存入matrix中。flag的值有两个：PathMeasure.POSITION_MATRIX_FLAG表示获取位置信息；PathMeasure.TANGENT_MATRIX_FLAG表示获取切边信息，使得图片可以按Path旋转。可以只指定一个，也可以用“|”同时指定。

- 例子：

  [加载条](https://github.com/xuesui/View/blob/master/app/src/main/java/com/example/view/myview/pathanim/PathAnim.java)

  [支付成功动画](https://github.com/xuesui/View/blob/master/app/src/main/java/com/example/view/myview/pathanim/PayAnim.java)
