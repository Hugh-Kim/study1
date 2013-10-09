package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }
  
  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
  test("intersect contains element both sets") {
    new TestSets {
      val unionSet1 = union(s1, s2)
      val unionSet2 = union(s2, s3)
      val s = intersect(unionSet1, unionSet2)
      assert(!contains(s, 1), "Intersect 1")
      assert(contains(s, 2), "Intersect 2")
      assert(!contains(s, 3), "Intersect 3")
    }
  }
  
  test("diff contains all elements in one set that not in other set") {
    new TestSets {
      val unionSet1 = union(s1, s2)
      val unionSet2 = union(s2, s3)
      val s = diff(unionSet1, unionSet2)
      assert(contains(s, 1), "diff 1")
      assert(!contains(s, 2), "diff 2")
      assert(!contains(s, 3), "diff 3")
    }
  }
  
  test("filter") {
    new TestSets {
      val unionSet = union(s1, union(s2, s3))
      val filtered = filter(unionSet, input => input < 3)
      assert(contains(filtered, 1), "filter 1")
      assert(contains(filtered, 2), "filter 2")
      assert(!contains(filtered, 3), "filter 3")
    }
  }
  
  test("for all") {
    new TestSets {
      val unionSet0 = union(s2, s3)
      val unionSet1 = union(singletonSet(-900), unionSet0)
      val unionSet2 = union(unionSet1, union(singletonSet(4), singletonSet(5)))
      assert(forall(unionSet0, x => 1 < x && x < 4), "forall 1");
      assert(forall(unionSet1, x => -901 < x && x < 4), "forall 2");
      assert(!forall(unionSet2, x => x < 5), "forall 3");
    }
  }
  
  test("exits") {
    new TestSets {
      val unionSet0 = union(s2, s3)
      val unionSet1 = union(singletonSet(-900), unionSet0)
      val unionSet2 = union(unionSet1, union(singletonSet(4), singletonSet(5)))
      assert(exists(unionSet2, x => -901 < x && x < 6), "exits 1")
      assert(exists(unionSet2, x => 2 < x && x < 4), "exits 2")
      assert(exists(unionSet2, x => 3 < x && x < 5), "exits 3")
      assert(exists(unionSet2, x => 4 < x && x < 6), "exits 4")
      assert(!exists(unionSet2, x => -901 < x && x < -900), "exits 5")
      
    }
  }
  
  test("map") {
    new TestSets {
      val result = map(s1, input => input * 10)
      assert(!contains(result, 1), "map 0")
      assert(contains(result, 10), "map 1")
      val result2 = map(union(s1, s2), input => input * 10)
      assert(contains(result2, 10), "map 2")
      assert(contains(result2, 20), "map 3")
      
      val unionSet0 = union(s2, s3)
      val unionSet1 = union(singletonSet(-90), unionSet0)
      val unionSet2 = union(unionSet1, union(singletonSet(4), singletonSet(5)))
      val result3 = map(unionSet2, input => input * 10)
      assert(contains(result3, -900), "map 4")
      assert(contains(result3, 20), "map 5")
      assert(contains(result3, 30), "map 6")
      assert(contains(result3, 40), "map 7")
      assert(contains(result3, 50), "map 8")
      assert(!contains(result3, 60), "map 8")
    }
  }
}
