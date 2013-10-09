package objsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TweetSetSuite extends FunSuite {
  trait TestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 50))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  test("filter: on empty set") {
    new TestSets {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 1)
    }
  }

  test("filter: 50 on set5") {
    new TestSets {
      val set6 = set5.incl(new Tweet("e", "e body", 50))
      assert(size(set6.filter(tw => tw.retweets == 50)) === 2)
    }
  }
  
  test("union: with empty set (1)") {
    new TestSets {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2)") {
    new TestSets {
      assert(size(set1.union(set5)) === 4)
    }
  }
  
  test("union: with empty set (3)") {
    new TestSets {
      assert(size(set2.union(set5).union(set2)) === 4)
    }
  }

  test("descending: set5") {
    new TestSets {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }
  
  test("descending: set6") {
    new TestSets {
      val set6 = set5.incl(new Tweet("e", "e body", 100))
      val trends = set6.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "e")
    }
  }
  
  test("mostRetweet") {
    new TestSets {
      assert(set5.mostRetweeted.retweets === 50)
    }
  }
  
  test("tweet data test1") {
    assert(GoogleVsApple.trending.head.retweets === 321)
    assert(GoogleVsApple.trending.tail.head.retweets === 290)
  }
}
