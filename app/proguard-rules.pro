# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-ignorewarnings

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
# ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#BaseRecyclerViewAdapterHelper
-keep class com.chad.library.** {*;}
-dontwarn com.chad.librar

#for okhttp
-dontwarn okhttp3.**
-keep class okhttp.** {*;}
-dontwarn okio.**
-keep class okio.** {*;}

#for glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.engine.EngineJob { *; }
-keep class com.bumptech.glide.load.engine.Engine { *; }

-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8

#for Retorfit
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**

#for gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.** {*;}
-dontwarn com.google.gson.**
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

-keep class com.zjf.finder.biz.home.model.** {*;}
-keep class com.zjf.finder.biz.home.service.** {*;}
-keep class com.zjf.finder.base.http.Result** {*;}
-keep interface com.zjf.finder.biz.home.contract.** {*;}
-keep interface com.zjf.finder.biz.home.interfaces.*{*;}