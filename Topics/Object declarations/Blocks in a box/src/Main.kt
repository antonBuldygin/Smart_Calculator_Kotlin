class Block(val color: String){
    object BlockProperties{
      var  length = 6
      var width =4

      fun blocksInBox(boxLength: Int, boxWidth: Int): Int{

                return (boxLength/ length)*(boxWidth/ width)}

    }

}