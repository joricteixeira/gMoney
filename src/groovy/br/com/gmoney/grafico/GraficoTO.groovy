package br.com.gmoney.grafico

/**
 * Created by T-850 on 23/11/2014.
 */
class GraficoTO {
    String nomeDiv
    String titulo
    List<DataTO> datas

    String toString(){
        StringBuilder script = new StringBuilder()

        script.append('<script type="text/javascript">')
        script.append(' window.onload = function () {')
        script.append('     var chart = new CanvasJS.Chart("'+ this.nomeDiv+ '",')
        script.append('     {')
        script.append('         title:{')
        script.append('             text: "'+ this.titulo+'"')
        script.append('         },')
        script.append('         legend:{')
        script.append('             verticalAlign: "center",')
        script.append('             horizontalAlign: "left",')
        script.append('             fontSize: 20,')
        script.append('             fontFamily: "Helvetica"')
        script.append('         },')
        script.append('         theme: "theme2",')
        script.append('         data: [')

        datas.each { it ->
            script.append('         {')
            script.append('             type: "'+it.type+'",')
            script.append('             indexLabelFontFamily: "Garamond",')
            script.append('             indexLabelFontSize: 20,')
            script.append('             indexLabel: "{label} R$ {y}",')
            script.append('             startAngle:-20,')
            script.append('             showInLegend: false,')
            script.append('             toolTipContent:"R$ {y}",')
            script.append('             dataPoints: [')

            StringBuilder pontos = new StringBuilder()
            it.dataPoints.each { item ->
                pontos.append('             {  y: '+ item.y +', legendText:"'+ item.label+'", label: "'+ item.label +'" },')
            }
            script.append(pontos.toString().substring(0,pontos.toString().length()-1))

            script.append('             ]')
            script.append('         }')
        }

        script.append('         ]')
        script.append('     });')
        script.append(' chart.render();')
        script.append('}')
        script.append('</script>')

        return script.toString()
    }
}
