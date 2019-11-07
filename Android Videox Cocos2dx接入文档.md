# CocosCreator Videox SDK接入文档

## 1.在CocosCteator中正确配置Android平台的环境，以及SDK和NDK的路径。

## 2.添加JS插件
解压压缩文件，将包内VideoxSdk.js文件放入CocosCreator工程文件Script目录下，如下图在属性检查器中设置为插件:

![](https://i.imgur.com/5Ufink0.png)

## 3.构建发布CocosCreator项目
构建发布设置建议Target API level选择 android-28 ，并将APP ABI的情况都勾选（cpu32位和64位都需要支持）。
![](https://i.imgur.com/FCPY3J5.png)

## 4.生成Android studio 项目
点击构建会自动生成android studio 项目，点击构建发布上的发布路径后边的打开，在build文件中找到对应的工程，用Android studio打开。
<br/>详细路径:**build\jsb-link\frameworks\runtime-src\**.android-studio**
![](https://i.imgur.com/8aQv5DY.png)

## 5.在Android studio项目中引入第三方仓库地址
为了可以引入广告平台的架包在根项目目录下的build.gradle文件的 allprojects属性中添加以下代码。，
```
	repositories {
    	google()
    	jcenter()
    	maven {
        	url "https://jitpack.io"
    	}
    	maven {
        	url "https://dl.bintray.com/ironsource-mobile/android-sdk"
    	}
    	maven {
        	url  "https://adcolony.bintray.com/AdColony"
    	}
    	maven {
        	url 'http://maven.getvideox.cn/repository/videox/'
    	}
	}
```
## 6.根据自己所需要的版本，引入Videox架包和广告平台架包（国内版本）,在gradle文件中添加依赖文件
```
	implementation 'com.google.android.gms:play-services-basement:15.0.1'
    implementation 'com.google.android.gms:play-services-basement:15.0.1'
    implementation 'com.google.code.gson:gson:2.8.2'
	//Videox	
    implementation 'com.videox.sdk:release_vc2.0.4.08:1111111'
```

## 7.根据自己所需要的版本，引入Videox架包和广告平台架包（国际版本）,在gradle文件中添加依赖文件
```
	//baselib
	implementation 'com.google.android.gms:play-services-basement:15.0.1'
	implementation 'com.google.android.gms:play-services-ads-identifier:15.0.1'
	implementation 'com.google.code.gson:gson:2.8.2'
	//Videox
	implementation 'com.videox.sdk:release_va2.0.1:11111111'
```
## 8.打开 /app目录下的build.gradle 需要注意以下设置
![](https://i.imgur.com/rGsUo5w.png)
 <br/>如上图所示请加入 multiDexEnabled true 这行代码,对多个dex文件的支持。

## 9.在Manifest.xml文件修改对应的主题，以及添加相应的权限。
![](https://i.imgur.com/b8uHTxy.png)

- （1）	修改Application的theme
```
android:theme="@android:style/Theme.Holo.NoActionBar"
```
- （2）	添加权限
```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
    tools:remove="maxSdkVersion" />
```

## 10.将包内 SdkBridge.java桥文件复制到指定目录
如下图所示:<br/>
![](https://i.imgur.com/P154m4J.png)

## 11.在应用的主界面上初始化广告的SDK
请打开AppActivity.java文件 并按图添加以下代码做广告SDK的初始化
```
SdkBridge.initAdSdk(this);
```![](https://i.imgur.com/hiA4DI2.png)


## 12.广告的加载请求直接请求VideoxSDK.js插件中的方法。
具体可以参照压缩文件内的HelloWorld.js中代码。广告请求事件的回调在SDKBridge.java文件的监听接口中。
![](https://i.imgur.com/kLn2OEe.png)

## 13.激励视频广告
HelloWrold.js中的示例代码：

![](https://i.imgur.com/wqMIp1a.png)

如上图所示直接调用VideoxSDK.js插件中的请求方法即可。<br/>
**注意事项：**

- （1）	为了更好的展示广告，需要在展示的场景之前加载激励视频广告，在使用的时候直接判断是否ready好，如果ready好，可直接展示。
- （2）	每次展示之前最好做一次isRewardAdReady（”slotid”）的逻辑判断,只有ready好的广告才可以正常展示。

## 14.插屏广告
HelloWrold.js中的示例代码：

![](https://i.imgur.com/T45O3dK.png)
使用方式同激励视频，但是不需要提前场景加载，实时加载即可。

## 15.横幅广告
横幅广告需要将返回类型为banner的View添加到界面中去，现在支持加载到UI界面的底部。
（1）参照AppActivity.java文件中的方式AppActivity implements SdkBridge.AddBannerInterface主要用来监听banner广告是否加载成功。
（2）将当前的AddBannerInterface传递到VideoxSDK.java中，用来回调。
![](https://i.imgur.com/UWNjva2.png)
（3）实现接口SdkBridge.AddBannerInterface中的addBannerView（bannerView）的回调方法；参照下图实现将Banner广告置于屏幕底部
![](https://i.imgur.com/rKAna3b.png)

具体代码见AppActivity.java文件

## 16.注意事项
（1）激励视频调用的时机：

- 不建议在 AdLoadListener.onError 或 AdShowListener.onFinish 回调中加载广告
- 不建议在 isReady 方法返回值为 false 的时候加载广告，
- onFinish 回调参数 isReward ，true 表示有奖励(用户完整观看了视频)，false 表示没有奖励(用户没有完整观看视频)。

（2）如果是国内的版本出现广告无法填充，需要进行权限申请，可以参照AppActivity.java文件中的checkAndRequestPermission方法。（国际版忽略）
 ![](https://i.imgur.com/rBjukvs.png)
## 17.添加混淆文件。
如果要开启混淆，需将文件夹中的proguard.txt文件下面的混淆语句，添加到app工程的Prguardproguard-rules.pro文件中。

## 18.如有出现任何问题可以直接联系我们进行及时沟通。
