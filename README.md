# number-formatter

[![](https://jitpack.io/v/draganstojanov/number-formatter-ktx.svg)](https://jitpack.io/#draganstojanov/number-formatter-ktx)


## README is under construction

  


A simple Android Kotlin library to help you format (mostly decimal) numbers.


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
	     implementation 'com.github.draganstojanov:Number-Formatter:x.x.x'
	    ...
	}


# Usage

<br/><br/>

## Kotlin extensions


	NUMBER.addLeadingZeros(noOfDigits)
noOfDigits -> Length of returning string. If NUMBER's integer part string presentation length > noOfDigits, it returns NUMBER\
noOfDigits value must be >= 1 and  <=8
 	
| Number| noOfDigits |Result |
| -------------: | -------------: |-------------: |
| 1 | 2 |01|
| 2.3 | 4 |0002.3|
| 12345678 | 3 |12345678|

<br/><br/>

	NUMBER.decimalsDisplayMode(decimalsMode)
	
| Number| decimalsMode |Result |
| -------------: | -------------: |-------------: |
| 1 | DEFAULT |1|
| 1.0 | DEFAULT |1.0|
| 1.23 | DEFAULT |1.23|
| 1 | ALWAYS_INCLUDING_INTEGERS |1.0|
| 1.0 | ALWAYS_INCLUDING_INTEGERS |1.0|
| 1.23 | ALWAYS_INCLUDING_INTEGERS |1.23|
| 1 | IF_CONTAINS |1|
| 1.0 | IF_CONTAINS |1|
| 1.23 | IF_CONTAINS |1.23|

<br/><br/>

	NUMBER.showIntegerPartIfZero(showIntIfZero)

| Number| showIntIfZero |Result |
| -------------: | -------------: |-------------: |
| 0.123| true|0.123|
| 0.123| false |.123|
| .123| true|0.123|
| .123| false |.123|

<br/><br/>

	NUMBER.maxDecimals(maxDecimals, addZerosAtEnd)
maxDecimals -> Max no of decimalsring\
maxDecimals value must be >= 1 and  <=8\
addZerosAtEnd -> If NUMBER contains less decimals than maxDecimals, it fills missing desimal places with zeros 

| Number| maxDecimals |addZerosAtEnd |Result |
| -------------: | -------------: |-------------: |-------------: |
| 1.2345678|3|true|1.234|
| 1.2345678|3|false|1.234|
| 1.23|6|true|1.230000|
| 1.23 |6|false|1.23|

<br/><br/>

	INTEGER.addSingleLeadingZero()
 Suitable for displaying dates and times. Returns a value with zero as the first character if the value is 0 <= INTEGER <= 9.
 
 | Number |Result |
| -------------: | -------------: |
| 1 | 01 |
| 12 | 12 |

<br/><br/>


## Batch formatting using NumberFormatter class instance

	fun formatNumber(number:Float):String{
        
      	val numberFormatter:NumberFormatter = NumberFormatter()

        	numberFormatter.apply {
            		digits = 4
            		decimalsMode = DecimalsMode.IF_CONTAINS
            		showIntIfZero = true
            		maxDecimals = 4
            		addZerosAtEnd = true }

        	return numberFormatter.getFormatted(number)
    }
    
<br/><br/>
<br/><br/>
	
## License

```
Copyright 2022 Dragan Stojanov

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


## README is under construction

	
