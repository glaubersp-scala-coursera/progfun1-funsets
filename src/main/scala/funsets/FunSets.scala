package funsets

/**
  * 2. Purely Functional Sets.
  */
trait FunSets extends FunSetsInterface {

  /**
    * We represent a set by its characteristic function, i.e.
    * its `contains` predicate.
    */
  override type FunSet = Int => Boolean
  val emptySet: FunSet = (y: Int) => false

  /**
    * Returns the intersection of the two given sets,
    * the set of all elements that are both in `s` and `t`.
    */
  def intersect(s: FunSet, t: FunSet): FunSet = (elem: Int) => s(elem) && t(elem)

  /**
    * Returns the difference of the two given sets,
    * the set of all elements of `s` that are not in `t`.
    */
  def diff(s: FunSet, t: FunSet): FunSet = (elem: Int) => s(elem) && !t(elem)

  /**
    * Returns the subset of `s` for which `p` holds.
    */
  def filter(s: FunSet, p: Int => Boolean): FunSet = (elem: Int) => p(elem) && s(elem)

  /**
    * Returns whether there exists a bounded integer within `s`
    * that satisfies `p`.
    */
  def exists(s: FunSet, p: Int => Boolean): Boolean = !forall(s, (elem) => !p(elem))

  /**
    * Returns whether all bounded integers within `s` satisfy `p`.
    */
  def forall(s: FunSet, p: Int => Boolean): Boolean = {
    @scala.annotation.tailrec
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (s(a) && !p(a)) false
      else iter(a + 1)
    }
    iter(-bound)
  }

  /**
    * The bounds for `forall` and `exists` are +/- 1000.
    */
  val bound = 1000

  /**
    * Returns a set transformed by applying `f` to each element of `s`.
    */
  def map(s: FunSet, f: Int => Int): FunSet = {
    def iterate(a: Int): FunSet = {
      if (a > bound) emptySet
      else if (s(a)) union(singletonSet(f(a)), iterate(a + 1))
      else iterate(a + 1)
    }
    iterate(-bound)
  }

  /**
    * Returns the set of the one given element.
    */
  def singletonSet(elem: Int): FunSet = (x: Int) => x == elem

  /**
    * Returns the union of the two given sets,
    * the sets of all elements that are in either `s` or `t`.
    */
  def union(s: FunSet, t: FunSet): FunSet = (elem: Int) => s(elem) || t(elem)

  /**
    * Prints the contents of a set on the console.
    */
  def printSet(s: FunSet): Unit = {
    println(toString(s))
  }

  /**
    * Displays the contents of a set
    */
  def toString(s: FunSet): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
    * Indicates whether a set contains a given element.
    */
  def contains(s: FunSet, elem: Int): Boolean = s(elem)
}

object FunSets extends FunSets
