package publicTrait

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/11/19
  */
trait MessageDispatcher[-T] {
  def sendMessage(msg: T): Unit
}
