package com.kid.playground.activity

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import androidx.core.view.isVisible
import com.kid.playground.R


class MainActivity : Activity() {

    var htmlText = """
        <p>Der realistisch malende Künstler <b>Valentin Reimann</b>, interpretiert und malt das, was er jetzt und heute sieht. Selektion im Sinne seiner Kunstauffassung und ästhetischem Empfinden.<br>Reimanns künstlerische Ausführungen sind jedoch modern und zeitgemäß, im 20. bzw. 21. Jahrhundert angekommen.<br>Sie malt er so perfekt, dass er darin restlos überzeugt. Und wer kann schon eine banale Mandarine so malen, eine einzelne Frucht, dass sie eine ganze Geschichte zu erzählen vermag? .... er malt diese Dinge nicht nur, er portraitiert sie wie Persönlichkeiten, die etwas zu sagen haben...Von solchen Künstlern gibt es nicht viele. Valentin Reimann ist einer von Ihnen. (<b>Bettina Kneller, Main Echo, 2016) </b><br><br><b>Peter Schäfer-Oswald</b> hat in Mainz Kunsterziehung und Freie Bildende Kunst studiert und als Kunstpädagoge und Kunsttherapeut in der Jugendhilfe gearbeitet.<br>Vielfalt ist sein Markenzeichen - nicht eine Handschrift - viele Handschriften." Mein Gemischtwarenladen", sagt er selbst manchmal dazu.<br><br>Ein thematischer Schwerpunkt von Peter Schäfer-Oswald ist die künstlerische Auseinandersetzung mit dem Spannungsverhältnis zwischen Natur und Technik/Zivilisation, zwischen Mensch und Tier. <br>Es entstehen Wunschbilder, ästhetische Einheiten die den Wunsch des Künstlers nach Harmonie und Aussöhnung versinnbildlichen.<br><br>Sein zweiter künstlerischer Schwerpunkt ist es die Schönheit des Unbeachtetem - die Schönheit im Abstoßendem, im Verborgenem sichtbar zu machen. Er ist fasziniert von der Ästhetik seiner Wildtierknochenfunde, der Formenvielfalt der Holzpilze an den Pappeln im Selztal, fasziniert von rostigen Schrauben, alten Brettern, altem Bodenbelag.<br>Durch Isolierung, serielle Präsentation, Collagierung oder Inszenierung erfahren diese Fundstücke in seinen Arbeiten einen Bedeutungswandel und werden zum Objekt, zum Bild, zur Collage.<br><br><br><br></p>
    """.trimIndent()

    var isDescExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }
}