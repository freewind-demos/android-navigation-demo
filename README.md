# android-navigation-demo

## 简介

本 demo 展示 Android Navigation Component 的基本用法，演示如何在 Fragment 之间进行导航。

## 基本原理

Navigation Component 是 Android Jetpack 的一部分，提供了一种声明式的方式来管理 Fragment 之间的导航。

主要组成部分：
- **NavHostFragment**：导航容器，负责显示目标 Fragment
- **NavController**：管理导航堆栈，控制 Fragment 切换
- **导航图 (Navigation Graph)**：定义导航关系和参数

## 启动和使用

### 环境要求
- Android Studio 3.0+
- JDK 1.8+
- Android SDK 28

### 安装和运行
1. 用 Android Studio 打开此项目
2. 连接 Android 设备或启动模拟器
3. 点击 Run 运行项目

## 教程

### 什么是 Navigation Component？

Navigation Component 是 Google 官方推出的导航框架，提供了一种简单、一致的方式在应用中进行导航。它使用导航图来定义应用中的所有导航路径。

### 添加依赖

```kotlin
implementation "android.arch.navigation:navigation-fragment-ktx:$navigation_version"
implementation "android.arch.navigation:navigation-ui-ktx:$navigation_version"
```

### 创建导航图

在 res/navigation 目录下创建 nav_graph.xml：

```xml
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/homeFragment"
        android:name="com.example.HomeFragment"
        android:label="首页">

        <action
            android:id="@+id/action_home_to_detail"
            app:destination="@id/detailFragment"/>
    </fragment>

    <fragment
        android:id="@+id/detailFragment"
        android:name="com.example.DetailFragment"
        android:label="详情页">
        <argument
            android:name="message"
            app:argType="string"/>
    </fragment>
</navigation>
```

### 在布局中使用 NavHostFragment

```xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    app:defaultNavHost="true"
    app:navGraph="@navigation/nav_graph"/>
```

### 获取 NavController

```kotlin
val navHostFragment = supportFragmentManager
    .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
val navController = navHostFragment.navController
```

### 导航到其他 Fragment

```kotlin
// 简单导航
findNavController().navigate(R.id.action_home_to_detail)

// 带参数导航
val bundle = Bundle().apply {
    putString("message", "Hello from Home")
}
findNavController().navigate(R.id.detailFragment, bundle)
```

### 返回导航

```kotlin
findNavController().navigateUp()
```

### 动画效果

在 action 中定义动画：

```xml
<action
    android:id="@+id/action_home_to_detail"
    app:destination="@id/detailFragment"
    app:enterAnim="@android:anim/fade_in"
    app:exitAnim="@android:anim/fade_out"
    app:popEnterAnim="@android:anim/fade_in"
    app:popExitAnim="@android:anim/fade_out"/>
```

### 注意事项

1. **defaultNavHost**：设置为 true 可以拦截系统返回按钮
2. **Navigation Safe Args**：可以使用 Safe Args 插件生成类型安全的参数类
3. **deep link**：Navigation Component 支持 deep link，可以处理外部链接
4. **ViewModel 共享**：可以使用导航图的 ViewModelStoreOwner 在 Fragment 之间共享数据
