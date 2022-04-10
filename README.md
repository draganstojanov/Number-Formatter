# number-formatter-ktx

[![](https://jitpack.io/v/draganstojanov/number-formatter-ktx.svg)](https://jitpack.io/#draganstojanov/number-formatter-ktx)


## README is under construction

  


A simple Android library to help you format (mostly decimal) numbers.


# Adding dependencies
Add this to your build.gradle:

    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
      }


In your app/build.gradle:

	dependencies {
	    ...
	     implementation 'com.github.draganstojanov:number-formatter-ktx:0.1.0'
	    ...
	}


# Usage

There are two ways:

## Kotlin extensions

	NUMBER.addLeadingZeros(noOfFigures)
noOfFigures -> Length of returning string. If NUMBER's string presentation length > noOfFigures, it returns NUMBER

Examples:	
| Number| noOfFigures |Result |
| -------------: | -------------: |-------------: |
| 1 | 2 |01|
| 2.3 | 4 |02.3|
| 12345678 | 3 |12345678|


## README is under construction

	
