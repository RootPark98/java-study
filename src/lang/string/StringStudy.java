package lang.string;

import java.util.Arrays;

public class StringStudy{
public static void main(String[] args){
System.out.println("1. String Basic");
stringBasic();

System.out.println("\n2. String Compare");
stringCompare();

System.out.println("\n3. String immutable object");
stringImmutable();

System.out.println("\n4. String Method");
stringMethods();

System.out.println("\n5. String Builder");
stringBuilderExample();

System.out.println("\n6. String Optimization");
stringOptimization();

System.out.println("\n7. Method Chaining");
methodChaining();
}

static void stringBasic(){
String str1 = "hello";
String str2 = new String("hello");

System.out.println("str1 = " + str1);
System.out.println("str2 = " + str2);
System.out.println("string length = " + str1.length());
}

static void stringCompare(){
String str1 = "hello";
String str2 = "hello";
String str3 = new String("hello");

System.out.println("str1 == str2: " + (str1 == str2));
System.out.println("str1 == str3: " + (str1 == str3));
System.out.println("str1.equals(str3): " + str1.equals(str3));
System.out.println("\"HELLO\".equalsIgnoreCase(\"hello\"): " + "HELLO".equalsIgnoreCase("hello"));
System.out.println("\"apple\".compareTo(\"banana\"): " + "apple".compareTo("banana"));
}

static void stringImmutable(){
String original = "hello";

String upper = original.toUpperCase();
String replaced = original.replace("h", "j");

System.out.println("original = " + original);
System.out.println("upper = " + upper);
System.out.println("replaced = " + replaced);

System.out.println("original == upper: " + (original == upper));
}

static void stringMethods(){
String text = " Hello Java World ";

System.out.println("original = [" + text + "]");
System.out.println("length = " + text.length());
System.out.println("charAt(2) = " + text.charAt(2));
System.out.println("contains(Java) = " + text.contains("Java"));
System.out.println("indexOf(Java) = " + text.indexOf("Java"));
System.out.println("startsWith empty = " + text.startsWith(" "));
System.out.println("endsWith empty = " + text.endsWith(" "));

System.out.println("strip = [" + text.strip() + "]");
System.out.println("toUpperCase = " + text.toUpperCase());

String clean = text.strip();

System.out.println("substring(6, 10) = " + clean.substring(6, 10));
System.out.println("replace = " + clean.replace("Java", "Sprint"));

String languages = "Java, C, Python";

String[] result = languages.split(",");

System.out.println("split = " + Arrays.toString(result));
}

static void stringBuilderExample(){
StringBuilder builder = new StringBuilder();

builder.append("Hello");
builder.append(" ");
builder.append("Java");
builder.append("!");

System.out.println("builder = " + builder);

builder.insert(6, "Study ");
System.out.println("insert = " + builder);

builder.delete(6, 12);
System.out.println("delete = " + builder);

builder.reverse();
System.out.println("reverse = " + builder);

String result = builder.toString();

System.out.println("final String = " + result);
}

static void stringOptimization(){
String result1 = "Hello" + " " + "Java";

String result2 = "";

for(int i = 1; i <= 5; i++){
result2 = result2 + i;
}

StringBuilder builder = new StringBuilder();

for(int i = 1; i <= 5; i++){
builder.append(i);
}

String result3 = builder.toString();

System.out.println("normal rufgkq = " + result1);
System.out.println("again + rufgkq = " + result2);
System.out.println("StringBuilder = " + result3);
}

static void methodChaining(){
String result = " hello java "
.strip()
.toUpperCase()
.replace("JAVA", "SPRINT");

System.out.println("String chaining = " + result);

String builderResult = new StringBuilder()
.append("Hello")
.append(" ")
.append("Spring")
.append("!")
.reverse()
.toString();

System.out.println("StringBuilder Chaining = " + builderResult);
}
}





