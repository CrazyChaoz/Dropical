package at.dropical.server.logging

import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Formatter
import java.util.logging.Handler
import java.util.logging.Level
import java.util.logging.LogRecord

class HtmlLogFormatter : Formatter() {
    // this method is called for every log records
    override fun format(rec: LogRecord): String {
        val buf = StringBuffer(1000)
        buf.append("<tr>\n")

        // colorize any levels >= WARNING in red

        when(rec.level.intValue()){
            Level.SEVERE.intValue()->buf.append("\t<td style=\"color:red\">")
            Level.WARNING.intValue()->buf.append("\t<td style=\"color:orange\">")
            Level.INFO.intValue()->buf.append("\t<td style=\"color:green\">")
            Level.FINE.intValue()->buf.append("\t<td style=\"color:lightgreen\">")
        }


        buf.append("<b>")
        buf.append(rec.level)
        buf.append("</b>")


        buf.append("</td>\n")
        buf.append("\t<td>")
        buf.append(calcDate(rec.millis))
        buf.append("</td>\n")
        buf.append("\t<td>")
        buf.append(formatMessage(rec))
        buf.append("</td>\n")
        buf.append("</tr>\n")

        return buf.toString()
    }

    private fun calcDate(millisecs: Long): String {
        val date_format = SimpleDateFormat("MMM dd,yyyy HH:mm")
        val resultdate = Date(millisecs)
        return date_format.format(resultdate)
    }

    // this method is called just after the handler using this
    // formatter is created
    override fun getHead(h: Handler?): String {
        return ("<!DOCTYPE html>\n<head>\n<style>\n"
                + "table { width: 100% }\n"
                + "th { font:bold 10pt Tahoma; }\n"
                + "td { font:normal 10pt Tahoma; }\n"
                + "h1 {font:normal 11pt Tahoma;}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>" + Date() + "</h1>\n"
                + "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n"
                + "<tr align=\"left\">\n"
                + "\t<th style=\"width:10%\">Loglevel</th>\n"
                + "\t<th style=\"width:15%\">Time</th>\n"
                + "\t<th style=\"width:75%\">Log Message</th>\n"
                + "</tr>\n")
    }

    // this method is called just after the handler using this
    // formatter is closed
    override fun getTail(h: Handler?): String {
        return "</table>\n</body>\n</html>"
    }

}
