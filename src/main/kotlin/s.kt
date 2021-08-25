package algo;
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class s {


}
fun main(args:Array<String>)
{
    val s =DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("UTC"))
        .format(Instant.now());
    val m = "2021-08-25T01:12:13.46702Z";
    DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("UTC"))
        .parse(m);
    println(s)
    println(m)
}
