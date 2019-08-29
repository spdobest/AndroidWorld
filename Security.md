# ANDROID SECURITY  
https://proandroiddev.com/secure-data-in-android-encryption-in-android-part-1-e5fd150e316f  
  
For Secure application's, Its very important to save the sensitive data with high security so that the middle man cant be hack or leak your sensitive data which leads to huge loss.  
  
TO overcome this isues, we need to follow the following steps   
  
1.Never publish your application without applying proguard, Otherwise anybody can get your code by reverse engineering.  
      ```  
      buildTypes {
      release {
          minifyEnabled true
          shrinkResources true
          proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
      }
  }```  

2. Each API have some keys or token or sessionId etc, which you need to store safely with encryption.  
3. If you are integrating firebase API, you need to follow the firebase guideline rules  
4. Restrict the google API, if its public any hacker can use the public key and make lots of request to the API  
5. HTTPS for all the apis. USE ***SSL PINNING*** for your network call.  
6. Never store private key and password in shared preference. U can use EncryptedShared preference which is released under jet pack.  
7. Remove all the log's you printing in console. ENable only for DEBUG mode.You can use 3rd party library like Timber  
        ```  
        class App :Application(){
      override fun onCreate() {
          super.onCreate()
          if(BuildConfig.DEBUG){
              Timber.plant(Timber.DebugTree())
          }
          //Reset of your code
        }
    }```  
    
8. You can use internal storage to store sensitive files/data.  
9. Use Sqlcipher instead of normal sqlite database  
10. Do not send any sensitive information through broadcast reciever.  
11. Keep your dependencies independent.  
12. Protect your service and Content provider from other application by using custom permission.  
      ```
      <service
    android:name=".MyAwesomeService"
    android:exported="false" />```  
      
13. Encrypt data on External Storage and Check the validity of that data  
14. Avoid Asking for Personal Data  
15. Don’t process any payments on a rooted device.  
16. Use as little permissions as possible  
17. You can use biometric authentication for more security based applications.  
  
## SSL PINNING  
Important Links  
https://www.netguru.com/codestories/3-ways-how-to-implement-certificate-pinning-on-android  
https://infinum.co/the-capsized-eight/securing-mobile-banking-on-android-with-ssl-certificate-pinning https://www.nowsecure.com/blog/2017/06/15/certificate-pinning-for-android-and-ios-mobile-man-in-the-middle-attack-prevention/  

There are two major factors in an HTTPS connection, a ***valid certificate*** that server presents during handshaking, and a ***cipher*** suite to be used for data encryption during transmission. The certificate is the essential component and serves as a proof of identity of the server. The client will only trust the server if the server can provide a valid certificate that is signed by one of the trusted Certificate Authorities that come pre-installed in the client, otherwise, the connection will be aborted.  
  
An attacker can abuse this mechanism by either install a malicious root CA certificate to user devices so the client will trust all certificates that are signed by the attacker, or even worse, compromised a CA completely. Therefore relying on the certificates received from servers alone cannot guarantee the authenticity of the server, and it is vulnerable to a potential man-in-the-middle attack.  
  
By using mobile application, we use ssl to exchange data between server and client. But sometime we share some sensitive data over the API calls.   
  
In many cases, you'll have to send sensitive data between your application and server. Take mobile banking applications for example. The last thing you want is a malicious hacker to steal someone's bank account info – or worse, their money.  
  
Security is crucial for a mobile banking solution, so you'll be using SSL to keep that data safe and secret. But there's a catch.  
  
  
**Q. What is SSL pinning ?**  
The SSL pinning (or public key, or certificate pinning) is a technique mitigating Man-in-the-middle attacks against the secure HTTPS communication.  
  
Ans : By default, when making an SSL connection, the client checks that the server’s certificate:

has a verifiable chain of trust back to a trusted (root) certificate  
matches the requested hostname  
What it doesn't do is check if the certificate in question is a specific certificate, namely the one you know your server is using.
  
Relying on matching certificates between the device's trust store and the remote server opens up a security hole. The device’s trust store can easily be compromised - the user can install unsafe certificates, thus allowing potential man-in-the-middle attacks.  
  
Certificate pinning is the solution to this problem. It means hard-coding the certificate known to be used by the server in the mobile application. The app can then ignore the device’s trust store and rely on its own, and allow only SSL connections to hosts signed with certificates stored inside the application.

This also gives a possibility of trusting a host with a self-signed certificate without the need to install additional certificates on the device.

**Benefits**  
Increased security - with pinned SSL certificates, the app is independent of the device’s trust store. Compromising the hard coded trust store in the app is not so easy - the app would need to be decompiled, changed and then recompiled again - and it can’t be signed using the same Android keystore that the original developer of the app used.  
  
Reduced costs - SSL certificate pinning gives you the possibility to use a self-signed certificate that can be trusted. For example, you’re developing an app that uses your own API server. You can reduce the costs by using a self-signed certificate on your server (and pinning that certificate in your app) instead of paying for a certificate. Although a bit convoluted, this way, you've actually improved security and saved yourself some money.  
  
***drawbacks***  
Less flexibility - when you do SSL certificate pinning, changing the SSL certificate is not that easy. For every SSL certificate change, you have to make an update to the app, push it to Google Play and hope the users will install it.  
  
**Q. What will happen if the certificate expires ?**  
https://github.com/wultra/ssl-pinning-android  
  
**Why do we need to implement SSL Pinning ?**  
  
SSL Pinning is an additional security layer to prevent MITM attack( Man in the Middle Attack) or sniffing data. To intercept the request, we mostly use a proxy tool. The proxy tool installs its own certificate on the device and application trust that certificate as a valid certificate and allow proxy tool to intercept application traffic.  
  
**Ways to Implement SSL Pinning :-**  
  
**1.Certificate Pinning**   
**2.Public Key Pinning**  
  
**Certificate Pinning :-** In certificate pinning , the developer hardcodes some bytecode of SSL certificate into application code. When the application communicates with the server, it checks whether the same bytecode is present in a certificate or not. If it is present, the application sends a request to the server. If the bytecode does not match it will throw an SSL certificate error. This technique prevents an attacker to use his/her own self-signed certificate.    
  
**Public Key Pinning :-** In public key pinning when a customer visits a website, the server pins (by way of injecting it) its public key in client (customer’s) browser. When the client revisits the same website, the server identifies its public key to check the integrity of the connection. This technique also prevents an attacker from using his/her self-signed certificate.  
  
