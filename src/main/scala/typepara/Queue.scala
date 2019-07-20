package typepara

/**
  * 信息隐藏
  *
  * @author DGQ
  * @since 2018/12/19
  */
trait Queue[T] {
  def head: T

  def tail: Queue[T]

  def enqueue(x: T): Queue[T]
}

object Queue {
  def apply[T](xs: T*): Queue[T] = new QueueImpl[T](xs.toList, Nil)

  private class QueueImpl[T](private val leading: List[T],
                             private val trailing: List[T]) extends Queue[T]
  {
    def mirror = {
      if (leading.isEmpty) new QueueImpl(trailing.reverse, Nil)
      else this
    }

    override def head: T = mirror.leading.head

    override def tail: Queue[T] = {

      val q = mirror

      new QueueImpl(q.leading.tail, q.trailing)
    }


    override def enqueue(x: T): Queue[T] = new QueueImpl(leading, x :: trailing)
  }


}
