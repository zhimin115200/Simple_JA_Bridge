# Simple_JA_Bridge
使用：在assets目录下添加my.html和my.js  
##原理：js调用prompt方法时，在android端的webChromeClient的onJsPrompt方法会被调用。因此，将prompt作为二者通讯的桥梁。  
构造数据：prototype://methodName：callbackId？params，  
1.在android中解析这个uri，  
2.反射调用methodName的method，  
3.callbackId作为回调js方法的索引，js根据此id来调用js中的回调函数，以实现异步调用。  
4.params是js传递给android的数据  
5.prototype是二者约定的协议  

##android调用js的原理：webView.loadUrl("jscode");
