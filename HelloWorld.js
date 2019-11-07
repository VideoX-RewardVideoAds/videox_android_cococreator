cc.Class({
    extends: cc.Component,

    properties: {
        label: {
            default: null,
            type: cc.Label
        },
        loadButton: {
            default: null,
            type: cc.Button
        },
        isReadyButton: {
            default: null,
            type: cc.Button
        },
        showButton: {
            default: null,
            type: cc.Button
        },
        slotIdBox: {
            default: null,
            type: cc.EditBox
        },
       loadInButton: {
            default: null,
            type: cc.Button
        },
        isReadyInButton: {
            default: null,
            type: cc.Button
        },
        showInButton: {
            default: null,
            type: cc.Button
        },

        showBannerButton: {
            default: null,
            type: cc.Button
        },
        // defaults, set visually when attaching this script to the Canvas
        slotid: ''
    },

    // use this for initialization
    onLoad: function () {
        this.label.string = 'load test';
        this.loadButton.node.on('click', this.onBtnLoadClicked, this);
        this.isReadyButton.node.on('click', this.onBtnReadyClicked, this);
        this.showButton.node.on('click', this.onBtnShowClicked, this);

        this.loadInButton.node.on('click', this.onBtnInLoadClicked, this);
        this.isReadyInButton.node.on('click', this.onBtnInReadyClicked, this);
        this.showInButton.node.on('click', this.onBtnInShowClicked, this);

        this.showBannerButton.node.on('click', this.onBtnBannerShowClicked, this);
    },

    // called every frame
    update: function (dt) {
       this.text = this.slotIdBox.string;
       //this.label.string = this.text;
    },

    onBtnLoadClicked: function() {
        console.log('click to Load Reward ad');
        this.label.string = 'load button';
        loadRewardAd(this.text);
    },

    onBtnReadyClicked: function() {
        console.log('click to check reward ad is ready or not');
        this.label.string = 'ready button';
       
       var isReady =   isRewardAdReady(this.text);
    if (!isReady) {
      jsb.reflection.callStaticMethod("org/cocos2dx/javascript/SdkBridge", "ShowToast", "(Ljava/lang/String;)V", "ad is not ready");
        return;
    }else{
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/SdkBridge", "ShowToast", "(Ljava/lang/String;)V", "ad is ready");
    }
    },

    onBtnShowClicked: function() {
        console.log('click to show reward ad');
        this.label.string = 'show button';
        showRewardAd(this.text);
    },

    onBtnInLoadClicked: function() {
        console.log('click to Load Interstitial ad');
        this.label.string = 'load button';
        loadInterstitialAd(this.text);
    },

    onBtnInReadyClicked: function() {
        console.log('click to check Interstitial ad is ready or not');
        this.label.string = 'ready button';

       var isReady =  isInterstitialAdReady(this.text);
    if (!isReady) {
        jsb.reflection.callStaticMethod("org/cocos2dx/javascript/SdkBridge", "ShowToast", "(Ljava/lang/String;)V", "ad is not ready");
        return;
    }else{
       jsb.reflection.callStaticMethod("org/cocos2dx/javascript/SdkBridge", "ShowToast", "(Ljava/lang/String;)V", "ad is ready");
    }
    },

    onBtnInShowClicked: function() {
        console.log('click to show Interstitial ad');
        this.label.string = 'show button';
        showInterstitialAd(this.text);
    },

     onBtnBannerShowClicked: function() {
        console.log('click to load banner ad');
        this.label.string = 'show button';
        showBannerAd(this.text);
    },

});



