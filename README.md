# Android Background Services 
- Binded Service
- Unbinded Service
- Intent Service
- Job Service
- Firebase Job Service
- Handle Background service In Android O and above devices



## Broadcast Reciever
- ### Local BroadcastManager
    - this is valid only for broadcasts within your application
    - ```
      public class LocalBroadcastReceiver extends BroadcastReceiver {
          private final String TAG = BroadcastReceiver.class.getSimpleName();
          @Override
          public void onReceive(Context context, Intent intent) {
              String data = intent.getExtras().getString("data");
              Toast.makeText(context, "Broadcast Received with data " + data, Toast.LENGTH_LONG).show();
          }
      }
      ```
    - In Activity or Fragment
        - ```
          LocalBroadcastManager.getInstance(this).registerReceiver(myBroadcastReceiver,  new IntentFilter("MY_ACTION"));
          
          // to send the broadcast
          Intent intent = new Intent("MY_ACTION");
          intent.putExtra("data", "Hello World!");
          LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
          // Unregister the broadcast in onStop
          
            protected void onStop() {
                  super.onStop();
                  LocalBroadcastManager.getInstance(this).unregisterReceiver(myBroadcastReceiver);
              }
          ```  
- ### Ordered BroadcastReceiver
    - Ordered broadcasts are like regular Intent broadcasts, except that they are ordered.
    - Broadcast the given intent to all interested BroadcastReceivers, delivering them one at a time to allow more preferred receivers to consume the broadcast before it is delivered to less preferred receivers. 
    - This call is asynchronous; it returns immediately, and you will continue executing while the receivers are run.
    - https://medium.com/@shawn.thye/send-ordered-broadcast-with-result-receiver-eec91dcfa02
    - Look at the example in the orderedBroadcast package
- ##Normal BroadcastReceiver
- **Implicit broadcast**
    - is one that does not target your application specifically so it is not exclusive to your application. 
    - Usually this can be done y registering the Broadcast Receiver in android manifest file
    - ```
      <receiver android:name=".YourBrodcastReceiverClass"  android:exported="true">
          <intent-filter>
              <!-- The actions you wish to listen to, below is an example -->
              <action android:name="android.intent.action.BOOT_COMPLETED"/>
          </intent-filter>
      </receiver>
      ```
- **Explicit Broadcast**
    - is one that is targeted specifically for your application on a component that is known in advance
    - This can be done by registering Broadcast Receiver in activity or fragment
    - ```
      BroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
      
      IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
      filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
      this.registerReceiver(myBroadcastReceiver, filter);
      
      @Override
      protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
      }
      ```
- ### Sticky Broadcast
    -       
    