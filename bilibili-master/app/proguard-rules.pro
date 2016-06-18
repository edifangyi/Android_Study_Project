# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/malin/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript inter
# class:
#-keepclassmembers class fqcn.of.javascript.inter.for.webview {
#   public *;
#}

#tencent.bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#butterknife
#https://github.com/JakeWharton/butterknife/blob/master/butterknife/proguard-rules.txt
# Retain generated class which implement ViewBinder.
-keep public class * implements butterknife.internal.ViewBinder { public <init>(); }
# Prevent obfuscation of types which use ButterKnife annotations since the simple name
# is used to reflectively look up the generated ViewBinder.
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }


#RxJava-----------------------------------------------------------
#http://www.on1024.com/2016/01/13/%E5%8D%87%E7%BA%A7%E5%88%B0%20RxAndroid%201.x.x%20%E4%B9%8B%E5%90%8E%E6%B7%B7%E6%B7%86%E8%A6%81%E6%B3%A8%E6%84%8F%E7%9A%84/
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#RxJava------------------------------------------------------------