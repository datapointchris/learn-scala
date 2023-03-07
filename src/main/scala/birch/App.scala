package birch

import scala.collection.mutable

object App {
  
  def main(args : Array[String]) {

    val tsv = scala.io.Source.fromResource("amazon_reviews_us_Video_Games_v1_00.tsv")
    val lines = tsv.getLines().toList
//     val lines = tsv.getLines().take(10).toList

    val splitlines = lines.map(_.split("\t").map(_.trim))
    val headers = splitlines.head
    val values = splitlines.tail
//    values.foreach(println)

    val titleRatings = values.map(x => (x(5), x(7).toInt))
//    titleRatings.foreach(println)

    val fiveStarRatings = titleRatings.filter(_._2.equals(5))
//    fiveStarRatings.foreach(println)

    val fiveStarCount = fiveStarRatings.groupBy(_._1).mapValues(_.size)
//    fiveStarCount.foreach(println)

//    val sortedFiveStar = mutable.ListMap(fiveStarCount.toSeq.sortWith(_._2 > _._2):_*)
    val sortedFiveStar = fiveStarCount.toSeq.sortWith(_._2 > _._2)
//    sortedFiveStar.foreach(println)

    val topTenFiveStar = sortedFiveStar.take(10)
    topTenFiveStar.foreach(println)

  }

}
