package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if(c == 0 || c == r) 1
    else if(c > r) 0
    else pascal((c-1),(r-1)) + pascal(c,(r-1)) 
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def checkParentheses(chars: List[Char], value: Int): Int = {
       val a = value
      if(!chars.isEmpty){
        if(chars.head == '(') if(1 + a > 0) 1 + checkParentheses(chars.tail, 1 + a) else 1 + a
        else if(chars.head == ')') if(-1 + a >= 0) -1 + checkParentheses(chars.tail, -1 + a) else -1 + a
        else checkParentheses(chars.tail, a)
      }else a
    }
    if(checkParentheses(chars, 0) == 0) true else false
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def countWays(money: Int, coins: List[Int]): Int={
     if(money == 0) 1
     else if(money < 0) 0
     else if(money > 0 && coins.isEmpty) 0
     else countWays(money, coins.tail) + countWays(money-coins.head, coins)
    }
    countWays(money,coins)
  }
}
