# androidDevCourse

This repository consists of all the mini Android apps I've made during my Android Development studies. The course I am using is from Udemy - https://www.udemy.com/the-complete-android-developer-course

##CheatSheet
A quick reference to basic Android features I've learned through this course.

####Toast
```java
Toast.makeText(getApplicationContext(), "Hello world!", Toast.LENGTH_LONG).show();
```

####Validate number in EditText
```java
EditText myText = (EditText)findViewById(R.id.editTextID);
    String str = myText.getText().toString();
    try {
        double usd = (Double.parseDouble(str));
    } catch(NumberFormatException e) {
        str = "Enter a valid number";
    }
```

####Random Number
```java
Random randGen = new Random();
rand = randGen.nextInt(100) + 1;  // Generate number between  1 - 100
rand = randGen.nextInt(100) + 50; // Generate number between 50 - 100
rand = randGen.nextInt(50);       // Generate number between  0 - 50
```

####Set ImageView Resource
```java
ImageView myImage = (ImageView)findViewById(R.id.myImageID);
myImage.setImageResource(R.drawable.myPicture); // Sets `myPicture` picture to myImage var
myImage.setImageRecource(0); // Sets myImage var to no picture 
```

####Animate ImageView
```java
ImageView myImage = (ImageView)findViewById(R.id.myImageID);
myImage.setTranslationY(-1000f);  // Move image -1000 pixels on Y axis (off screen)
myImage.animate()
    .translationYBy(1000)         // Move image 1000 pixels on Y axis
    .rotation(360)                // Rotate 360 degrees
    .setDuration(500);            // Animate for 500 miliseconds
```

####Have Layout Slide Up / Slide Down
>#####add `slide_up.xml` to `res/anim`
>```java
><?xml version="1.0" encoding="utf-8"?>
><set xmlns:android="http://schemas.android.com/apk/res/android" >
>    <translate
>        android:duration="250"
>        android:fromYDelta="100%"
>        android:toYDelta="0" />
></set>
>```
>
>#####add `slide_down.xml` to `res/anim`
>```java
><?xml version="1.0" encoding="utf-8"?>
><set xmlns:android="http://schemas.android.com/apk/res/android" >
>    <translate
>        android:duration="250"
>        android:fromYDelta="0"
>        android:toYDelta="100%" />
></set>
>```
>
>#####Implementation
> ```java
>// Slide up
>LinearLayout gameEnd = (LinearLayout)findViewById(R.id.endGameView);
> gameEnd.setVisibility(View.VISIBLE);
>
>Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
>gameEnd.startAnimation(slide_up);
>
>// Slide Down
>LinearLayout gameEndView = (LinearLayout)findViewById(R.id.endGameView);
>Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
>
>gameEndView.startAnimation(slide_down);
>gameEndView.setVisibility(View.INVISIBLE);
>```
