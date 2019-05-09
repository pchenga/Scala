package json4s

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.{read,write}

object Test extends App {
  //1 . Input 
  val inputStr = """{"foo":"bar"}"""
  val jvalue = parse(inputStr)
  // JValue
  val jobject = parse(inputStr).asInstanceOf[JObject]
  
  
  println(jvalue)
  println(jobject)
  
  //Extracting a field from a JObject
  val myObj = parse("""{"foo":"bar"}""").asInstanceOf[JObject]
  
  val mapValues = myObj.map(value => value)
  println(mapValues)
  
  println(myObj \ "foo1")
  
  println((myObj \ "foo1").toOption) //Some(JString()) or None
  println(myObj \ "baz")
  println((myObj \ "baz").toOption)
  
  
  //Build a JObject step by step or 2.  DSL 
  val jobj1=  JObject("id" -> JInt(100), "name" -> JString("Prakash"))
   // { "id": 100 }
  println(jobj1)
  println(jobj1 \ "id")
  println(jobj1 \ "name")
  
//Parse a json string into a case class
// json4s requires you to have this in scope to call extract
implicit val formats = DefaultFormats

case class Person(name:String, age: Int)

val jsValue = parse("""{"name":"john", "age": 28}""")

val p = jsValue.extract[Person]
// p is Person("john",28)

val maybeP = jsValue.extractOpt[Person]
// safer version, maybeP is of type Option[Person]
  
 println(p)
 println(maybeP)
 
 //Option attributes are also supported:
 
 // car may or may not have an owner
case class Car(model:String,year:Int,ownerName:Option[String])

val car1 = parse("""{"model":"c-class","year":2011}""").extract[Car]
// car1: Car = Car(c-class,2011,None)

val car2 = parse("""{ "model":"b-class","year":2013,"ownerName": "john doe"}""").extract[Car]
// car2: Car = Car(b-class,2013,Some(john doe))
  
println(car1)
println(car2)

//Creating and iterating over the elements of a JArray

val jarr2 = JArray(List(JString("foo"),JInt(42)))
// equivalent to ["foo",42]

val jsvalues = jarr2.arr
// it's a List[JsValue]

jarr2.arr.foreach{ jsval =>
    println(s"Hi i'm a ${jsval.getClass}, ${jsval}")
}
  
  
//Transform models to and from json strings using read and write
/*  
val john = Person("john",45)

println(write(john))

val maryAsString = """{"name":"mary", "age":89} """

println(read[Person](maryAsString))

val john1 = read[Person]("""{"name":"john"}""")

val mary1 = read[Person]("""{"name":"mary","age":20}""")

*/



//Json4s DSL
import org.json4s.JsonDSL._
import org.json4s._

// connect tuples with "~" to create a json object
val obj1: JObject = ("foo", "bar") ~ ("baz", "quux") ~ ("baz1", "quux1")
// {"foo":"bar","baz":"quux"}

// you can also use "->" notation to create tuples
val obj2: JObject = ("foo" -> "bar") ~ ("baz" -> "quux")
println(write(obj2))
// {"foo":"bar","baz":"quux"}

// use any sequence to make an array of json elements
val array1: JArray = Seq(obj1,obj2)
println(write(array1))
// [{"foo":"bar","baz":"quux"},{"foo":"bar","baz":"quux"}]

val array2: JArray = Seq("foo","bar")
println(write(array2))
// ["foo","bar"]

}